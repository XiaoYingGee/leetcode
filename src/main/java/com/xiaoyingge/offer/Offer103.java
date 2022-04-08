package com.xiaoyingge.offer;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 你可以认为每种硬币的数量是无限的
 *
 * @author XiaoYingGee
 * @date 2022/3/26 22:54
 */
public class Offer103 {

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        int result1 = process(coins, 0, amount);
        System.out.println(result1);
        int result2 = process2(coins, amount);
        System.out.println(result2);
        return result1;
    }

    public int process(int[] coins, int index, int rest) {
        //即从右向左跑完了
        if (index == coins.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        //从最大的面值，最大的硬币数开始尝试
        for (int num = 0; num * coins[index] <= rest; num++) {
            //这是剩余的面额
            int next = process(coins, index + 1, rest - coins[index] * num);
            //说明是个无效解
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(next + num, ans);
            }
        }
        return ans;
    }

    public int process2(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 1; i < dp[0].length; i++) {
            dp[coins.length][i] = Integer.MAX_VALUE;
        }
        for (int row = coins.length - 1; row >= 0; row--) {
            for (int col = 0; col <= amount; col++) {
//                int ans = Integer.MAX_VALUE;
//                //从最大的面值，最大的硬币数开始尝试
//                for (int num = 0; num * coins[row] <= col; num++) {
//                    //这是剩余的面额
//                    int next = dp[row + 1][col - coins[row] * num];
//                    //说明是个无效解
//                    if (next != Integer.MAX_VALUE) {
//                        ans = Math.min(next + num, ans);
//                    }
//                }
                dp[row][col] = dp[row + 1][col];
                if (col - coins[row] >= 0 && dp[row][col - coins[row]] != Integer.MAX_VALUE) {
                    dp[row][col] = Math.min(dp[row + 1][col], dp[row][col - coins[row]] + 1);
                }
            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }

    public static void main(String[] args) {
        System.out.println(new Offer103().coinChange(new int[]{1, 2, 5}, 21));
    }
}
