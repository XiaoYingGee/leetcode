package com.xiaoyingge.algorithm.part01;

import com.xiaoyingge.common.TreeNode;

/**
 * 判断两个树是否完全相等
 *
 * @author XiaoYingGee
 * @date 2022/3/12 23:00
 */
public class Question0100 {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }

        if (p == null && q == null) {
            return true;

        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
