package com.xiaoyingge.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaoYingGee
 * @date 2022/5/3 20:59
 */
public class Offer59 {

    public int[][] findContinuousSequence(int target) {
        if (target <= 2) {
            return new int[0][0];
        }
        //使用滑动窗口来解
        int mid = (target + 1) >> 1;
        int[] arr = new int[mid + 1];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= mid; i++) {
            arr[i] = i + arr[i - 1];
        }
        int left = 0;
        int right = 0;
        while (arr[right] < target) {
            right++;
        }
        List<List<Integer>> result = new ArrayList<>();
        while (right <= mid) {
            if (arr[right] - arr[left] == target) {
                result.add(findResult(left, right));
                left++;
            } else if (arr[right] - arr[left] > target) {
                left++;
            } else {
                right++;
            }
        }
        int[][] ans = buildFrom(result);
        return ans;
    }

    private int[][] buildFrom(List<List<Integer>> result) {
        int[][] ans = new int[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            List<Integer> integers = result.get(i);
            int cols = integers.size();
            ans[i] = new int[cols];
            for (int j = 0; j < cols; j++) {
                ans[i][j] = integers.get(j);
            }
        }
        return ans;
    }

    public List<Integer> findResult(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left + 1; i <= right; i++) {

            ans.add(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] continuousSequence = new Offer59().findContinuousSequence(15);

    }
}
