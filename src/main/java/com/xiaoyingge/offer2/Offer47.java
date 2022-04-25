package com.xiaoyingge.offer2;

/**
 * @author XiaoYingGee
 * @date 2022/4/19 22:03
 */
public class Offer47 {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        Info info = process(root);
        return info.needDelete == true ? null : root;
    }

    public Info process(TreeNode node) {
        if (node.left == null && node.right == null) {
            if (node.val == 0) {
                return new Info(true);
            }
            return new Info(false);
        }

        Info left = new Info(true);
        Info right = new Info(true);

        if (node.left != null) {
            left = process(node.left);
        }
        if (node.right != null) {
            right = process(node.right);
        }

        if (left.needDelete) {
            node.left = null;
        }
        if (right.needDelete) {
            node.right = null;
        }
        return new Info(left.needDelete && right.needDelete && node.val == 0);

    }

    public class Info {

        boolean needDelete;

        Info(boolean needDelete) {
            this.needDelete = needDelete;
        }
    }
}
