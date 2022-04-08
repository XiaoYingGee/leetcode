package com.xiaoyingge.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * @author XiaoYingGee
 * @date 2022/3/19 0:49
 */
public class View0809 {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            List<String> list = new View0809().generateParenthesis(i);
            System.out.println(StringUtils.join(list, ","));

        }
    }

    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return Collections.emptyList();
        }
        int index = 1;
        HashSet<String> set = new HashSet<>();
        set.add("()");
        while (index++ < n) {
            HashSet<String> newSet = new HashSet<>();
            for (String s : set) {
                int i = 0;
                while (i++ < s.length()) {
                    newSet.add(s.substring(0, i) + "()" + s.substring(i));
                }
            }
            set = newSet;
        }
        return new ArrayList<>(set);

    }
}
