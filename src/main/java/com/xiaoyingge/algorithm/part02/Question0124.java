package com.xiaoyingge.algorithm.part02;

import com.xiaoyingge.common.TreeNode;

/**
 * @author XiaoYingGee
 * @date 2022/3/15 0:19
 */
public class Question0124 {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewPath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewPath);

        // 返回节点的最大贡献值
        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        TreeNode four = new TreeNode(4, null, 1);
        TreeNode three = new TreeNode(3, four, null);
        TreeNode six = new TreeNode(6, null, 5);
        TreeNode noFive = new TreeNode(-5, six, three);

        TreeNode noNine = new TreeNode(-9, 2);

        TreeNode five = new TreeNode(5, noFive, noNine);
        int i = new Question0124().maxPathSum(five);
        System.out.println(i);
    }
}
