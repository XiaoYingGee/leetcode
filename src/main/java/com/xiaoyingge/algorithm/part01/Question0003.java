package com.xiaoyingge.algorithm.part01;

import java.util.HashSet;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 基本上能断定是用的滑动窗口
 *
 * @author xiaoyinggee Created on 2020-10-17
 */
public class Question0003 {

    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 1) {
            return 1;
        }
        char[] chars = s.toCharArray();
        HashSet<Character> set = new HashSet<>();
        int max = 0;
        int start = 0;
        int end = 0;
        while (end < chars.length) {
            if (!set.contains(chars[end])) {
                set.add(chars[end++]);
                max = Math.max(max, end - start);
            } else {
                set.remove(chars[start++]);
                max = Math.max(max, end - start);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abbcb"));
    }
}
