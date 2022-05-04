package com.xiaoyingge.algorithm.part03;

/**
 * @author XiaoYingGee
 * @date 2022/4/27 0:42
 */
public class Question0283 {

    //解法一：归并
    public void moveZeroes(int[] nums) {
        process(nums, 0, nums.length - 1);
    }

    public void process(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        process(nums, left, mid);
        process(nums, mid + 1, right);
        merge(nums, left, mid, right);

    }

    public void merge(int[] nums, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int l = left;
        int r = mid + 1;
        int index = 0;
        while (l <= mid && nums[l] != 0) {
            help[index++] = nums[l++];
        }
        while (r <= right && nums[r] != 0) {
            help[index++] = nums[r++];
        }
        for (int i = 0; i < help.length; i++) {
            nums[left + i] = help[i];
        }
    }

    //解法二，遍历处理
    public void moveZeroes2(int[] nums) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                right++;
                continue;
            }
            swap(nums, left++, right++);
        }
    }

    public void swap(int[] nums, int a, int b) {
        if (a == b) {
            return;
        }
        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
    }

    public static void main(String[] args) {

        int[] nums = {0, 1, 0, 3, 12};
        new Question0283().moveZeroes2(nums);
        System.out.println();
    }

}
