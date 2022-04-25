package com.xiaoyingge.algorithm.part04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author XiaoYingGee
 * @date 2022/4/25 21:25
 */
public class Question0398 {

    class Solution {

        Map<Integer, List<Integer>> cache;
        Random random;

        public Solution(int[] nums) {
            cache = new HashMap<>();
            random = new Random();
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                if (cache.containsKey(num)) {
                    List<Integer> list = cache.get(num);
                    list.add(i);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    cache.put(num, list);
                }
            }
        }

        public int pick(int target) {
            if (cache.size() == 0 || !cache.containsKey(target)) {
                return -1;
            }
            List<Integer> list = cache.get(target);
            int random = this.random.nextInt(list.size());
            return list.get(random);
        }
    }

}
