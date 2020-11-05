package com.xiaoyingge.algorithm.part1;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author xiaoyinggee
 * Created on 2020-10-29
 */
public class Question0014 {

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Node[] nodes = new Node[26];
        Node head = new Node("", 0, nodes);
        Node run;
        for (String str : strs) {
            run = head;
            run.access++;
            char[] chars = str.toCharArray();
            for (char aChar : chars) {
                Node child = run.nodes[aChar - 97];
                if (child == null) {
                    Node[] childs = new Node[26];
                    child = new Node(String.valueOf(aChar), 0, childs);
                    run.nodes[aChar - 97] = child;
                }
                child.access++;
                run = child;
            }
        }
        StringBuilder sb = new StringBuilder();
        findResult(strs.length, head, sb);
        System.out.println(sb.toString());
        return sb.toString();
    }

    private static void findResult(int max, Node node, StringBuilder sb) {
        for (Node current : node.nodes) {
            if (current != null && current.access == max) {
                sb.append(current.s);
                findResult(max, current, sb);
            }
        }
    }


    public static class Node {
        public String s;
        public int access;
        public Node[] nodes;

        public Node(String s, int access, Node[] nodes) {
            this.s = s;
            this.access = access;
            this.nodes = nodes;
        }
    }

    public static void main(String[] args) {
        String[] strs = new String[] {"flower", "flow", "flight"};
        longestCommonPrefix(strs);
    }
}
