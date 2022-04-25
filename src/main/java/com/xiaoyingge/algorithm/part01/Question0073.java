package com.xiaoyingge.algorithm.part01;

/**
 * @author XiaoYingGee
 * @date 2022/4/17 21:14
 */
public class Question0073 {

    public void setZeroes(int[][] matrix) {
        int[] row = new int[matrix.length + 1];
        int[] col = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[i] == 1 || col[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void setZeroesBits(int[][] matrix) {
        int[] row = new int[(matrix.length + 31) >> 5];
        int[] col = new int[(matrix[0].length + 31) >> 5];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    row[i >> 5] |= 1 << (i & 31);
                    col[j >> 5] |= 1 << (i & 31);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if ((row[i >> 5] & (1 << (i & 31))) != 0 || (col[j >> 5] & (1 << (j & 31))) != 0) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                new int[]{1, 1, 1},
                new int[]{1, 0, 1},
                new int[]{1, 1, 1}
        };
        new Question0073().setZeroesBits(nums);
    }
}
