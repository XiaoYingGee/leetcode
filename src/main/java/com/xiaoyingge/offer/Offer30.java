package com.xiaoyingge.offer;

import java.util.Stack;

/**
 * @author XiaoYingGee
 * @date 2022/4/5 0:23
 */
public class Offer30 {

    class MinStack {

        Stack<Integer> normal;
        Stack<Integer> min;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            normal = new Stack<>();
            min = new Stack<>();
        }

        public void push(int x) {
            normal.push(x);
            if (min.isEmpty()) {
                min.push(x);
                return;
            }
            min.push(Math.min(min.peek(), x));
        }

        public void pop() {
            min.pop();
            normal.pop();
        }

        public int top() {
            return normal.peek();
        }

        public int min() {
            return min.peek();
        }
    }
}
