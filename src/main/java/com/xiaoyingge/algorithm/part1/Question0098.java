package com.xiaoyingge.algorithm.part1;

import com.xiaoyingge.common.TreeNode;

/**
 * @author XiaoYingGee
 * @date 2022/3/13 14:07
 */
public class Question0098 {

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBst;

    }

    private Info process(TreeNode root) {
        if (root == null) {
            return null;
        }

        Info left = process(root.left);
        Info right = process(root.right);

        int max = root.val;
        int min = root.val;
        boolean isBst = true;

        if (left != null) {
            max = Math.max(left.max, max);
            min = Math.min(left.min, min);
        }
        if (right != null) {
            max = Math.max(right.max, max);
            min = Math.min(right.min, min);
        }

        if (left != null && !left.isBst) {
            isBst = false;
        }
        if (right != null && !right.isBst) {
            isBst = false;
        }
        if (left != null && left.max >= root.val) {
            isBst = false;
        }
        if (right != null && right.min <= root.val) {
            isBst = false;
        }
       
        return new Info(isBst, max, min);
    }

    private class Info {

        boolean isBst;
        int max;
        int min;

        Info(boolean isBst, int max, int min) {
            this.isBst = isBst;
            this.max = max;
            this.min = min;
        }
    }
}
