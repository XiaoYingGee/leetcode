package com.xiaoyingge.algorithm.part01;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * @author XiaoYingGee
 * @date 2022/3/12 19:17
 */
public class Question0023 {

    public static class ListNode {

        int val;
        ListNode next;

        public int getVal() {
            return val;
        }

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode[] nodes = new ListNode[]{
                null, null
        };
        new Question0023().mergeKLists(nodes);
    }

    /**
     * 左神小根堆
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null) {
                priorityQueue.add(node);
            }
        }
        if (priorityQueue.peek() == null) {
            return null;
        }
        ListNode head = priorityQueue.poll();

        ListNode pre = head;
        if (pre.next != null) {
            priorityQueue.add(pre.next);
        }
        while (!priorityQueue.isEmpty()) {
            ListNode current = priorityQueue.poll();
            if (current.next != null) {
                priorityQueue.add(current.next);
            }
            pre.next = current;
            pre = current;
        }
        return head;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        //将为空的节点处理掉
        ListNode[] newLists = removeNull(lists);
        if (newLists == null || newLists.length == 0) {
            return null;
        }
        if (newLists.length == 1) {
            return newLists[0];
        }
        //找出第一个最小的头节点作为返回节点
        int currentIndex = getMinIndex(newLists);
        ListNode head = newLists[currentIndex];
        //后移
        newLists[currentIndex] = newLists[currentIndex].next;

        ListNode pre = head;

        while (getMinIndex(newLists) >= 0) {
            //这个索引的值是仅次于currentIndex索引的值
            int nextIndex = getMinIndex(newLists);
            ListNode current = newLists[nextIndex];
            //如果最小值还是在这条链上，向后跳一位
            if (currentIndex == nextIndex) {
                //将对应的数组内容向后移动，并且记录当前的最小值
                newLists[currentIndex] = newLists[currentIndex].next;
                pre = current;
                continue;
            }
            newLists[nextIndex] = newLists[nextIndex].next;
            pre.next = current;
            pre = current;
            currentIndex = nextIndex;
        }

        return head;
    }

    private ListNode[] removeNull(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int length = 0;
        for (ListNode list : lists) {
            if (list != null) {
                length++;
            }
        }

        ListNode[] result = new ListNode[length];
        length = 0;
        for (ListNode list : lists) {
            if (list != null) {
                result[length++] = list;
            }

        }
        return result;
    }

    /**
     * line在lists区间内时，在对应行会检查它的后继节点
     *
     * @param lists
     * @return
     */
    private int getMinIndex(ListNode[] lists) {
        ListNode minNode = null;
        int index = -1;
        for (int i = 0; i < lists.length; i++) {
            ListNode node = lists[i];
            if (node == null) {
                continue;
            }
            if (minNode == null) {
                minNode = node;
                index = i;
            }
            if (node.val < minNode.val) {
                minNode = node;
                index = i;
            }
        }
        return index;
    }
}
