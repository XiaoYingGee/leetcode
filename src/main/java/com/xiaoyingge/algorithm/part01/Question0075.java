package com.xiaoyingge.algorithm.part01;

/**
 * @author XiaoYingGee
 * @date 2022/4/26 0:06
 */
public class Question0075 {

    public void sortColors(int[] nums) {
        int red = 0;
        int white = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                red++;
            } else if (nums[i] == 1) {
                white++;
            }
        }
        for (int i = 0; i < red; i++) {
            nums[i] = 0;
        }
        for (int i = red; i < red + white; i++) {
            nums[i] = 1;
        }
        for (int i = red + white; i < nums.length; i++) {
            nums[i] = 2;
        }
    }
}
