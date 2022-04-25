package com.xiaoyingge.algorithm.part01;

/**
 * @author XiaoYingGee
 * @date 2022/4/25 23:49
 */
public class Question0055 {

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int n = nums.length;
        int max = nums[0];
        if (max == 0) {
            return n == 1;
        }
        for (int i = 1; i < n - 1; i++) {
            max = Math.max(max - 1, nums[i]);
            if (max == 0) {
                return false;
            }
        }
        return max > 0;
    }

    public static void main(String[] args) {
        System.out.println(new Question0055().canJump(new int[]{0}));
    }

}
