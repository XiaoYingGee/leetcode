package com.xiaoyingge.algorithm.part01;

/**
 * @author XiaoYingGee
 * @date 2022/4/25 23:24
 */
public class Question0048 {

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix.length == 1) {
            return;
        }
        int n = matrix.length;
        boolean[][] fixed = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (fixed[i][j]) {
                    continue;
                }
                int ax = i;
                int ay = j;
                int a = matrix[ax][ay];

                int bx = j;
                int by = n - i - 1;
                int b = matrix[bx][by];

                int cx = n - i - 1;
                int cy = n - j - 1;
                int c = matrix[cx][cy];

                int dx = n - j - 1;
                int dy = i;
                int d = matrix[dx][dy];
                write(matrix, a, bx, by, fixed);
                write(matrix, b, cx, cy, fixed);
                write(matrix, c, dx, dy, fixed);
                write(matrix, d, ax, ay, fixed);
            }
        }
    }

    public void write(int[][] matrix, int val, int x, int y, boolean[][] cache) {
        if (cache[x][y]) {
            return;
        }
        matrix[x][y] = val;
        cache[x][y] = true;

    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 9}
        };
        new Question0048().rotate(nums);
    }
}
