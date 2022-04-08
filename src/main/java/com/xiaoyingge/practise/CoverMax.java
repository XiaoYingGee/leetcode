package com.xiaoyingge.practise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 给定一组线段，返回线段的最大重合次数
 *
 * @author XiaoYingGee
 * @date 2022/3/22 21:49
 */
public class CoverMax {

    public static int maxCover1(int[][] lines) {
        if (lines == null || lines.length == 0) {
            return 0;
        }
        Line[] lineList = new Line[lines.length];
        for (int i = 0; i < lines.length; i++) {
            lineList[i] = new Line(lines[i][0], lines[i][1]);
        }
        Arrays.sort(lineList, Comparator.comparingInt(Line::getStart));
        int max = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (Line line : lineList) {
            priorityQueue.add(line.end);
            while (!priorityQueue.isEmpty() && priorityQueue.peek() <= line.getStart()) {
                priorityQueue.poll();
            }
            max = Math.max(max, priorityQueue.size());
        }
        return max;
    }

    @Data
    @AllArgsConstructor
    private static class Line {

        private int start;
        private int end;
    }

    // 和maxCover2过程是一样的
    // 只是代码更短
    // 不使用类定义的写法
    public static int maxCover3(int[][] m) {
        // m是二维数组，可以认为m内部是一个一个的一维数组
        // 每一个一维数组就是一个对象，也就是线段
        // 如下的code，就是根据每一个线段的开始位置排序
        // 比如, m = { {5,7}, {1,4}, {2,6} } 跑完如下的code之后变成：{ {1,4}, {2,6}, {5,7} }
        Arrays.sort(m, (a, b) -> (a[0] - b[0]));
        // 准备好小根堆，和课堂的说法一样
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int[] line : m) {
            while (!heap.isEmpty() && heap.peek() <= line[0]) {
                heap.poll();
            }
            heap.add(line[1]);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {

        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans3 = maxCover3(lines);
            if (ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }
}
