package com.xiaoyingge.algorithm.part05;

/**
 * @author XiaoYingGee
 * @date 2022/5/1 10:34
 */
public class Question0426 {

    static class Node {

        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }

        @Override
        public String toString() {
            return "" + this.val;
        }
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Info info = process(root);
        info.pre.left = info.next;
        info.next.right = info.pre;
        return info.pre;
    }

    public Info process(Node node) {
        if (node == null) {
            return new Info(null, null);
        }
        Info left = process(node.left);
        Info right = process(node.right);

        if (left.next != null) {
            left.next.right = node;
            node.left = left.next;
        }

        if (right.pre != null) {
            right.pre.left = node;
            node.right = right.pre;
        }

        Node pre = left.pre == null ? node : left.pre;
        Node next = right.next == null ? node : right.next;
        return new Info(pre, next);

    }

    public class Info {

        Node pre;
        Node next;

        Info(Node pre, Node next) {
            this.pre = pre;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(4, new Node(2, new Node(1), new Node(3)), new Node(5));
        Node node1 = new Question0426().treeToDoublyList(node);
        System.out.println(node1);
    }
}
