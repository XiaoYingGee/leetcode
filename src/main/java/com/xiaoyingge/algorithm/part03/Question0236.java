package com.xiaoyingge.algorithm.part03;

/**
 * @author XiaoYingGee
 * @date 2022/4/19 21:28
 */
public class Question0236 {

    public static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int x, TreeNode left, TreeNode right) {
            val = x;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode ans = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        process(root, p, q);
        return ans;
    }

    private Info process(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new Info(false, false);
        }

        Info left = process(root.left, p, q);
        Info right = process(root.right, p, q);

        boolean findP = root == p || left.findP || right.findP;
        boolean findQ = root == q || left.findQ || right.findQ;
        if (ans == null && findP && findQ) {
            ans = root;
        }
        return new Info(findP, findQ);
    }

    public class Info {

        boolean findP;
        boolean findQ;

        Info(boolean findP, boolean findQ) {
            this.findP = findP;
            this.findQ = findQ;
        }
    }

    public static void main(String[] args) {
        TreeNode left = new TreeNode(5);
        TreeNode right = new TreeNode(1);
        TreeNode root = new TreeNode(3, left, right);
        new Question0236().lowestCommonAncestor(root, left, right);
    }
}
