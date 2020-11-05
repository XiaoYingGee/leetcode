package com.xiaoyingge.algorithm.part1;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)
 * 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author xiaoyinggee
 * Created on 2020-10-30
 */
public class Question0011 {
    public static int maxArea(int[] arr) {
        List<Point> points = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            points.add(new Point(arr[i], i));
        }
        points.sort((a, b) -> b.value - a.value);
        int max = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            max = Math.max(max, Math.abs(points.get(i).index - points.get(i + 1).index) * Math
                    .min(points.get(i).value, points.get(i + 1).value));
        }
        return max;
    }

    public static class Point {
        private int index;
        private int value;

        public Point(int v, int i) {
            this.index = i;
            this.value = v;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 0, 0, 0, 0, 0, 0, 2, 2};
        System.out.println(maxArea(arr));
    }
}
