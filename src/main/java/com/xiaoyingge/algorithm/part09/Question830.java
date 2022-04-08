package com.xiaoyingge.algorithm.part09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author XiaoYingGee
 * @date 2022/3/18 22:22
 */
public class Question830 {

    public static void main(String[] args) {
        List<List<Integer>> l1 = new Question830().largeGroupPositions("abbxxxxzzy");
        List<List<Integer>> l2 = new Question830().largeGroupPositions("abc");
        List<List<Integer>> l3 = new Question830().largeGroupPositions("abcdddeeeeaabbbcd");
        List<List<Integer>> l4 = new Question830().largeGroupPositions("aba");
        System.out.println("===============");
    }

    public List<List<Integer>> largeGroupPositions(String s) {
        if (s == null || s.length() < 3) {
            return Collections.emptyList();
        }
        char[] chars = s.toCharArray();
        int start = 0;
        int end = 0;
        List<List<Integer>> result = new ArrayList<>();
        while (end < chars.length) {
            if (chars[start] == chars[end]) {
                end++;
                continue;
            }
            //这时不相等
            if (end - start >= 3) {
                List<Integer> answer = new ArrayList<>();
                answer.add(start);
                answer.add(end - 1);
                result.add(answer);
            }
            start = end;
            end++;
        }
        if (end - start >= 3) {
            List<Integer> answer = new ArrayList<>();
            answer.add(start);
            answer.add(end - 1);
            result.add(answer);
        }
        return result;
    }
}
