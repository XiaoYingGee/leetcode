package com.xiaoyingge.algorithm.part01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author XiaoYingGee
 * @date 2022/4/28 22:14
 */
public class Question0049 {

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return Collections.emptyList();
        }
        List<Info> list = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            list.add(new Info(strs[i]));
        }
        list.sort((o1, o2) -> {
            if (o1.mark == o2.mark) {
                return o1.timeMark.compareTo(o2.timeMark);
            } else {
                return o1.mark - o2.mark;
            }
        });
        List<List<String>> result = new ArrayList<>();
        ArrayList<Info> pre = null;
        for (int i = 0; i < list.size(); i++) {
            Info cur = list.get(i);
            if (pre == null) {
                pre = new ArrayList<>();
                pre.add(cur);
            } else {
                if (cur.mark == pre.get(0).mark && cur.timeMark.equals(pre.get(0).timeMark)) {
                    pre.add(cur);
                } else {
                    result.add(pre.stream().map(info -> info.str).collect(Collectors.toList()));
                    pre = new ArrayList<>();
                    pre.add(cur);
                }
            }
        }
        if (pre != null) {
            result.add(pre.stream().map(info -> info.str).collect(Collectors.toList()));
        }
        return result;
    }

    public class Info {

        int mark;
        String timeMark;
        int[] times;
        String str;

        Info(String str) {
            this.str = str;
            this.times = new int[26];
            calc(str);
        }

        private void calc(String s) {
            this.mark = 0;
            if (s.length() > 0) {
                for (char c : s.toCharArray()) {
                    this.times[c - 'a']++;
                    this.mark |= (1 << (c - 'a'));
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 25; i++) {
                if (times[i] > 0) {
                    sb.append(times[i]).append(":");
                }
            }
            timeMark = sb.toString();
        }
    }

    public static void main(String[] args) {
        String[] strs = {"abbbbbbbbbbb", "aaaaaaaaaaab"};
        List<List<String>> lists = new Question0049().groupAnagrams(strs);

    }
}
