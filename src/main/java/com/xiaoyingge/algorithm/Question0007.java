package com.xiaoyingge.algorithm;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 主要需要注意反转后的越界问题
 *
 * @author xiaoyinggee
 * Created on 2020-10-18
 */
public class Question0007 {
    public static int reverse(int x) {
        int result = 0;
        boolean lessThanZero = x < 0;
        if (lessThanZero) {
            x = 0 - x;
        }
        while (x > 0) {
            int temp = x % 10;
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
                return 0;
            }
            result = result * 10 + temp;
            x = x / 10;
        }
        return lessThanZero ? 0 - result : result;
    }

    public static void main(String[] args) {
        System.out.println(reverse(100));
        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(1534236469));
    }
}
