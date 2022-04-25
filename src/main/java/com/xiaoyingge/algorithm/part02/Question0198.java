package com.xiaoyingge.algorithm.part02;

/**
 * @author XiaoYingGee
 * @date 2022/4/18 0:01
 */
public class Question0198 {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int l = nums.length;
        if (l == 1) {
            return nums[0];
        }
        int[] dp = new int[l + 2];
        for (int i = l - 1; i >= 0; i--) {
            int p1 = dp[i + 1];
            int p2 = nums[i] + dp[i + 2];
            dp[i] = Math.max(p1, p2);
        }
        return dp[0];
    }
}
