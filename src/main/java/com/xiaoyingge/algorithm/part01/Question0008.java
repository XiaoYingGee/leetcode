package com.xiaoyingge.algorithm.part01;

/**
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 * <p>
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 * <p>
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/string-to-integer-atoi 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author xiaoyinggee Created on 2020-10-18
 */
public class Question0008 {

    public static int myAtoi(String s) {
        if (s == null || s.trim().length() == 0) {
            return 0;
        }
        s = s.trim();
        char[] chars = s.toCharArray();
        if (chars[0] != '+' && chars[0] != '-' && (chars[0] > 57 || chars[0] < 48)) {
            return 0;
        }
        int index = 0;
        boolean lessThanZero = false;
        if (chars[0] == '+') {
            index = 1;
        } else if (chars[0] == '-') {
            index = 1;
            lessThanZero = true;
        }
        int result = 0;
        while (index < chars.length) {
            if (chars[index] >= 48 && chars[index] <= 57) {
                if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && chars[index] > 55)) {
                    return lessThanZero ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result = result * 10 + (chars[index] - 48);
            } else {
                break;
            }
            index++;
        }
        return lessThanZero ? 0 - result : result;
    }

    public static void main(String[] args) {

        System.out.println(myAtoi("-91283472332"));

    }
}
