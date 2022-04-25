package com.xiaoyingge.algorithm.part01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author XiaoYingGee
 * @date 2022/4/25 22:59
 */
public class Question0046 {

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();

        }
        List<List<Integer>> result = new ArrayList<>();
        process(result, nums, new ArrayList<>());
        return result;

    }

    public void process(List<List<Integer>> result, int[] nums, List<Integer> path) {
        if (nums.length == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            path.add(nums[i]);
            process(result, copyAndRemove(nums, i), path);
            path.remove(path.size() - 1);
        }
    }

    public int[] copyAndRemove(int[] nums, int k) {
        int[] copy = new int[nums.length - 1];
        int i = 0;
        int j = 0;
        while (i < nums.length) {
            if (i == k) {
                i++;
                continue;
            }
            copy[j++] = nums[i++];
        }
        return copy;
    }

    public static void main(String[] args) {
        List<List<Integer>> permute = new Question0046().permute(new int[]{1, 2, 3});
        System.out.println();
    }

}
