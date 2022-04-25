package com.xiaoyingge.algorithm.part01;

/**
 * @author XiaoYingGee
 * @date 2022/4/17 20:24
 */
public class Question0034 {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                return new int[]{0, 0};
            }
            return new int[]{-1, -1};
        }
        int left = -1;
        if (nums[0] == target) {
            left = 0;
        } else {
            left = find(nums, 0, nums.length - 1, target, true);
        }
        int right = -1;
        if (nums[nums.length - 1] == target) {
            right = nums.length - 1;
        } else {
            right = find(nums, 0, nums.length - 1, target, false);
        }
        return new int[]{left, right};
    }

    public int find(int[] nums, int left, int right, int target, boolean findLower) {
        int index = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                index = mid;
                if (findLower) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] ints = new Question0034().searchRange(new int[]{8, 8, 8, 8, 8, 8}, 8);
        System.out.println(ints[0] + " " + ints[1]);
    }
}
