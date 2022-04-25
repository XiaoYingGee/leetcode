package com.xiaoyingge.offer;

import com.xiaoyingge.common.TreeNode;

/**
 * @author XiaoYingGee
 * @date 2022/4/19 22:11
 */
public class Offer55 {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return process(root).isBalanced;
    }

    private Info process(TreeNode node) {
        if (node == null) {
            return new Info(0, true);
        }
        Info left = process(node.left);
        Info right = process(node.right);

        int height = Math.max(left.height, right.height) + 1;
        boolean isBalanced = left.isBalanced && right.isBalanced && Math.abs(left.height - right.height) <= 1;
        return new Info(height, isBalanced);

    }

    public class Info {

        int height;
        boolean isBalanced;

        Info(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }
}
