package com.xiaoyingge.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * @author XiaoYingGee
 * @date 2022/2/22 22:21
 */
public class PrintUtil {

    public static void print(int[] arr) {
        System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.toList()));
    }

    public static void print(List<Integer> integers) {
        System.out.println(StringUtils.join(integers));
    }
}
