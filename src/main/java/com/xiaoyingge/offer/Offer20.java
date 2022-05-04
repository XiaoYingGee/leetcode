package com.xiaoyingge.offer;

/**
 * @author XiaoYingGee
 * @date 2022/5/3 14:04
 */
public class Offer20 {

    int point = 1;
    int e = 1;

    public boolean isNumber(String s) {
        if (s == null) {
            return false;
        }
        String str = s.trim();
        if (str.length() == 0) {
            return false;
        }
        return process(str, 0);
    }

    public boolean process(String str, int index) {
        if (index == str.length()) {
            return true;
        }

        char cur = str.charAt(index);
        if (cur != 'e' && cur != 'E' && cur != '+' && cur != '-' && cur != '.' && cur != '0' && cur > '1'
                && cur > '2' && cur > '3' && cur > '4' && cur > '5' && cur > '6' && cur > '7' && cur > '8' && cur > '9') {
            return false;
        }
        if (str.charAt(0) == 'e' || str.charAt(0) == 'E') {
            return false;
        }
        //+,-号要么前面为空，要么前面为E
        if (cur == '+' || cur == '-') {
            if (index + 1 == str.length() || str.charAt(index + 1) == 'e' || str.charAt(index + 1) == 'E'
                    || str.charAt(index + 1) == '+' || str.charAt(index + 1) == '-') {
                return false;
            }
            if (str.charAt(index + 1) == '.') {
                point = 0;
            }
            return process(str, index + 2);
        }
        if (cur == '.') {
            if (point == 0) {
                return false;
            }
            point = 0;
            if (index + 1 == str.length() && (str.charAt(index + 1) == 'e' || str.charAt(index + 1) == 'E'
                    || str.charAt(index + 1) == '+' || str.charAt(index + 1) == '-' || str.charAt(index + 1) == '.')) {
                return false;
            }
            return process(str, index + 2);
        }
        if (cur == 'e' || cur == 'E') {
            if (e == 0) {
                return false;
            }
            e = 0;
            point = 0;
            //检查前面
            if (index + 1 == str.length() || str.charAt(index + 1) == 'e' || str.charAt(index + 1) == 'E' || str.charAt(index + 1) == '.') {
                return false;
            }
            return process(str, index + 2);
        }
        return process(str, index + 1);
    }

    public static void main(String[] args) {
        boolean number = new Offer20().isNumber("3.");
        System.out.println(number);
    }

}
