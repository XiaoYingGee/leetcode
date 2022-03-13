package com.xiaoyingge.algorithm.part2;

import com.xiaoyingge.common.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XiaoYingGee
 * @date 2022/3/12 23:37
 */
public class Question0105 {

    public static void main(String[] args) {
        int[] a = {3, 9, 20, 15, 7};
        int[] b = {9, 3, 15, 20, 7};
        TreeNode root = new Question0105().buildTree(a, b);
        System.out.println(root);

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode build(int[] pre, int l1, int r1, int[] in, int l2, int r2, Map<Integer, Integer> map) {
        if (l1 > r1) {
            return null;
        }
        TreeNode root = new TreeNode(pre[l1]);
        if (l1 == r1) {
            return root;
        }
        //pre[l1]即为根节点
        int find = map.get(pre[l1]);

        root.left = build(pre, l1 + 1, l1 + find - l2, in, l2, find - 1, map);
        root.right = build(pre, l1 + find - l2 + 1, r1, in, find + 1, r2, map);
        return root;

    }
}
