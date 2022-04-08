package com.xiaoyingge.algorithm.part02;

/**
 * @author XiaoYingGee
 * @date 2022/3/13 0:32
 */
public class Question0136 {

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
