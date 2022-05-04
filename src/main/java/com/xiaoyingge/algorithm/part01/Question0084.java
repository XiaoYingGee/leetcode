package com.xiaoyingge.algorithm.part01;

import java.util.Stack;

/**
 * @author XiaoYingGee
 * @date 2022/4/27 23:38
 */
public class Question0084 {

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int length = heights.length;
        if (length == 1) {
            return heights[0];
        }

        int[][] infos = new int[length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            int cur = heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] >= cur) {
                int index = stack.pop();
                infos[index][0] = stack.isEmpty() ? -1 : stack.peek();
                infos[index][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            infos[index][0] = stack.isEmpty() ? -1 : stack.peek();
            infos[index][1] = length;
        }
        int ans = 0;
        for (int i = 0; i < length; i++) {
            int cur = heights[i];
            int left = infos[i][0];
            int right = infos[i][1];
            ans = Math.max(ans, (right - left - 1) * cur);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 6, 2, 3};
        new Question0084().largestRectangleArea(nums);
    }

}
