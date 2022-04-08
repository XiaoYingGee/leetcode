package com.xiaoyingge.algorithm;

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
 * @date 2022/3/26 21:55
 */
public class Question1462 {

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Boolean> result = new ArrayList<>();
        if (prerequisites.length == 0) {
            for (int i = 0; i < queries.length; i++) {
                result.add(false);
            }
            return result;
        }
        Graph graph = new Graph(numCourses, prerequisites);
        for (int[] query : queries) {
            result.add(queryAtoB(graph, query[0], query[1]));
        }
        return result;
    }

    private boolean queryAtoB(Graph graph, int a, int b) {
        Node endNode = graph.nodeMap.get(b);
        return endNode.preNodes.contains(a);
    }

    private class Graph {

        public Map<Integer, Node> nodeMap;

        public Graph(int numCourses, int[][] prerequisites) {
            this.nodeMap = new HashMap<>();
            for (int i = 0; i < numCourses; i++) {
                nodeMap.put(i, new Node(i));
            }
            for (int[] prerequisite : prerequisites) {
                Node start = this.nodeMap.get(prerequisite[0]);
                Node end = this.nodeMap.get(prerequisite[1]);
                //从end节点向前找，把所有的节点都给抓出来
                end.preNodes.add(start.id);
                end.preNodes.addAll(start.preNodes);
                start.nextNodes.add(end.id);
                //更新后面的结点
                Queue<Integer> queue = new LinkedList<>();
                queue.addAll(end.nextNodes);
                while (!queue.isEmpty()) {
                    Integer poll = queue.poll();
                    nodeMap.get(poll).preNodes.addAll(end.preNodes);
                    queue.addAll(nodeMap.get(poll).nextNodes);
                }
            }
        }
    }

    private class Node {

        public int id;
        //记录从后向前的指针
        public Set<Integer> preNodes;
        public Set<Integer> nextNodes;

        public Node(int id) {
            this.id = id;
            this.preNodes = new HashSet<>();
            this.nextNodes = new HashSet();
        }
    }

    public static void main(String[] args) {
        /**
         *5
         * [[3,4],[2,3],[1,2],[0,1]]
         * [[0,4],[4,0],[1,3],[3,0]]
         */
        new Question1462().checkIfPrerequisite(5, new int[][]{
                new int[]{3, 4},
                new int[]{2, 3},
                new int[]{1, 2},
                new int[]{0, 1},
        }, new int[][]{
                new int[]{0, 4},
                new int[]{4, 0},
                new int[]{1, 3},
                new int[]{3, 0}
        });
    }
}
