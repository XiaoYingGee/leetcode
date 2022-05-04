package com.xiaoyingge.algorithm.part03;

import com.xiaoyingge.util.PrintUtil;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author XiaoYingGee
 * @date 2022/4/30 21:33
 */
public class Question0239 {

    public static void main(String[] args) {
        Question0239 question0239 = new Question0239();
        int[] ints = question0239.maxSlidingWindow2(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        PrintUtil.print(ints);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Node> linkedList = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            addToDeque(linkedList, i, nums[i]);
        }
        result[0] = linkedList.getFirst().val;
        for (int i = k; i < nums.length; i++) {
            addToDeque(linkedList, i, nums[i]);
            removeFromDeque(linkedList, i - k);
            result[i - k + 1] = linkedList.getFirst().val;
        }
        return result;
    }

    public void addToDeque(Deque<Node> deque, int index, int val) {
        while (!deque.isEmpty() && deque.getLast().val <= val) {
            deque.pollLast();
        }
        deque.addLast(new Node(index, val));
    }

    public void removeFromDeque(Deque<Node> deque, int index) {
        while (!deque.isEmpty() && deque.getFirst().index < index) {
            deque.removeFirst();
        }
    }

    public class Node {

        int index;
        int val;

        Node(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        LinkedList<Node> linkedList = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!linkedList.isEmpty() && linkedList.getLast().val <= nums[i]) {
                linkedList.removeLast();
            }
            linkedList.add(new Node(i, nums[i]));
        }
        int[] result = new int[nums.length - k + 1];
        result[0] = linkedList.getFirst().val;
        for (int i = k; i < nums.length; i++) {
            if (linkedList.getFirst().index <= i - k) {
                linkedList.removeFirst();
            }
            while (!linkedList.isEmpty() && linkedList.getLast().val <= nums[i]) {
                linkedList.removeLast();
            }
            linkedList.add(new Node(i, nums[i]));
            result[i - k + 1] = linkedList.getFirst().val;
        }
        return result;
    }

}
