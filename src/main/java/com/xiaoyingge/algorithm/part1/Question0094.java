package com.xiaoyingge.algorithm.part1;

import com.xiaoyingge.common.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaoYingGee
 * @date 2022/3/13 0:47
 */
public class Question0094 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        process(result, root);
        return result;
    }

    private void process(List<Integer> result, TreeNode root) {
        if (root == null) {
            return;
        }
        process(result, root.left);
        result.add(root.val);
        process(result, root.right);
    }

}
