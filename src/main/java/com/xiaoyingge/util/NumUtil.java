package com.xiaoyingge.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 数组生成器
 *
 * @author XiaoYingGee
 * @date 2022/2/19 17:58
 */
public class NumUtil {

    public static int random() {
        return createRandom().nextInt(Integer.MAX_VALUE);
    }

    public static int random(int max) {
        return createRandom().nextInt(max);
    }

    public static int[] createRandomArray(int nums, boolean hasNegativeNumber) {
        return createRandomArray(nums, hasNegativeNumber, Integer.MAX_VALUE);
    }

    public static int[] createRandomArray(int nums) {
        return createRandomArray(nums, false, Integer.MAX_VALUE);
    }

    public static int[] createRandomArray(int nums, int max) {
        return createRandomArray(nums, false, max);
    }

    public static int[] createRandomArray(int nums, boolean hasNegativeNumber, int max) {
        SecureRandom random = createRandom();
        int[] result = new int[nums];
        for (int i = 0; i < nums; i++) {
            if (!hasNegativeNumber) {
                result[i] = random.nextInt(max);
            } else {
                result[i] = random.nextInt(2) == 1 ? random.nextInt(max) : 0 - random.nextInt(max);
            }
        }
        return result;
    }

    public static SecureRandom createRandom() {
        try {
            return SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[] copy(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static void compare(int[] arr, int[] test) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != test[i]) {
                throw new RuntimeException("数据不一致");
            }
        }
        System.out.println("AMD YES!!!");
    }

    public static void swap(int[] arr, int from, int to) {
        if (from == to) {
            return;
        }
        arr[from] = arr[to] ^ arr[from];
        arr[to] = arr[to] ^ arr[from];
        arr[from] = arr[to] ^ arr[from];
    }

}
