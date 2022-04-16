package com.xiaoyingge.algorithm.part03;

import java.util.Random;

/**
 * @author XiaoYingGee
 * @date 2022/4/16 15:06
 */
public class Question0215 {

    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (left == right) {
                return nums[left];
            }
            int random = getRandomIndex(nums, left, right);
            int[] range = paritition(nums, left, right, random);
            if (range[0] > nums.length - k) {
                right = range[0] - 1;
            } else if (range[1] < nums.length - k) {
                left = range[1] + 1;
            } else {
                return nums[nums.length - k];
            }
        }
        return -1;
    }

    public int getRandomIndex(int[] nums, int left, int right) {
        return left + new Random().nextInt(right - left + 1);
    }

    public int[] paritition(int[] nums, int left, int right, int vIndex) {
        int li = left;
        int ri = right;
        int val = nums[vIndex];
        swap(nums, vIndex, right);
        for (int i = left; i < ri; ) {
            if (nums[i] < val) {
                swap(nums, li++, i++);
            } else if (nums[i] == val) {
                i++;
            } else {
                swap(nums, i, --ri);
            }
        }
        swap(nums, right, ri);
        return new int[]{li, ri};
    }

    public void swap(int[] nums, int a, int b) {
        if (a == b) {
            return;
        }
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        int kthLargest = new Question0215().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 6);
        System.out.println(kthLargest);
    }

}
