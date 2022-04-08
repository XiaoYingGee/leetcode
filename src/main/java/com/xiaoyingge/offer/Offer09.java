package com.xiaoyingge.offer;

import java.util.Stack;

/**
 * @author XiaoYingGee
 * @date 2022/4/5 0:10
 */
public class Offer09 {

    public class StackQueue {

        Stack<Integer> one = new Stack<>();
        Stack<Integer> two = new Stack<>();

        public StackQueue() {
            Stack<Integer> one = new Stack<>();
            Stack<Integer> two = new Stack<>();
        }

        public void appendTail(int value) {
            one.push(value);
        }

        public int deleteHead() {
            if (one.isEmpty()) {
                return -1;
            }
            while (!one.isEmpty()) {
                two.push(one.pop());
            }
            int result = two.pop();
            while (!two.isEmpty()) {
                one.push(two.pop());
            }
            return result;
        }
    }
}
