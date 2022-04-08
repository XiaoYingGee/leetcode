package com.xiaoyingge.algorithm.part02;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiaoYingGee
 * @date 2022/3/30 0:05
 */
public class Question0146 {

    static class LRUCache {

        private int less;
        private int index;
        private Map<Integer, Integer> valueMap;
        private Map<Integer, Integer> indexMap;
        private Map<Integer, Integer> reIndexMap;
        private int limit;

        public LRUCache(int capacity) {
            valueMap = new HashMap<>(capacity);
            indexMap = new HashMap<>(capacity);
            reIndexMap = new HashMap<>(capacity);
            index = 0;
            less = 0;
            limit = capacity;
        }

        public int get(int key) {
            if (!valueMap.containsKey(key)) {
                return -1;
            }
            Integer oldIndex = indexMap.get(key);
            reIndexMap.remove(oldIndex);
            if (oldIndex == less) {
                less++;
            }
            indexMap.put(key, index);
            reIndexMap.put(index++, key);
            return valueMap.get(key);

        }

        public void put(int key, int value) {
            if (valueMap.size() == limit) {
                Integer lessKey = reIndexMap.get(less);
                if (!valueMap.containsKey(key)) {
                    valueMap.remove(lessKey);
                    indexMap.remove(lessKey);
                    reIndexMap.remove(less++);
                } else {
                    Integer oldIndex = indexMap.get(key);
                    if (oldIndex == less) {
                        less++;
                    }
                }
            }
            valueMap.put(key, value);
            reIndexMap.remove(indexMap.get(key));
            indexMap.put(key, index);
            reIndexMap.put(index++, key);
        }
    }

    public static void main(String[] args) {
        LRUCache obj = new LRUCache(2);
        obj.put(2, 1);
        obj.put(1, 1);
        obj.put(2, 3);
        obj.put(4, 1);
        System.out.println(obj.get(1));
        System.out.println(obj.get(2));

    }
}
