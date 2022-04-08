package com.xiaoyingge.algorithm.part02;

import com.xiaoyingge.common.TreeNode;

/**
 * 判断一个树是否是镜面树
 *
 * @author XiaoYingGee
 * @date 2022/3/12 22:59
 */
public class Question0101 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        if (left == null ^ right == null) {
            return false;
        }
        if (left == null && right == null) {
            return true;
        }

        return left.val == right.val && check(left.left, right.right) && check(left.right, right.left);
    }
}
