package com.xiaoyingge.algorithm.part01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author XiaoYingGee
 * @date 2022/4/12 23:35
 */
public class Question0079 {

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0
                || word == null || word.length() == 0) {
            return false;
        }
        char[] chars = word.toCharArray();
        HashSet<Character> set = new HashSet<>();
        List<From> froms = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == chars[0]) {
                    froms.add(new From(i, j));
                }
                set.add(board[i][j]);
            }
        }
        for (char aChar : chars) {
            if (!set.contains(aChar)) {
                return false;
            }
        }
        for (From from : froms) {
            boolean[][] cache = new boolean[board.length][board[0].length];
            boolean find = process(board, cache, chars, from.x, from.y, 0);
            if (find) {
                return true;
            }
        }
        return false;
    }

    private class From {

        private int x;
        private int y;

        From(int a, int b) {
            x = a;
            y = b;
        }
    }

    private boolean process(char[][] board, boolean[][] cache, char[] chars, int i, int j, int index) {

        if (board[i][j] != chars[index]) {
            return false;
        }
        cache[i][j] = true;
        if (index == chars.length - 1) {
            return true;
        }
        if (i - 1 >= 0 && !cache[i - 1][j] && process(board, cache, chars, i - 1, j, index + 1)) {
            return true;
        }
        if (i + 1 < board.length && !cache[i + 1][j] && process(board, cache, chars, i + 1, j, index + 1)) {
            return true;
        }
        if (j - 1 >= 0 && !cache[i][j - 1] && process(board, cache, chars, i, j - 1, index + 1)) {
            return true;
        }
        if (j + 1 < board[0].length && !cache[i][j + 1] && process(board, cache, chars, i, j + 1, index + 1)) {
            return true;
        }

        cache[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        char[][] chars = new char[][]{
                new char[]{'a', 'b', 'c', 'e'},
                new char[]{'s', 'f', 'e', 's'},
                new char[]{'a', 'd', 'e', 'e'}
        };
        boolean abccseed = new Question0079().exist2(chars, "abceseeefs");
        System.out.println(abccseed);

    }

    public boolean exist2(char[][] board, String word) {
        if (word == null || board == null || board.length == 0 || board[0].length == 0 || word.length() > board.length * board[0].length) {
            return false;
        }

        int rowLength = board.length;
        int colLength = board[0].length;
        Map<Character, List<Node>> map = new HashMap<>();
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                char c = board[i][j];
                Node node = new Node(i, j);
                if (map.containsKey(c)) {
                    List<Node> nodes = map.get(c);
                    nodes.add(node);
                    map.put(c, nodes);
                } else {
                    List<Node> list = new ArrayList<>();
                    list.add(node);
                    map.put(c, list);
                }
            }
        }
        boolean[][] cache = new boolean[rowLength][colLength];
        return process(map, word, 0, cache, null);
    }

    public boolean process(Map<Character, List<Node>> map, String word, int index, boolean[][] cache,
            Node pre) {
        if (index == word.length()) {
            return true;
        }
        char cur = word.charAt(index);
        List<Node> list = map.get(cur);
        if (list == null) {
            return false;
        }
        for (Node node : list) {
            if (!isConnected(node, pre)) {
                continue;
            }
            cache[node.x][node.y] = true;

            List<Node> copy = copyAndRemove(list, node);
            map.put(cur, copy);
            boolean result = process(map, word, index + 1, cache, node);
            if (result) {
                return true;
            }
            map.put(cur, list);
            cache[node.x][node.y] = false;
        }
        return false;

    }

    public List<Node> copyAndRemove(List<Node> list, Node remove) {
        List<Node> newList = new ArrayList();
        for (Node node : list) {
            if (node == remove) {
                continue;
            }
            newList.add(node);
        }
        return newList;
    }

    public boolean isConnected(Node node, Node pre) {
        if (pre == null) {
            return true;
        }
        if (Math.abs(node.x - pre.x) == 1 && node.y == pre.y) {
            return true;
        }
        return Math.abs(node.y - pre.y) == 1 && node.x == pre.x;
    }

    public class Node {

        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
