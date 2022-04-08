package com.xiaoyingge.algorithm.part11;

import com.xiaoyingge.common.TreeNode;

/**
 * @author XiaoYingGee
 * @date 2022/3/18 23:07
 */
public class Question1008 {

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return process(preorder, 0, preorder.length - 1);
    }

    private TreeNode process(int[] preorder, int start, int end) {
        if (start == end) {
            return new TreeNode(preorder[start]);
        }
        //二分找索引
        TreeNode current = new TreeNode(preorder[start]);
        int index = findIndex(preorder, preorder[start], start + 1, end);

        //返回的值 要么是大于 它的 要么是小于它的
        if (preorder[start] > preorder[index]) {
            current.left = process(preorder, start + 1, index);
            current.right = index == end ? null : process(preorder, index + 1, end);
        } else {
            current.left = start == index - 1 ? null : process(preorder, start + 1, index - 1);
            current.right = process(preorder, index, end);
        }
        return current;
    }

    /**
     * 数字是不在数组里的，但是必会找到一个【a,b】 a小于它 b大于它，返回较小的区间
     *
     * @param preorder
     * @param findValue
     * @param start
     * @param end
     * @return
     */
    private int findIndex(int[] preorder, int findValue, int start, int end) {
        if (end == start) {
            return start;
        }
        int mid = start + ((end - start) >> 1);
        //大于说明找到右边去了
        if (preorder[mid] > findValue) {
            if (mid == start) {
                return start;
            }
            return findIndex(preorder, findValue, start, mid - 1);
        } else {
            if (mid == end) {
                return end;
            }
            return findIndex(preorder, findValue, mid + 1, end);
        }
    }

    public static void main(String[] args) {
        Question1008 question1008 = new Question1008();
        TreeNode node = question1008.bstFromPreorder(new int[]{});
        TreeNode node1 = question1008.bstFromPreorder(new int[]{1});
        TreeNode node2 = question1008.bstFromPreorder(new int[]{1, 2});
        TreeNode node3 = question1008.bstFromPreorder(new int[]{2, 1});
        TreeNode node4 = question1008.bstFromPreorder(new int[]{5, 4, 3, 2, 1});
        TreeNode node5 = question1008.bstFromPreorder(new int[]{5, 6, 7, 8, 9});
        TreeNode node6 = question1008.bstFromPreorder(new int[]{5, 2, 1, 3, 7, 6, 8, 9});
        TreeNode node7 = question1008.bstFromPreorder(new int[]{15, 13, 12, 18, 20});
        System.out.println("1");
    }
}
