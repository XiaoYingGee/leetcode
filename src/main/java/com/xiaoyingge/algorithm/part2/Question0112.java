package com.xiaoyingge.algorithm.part2;

import com.xiaoyingge.common.TreeNode;

/**
 * @author XiaoYingGee
 * @date 2022/3/13 14:52
 */
public class Question0112 {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return process(root, targetSum).find;
    }

    private Info process(TreeNode root, int targetSum) {
        if (root.left == null && root.right == null) {
            return new Info(targetSum == root.val);
        }
        Info left = null, right = null;
        if (root.left != null) {
            left = process(root.left, targetSum - root.val);
        }
        if (root.right != null) {
            right = process(root.right, targetSum - root.val);
        }
        boolean leftFind = left == null ? false : left.find;
        boolean rightFind = right == null ? false : right.find;

        return new Info(leftFind || rightFind);
    }

    private class Info {

        private boolean find;

        Info(boolean find) {
            this.find = find;
        }
    }
}
