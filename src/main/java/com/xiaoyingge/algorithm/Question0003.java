package com.xiaoyingge.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 基本上能断定是用的滑动窗口
 *
 * @author xiaoyinggee
 * Created on 2020-10-17
 */
public class Question0003 {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int start = 0;
        int max = 1;
        Set<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        set.add(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            char currentChar = chars[i];
            if (set.contains(currentChar)) {
                //包含的情况下,左边界需要右移,移动到当前重复的当前元素右侧
                while (chars[start] != currentChar) {
                    set.remove(chars[start]);
                    start++;
                }
                //跳出循环意味着当前指向的就是重复的元素,此时不必删除不必添加,SET中保留这个元素即可,将位置进行偏移
                start++;
                //并且需要重置当前的长度
            } else {
                //不包含的情况下,MAX进行重算,同时向SET里添加元素
                max = Math.max(max, i - start + 1);
                set.add(currentChar);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("bbb"));
    }
}
