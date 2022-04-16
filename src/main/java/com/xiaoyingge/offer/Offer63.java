package com.xiaoyingge.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaoYingGee
 * @date 2022/4/15 11:25
 */
public class Offer63 {

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int min = prices[0];
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            int cur = prices[i];
            max = Math.max(max, cur - min);
            if (cur < min) {
                min = cur;
            }
        }
        return max;
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        List<Integer> newPrices = removeEqual(prices);
        if (newPrices.size() < 2) {
            return 0;
        }
        List<Node> min = new ArrayList<>();
        List<Node> max = new ArrayList<>();
        for (int i = 0; i < newPrices.size(); i++) {
            if (bigger(newPrices, i, i - 1) && bigger(newPrices, i, i + 1)) {
                max.add(new Node(newPrices.get(i), i));
                continue;
            }
            if (smaller(newPrices, i, i - 1) && smaller(newPrices, i, i + 1)) {
                min.add(new Node(newPrices.get(i), i));
            }
        }
        int maxVal = 0;
        for (int i = 0; i < min.size(); i++) {
            Node curMin = min.get(i);
            for (int j = max.size() - 1; j >= 0; j--) {
                Node curMax = max.get(j);
                if (curMin.index >= curMax.index) {
                    break;
                }
                maxVal = Math.max(maxVal, curMax.val - curMin.val);
            }
        }
        return maxVal;
    }

    public boolean bigger(List<Integer> prices, int a, int b) {
        if (b < 0 || b >= prices.size()) {
            return true;
        }
        return prices.get(a) > prices.get(b);
    }

    public boolean smaller(List<Integer> prices, int a, int b) {
        if (b < 0 || b >= prices.size()) {
            return true;
        }
        return prices.get(a) < prices.get(b);
    }

    public List<Integer> removeEqual(int[] prices) {
        List<Integer> result = new ArrayList<>();
        int pre = prices[0];
        result.add(prices[0]);
        for (int i = 1; i < prices.length; i++) {
            int cur = prices[i];
            if (cur == pre) {
                continue;
            }
            result.add(cur);
            pre = cur;
        }
        return result;
    }

    public class Node {

        int val;
        int index;

        public Node(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 2, 0, 1};
        int i = new Offer63().maxProfit2(nums);
        System.out.println(i);
    }
}
