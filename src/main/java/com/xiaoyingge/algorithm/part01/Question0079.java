package com.xiaoyingge.algorithm.part01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
        boolean abccseed = new Question0079().exist(chars, "abceseeefs");
        System.out.println(-2147483648);
        System.out.println(Integer.MIN_VALUE);
    }

}
