package com.xiaoyingge.algorithm.part10;

/**
 * @author XiaoYingGee
 * @date 2022/4/28 21:39
 */
public class Question0985 {

    public int[] sortArrayByParity(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return nums;
        }
        int left = 0;
        int right = length - 1;
        while (left < right) {
            if ((nums[left] & 1) == 1) {
                swap(nums, left, right);
                right--;
            } else {
                left++;
            }

        }
        return nums;
    }

    public void swap(int[] nums, int a, int b) {
        if (a == b) {
            return;
        }
        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
    }
}
