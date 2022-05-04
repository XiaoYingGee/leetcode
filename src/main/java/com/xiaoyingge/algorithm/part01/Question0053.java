package com.xiaoyingge.algorithm.part01;

import com.xiaoyingge.util.NumUtil;
import com.xiaoyingge.util.PrintUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaoYingGee
 * @date 2022/4/16 6:55
 */
public class Question0053 {

    int singleMax = Integer.MIN_VALUE;

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        List<Integer> newNums = count(nums);
        if (newNums.size() == 1) {
            return newNums.get(0);
        }
        int min = newNums.get(0);
        int areaMax = Integer.MIN_VALUE;
        for (int i = 1; i < newNums.size(); i++) {
            int cur = newNums.get(i);
            if (cur < min) {
                min = cur;
                continue;
            }
            areaMax = Math.max(areaMax, cur - min);
        }
        return Math.max(areaMax, singleMax);
    }

    /**
     * 求出最大值与前缀和数组
     */
    public List<Integer> count(int[] nums) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        singleMax = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            singleMax = Math.max(nums[i - 1], singleMax);
            result.add(nums[i - 1] + result.get(i - 1));
        }
        return result;
    }

    public int dp(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;

    }

    public static void main(String[] args) {
        Question0053 question0053 = new Question0053();
        for (int i = 0; i < 100; i++) {
            int[] randomArray = NumUtil.createRandomArray(NumUtil.random(10), true, 100);
            int r = question0053.maxSubArray2(randomArray);
            int dp = question0053.dp(randomArray);
            if (r != dp) {
                System.out.println(r + "," + dp);
                PrintUtil.print(randomArray);
            }
        }

    }

    public int maxSubArray2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int pre = nums[0];
        int max = pre;
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i] + (pre > 0 ? pre : 0);
            max = Math.max(max, cur);
            pre = cur;
        }
        return max;

    }

}
