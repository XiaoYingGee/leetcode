package com.xiaoyingge.algorithm.part02;

import com.xiaoyingge.common.TreeNode;

/**
 * 二叉树最大深度
 *
 * @author XiaoYingGee
 * @date 2022/3/12 23:06
 */
public class Question0104 {

    public int maxDepth(TreeNode root) {
        return process(root).depth;
    }

    private NodeInfo process(TreeNode root) {
        if (root == null) {
            return new NodeInfo(0);
        }
        NodeInfo left = process(root.left);
        NodeInfo right = process(root.right);
        int depth = Math.max(left.depth, right.depth) + 1;
        return new NodeInfo(depth);

    }

    private class NodeInfo {

        int depth;

        NodeInfo(int depth) {
            this.depth = depth;
        }
    }
}
