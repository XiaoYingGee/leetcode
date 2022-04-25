package com.xiaoyingge.algorithm.part03;

/**
 * @author XiaoYingGee
 * @date 2022/4/18 0:37
 */
public class Question0213 {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int l = nums.length;
        if (l == 1) {
            return nums[0];
        }
        return Math.max(nums[0] + process(nums, 2, l - 1), process(nums, 1, l));
    }

    public int dp(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int l = nums.length;
        if (l == 1) {
            return nums[0];
        }
        int[] dp1 = new int[l + 2];
        int[] dp2 = new int[l + 2];
        for (int i = l - 2; i >= 0; i--) {
            int p1 = nums[i] + dp1[i + 2];
            int p2 = dp1[i + 1];
            dp1[i] = Math.max(p1, p2);
        }
        for (int i = l - 1; i >= 0; i--) {
            int p1 = nums[i] + dp2[i + 2];
            int p2 = dp2[i + 1];
            dp2[i] = Math.max(p1, p2);
        }
        return Math.max(nums[0] + dp1[2], dp2[1]);
    }

    public int process(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int p1 = nums[left] + process(nums, left + 2, right);
        int p2 = process(nums, left + 1, right);
        return Math.max(p1, p2);
    }

    public static void main(String[] args) {

        int[] ints = {2, 3, 2};
        Question0213 question0213 = new Question0213();
        int rob = question0213.rob(ints);
        int dp = question0213.dp(ints);
        System.out.println(rob);
        System.out.println(dp);
    }
}
