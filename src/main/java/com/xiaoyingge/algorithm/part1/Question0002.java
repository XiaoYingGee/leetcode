package com.xiaoyingge.algorithm.part1;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author xiaoyinggee
 * Created on 2020-10-16
 */
public class Question0002 {

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1.next == null && l1.val == 0) {
            return l2;
        }
        if (l2.next == null && l1.val == 0) {
            return l1;
        }
        boolean hasTen = false;
        ListNode head = new ListNode();
        ListNode current1 = l1;
        ListNode current2 = l2;
        ListNode current = head;
        while (current1 != null || current2 != null || hasTen) {
            int number = 0;
            if (hasTen) {
                number = 1;
                hasTen = false;
            }
            if (current1 != null) {
                number += current1.val;
                current1 = current1.next;
            }
            if (current2 != null) {
                number += current2.val;
                current2 = current2.next;
            }
            current.val = number % 10;
            hasTen = number >= 10;
            if (current1 != null || current2 != null || hasTen) {
                current.next = new ListNode();
            }
            current = current.next;
        }
        return head;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1.next == null && l1.val == 0) {
            return l2;
        }
        if (l2.next == null && l1.val == 0) {
            return l1;
        }
        List<Integer> stack1 = new ArrayList<>();
        List<Integer> stack2 = new ArrayList<>();
        while (l1 != null) {
            stack1.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.add(l2.val);
            l2 = l2.next;
        }
        List<Integer> list = new ArrayList<>();
        boolean hasTen = false;
        int length = Math.max(stack1.size(), stack2.size());

        for (int index = 0; index < length || hasTen; index++) {
            if (index >= stack1.size() && index >= stack2.size() && hasTen) {
                list.add(1);
                hasTen = false;
                continue;
            }
            if (index >= stack1.size()) {
                int s2 = stack2.get(index);
                if (hasTen) {
                    s2 = s2 + 1;
                    if (s2 == 10) {
                        list.add(0);
                        hasTen = true;
                        continue;
                    } else {
                        list.add(s2);
                        hasTen = false;
                        continue;
                    }
                } else {
                    list.add(s2);
                    hasTen = false;
                    continue;
                }
            }
            if (index >= stack2.size()) {
                int s1 = stack1.get(index);
                if (hasTen) {
                    s1 = s1 + 1;
                    if (s1 == 10) {
                        list.add(0);
                        hasTen = true;
                        continue;
                    } else {
                        list.add(s1);
                        hasTen = false;
                        continue;
                    }
                } else {
                    list.add(s1);
                    hasTen = false;
                    continue;
                }
            }
            int n1 = stack1.get(index);
            int n2 = stack2.get(index);
            int n3 = n1 + n2;
            if (hasTen) {
                n3 = n3 + 1;
            }
            if (n3 >= 10) {
                hasTen = true;
                list.add(n3 % 10);
            } else {
                list.add(n3);
                hasTen = false;
            }
        }
        ListNode head = new ListNode();
        ListNode current = head;
        for (int i = 0; i < list.size(); i++) {
            current.val = list.get(i);
            if (i != list.size() - 1) {
                current.next = new ListNode();
                current = current.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(9, null))));
        ListNode l2 = new ListNode(2, new ListNode(4, new ListNode(9, null)));
        ListNode result = addTwoNumbers2(l1, l2);
        while (result != null) {
            System.out.println(result.val + " ");
            result = result.next;
        }

    }

    public static class ListNode {
        int val;
        ListNode next;

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
}
