package com.xiaoyingge.algorithm.part04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author XiaoYingGee
 * @date 2022/4/13 0:50
 */
public class Question0380 {

    class RandomizedSet {

        HashMap<Integer, Integer> map;
        List<Integer> list;

        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, list.size());
            list.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (list.size() == 0 || !map.containsKey(val)) {
                return false;
            }
            Integer index = map.get(val);
            if (index != list.size() - 1) {
                int tmp = list.get(index);
                list.set(index, list.get(list.size() - 1));
                list.set(list.size() - 1, tmp);
            }
            map.remove(val);
            list.remove(index.intValue());

            return true;
        }

        public int getRandom() {
            int i = new Random().nextInt(list.size());
            return list.get(i);
        }
    }

}
