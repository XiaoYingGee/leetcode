package com.xiaoyingge.algorithm.part01;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/add-two-numbers 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author xiaoyinggee Created on 2020-10-16
 */
public class Question0002 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int length1 = getLength(l1);
        int length2 = getLength(l2);

        ListNode longer = length1 >= length2 ? l1 : l2;
        ListNode smaller = length1 < length2 ? l1 : l2;
        ListNode head = longer;
        ListNode pre = longer;
        boolean tenFlag = false;
        while (smaller != null) {
            int result = longer.val + smaller.val + (tenFlag ? 1 : 0);
            tenFlag = result >= 10;
            longer.val = tenFlag ? result - 10 : result;
            smaller = smaller.next;
            pre = longer;
            longer = longer.next;
        }
        while (longer != null) {
            int result = longer.val + (tenFlag ? 1 : 0);
            tenFlag = result >= 10;
            longer.val = tenFlag ? result - 10 : result;
            pre = longer;
            longer = longer.next;
        }

        if (tenFlag) {
            pre.next = new ListNode(1);
        }

        return head;
    }

    private static int getLength(ListNode node) {
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(3, new ListNode(7));
        ListNode l2 = new ListNode(9, new ListNode(2));
        ListNode result = addTwoNumbers(l1, l2);
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
