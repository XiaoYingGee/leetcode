package com.xiaoyingge.algorithm.part16;

/**
 * @author XiaoYingGee
 * @date 2022/4/18 23:41
 */
public class Question1567 {

    public int getMaxLen(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
//        int length = nums.length;
//        int positive = nums[0] > 0 ? 1 : 0;
//        int negative = nums[0] < 0 ? 1 : 0;
//        int maxLength = positive;
//        for (int i = 1; i < length; i++) {
//            if (nums[i] > 0) {
//                positive++;
//                negative = negative > 0 ? negative + 1 : 0;
//            } else if (nums[i] < 0) {
//                int newPositive = negative > 0 ? negative + 1 : 0;
//                int newNegative = positive + 1;
//                positive = newPositive;
//                negative = newNegative;
//            } else {
//                positive = 0;
//                negative = 0;
//            }
//            maxLength = Math.max(maxLength, positive);
//        }
//        return maxLength;

        int upper = nums[0] > 0 ? 1 : 0;
        int lower = nums[0] < 0 ? 1 : 0;
        int max = upper;
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            if (cur == 0) {

                upper = 0;
                lower = 0;
            } else if (cur > 0) {
                upper++;
                lower = lower > 0 ? lower + 1 : 0;
            } else {
                int u = 0;
                int l = 0;
                if (lower > 0) {
                    u = lower + 1;
                }
                l = upper + 1;
                lower = l;
                upper = u;
            }
            max = Math.max(max, upper);
        }
        return max;
    }

    public static void main(String[] args) {
        Question1567 question1567 = new Question1567();
        int maxLen = question1567.getMaxLen(new int[]{5, -20, -20, -39, -5, 0, 0, 0, 36, -32, 0, -7, -10, -7, 21, 20, -12, -34, 26, 2});
        System.out.println(maxLen);
    }
}
