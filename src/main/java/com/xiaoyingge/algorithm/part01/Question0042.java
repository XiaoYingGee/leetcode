package com.xiaoyingge.algorithm.part01;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author XiaoYingGee
 * @date 2022/4/23 21:39
 */
public class Question0042 {

    public int trap(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        Map<Integer, TreeSet<Integer>> heightMap = new HashMap<>();
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            int h = height[i];
            if (!heightMap.containsKey(h)) {
                heightMap.put(h, new TreeSet<>());
            }
            heightMap.get(h).add(i);
            max = Math.max(h, max);
        }
        if (max == 0) {
            return 0;
        }
        int count = 0;
        TreeSet<Integer> preSet = new TreeSet<>();
        for (int i = max; i >= 1; i--) {
            TreeSet<Integer> indexSet = heightMap.get(i);
            if (indexSet != null) {
                indexSet.addAll(preSet);
            } else {
                indexSet = preSet;
            }
            if (indexSet.size() <= 1) {
                preSet = indexSet;
                continue;
            }
            // endIndex-startIndex-1 代表这一层的最大跨度，再去掉中间的隔层 set.Size - 2
            count += indexSet.last() - indexSet.first() - indexSet.size() + 1;
            preSet = indexSet;
        }
        return count;
    }

    public int trap2(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int ans = 0;
        int left_max = 0, right_max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= left_max) {
                    left_max = height[left];
                } else {
                    ans += (left_max - height[left]);
                }
                ++left;
            } else {
                if (height[right] >= right_max) {
                    right_max = height[right];
                } else {
                    ans += (right_max - height[right]);
                }
                --right;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 4, 2, 0, 3, 2, 5, 0, 0, 3};
        int trap = new Question0042().trap2(nums);
        System.out.println(trap);
    }
}
