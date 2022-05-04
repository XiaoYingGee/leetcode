package com.xiaoyingge.algorithm.part10;

import java.util.Stack;

/**
 * @author XiaoYingGee
 * @date 2022/5/3 13:24
 */
public class Question0946 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) {
            return false;
        }
        if (pushed.length <= 1) {
            return true;
        }
        int length = popped.length;
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int i = 0; i < length; i++) {
            int curPop = popped[i];
            if (!stack.isEmpty() && stack.peek() == curPop) {
                stack.pop();
                continue;
            }
            while (index < length && pushed[index] != curPop) {
                stack.push(pushed[index]);
                index++;

            }
            index++;

        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        boolean b = new Question0946().validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 1, 2, 5});
        System.out.println(b);
    }
}
