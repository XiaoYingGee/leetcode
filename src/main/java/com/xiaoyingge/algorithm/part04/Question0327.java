package com.xiaoyingge.algorithm.part04;

import com.xiaoyingge.util.NumUtil;
import com.xiaoyingge.util.PrintUtil;

/**
 * @author XiaoYingGee
 * @date 2022/3/16 22:57
 */
public class Question0327 {

    public static void main(String[] args) {
        System.out.println(97 % 3);
        System.out.println(-97 % 3);
        for (int i = 0; i < 1000000; i++) {
            int[] nums = NumUtil.createRandomArray(NumUtil.random(10), true, 10000);
            int random1 = NumUtil.random(50000);
            int random2 = NumUtil.random(50000);
            int min = Math.min(random1, random2);
            int max = Math.max(random1, random2);
//            int[] nums = {6172, -2016, 9526};
//            int min = 9957;
//            int max = 36706;

            int[] copy = NumUtil.copy(nums);
            int oneByOne = tryFind(copy, min, max);
            int merge = new Question0327().countRangeSum(nums, min, max);
            if (merge != oneByOne) {
                PrintUtil.print(copy);
                System.out.println(min + "," + max + "," + oneByOne + "," + merge);
                throw new RuntimeException("ooooooo");
            }
        }
    }

    private static int tryFind(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0] <= upper && nums[0] >= lower ? 1 : 0;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (inRange(nums, j, i, lower, upper)) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private static boolean inRange(int[] nums, int left, int right, int lower, int upper) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            count += nums[i];
        }
        return count <= upper && count >= lower;
    }

    //就是求从哪到哪加起来的值在他的范围内
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0] <= upper && nums[0] >= lower ? 1 : 0;
        }
        //数组转换成前缀和数组
        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = nums[i] + sums[i - 1];
        }
        return process(sums, 0, nums.length - 1, lower, upper);
    }

    private int process(long[] sums, int l, int r, int lower, int upper) {
        if (l == r) {
            return sums[l] >= lower && sums[l] <= upper ? 1 : 0;
        }
        int mid = l + ((r - l) >> 1);
        int lSum = process(sums, l, mid, lower, upper);
        int rSum = process(sums, mid + 1, r, lower, upper);
        int mSum = merge(sums, l, mid, r, lower, upper);
        return lSum + rSum + mSum;
    }

    private int merge(long[] sums, int l, int mid, int r, int lower, int upper) {
        int result = 0;
        int lowerIndex = l;
        int upperIndex = l;
        for (int i = mid + 1; i <= r; i++) {
            long min = sums[i] - upper;
            long max = sums[i] - lower;
            while (upperIndex <= mid && sums[upperIndex] <= max) {
                upperIndex++;
            }
            while (lowerIndex <= mid && sums[lowerIndex] < min) {
                lowerIndex++;
            }
            result += upperIndex - lowerIndex;
        }

        long[] help = new long[r - l + 1];
        int index = 0;
        int left = l;
        int right = mid + 1;
        while (left <= mid && right <= r) {
            help[index++] = sums[left] < sums[right] ? sums[left++] : sums[right++];
        }
        while (left <= mid) {
            help[index++] = sums[left++];
        }
        while (right <= r) {
            help[index++] = sums[right++];
        }

        for (int i = 0; i < help.length; i++) {
            sums[l + i] = help[i];
        }
        return result;
    }
}
