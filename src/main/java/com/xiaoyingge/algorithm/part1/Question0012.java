package com.xiaoyingge.algorithm.part1;

import java.util.LinkedHashMap;

/**
 * 整数转罗马数字
 *
 * @author xiaoyinggee
 * Created on 2020-10-30
 */
public class Question0012 {
    public static String intToRoman(int num) {
        //其实从题目的限制范围来说,只需要使用MAP映射就可以完成,但边界与异常输入控制的并不完美
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
        if (map.containsKey(num)) {
            return map.get(num);
        }
        StringBuilder sb = new StringBuilder();
        for (Integer integer : map.keySet()) {
            if (num < integer) {
                continue;
            }
            int print = num / integer;
            if (print > 0) {
                for (int i = 0; i < print; i++) {
                    sb.append(map.get(integer));
                }
            }
            num = num - print * integer;
            if (num == 0) {
                return sb.toString();
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(58));

    }
}
