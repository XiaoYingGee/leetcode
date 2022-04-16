package com.xiaoyingge.offer;

/**
 * @author XiaoYingGee
 * @date 2022/4/15 0:42
 */
public class Offer10 {

    public int fib(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }

        long[][] martix = new long[][]{new long[]{1, 1}, new long[]{1, 0}};
        long[] start = new long[]{1, 1};
        long[] result = multi(start, power(martix, n - 2));
        return (int) (result[0] % 1000000007L);
    }

    public long[] multi(long[] start, long[][] martix) {
        int length = martix.length;
        long[] result = new long[length];
        for (int i = 0; i < length; i++) {
            result[i] = 0;
            for (int j = 0; j < length; j++) {
                result[i] = (result[i] + start[j] * martix[j][i]) % 1000000007;
            }
        }
        return result;
    }

    public long[][] power(long[][] martix, int n) {
        long[][] result = new long[][]{
                new long[]{1, 0},
                new long[]{0, 1}
        };
        while (n > 0) {
            if ((n & 1) == 1) {
                result = multi(result, martix);
            }
            martix = multi(martix, martix);
            n >>= 1;

        }
        return result;
    }

    public long[][] multi(long[][] a, long[][] b) {
        int length = a.length;
        long[][] result = new long[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < length; k++) {
                    result[i][j] += (a[i][k] % 1000000007) * (b[k][j] % 1000000007);
                }
            }

        }
        return result;

    }

    public static void main(String[] args) {
        Offer10 offer10 = new Offer10();
        System.out.println(offer10.fib(0));
        System.out.println(offer10.fib(1));
        System.out.println(offer10.fib(2));
        System.out.println(offer10.fib(3));
        System.out.println(offer10.fib(4));
        System.out.println(offer10.fib(5));
        System.out.println(offer10.fib(6));
        System.out.println(offer10.fib(7));
        System.out.println(offer10.fib(50));
    }
}
