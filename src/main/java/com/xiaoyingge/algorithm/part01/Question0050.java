package com.xiaoyingge.algorithm.part01;

import com.xiaoyingge.common.TreeNode;

/**
 * @author XiaoYingGee
 * @date 2022/4/27 23:07
 */
public class Question0050 {

    public boolean verifyPostorder(int[] postorder) {
        TreeNode node = buildTree(postorder, 0, postorder.length - 1, postorder.length - 1);
        return process(node).searchTree;
    }

    public Info process(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info left = process(node.left);
        Info right = process(node.right);

        int max = node.val;
        int min = node.val;
        boolean search = true;
        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
            if (left.max >= node.val) {
                search = false;
            }
            if (!left.searchTree) {
                search = false;
            }
        }
        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
            if (right.min <= node.val) {
                search = false;
            }
            if (!right.searchTree) {
                search = false;
            }
        }
        return new Info(max, min, search);
    }

    public class Info {

        int max;
        int min;
        boolean searchTree;

        Info(int max, int min, boolean searchTree) {
            this.max = max;
            this.min = min;
            this.searchTree = searchTree;
        }

    }

    public TreeNode buildTree(int[] nums, int left, int right, int index) {
        if (left > right) {
            return null;
        }
        TreeNode root = new TreeNode(nums[index]);
        int i = findIndex(nums, root.val, index);
        root.left = buildTree(nums, left, i, i);
        root.right = buildTree(nums, i + 1, index - 1, index - 1);
        return root;
    }

    public int findIndex(int[] nums, int target, int index) {
        for (int i = index; i >= 0; i--) {
            if (nums[i] < target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 6, 5};
        new Question0050().verifyPostorder(nums);
    }

}
