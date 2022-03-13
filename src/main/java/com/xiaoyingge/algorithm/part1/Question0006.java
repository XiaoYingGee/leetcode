package com.xiaoyingge.algorithm.part1;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p> k=2时 </p>
 * <p>              第一行的字为 1 3 5 7 等差=2</p>         公式为 index = (k-1)*2;
 * <p>              第二行的字为 2 4 6 8 等差=2</p>
 * <p> k=3时 </p>
 * <p>              第一行的字为 1 5 9 13 等差=4</p>        公式为 index = (k-1)*2;
 * <p>              第二行的字为 2 4 6 8 等差=2</p>  第二行提取公式为 index = (k-1)*2 ;
 * <p>              第三行的字为 3 7 11 15 等差=4</p>
 * <p> k=4时 </p>
 * <p>              第一行的字为 1 7 13 19 等差=6</p>       公式为 index = (k-1)*2;
 * <p>              第二行的字为 2 6 8 12 等差=4和2</p>
 * <p>              第三行的字为 3 5 9 13 等差=4和2</p>
 * <p>              第四行的字为 4 10 16 22 等差=6</p>
 * <p> k=5 </p>
 * <p>              第一行的字为 1 9 17  等差=8</p>         公式为 index = (k-1)*2*i;
 * <p>              第二行的字为 2 8 10 16 18等差=6和2</p>  公式为 index = (k-1)*2*i + line 和 (k-1)*2*i + 2(k -line ) +1
 * <p>              第三行的字为 3 7 11 15 19等差=4</p> 公式为 index = (k-1)*2*i + line 和 (k-1)*2*i + 2(k -line ) +1
 * <p>              第四行的字为 4 6 12 14 20等差=6和2</p>
 * <p>              第五行的字为 5 13 21  等差=8</p>
 * <p>              第五行的字为 5 13 21  等差=8</p>
 *
 * <p>  将数据以（k-1）*2分组，内部再划分为k 与k-2两部分</p>
 * <p>  前半部分的值计算为   2*i*(k-1) + line </p>
 * <p>  前半部分的值计算为   2*(i+1)*(k-1) - line </p>
 *
 * @author xiaoyinggee Created on 2020-10-18
 */
public class Question0006 {

    public static String convert(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        //从第一行遍历到最后一行
        for (int line = 0; line < numRows; line++) {
            int group = 0;
            while ((numRows - 1) * 2 * group <= chars.length) {
                //如果左半部分有值，加入，去看有没有右侧
                if (2 * group * (numRows - 1) + line < chars.length) {
                    sb.append(chars[2 * group * (numRows - 1) + line]);
                } else {
                    break;
                }
                if (2 * (group + 1) * (numRows - 1) - line < chars.length) {
                    //非首尾行需要加上右半部分
                    if (line != 0 && line != numRows - 1) {
                        sb.append(chars[2 * (group + 1) * (numRows - 1) - line]);
                    }
                } else {
                    break;
                }
                group++;
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
