package com.xiaoyingge.algorithm.part05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author XiaoYingGee
 * @date 2022/4/30 14:58
 */
public class Question0491 {

    public List<List<Integer>> findSubsequences(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return Collections.emptyList();
        }

        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        List<List<Integer>> first = new ArrayList<>();
        ArrayList<Integer> firstList = new ArrayList<>();
        firstList.add(nums[0]);
        first.add(firstList);
        map.put(0, first);
        for (int i = 1; i < length; i++) {
            List<List<Integer>> curPackage = new ArrayList<>();
            int cur = nums[i];
            List<Integer> row = new ArrayList<>();
            row.add(cur);
            curPackage.add(row);
            for (int j = 0; j < i; j++) {
                int pre = nums[j];
                if (pre > cur) {
                    continue;
                }
                List<List<Integer>> prePackage = map.get(j);
                for (List<Integer> preList : prePackage) {
                    List<Integer> now = new ArrayList<>(preList);
                    now.add(cur);
                    curPackage.add(now);
                }
            }
            map.put(i, curPackage);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (Entry<Integer, List<List<Integer>>> entry : map.entrySet()) {
            for (List<Integer> integers : entry.getValue()) {
                if (integers.size() == 1) {
                    continue;
                }
                result.add(integers);
            }
        }
        return result;
    }

    public List<List<Integer>> findSubsequences2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        process(ans, nums, 0, Integer.MIN_VALUE, new ArrayList<>());
        return ans;
    }

    private void process(List<List<Integer>> ans, int[] nums, int index, int pre, List<Integer> path) {
        if (index == nums.length) {
            if (path.size() > 1) {
                ans.add(path);
            }
            return;
        }
        int cur = nums[index];
        //选择当前的数
        if (cur >= pre) {
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(cur);
            process(ans, nums, index + 1, cur, newPath);
        }
        //不选择当前的数
        if (cur != pre) {
            process(ans, nums, index + 1, pre, path);
        }
    }

    public static void main(String[] args) {
        new Question0491().findSubsequences2(new int[]{4, 4, 5, 2, 1});
    }
}
