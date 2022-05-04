package com.xiaoyingge.algorithm.part02;

import com.xiaoyingge.common.TreeNode;

/**
 * @author XiaoYingGee
 * @date 2022/4/29 0:06
 */
public class Question0114 {

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        process(root);
    }

    public TreeNode process(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode right = process(node.right);
        TreeNode left = process(node.left);
        if (left != null) {
            TreeNode cur = left;
            TreeNode next = cur.right;
            while (next != null) {
                cur = next;
                next = cur.right;
            }
            cur.right = right;
            node.right = left;
            node.left = null;
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(5, null, new TreeNode(6)));
        new Question0114().flatten(treeNode);
    }
}
