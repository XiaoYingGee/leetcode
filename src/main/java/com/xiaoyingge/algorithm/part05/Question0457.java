package com.xiaoyingge.algorithm.part05;

/**
 * @author XiaoYingGee
 * @date 2022/3/17 1:37
 */
public class Question0457 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        new Question0457().circularArrayLoop(nums);
    }

    public boolean circularArrayLoop(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                return false;
            }
            int size = Math.abs(nums[i] % nums.length) - i;
            boolean isPositive = nums[i] > 0;
            int nextIndex = i;
            int step = 0;
            while (size < nums.length) {

                nextIndex = (nextIndex + nums[nextIndex]) % nums.length;
                nextIndex = nextIndex > 0 ? nextIndex : nums.length + nextIndex;
                int next = nums[nextIndex];
                if (next == 0 || (isPositive ^ next > 0)) {
                    return false;
                }
                step++;
                size += Math.abs(nums[nextIndex] % nums.length);
            }
            if (size == nums.length) {
                return step != 1;
            }
        }
        return false;
    }

}
