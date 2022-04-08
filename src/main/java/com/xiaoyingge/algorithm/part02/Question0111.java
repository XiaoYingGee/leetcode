package com.xiaoyingge.algorithm.part02;

import com.xiaoyingge.common.TreeNode;

/**
 * @author XiaoYingGee
 * @date 2022/3/13 14:43
 */
public class Question0111 {

    public static void main(String[] args) {
        //[2,null,3,null,4,null,5,null,6]
        System.out.println(new Question0111().minDepth(null));
    }

    public int minDepth(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = 0;
        if (root.left != null) {
            left = minDepth(root.left);
        }
        int right = 0;
        if (root.right != null) {
            right = minDepth(root.right);
        }
        //说明左边没节点
        if (left == 0) {
            return right + 1;
        }
        //说明右边没节点
        if (right == 0) {
            return left + 1;
        }
        return Math.min(left, right) + 1;
    }

}
