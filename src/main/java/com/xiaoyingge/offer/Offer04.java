package com.xiaoyingge.offer;

/**
 * @author XiaoYingGee
 * @date 2022/4/12 21:17
 */
public class Offer04 {

    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int min = matrix[0][0];
        int max = matrix[row - 1][col - 1];
        if (target < min || target > max) {
            return false;
        }
        if (target == min || target == max) {
            return true;
        }
        //如果只有一列
        if (col == 1 && row == 1) {
            return min == target;
        }
        for (int i = 0; i < row; ) {
            for (int j = 0; j < col; ) {
                int cur = matrix[i][j];
                if (cur == target) {
                    return true;
                }
                int left = Integer.MAX_VALUE;
                int right = Integer.MAX_VALUE;
                if (i + 1 < row) {
                    right = matrix[i + 1][j];
                }
                if (j + 1 < col) {
                    left = matrix[i][j + 1];
                }
                if (left == target && right == target) {
                    return true;
                } else if (left > target && right > target) {
                    return false;
                } else if (right > target && left < target) {
                    j++;
                } else if (right < target && left > target) {
                    i++;
                } else {
                    i++;
                }
            }
        }
        return false;

    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int min = matrix[0][0];
        int max = matrix[row - 1][col - 1];
        if (target < min || target > max) {
            return false;
        }
        if (target == min || target == max) {
            return true;
        }
        //如果只有一列
        if (col == 1 && row == 1) {
            return min == target;
        }
        //只有一行，对横向二分
        if (row == 1) {
            return binarySearch(matrix[0], target, 0, col - 1);
        }
        //只有一列，竖向二分
        if (col == 1) {
            return binarySearch(changeToArray(matrix), target, 0, row - 1);
        }
        for (int i = 0; i < matrix.length; i++) {
            int[] rows = matrix[i];
            if (rows[col - 1] == target || rows[0] == target) {
                return true;
            }
            if (rows[0] > target || rows[col - 1] < target) {
                continue;
            }
            //可能在里面，二分
            boolean find = binarySearch(rows, target, 0, col - 1);
            if (find) {
                return true;
            }
        }
        return false;
    }

    private int[] changeToArray(int[][] matrix) {
        int[] result = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            result[i] = matrix[i][0];
        }
        return result;
    }

    private boolean binarySearch(int[] rows, int target, int left, int right) {
        if (left == right) {
            return target == rows[left];
        }
        int mid = left + ((right - left) >> 1);
        if (target == rows[mid]) {
            return true;
        } else if (target > rows[mid]) {
            return binarySearch(rows, target, mid + 1, right);
        } else {
            return binarySearch(rows, target, left, mid);
        }
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                new int[]{3, 5, 9, 9, 14},
                new int[]{7, 8, 11, 15, 15},
                new int[]{8, 10, 16, 16, 17}
        };
        System.out.println(new Offer04().findNumberIn2DArray2(nums, 11));
    }
}
