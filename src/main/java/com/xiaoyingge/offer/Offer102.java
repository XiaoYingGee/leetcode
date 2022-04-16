package com.xiaoyingge.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiaoYingGee
 * @date 2022/4/15 1:01
 */
public class Offer102 {

    public int numWays(int n) {
        if (n <= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        Map<Integer, Integer> cache = new HashMap<>();
        return process(n, cache);
    }

    public int process(int n, Map<Integer, Integer> cache) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        if (n <= 0) {
            return n == 0 ? 1 : 0;
        }
        int p1 = process(n - 1, cache);
        int p2 = process(n - 2, cache);
        cache.put(n, p1 + p2);
        return p1 + p2;
    }
}
