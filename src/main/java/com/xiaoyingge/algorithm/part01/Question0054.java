package com.xiaoyingge.algorithm.part01;

import com.xiaoyingge.util.PrintUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author XiaoYingGee
 * @date 2022/4/12 23:00
 */
public class Question0054 {

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 1) {
            for (int i = 0; i < matrix[0].length; i++) {
                result.add(matrix[0][i]);
            }
            return result;
        }
        if (matrix[0].length == 1) {
            for (int i = 0; i < matrix.length; i++) {
                result.add(matrix[i][0]);
            }
            return result;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int tRow = 0;
        int bRow = row - 1;
        int lCol = 0;
        int rCol = col - 1;
        while (true) {
            for (int i = lCol; i <= rCol; i++) {
                result.add(matrix[tRow][i]);
            }
            if (tRow == bRow) {
                break;
            }
            tRow++;
            for (int i = tRow; i <= bRow; i++) {
                result.add(matrix[i][rCol]);
            }
            if (lCol == rCol) {
                break;
            }
            rCol--;
            for (int i = rCol; i >= lCol; i--) {
                result.add(matrix[bRow][i]);
            }
            if (tRow == bRow) {
                break;
            }
            bRow--;
            for (int i = bRow; i >= tRow; i--) {
                result.add(matrix[i][lCol]);
            }
            if (lCol == rCol) {
                break;
            }
            lCol++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                new int[]{1, 2, 3, 4, 5},
                new int[]{6, 7, 8, 9, 10},
                new int[]{11, 12, 13, 14, 15},
                new int[]{16, 17, 18, 19, 20},
                new int[]{21, 22, 23, 24, 25}
        };
        List<Integer> integers = new Question0054().spiralOrder(nums);
        PrintUtil.print(integers);
    }
}
