package com.xiaoyingge.algorithm.part07;

import java.util.Stack;

/**
 * @author XiaoYingGee
 * @date 2022/5/3 17:31
 */
public class Question0678 {

    public boolean checkValidString(String s) {
        if (s.length() == 0 || "*".equals(s)) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        Stack<Character> star = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == '(') {
                stack.push(cur);
            } else if (cur == '*') {
                star.push(cur);
            } else {
                if (stack.isEmpty() && star.isEmpty()) {
                    return false;
                } else if (stack.isEmpty()) {
                    star.pop();
                } else {
                    stack.pop();
                }
            }
        }
        //跑完之后，可能还有剩余
        while (!stack.isEmpty() && !star.isEmpty()) {
            stack.pop();
            star.pop();
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean b = new Question0678().checkValidString(
                "((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()");
        System.out.println(b);
    }
}
