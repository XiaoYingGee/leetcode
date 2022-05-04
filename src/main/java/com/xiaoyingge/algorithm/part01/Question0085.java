package com.xiaoyingge.algorithm.part01;

import java.util.Stack;

/**
 * @author XiaoYingGee
 * @date 2022/4/26 0:42
 */
public class Question0085 {

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int height = matrix.length;
        int[] help = new int[matrix[0].length];
        int max = 0;
        for (int i = 0; i < height; i++) {
            help = mergeArray(help, matrix[i]);
            max = Math.max(max, getMax(help));
        }
        return max;
    }

    public int[] mergeArray(int[] a, char[] b) {
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = b[i] == '0' ? 0 : a[i] + b[i] - '0';
        }
        return result;
    }

    public int getMax(int[] nums) {
        int[][] infos = new int[nums.length][2];
        Stack<Info> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            while (!stack.isEmpty() && stack.peek().val >= cur) {
                Info info = stack.pop();
                infos[info.index][0] = stack.isEmpty() ? -1 : stack.peek().index;
                infos[info.index][1] = i;
            }
            stack.push(new Info(cur, i));
        }
        while (!stack.isEmpty()) {
            Info info = stack.pop();
            infos[info.index][0] = stack.isEmpty() ? -1 : stack.peek().index;
            infos[info.index][1] = -1;
        }
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int left = infos[i][0];
            int right = infos[i][1] == -1 ? nums.length : infos[i][1];
            max = Math.max(max, cur * (right - left - 1));
        }
        return max;
    }

    public class Info {

        public int val;
        public int index;

        Info(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        char[][] chars = new char[][]{
                new char[]{'1', '0', '1', '0', '0'},
                new char[]{'1', '0', '1', '1', '1'},
                new char[]{'1', '1', '1', '1', '1'},
                new char[]{'1', '0', '0', '1', '0'},
        };
        int i = new Question0085().maximalRectangle(chars);
        System.out.println(i);
    }
}
