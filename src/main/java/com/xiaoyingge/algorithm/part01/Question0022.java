package com.xiaoyingge.algorithm.part01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaoYingGee
 * @date 2022/4/11 23:39
 */
public class Question0022 {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        String path = "(";
        process(result, n - 1, n, path);
        return result;
    }

    public void process(List<String> result, int left, int right, String path) {
        if (left > right) {
            return;
        }
        if (left == 0 && right == 0) {
            result.add(path);
        }
        if (left > 0) {
            process(result, left - 1, right, path + "(");
        }
        if (right > 0) {
            process(result, left, right - 1, path + ")");
        }
        
    }
}
