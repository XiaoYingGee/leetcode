package com.xiaoyingge.common;

/**
 * @author XiaoYingGee
 * @date 2022/3/13 0:47
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int val, int left) {
        this.val = val;
        this.left = new TreeNode(left);

    }

    public TreeNode(int val, Integer left, Integer right) {
        this.val = val;
        this.left = left == null ? null : new TreeNode(left);
        this.right = right == null ? null : new TreeNode(right);
    }

    @Override
    public String toString() {
        return val + " ";
    }
}

