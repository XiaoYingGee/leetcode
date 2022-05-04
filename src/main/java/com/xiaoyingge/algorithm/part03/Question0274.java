package com.xiaoyingge.algorithm.part03;

import java.util.Arrays;

/**
 * @author XiaoYingGee
 * @date 2022/5/3 17:03
 */
public class Question0274 {

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        return citations[citations.length >> 1];
    }
}
