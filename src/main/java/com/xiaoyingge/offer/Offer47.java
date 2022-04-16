package com.xiaoyingge.offer;

import java.util.Random;

/**
 * @author XiaoYingGee
 * @date 2022/4/16 8:16
 */
public class Offer47 {

    public int maxValue(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int dp[][] = new int[grid.length + 1][grid[0].length + 1];
        for (int x = grid.length - 1; x >= 0; x--) {
            for (int y = grid[0].length - 1; y >= 0; y--) {
                dp[x][y] = grid[x][y] + Math.max(dp[x + 1][y], dp[x][y + 1]);
            }

        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(new Random().nextInt(5));
        }
    }
}
