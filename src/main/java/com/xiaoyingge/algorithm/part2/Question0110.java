package com.xiaoyingge.algorithm.part2;

import com.xiaoyingge.common.TreeNode;

/**
 * @author XiaoYingGee
 * @date 2022/3/13 0:25
 */
public class Question0110 {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root).isBalance;
    }

    private NodeInfo check(TreeNode node) {
        if (node == null) {
            return new NodeInfo(0, true);
        }
        NodeInfo left = check(node.left);
        NodeInfo right = check(node.right);

        int height = Math.max(left.height, right.height) + 1;

        boolean isBalance = left.isBalance && right.isBalance && Math.abs(left.height - right.height) <= 1;
        return new NodeInfo(height, isBalance);
    }

    private class NodeInfo {

        int height;
        boolean isBalance;

        NodeInfo(int height, boolean isBalance) {
            this.height = height;
            this.isBalance = isBalance;
        }
    }
}
