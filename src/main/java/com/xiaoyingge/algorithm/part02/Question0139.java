package com.xiaoyingge.algorithm.part02;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * @author XiaoYingGee
 * @date 2022/4/26 23:02
 */
public class Question0139 {

    public boolean wordBreak(String s, List<String> wordDict) {
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            if (!dp[i - 1]) {
                continue;
            }
            for (String dic : wordDict) {
                int j = i - 1 + dic.length();
                if (j <= length && s.startsWith(dic, i - 1)) {
                    dp[j] = true;
                }
            }
        }
        return dp[length];
    }

    public static void main(String[] args) {
        boolean b = new Question0139().wordBreak("cars", Lists.newArrayList("car", "ca", "rs", "and", "cat"));
        System.out.println(b);
    }

}
