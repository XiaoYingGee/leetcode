package com.xiaoyingge.algorithm.part23;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 * @author XiaoYingGee
 * @date 2022/3/26 21:36
 */
public class Question2206 {

    //不能使用异或解的原因是如果有多个奇数次的数异或在一起是可能为0的，比如2^4^6=0
    public static boolean divideArray(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        return set.size() == 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{9, 9, 19, 10, 9, 12, 2, 12, 3, 3, 11, 5, 8, 4, 13, 6, 2, 11, 9, 19, 11, 15, 9, 17, 15, 12, 5, 14, 12, 16, 18, 16, 10,
                3, 8, 9, 16, 20, 2, 4, 16, 12, 11, 14, 20, 16, 2, 18, 17, 20, 3, 13, 16, 17, 1, 1, 11, 20, 20, 4};
        Arrays.sort(nums);
        System.out.println(StringUtils.join(nums, ','));
        System.out.println(divideArray(nums));

        System.out.println(2 ^ 6 ^ 4);
    }

}
