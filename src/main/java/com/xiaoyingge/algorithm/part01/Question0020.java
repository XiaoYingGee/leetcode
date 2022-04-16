package com.xiaoyingge.algorithm.part01;

import java.util.Stack;

/**
 * @author XiaoYingGee
 * @date 2022/4/11 23:24
 */
public class Question0020 {

    public boolean isValid(String s) {
        if (s == null || (s.length() & 1) == 1) {
            return false;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            if (c == ')' && !stack.pop().equals('(')) {
                return false;
            }
            if (c == '}' && stack.pop() != '{') {
                return false;
            }

            if (c == ']' && stack.pop() != '[') {
                return false;
            }

        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Question0020().isValid("()"));
    }

}
