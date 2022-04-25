package com.xiaoyingge.algorithm.part01;

/**
 * @author XiaoYingGee
 * @date 2022/4/17 21:39
 */
public class Question0041 {

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] <= 0) {
                nums[i] = length + 1;
            }
        }
        for (int i = 0; i < length; i++) {
            if (Math.abs(nums[i]) <= length && nums[Math.abs(nums[i]) - 1] > 0) {
                nums[Math.abs(nums[i]) - 1] = -nums[Math.abs(nums[i]) - 1];
            }
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        int i = new Question0041().firstMissingPositive(new int[]{1, 6, 3, 4, 5, 2, 3, 4, 7, 3, 4});
        System.out.println(i);
    }
}
