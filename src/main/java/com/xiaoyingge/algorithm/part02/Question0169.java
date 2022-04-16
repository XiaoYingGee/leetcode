package com.xiaoyingge.algorithm.part02;

/**
 * @author XiaoYingGee
 * @date 2022/4/11 22:38
 */
public class Question0169 {

    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int cur = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {

            int num = nums[i];
            if (count == 0) {
                cur = num;
                count++;
                continue;
            }
            if (cur == num) {
                count++;
            } else {
                count--;
            }
        }
        return cur;
    }

    public static void main(String[] args) {

    }
}
