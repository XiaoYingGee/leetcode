package com.xiaoyingge.practise;

/**
 * 1.微软题 2.全是母牛，母牛不会死 3.第一年只有一头，且成熟的母牛每年生一只小牛 4.生出的小牛在第三年成熟
 *
 * @author XiaoYingGee
 * @date 2022/4/10 13:01
 */
public class CowInYear {

    public static void main(String[] args) {
        System.out.println(new CowInYear().calc(4));
    }

    /**
     * <p>年份：1  2  3   4    5     6 </p>
     * <p>成熟：A  A  A   A    AB    ABC</p>
     * <p>不熟：   B  BC  BCD  CDEF  DEFGHI  </p>
     *
     * @param n
     * @return
     */
    public int calc(int n) {
        if (n <= 0) {
            return -1;
        }
        if (n <= 3) {
            return 1;
        }
        //推导公式为 f(n) = f(n-1) +f(n-3)
        //             | a  b  c|
        //     (x,y,z)*| d  e  f|
        //             | g  h  i|
        //    4,3,2 = 3,2,1 * 矩阵
        //    6,4,3 = 4,3,2 * 矩阵
        //    9,6,4 = 6,4,3 * 矩阵
        //  a=1  b=1  c=0
        //  d=0  e=0  f=1
        //  g=1  e=0  i=0
        return f(n);
    }

    public int f(int n) {
        int[][] matrix = new int[][]{
                {1, 1, 0},
                {0, 0, 1},
                {1, 0, 0}
        };
        int[] f_321 = new int[]{3, 2, 1};
        //fn fn-1 fn-2 = f3 f2 f1 * (矩阵)^(n-3)
        int[] result = calc(f_321, power(matrix, n - 3));
        return result[0];
    }

    /**
     * 计算矩阵相乘
     *
     * @param array
     * @param matrix
     * @return
     */
    private int[] calc(int[] array, int[][] matrix) {
        if (array.length != matrix.length) {
            return null;
        }
        int[] result = new int[array.length];
        for (int col = 0; col < array.length; col++) {
            int ans = 0;
            for (int row = 0; row < array.length; row++) {
                ans += array[row] * matrix[col][row];
            }
            result[col] = ans;
        }
        return result;
    }

    private int[][] power(int[][] matrix, int n) {
        int[][] result = new int[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        while (n > 0) {
            //如果二进位有一，结果乘进去
            if ((n & 1) == 1) {
                result = multiplication(result, matrix);
            }
            //matrix二次方
            matrix = multiplication(matrix, matrix);
            n >>= 1;
        }
        return result;
    }

    private int[][] multiplication(int[][] a, int[][] b) {
        int[][] result = new int[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                for (int k = 0; k < a.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }
}
