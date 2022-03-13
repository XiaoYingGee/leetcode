package com.xiaoyingge.algorithm.part2;

import com.xiaoyingge.common.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author XiaoYingGee
 * @date 2022/3/13 15:03
 */
public class Question0113 {

    public static void main(String[] args) {
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        process(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void process(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> result) {
        if (root.left == null && root.right == null && targetSum == root.val) {
            List<Integer> ans = new ArrayList<>(path);
            //这里没有干扰path,不必处理
            ans.add(root.val);
            result.add(ans);
            return;
        }

        path.add(root.val);
        if (root.left != null) {
            process(root.left, targetSum - root.val, path, result);
        }
        if (root.right != null) {
            process(root.right, targetSum - root.val, path, result);
        }
        path.remove(path.size() - 1);
    }

}
