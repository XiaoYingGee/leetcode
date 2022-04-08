package com.xiaoyingge.algorithm.part01;

import java.util.HashMap;
import java.util.Map;

/**
 * 解析罗马数字
 *
 * @author xiaoyinggee Created on 2020-10-29
 */
public class Question0013 {

    public static int romanToInt(String s) {
        //其实从题目的限制范围来说,只需要使用MAP映射就可以完成,但边界与异常输入控制的并不完美
        Map<Integer, Integer> map = new HashMap<>();
        map.put(73, 1);
        map.put(86, 5);
        map.put(88, 10);
        map.put(76, 50);
        map.put(67, 100);
        map.put(68, 500);
        map.put(77, 1000);

        if (s == null || "".equals(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            int currentChar = chars[i];
            int nextChar = 0;
            boolean hasNext = false;
            if (i + 1 < chars.length) {
                nextChar = chars[i + 1];
                hasNext = true;
            }
            if (hasNext && map.get(currentChar) < map.get(nextChar)) {
                i++;
                result += map.get(nextChar) - map.get(currentChar);
            } else {
                result += map.get(currentChar);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));

    }
}
