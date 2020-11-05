package com.xiaoyingge.algorithm.part1;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * @author xiaoyinggee
 * Created on 2020-10-18
 */
public class Question0006 {
    public static String convert(String s, int numRows) {
        //小于一行直接返回即可
        if (numRows <= 1 || s.length() <= numRows) {
            return s;
        }
        //两行以上,需要根据索引位置计算出对应的行数
        int range = (numRows - 1) << 1;
        //循环的轮次
        int round = s.length() / range + 1;
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < round; j++) {
                if (j * range + i < s.length()) {
                    sb.append(chars[j * range + i]);
                }
                //头尾都明确可以算出一个元素
                if (i != 0 && i != numRows - 1) {
                    if (j * range + numRows + numRows - i - 2 < s.length()) {
                        sb.append(chars[j * range + numRows + numRows - i - 2]);
                    }
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING", 3));
        System.out.println(convert("LEETCODEISHIRING", 4));
        System.out.println(convert("LEETCODEISHIRING", 3).equals("LCIRETOESIIGEDHN"));
        System.out.println(convert("LEETCODEISHIRING", 4).equals("LDREOEIIECIHNTSG"));
    }
}
