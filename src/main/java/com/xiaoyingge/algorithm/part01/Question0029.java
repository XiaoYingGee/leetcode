package com.xiaoyingge.algorithm.part01;

/**
 * @author XiaoYingGee
 * @date 2022/4/17 20:18
 */
public class Question0029 {

    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == 1) {
            return Integer.MIN_VALUE;
        }
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        boolean flag = a >= 0 ^ b >= 0;
        if (a > 0) {
            a = -a;
        }
        if (b > 0) {
            b = -b;
        }
        int limit = Integer.MIN_VALUE >> 1;
        int ans = 0;
        while (a <= b) {
            int cur = b;
            int d = 1;
            while (cur > limit && cur + cur >= a) {
                cur <<= 1;
                d <<= 1;
            }
            a -= cur;
            ans += d;
        }
        return flag ? -ans : ans;
    }
}
