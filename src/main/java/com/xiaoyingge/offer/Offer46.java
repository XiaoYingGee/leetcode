package com.xiaoyingge.offer;

/**
 * @author XiaoYingGee
 * @date 2022/4/17 13:58
 */
public class Offer46 {

    public int translateNum(int num) {
        if (num < 10) {
            return 1;
        }

        char[] nums = String.valueOf(num).toCharArray();
        int[] dp = new int[nums.length + 1];
        dp[nums.length] = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            int p1 = 0;
            int p2 = 0;
            p1 = dp[i + 1];
            if (i + 2 < nums.length
                    && '0' != nums[i]
                    && Integer.valueOf(nums[i] + "" + nums[i + 1]) < 26) {
                p2 = dp[i + 2];
            }
            dp[i] = p1 + p2;
        }
        return dp[0];
    }

    public int process(char[] nums, int index) {
        if (index == nums.length) {
            return 1;
        }
        int p1 = 0;
        int p2 = 0;
        p1 = process(nums, index + 1);
        if (index + 2 < nums.length
                && '0' != nums[index]
                && Integer.valueOf(nums[index] + "" + nums[index + 1]) < 26) {
            p2 = process(nums, index + 2);
        }
        return p1 + p2;

    }

    public static void main(String[] args) {
        int i = new Offer46().translateNum(12258);
        System.out.println(i);
    }
}
