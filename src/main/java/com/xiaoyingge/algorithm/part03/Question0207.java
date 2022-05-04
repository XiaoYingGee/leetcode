package com.xiaoyingge.algorithm.part03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author XiaoYingGee
 * @date 2022/4/30 10:42
 */
public class Question0207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true;
        }
        Graph graph = buildGraph(numCourses, prerequisites);
        graph.nodes.sort((n1, n2) -> n1.in - n2.in);
        if (graph.nodes.get(0).in > 0) {
            return false;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        for (Node node : graph.nodes) {
            if (node.in == 0) {
                queue.add(node);
                set.add(node);
                continue;
            }
            break;
        }
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            List<Edge> curEdges = cur.edges;
            for (Edge e : curEdges) {
                if (set.contains(e.out)) {
                    return false;
                }
                e.out.in--;
                if (e.out.in == 0) {
                    queue.add(e.out);
                    set.add(e.out);
                }
            }
        }
        return set.size() == numCourses;
    }

    public Graph buildGraph(int count, int[][] nums) {
        Map<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Node in;
            Node out;
            if (map.containsKey(nums[i][0])) {
                in = map.get(nums[i][0]);
            } else {
                in = new Node(nums[i][0]);
                map.put(nums[i][0], in);
            }
            if (map.containsKey(nums[i][1])) {
                out = map.get(nums[i][1]);
            } else {
                out = new Node(nums[i][1]);
                map.put(nums[i][1], out);
            }
            out.in++;
            Edge edge = new Edge(in, out);
            in.edges.add(edge);
        }
        for (int i = 0; i < count; i++) {
            if (!map.containsKey(i)) {
                map.put(i, new Node(i));
            }
        }
        return new Graph(new ArrayList<>(map.values()));
    }

    public class Graph {

        List<Node> nodes;

        Graph(List<Node> nodes) {
            this.nodes = nodes;
        }
    }

    public class Node {

        int val;
        List<Edge> edges;
        int in;

        Node(int val) {
            this.val = val;
            this.edges = new ArrayList<>();

        }
    }

    public class Edge {

        Node in;
        Node out;

        Edge(Node in, Node out) {
            this.in = in;
            this.out = out;
        }
    }

    public static void main(String[] args) {
        int[][] nums = {
                {1, 4}, {2, 4}, {3, 1}, {3, 2}
        };
        boolean b = new Question0207().canFinish(5, nums);
        System.out.println(b);
    }
}
