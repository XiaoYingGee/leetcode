package com.xiaoyingge.offer;

import com.xiaoyingge.common.TreeNode;
import java.util.LinkedList;

/**
 * @author XiaoYingGee
 * @date 2022/4/14 23:40
 */
public class Offer28 {

    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode x = queue.poll();
            TreeNode y = queue.poll();
            if (x == null && y == null) {
                continue;
            }
            if ((x == null || y == null) || x.val != y.val) {
                return false;
            }

            queue.add(x.left);
            queue.add(y.right);
            queue.add(x.right);
            queue.add(y.left);

        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, 3, 1), new TreeNode(2, 4, 3));
        boolean symmetric = new Offer28().isSymmetric(root);
        System.out.println(symmetric);
    }
}
