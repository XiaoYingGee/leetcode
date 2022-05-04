package com.xiaoyingge.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaoYingGee
 * @date 2022/4/30 11:17
 */
public class Offer66 {

    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0) {
            return new int[0];
        }
        if (a.length == 1) {
            return new int[]{0};
        }
        int ans = 1;
        List<Integer> index0 = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                index0.add(i);
            } else {
                ans *= a[i];
            }
        }
        int[] b = new int[a.length];
        if (index0.size() == 1) {
            b[index0.get(0)] = ans;
        } else if (index0.size() == 0) {
            for (int i = 0; i < a.length; i++) {
                b[i] = div(ans, a[i]);
            }
        }
        return b;
    }

    public int div(int a, int b) {
        if (a == b) {
            return 1;
        }
        if (b == 1) {
            return a;
        }
        int ans = 0;

        int sign = a > 0 == b > 0 ? 1 : -1;
        a = a < 0 ? -a : a;
        b = b < 0 ? -b : b;
        while (a >= b) {
            int bit = 0;
            int curB = b;
            while (a >= curB) {
                curB = b << (++bit);
            }
            a -= b << (--bit);
            ans += 1 << bit;
        }

        return sign < 0 ? -ans : ans;
    }

    public int[] constructArr2(int[] a) {
        if (a == null || a.length == 0) {
            return new int[0];
        }
        if (a.length == 1) {
            return new int[]{0};
        }
        int[] left = new int[a.length];
        int[] right = new int[a.length];
        left[0] = 1;
        right[a.length - 1] = 1;
        for (int i = 1; i < a.length; i++) {
            left[i] = left[i - 1] * a[i - 1];
        }
        for (int i = a.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i + 1];
        }
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = left[i] * right[i];
        }
        return b;
    }

    public static void main(String[] args) {
        new Offer66().constructArr2(new int[]{7, 2, 2, 4, 2, 1, 8, 8, 9, 6, 8, 9, 6, 3, 2, 1});
    }
}
