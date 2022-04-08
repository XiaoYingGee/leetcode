package com.xiaoyingge.algorithm.part08;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @author XiaoYingGee
 * @date 2022/3/17 0:43
 */
public class Question0720 {

    public String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        HashSet<String> set = new HashSet<>();
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(new StringComparator());
        for (String word : words) {
            priorityQueue.add(word);
            set.add(word);
        }

        while (!priorityQueue.isEmpty()) {
            String longest = priorityQueue.poll();
            int length = longest.length();
            boolean find = true;
            while (length > 0) {
                if (set.contains(longest.substring(0, length--))) {
                    continue;
                }
                find = false;
            }
            if (find) {
                return longest;
            }
        }
        return "";
    }

    private class StringComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return o2.length() - o1.length();
        }
    }

}
