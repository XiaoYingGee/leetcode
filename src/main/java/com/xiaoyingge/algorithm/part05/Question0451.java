package com.xiaoyingge.algorithm.part05;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author XiaoYingGee
 * @date 2022/3/18 22:35
 */
public class Question0451 {

    /**
     * 常规排序
     *
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        Map<Character, Integer> count = new HashMap<>();
        for (char aChar : chars) {
            count.put(aChar, count.containsKey(aChar) ? count.get(aChar) + 1 : 1);
        }
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((o1, o2) -> o2.count - o1.count);
        for (Character character : count.keySet()) {
            priorityQueue.add(new Node(character, count.get(character)));
        }
        StringBuilder sb = new StringBuilder();
        while (!priorityQueue.isEmpty()) {
            Node poll = priorityQueue.poll();
            for (int i = 0; i < poll.count; i++) {
                sb.append(poll.c);
            }
        }
        return sb.toString();
    }

    public class Node {

        char c;
        int count;

        Node(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }

    /**
     * 桶排序实现
     *
     * @param s
     * @return
     */
    public String bucketSort(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        Map<Character, Integer> count = new HashMap<>();
        int max = 0;
        for (char aChar : chars) {
            int times = count.getOrDefault(aChar, 0) + 1;
            count.put(aChar, times);
            max = Math.max(max, times);
        }
        //得到最大的次数
        StringBuilder[] tire = new StringBuilder[max];
        for (Character character : count.keySet()) {
            Integer times = count.get(character);
            tire[times - 1] = (tire[times - 1] == null ? new StringBuilder(character + "") : tire[times - 1].append(character + ""));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = tire.length - 1; i >= 0; i--) {
            int times = i + 1;
            if (tire[i] == null) {
                continue;
            }
            char[] cc = tire[i].toString().toCharArray();
            for (char c : cc) {
                for (int j = 0; j < times; j++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Question0451().bucketSort("cccaaa"));
    }
}
