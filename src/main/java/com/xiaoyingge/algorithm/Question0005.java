package com.xiaoyingge.algorithm;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 题解中需要使用到动态规划,暂时不懂,先这么解
 *
 * @author xiaoyinggee
 * Created on 2020-10-17
 */
public class Question0005 {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int length = s.toCharArray().length;
        String maxString = "";
        int max = 0;
        //查找单中点
        for (int i = 0; i < length; i++) {
            int left = i;
            int right = i;
            StringBuilder sb = new StringBuilder();
            while (left >= 0 && right <= length - 1 && chars[left] == chars[right]) {
                sb.append(chars[left]);
                if (left != right) {
                    sb.insert(0, chars[right]);
                }
                left--;
                right++;
            }
            if (sb.length() > max) {
                max = sb.length();
                maxString = sb.toString();
            }
        }
        //查找双中点
        for (int i = 0; i < length - 1; i++) {
            int left = i;
            int right = i + 1;
            StringBuilder sb = new StringBuilder();
            while (left >= 0 && right <= length - 1 && chars[left] == chars[right]) {
                sb.append(chars[left]);
                if (left != right) {
                    sb.insert(0, chars[right]);
                }
                left--;
                right++;
            }
            if (sb.length() > max) {
                max = sb.length();
                maxString = sb.toString();
            }
        }
        return maxString;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcdcba"));
    }
}
