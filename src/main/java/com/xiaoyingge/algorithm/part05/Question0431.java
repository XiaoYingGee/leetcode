package com.xiaoyingge.algorithm.part05;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

/**
 * 将多叉树转为二叉树进行序列化
 *
 * @author XiaoYingGee
 * @date 2022/3/27 14:40
 */
public class Question0431 {

    // 提交时不要提交这个类
    public static class Node {

        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }

    // 提交时不要提交这个类
    public static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }

    // 将多叉树转为二叉树
    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode result = new TreeNode(root.val);
        processEncode(result, root.children);
        return result;
    }

    private void processEncode(TreeNode parent, List<Node> children) {
        if (parent == null || children == null || children.size() == 0) {
            return;
        }
        TreeNode current = null;
        for (Node child : children) {
            if (current == null) {
                current = new TreeNode(child.val);
                parent.left = current;
                processEncode(parent.left, child.children);
                continue;
            }
            current.right = new TreeNode(child.val);
            current = current.right;
            processEncode(current, child.children);
        }
    }

    // 将二叉树转为多叉树
    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        Node node = new Node(root.val);
        processDecode(node, root);
        return node;
    }

    private void processDecode(Node node, TreeNode root) {
        if (root == null) {
            return;
        }
        node.val = root.val;
        node.children = new ArrayList<>();
        TreeNode current = root.left;
        while (current != null) {
            Node currentNode = new Node(current.val);
            processDecode(currentNode, current);
            node.children.add(currentNode);
            current = current.right;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1, Lists.newArrayList(
                new Node(2),
                new Node(3, Lists.newArrayList(new Node(6, Lists.newArrayList(new Node(7))), new Node(8))),
                new Node(4, Lists.newArrayList(new Node(9))),
                new Node(5, Lists.newArrayList(new Node(10), new Node(11, Lists.newArrayList(new Node(13), new Node(14))), new Node(12)))
        ));
        TreeNode node = new Question0431().encode(root);
        System.out.println("===");
        Node result = new Question0431().decode(node);
        System.out.println("===========");
    }
}
