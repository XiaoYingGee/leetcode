package com.xiaoyingge.algorithm.part01;

import com.xiaoyingge.util.NumUtil;

/**
 * @author XiaoYingGee
 * @date 2022/4/17 15:49
 */
public class Question0043 {

    public String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int length1 = num1.length();
        int length2 = num2.length();

        int[][] cache = generateCache();
        String result = "0";
        for (int i = length1 - 1; i >= 0; i--) {
            char a = num1.charAt(i);
            for (int j = length2 - 1; j >= 0; j--) {
                char b = num2.charAt(j);
                int multi = cache[a - '0'][b - '0'];
                result = plus(result, multi, length1 + length2 - i - j - 2);
            }
        }
        return result;
    }

    private String plus(String result, int multi, int power) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(multi));
        for (int i = 0; i < power; i++) {
            sb.append("0");
        }
        String plusString = sb.toString();
        int a = result.length() - 1;
        int b = plusString.length() - 1;
        String addResult = new String();
        int plus = 0;
        while (a >= 0 && b >= 0) {
            int plusVal = Integer.valueOf(result.charAt(a--) - '0') + Integer.valueOf(plusString.charAt(b--) - '0') + plus;
            addResult = plusVal % 10 + addResult;
            plus = plusVal > 9 ? 1 : 0;
        }
        while (b >= 0) {
            int plusVal = Integer.valueOf(plusString.charAt(b--) - '0') + plus;
            addResult = plusVal % 10 + addResult;
            plus = plusVal > 9 ? 1 : 0;
        }
        while (a >= 0) {
            int plusVal = Integer.valueOf(result.charAt(a--) - '0') + plus;
            addResult = plusVal % 10 + addResult;
            plus = plusVal > 9 ? 1 : 0;
        }
        if (plus == 1) {
            addResult = "1" + addResult;
        }
        return addResult;
    }

    private int[][] generateCache() {
        int[][] result = new int[10][10];
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                result[i][j] = i * j;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(true ^ true);
        System.out.println(false ^ false);
        System.out.println(true ^ false);
        System.out.println(Integer.MIN_VALUE);

        for (int i = 0; i < 100000; i++) {
            int num1 = NumUtil.random(10000);
            int num2 = NumUtil.random(100);
            String multi = String.valueOf(num1 * num2);
            String multiply = new Question0043().multiply(String.valueOf(num1), String.valueOf(num2));
            if (!multiply.equals(multi)) {
                System.out.println("ooooooooooooooooo" + num1 + "," + num2);
            }
        }

    }

}
