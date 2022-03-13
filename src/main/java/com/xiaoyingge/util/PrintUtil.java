package com.xiaoyingge.util;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author XiaoYingGee
 * @date 2022/2/22 22:21
 */
public class PrintUtil {

    public static void print(int[] arr) {
        System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.toList()));
    }

}
