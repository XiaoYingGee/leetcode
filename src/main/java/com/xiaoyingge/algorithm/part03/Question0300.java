package com.xiaoyingge.algorithm.part03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaoYingGee
 * @date 2022/4/30 16:35
 */
public class Question0300 {

    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 1;
        }
        int[] dp = new int[length];
        dp[0] = 1;
        int max = 0;
        for (int i = 1; i < length; i++) {
            int curMax = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    curMax = Math.max(curMax, dp[j]);
                }
            }
            dp[i] = curMax + 1;
            max = Math.max(max, dp[i]);
        }
        return max;

    }

    public static void main(String[] args) {
        new Question0300().lengthOfLIS(new int[]{
                0, 1, 0, 0});
    }

    public int lengthOfLIS2(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return 1;
        }
        List<Integer> help = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < length; i++) {
            max = Math.max(max, mark(nums[i], help));
        }
        return max;

    }

    public int mark(int val, List<Integer> list) {
        if (list.size() == 0) {
            list.add(val);
            return 1;
        }
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = left = ((right - left) >> 1);
            if (list.get(mid) == val) {
                return mid + 1;
            }
        }
        return 1;
    }
}
