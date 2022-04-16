package com.xiaoyingge.offer;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * @author XiaoYingGee
 * @date 2022/4/12 22:30
 */
public class Offer50 {

    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }
        if (s.length() == 1) {
            return s.toCharArray()[0];
        }
        Count count = new Count();
        for (char c : s.toCharArray()) {
            count.mark(c);
        }
        return count.get();
    }

    public class Count {

        HashSet<Character> show = new HashSet<>();
        LinkedHashSet<Character> ans = new LinkedHashSet<>();

        public void mark(char c) {
            //不为空则把对应的位置废弃
            if (show.contains(c)) {
                ans.remove(c);
            } else {
                show.add(c);
                ans.add(c);
            }
        }

        public char get() {
            return ans.isEmpty() ? ' ' : ans.iterator().next();
        }

    }

}
