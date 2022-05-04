package com.xiaoyingge.algorithm.part01;

/**
 * @author XiaoYingGee
 * @date 2022/4/28 23:40
 */
public class Question0072 {

    public int minDistance(String word1, String word2) {
        if ((word1 == null || word1.length() == 0)
                && (word2 == null || word2.length() == 0)) {
            return 0;
        }
        if (word1 == null || word1.length() == 0) {
            return word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            return word1.length();
        }
        return getDistance(word1, word2, 1, 1, 1);
    }

    private int getDistance(String word1, String word2, int add, int delete, int replace) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i * add;
        }
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i * add;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + replace;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + add);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + delete);
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        new Question0072().minDistance("horse", "ros");
    }
}
