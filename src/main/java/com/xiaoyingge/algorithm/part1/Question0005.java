package com.xiaoyingge.algorithm.part1;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 题解中需要使用到动态规划,暂时不懂,先这么解
 *
 * @author xiaoyinggee Created on 2020-10-17
 */
public class Question0005 {

    public static String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcdcba"));
    }
}
