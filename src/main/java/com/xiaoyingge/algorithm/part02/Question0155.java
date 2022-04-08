package com.xiaoyingge.algorithm.part02;

import java.util.Stack;

/**
 * @author XiaoYingGee
 * @date 2022/3/15 0:00
 */
public class Question0155 {

    static class MinStack {

        Stack<Integer> normal;
        Stack<Integer> min;

        public MinStack() {
            normal = new Stack();
            min = new Stack<>();
        }

        public void push(int val) {
            normal.push(val);
            if (min.size() == 0) {
                min.push(val);
            } else if (val <= min.peek()) {
                min.push(val);
            }
        }

        public void pop() {
            int pop = normal.pop().intValue();
            if (min.peek() == pop) {
                min.pop();
            }
        }

        public int top() {
            return normal.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(512);
        stack.push(-1024);
        stack.push(-1024);
        stack.push(512);
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
    }
}
