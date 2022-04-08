package com.xiaoyingge.algorithm.part07;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaoYingGee
 * @date 2022/3/26 21:25
 */
public class Question0682 {

    public int calPoints(String[] ops) {
        if (ops == null || ops.length == 0) {
            return 0;
        }
        if (ops.length == 1) {
            return Integer.parseInt(ops[0]);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < ops.length; i++) {
            String op = ops[i];
            if ("C".equals(op)) {
                result.remove(result.size() - 1);
            } else if ("D".equals(op)) {
                result.add(result.get(result.size() - 1) * 2);
            } else if ("+".equals(op)) {
                result.add(result.get(result.size() - 1) + result.get(result.size() - 2));
            } else {
                result.add(Integer.parseInt(op));
            }
        }
        if (result.size() == 0) {
            return 0;
        }
        return result.stream().mapToInt(Integer::intValue).sum();
    }
}
