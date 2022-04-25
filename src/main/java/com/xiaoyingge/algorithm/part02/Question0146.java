package com.xiaoyingge.algorithm.part02;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiaoYingGee
 * @date 2022/3/30 0:05
 */
public class Question0146 {

    static class LRUCache {

        private int limit;
        private Map<Integer, Node> nodeMap;
        private Node head;
        private Node tail;

        public LRUCache(int capacity) {
            this.limit = capacity;
            this.nodeMap = new HashMap<>();
            this.head = new Node();
            this.tail = new Node();
            this.head.next = tail;
            this.tail.pre = head;
        }

        public int get(int key) {
            if (limit == 0) {
                return -1;
            }
            if (nodeMap.containsKey(key)) {
                updateNode(key, nodeMap.get(key).val);
                return nodeMap.get(key).val;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (limit == 0) {
                return;
            }
            //检查是否已存在，存在更新
            if (nodeMap.containsKey(key)) {
                updateNode(key, value);
            } else if (nodeMap.size() < limit) {
                addNode(key, value);
            } else {
                removeOld();
                addNode(key, value);
            }
        }

        private void updateNode(int key, int value) {
            Node cur = nodeMap.get(key);
            cur.val = value;
            Node pre = cur.pre;
            Node next = cur.next;
            pre.next = next;
            next.pre = pre;

            Node headNext = head.next;
            head.next = cur;
            cur.pre = head;
            cur.next = headNext;
            headNext.pre = cur;
        }

        private void addNode(int key, int value) {
            Node node = new Node(key, value);
            nodeMap.put(key, node);
            Node next = head.next;
            next.pre = node;
            node.next = next;
            head.next = node;
            node.pre = head;
        }

        private void removeOld() {
            if (limit == 0) {
                return;
            }
            if (nodeMap.isEmpty()) {
                return;
            }
            Node old = tail.pre;
            Node oldPre = old.pre;
            nodeMap.remove(old.key);
            old.pre = null;
            old.next = null;
            oldPre.next = tail;
            tail.pre = oldPre;
        }

    }

    private static class Node {

        private int key;
        private int val;
        private Node pre;
        private Node next;

        public Node() {
        }

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public String toString() {
            return this.key + " : " + this.val;
        }
    }

    public static void main(String[] args) {
        LRUCache obj = new LRUCache(2);
        obj.put(1, 11);
        obj.put(2, 22);
        System.out.println(obj.get(1));
        obj.put(3, 33);
        System.out.println(obj.get(2));
        obj.put(4, 44);
        System.out.println(obj.get(1));
        System.out.println(obj.get(3));
        System.out.println(obj.get(4));
    }
}
