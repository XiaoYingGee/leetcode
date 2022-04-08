package com.xiaoyingge.algorithm.part07;

import com.xiaoyingge.common.TreeNode;

/**
 * @author XiaoYingGee
 * @date 2022/3/18 22:06
 */
public class Question0606 {

    public String tree2str(TreeNode root) {
        return process(root);
    }

    public static void main(String[] args) {
        System.out.println(new Question0606().process(new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3))));
    }

    private String process(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(root.val + "");

        String left = process(root.left);
        String right = process(root.right);
        if (left == null && right == null) {
            return sb.toString();
        } else if (left == null) {
            return sb.append("()(").append(right).append(")").toString();
        } else if (right == null) {
            return sb.append("(").append(left).append(")").toString();
        } else {
            return sb.append("(").append(left).append(")").append("(").append(right).append(")").toString();
        }
    }
}