package com.xiaoyingge.algorithm.part02;

/**
 * @author XiaoYingGee
 * @date 2022/3/13 21:39
 */
public class Question0137 {

    public static void main(String[] args) {
        int[] nums2 = {2, 3, 2, 2};
        int result = new Question0137().calc(nums2, 3);
        System.out.println(result);
    }

    /**
     * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int[] nums2 = {2, 3, 2, 2};
        int result = calc(nums2, 3);
        return result;
    }

    private int calc(int[] nums, int k) {
        int[] help = new int[32];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < 32; j++) {
                help[j] += nums[i] >> j & 1;
            }
        }
        //其实问题就是求出现了 M和N次的数中，那个M是谁
        int result = 0;
        for (int i = 0; i < help.length; i++) {
            if (help[i] % k != 0) {
                result |= (1 << i);
            }
        }
        return result;
    }
}
