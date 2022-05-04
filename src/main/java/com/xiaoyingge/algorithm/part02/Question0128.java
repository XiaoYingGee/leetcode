package com.xiaoyingge.algorithm.part02;

import java.util.Stack;

/**
 * @author XiaoYingGee
 * @date 2022/4/26 23:45
 */
public class Question0128 {

    public int longestConsecutive(int[] nums) {
        int max = 0;
        int[][] infos = new int[nums.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            max = nums[max] < cur ? i : max;
            while (!stack.isEmpty() && nums[stack.peek()] >= cur) {
                int index = stack.pop();
                infos[index][0] = stack.isEmpty() ? -1 : stack.peek();
                infos[index][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            infos[index][0] = stack.isEmpty() ? -1 : stack.peek();
            infos[index][1] = -1;
        }

        int ans = 1;
        int maxAns = 1;
        while (infos[max][0] != -1 && infos[max][1] != -1) {
            int leftMin = infos[max][0];
            int rightMin = infos[max][1];

            if (nums[max] - nums[leftMin] == 1 || nums[max] - nums[rightMin] == 1) {
                ans++;
                maxAns = Math.max(maxAns, ans);
            } else {
                ans = 1;
            }

            if (nums[leftMin] > nums[rightMin]) {
                max = leftMin;
            } else {
                max = rightMin;
            }

        }
        return maxAns;
    }

    public static void main(String[] args) {
        int i = new Question0128().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
        System.out.println(i);
    }
}
