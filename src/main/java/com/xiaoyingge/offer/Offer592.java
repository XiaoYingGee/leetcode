package com.xiaoyingge.offer;

import java.util.LinkedList;

/**
 * @author XiaoYingGee
 * @date 2022/5/4 1:02
 */
public class Offer592 {

    static class MaxQueue {

        long index;
        int size;
        LinkedList<Node> data;
        LinkedList<Node> max;

        private class Node {

            long index;
            int val;

            Node(long index, int val) {
                this.index = index;
                this.val = val;
            }
        }

        public MaxQueue() {
            index = 0;
            size = 0;
            data = new LinkedList<>();
            max = new LinkedList<>();
        }

        public int max_value() {
            if (size == 0) {
                return -1;
            }
            return max.getFirst().val;
        }

        public void push_back(int value) {
            size++;
            Node node = new Node(index++, value);
            data.addLast(node);
            while (!max.isEmpty() && max.getLast().val <= value) {
                max.pollLast();
            }
            max.addLast(node);

        }

        public int pop_front() {
            if (size == 0) {
                return -1;
            }
            size--;
            Node pop = data.pop();
            while (!max.isEmpty() && max.getFirst().index <= pop.index) {
                max.pop();
            }
            return pop.val;
        }
    }

    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        maxQueue.push_back(1);
        maxQueue.push_back(2);
        System.out.println(maxQueue.max_value());
        maxQueue.push_back(1);
        maxQueue.push_back(1);
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.max_value());
        maxQueue.push_back(3);
        System.out.println(maxQueue.max_value());
    }
}
