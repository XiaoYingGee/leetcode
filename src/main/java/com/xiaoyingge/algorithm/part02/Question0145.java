package com.xiaoyingge.algorithm.part02;

import com.xiaoyingge.common.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author XiaoYingGee
 * @date 2022/3/15 0:18
 */
public class Question0145 {

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        process(result, root);
        return result;
    }

    private void process(List<Integer> result, TreeNode root) {
        if (root == null) {
            return;
        }
        process(result, root.left);
        process(result, root.right);
        result.add(root.val);

    }
}
