package com.xiaoyingge.practise;

import com.xiaoyingge.util.NumUtil;
import com.xiaoyingge.util.PrintUtil;

/**
 * 尝试求小和问题
 *
 * @author XiaoYingGee
 * @date 2022/3/16 22:28
 */
public class MinSum {

    public static void main(String[] args) {
        int maxTimes = 100_000;
        for (int i = 0; i < maxTimes; i++) {
            int length = NumUtil.random(100);
            int[] nums = NumUtil.createRandomArray(length, 100);
            int[] copy = NumUtil.copy(nums);
            int result = oneByone(copy);

            int result2 = mergeSort(nums);
            if (result != result2) {
                PrintUtil.print(copy);
                throw new RuntimeException("出错啦");
            }
        }
    }

    private static int mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return process(nums, 0, nums.length - 1);
    }

    private static int process(int[] nums, int l, int r) {
        if (l == r) {
            //同一个点不会产生小和
            return 0;
        }
        int mid = ((r - l) >> 1) + l;
        int left = process(nums, l, mid);
        int right = process(nums, mid + 1, r);
        int mergeResult = merge(nums, l, mid, r);
        return left + right + mergeResult;
    }

    private static int merge(int[] nums, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int index = 0;
        int left = l;
        int right = mid + 1;

        int sum = 0;
        while (left <= mid && right <= r) {
            //左边小于右边时，会产生小和
            if (nums[left] < nums[right]) {
                sum += nums[left] * (r - right + 1);
                help[index++] = nums[left++];
            } else {
                help[index++] = nums[right++];
            }
        }
        while (left <= mid) {
            help[index++] = nums[left++];
        }
        while (right <= r) {
            help[index++] = nums[right++];
        }

        for (int i = 0; i < help.length; i++) {
            nums[l + i] = help[i];
        }
        return sum;
    }

    /**
     * 暴力计算
     *
     * @param nums
     * @return
     */
    private static int oneByone(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    result += nums[j];
                }
            }
        }
        return result;
    }
}
