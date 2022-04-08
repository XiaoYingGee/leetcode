package com.xiaoyingge.algorithm.part06;

import java.util.HashSet;
import java.util.Set;

/**
 * @author XiaoYingGee
 * @date 2022/3/26 21:49
 */
public class Question0575 {

    public int distributeCandies(int[] candyType) {
        Set<Integer> set = new HashSet<>();
        for (int type : candyType) {
            if (set.contains(type)) {
                continue;
            }
            set.add(type);
        }
        return Math.min(candyType.length >> 1, set.size());

    }
}
