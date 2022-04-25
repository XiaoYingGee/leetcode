package com.xiaoyingge.offer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author XiaoYingGee
 * @date 2022/4/17 14:16
 */
public class Offer48 {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        char[] chars = s.toCharArray();
        HashSet<Character> set = new HashSet<>();
        int max = 0;
        int left = 0;
        int right = 0;
        while (right < chars.length) {
            if (set.contains(chars[right])) {
                while (chars[left++] != chars[right]) {

                }
                for (int i = 0; i < left; i++) {
                    set.remove(chars[i]);
                }
            }
            set.add(chars[right]);
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        char[] chars = s.toCharArray();

        Queue<Character> queue = new LinkedList<>();
        int max = 0;
        int right = 0;
        while (right < chars.length) {
            if (queue.contains(chars[right])) {
                while (queue.poll() != chars[right]) {
                }
            }
            queue.add(chars[right]);
            max = Math.max(max, queue.size());
            right++;
        }
        return max;
    }

    public static void main(String[] args) {
        int r = new Offer48().lengthOfLongestSubstring("tmmzuxt");
        System.out.println(r);
    }
}
