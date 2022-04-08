package com.xiaoyingge.practise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * 加强堆的实现
 *
 * @author XiaoYingGee
 * @date 2022/3/24 23:26
 */
@Data
public class PowerHeap<T> {

    private List<T> array;
    private int size;
    private Map<T, Integer> indexMap;
    private Comparator<T> comparator;

    public PowerHeap(Comparator<T> comparator) {
        this.array = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.comparator = comparator;
        this.size = 0;
    }

    public void push(T obj) {
        array.add(obj);
        indexMap.put(obj, size);
        heapInsert(size++);
    }

    public T pop() {
        T result = array.get(0);
        swap(0, size - 1);
        indexMap.remove(result);
        array.remove(size - 1);
        heapify(0);
        size--;
        return result;
    }

    public void remove(T t) {
        T replace = array.get(size - 1);
        int index = indexMap.get(t);
        indexMap.remove(t);
        array.remove(--size);
        if (t != replace) {
            array.set(index, replace);
            indexMap.put(replace, index);
            resign(index);
        }
    }

    private void resign(int index) {
        heapInsert(index);
        heapify(index);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T peek() {
        return array.get(0);
    }

    public boolean contains(T t) {
        return indexMap.containsKey(t);
    }

    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < size) {
            int best = left + 1 < size && comparator.compare(array.get(left + 1), array.get(left)) < 0 ? (left + 1) : left;
            best = comparator.compare(array.get(best), array.get(index)) < 0 ? best : index;
            if (best == index) {
                break;
            }
            swap(best, index);
            index = best;
            left = index * 2 + 1;
        }
    }

    private void heapInsert(int index) {
        while (comparator.compare(array.get(index), array.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(int x, int y) {
        T o1 = array.get(x);
        T o2 = array.get(y);
        array.set(x, o2);
        array.set(y, o1);
        indexMap.put(o2, x);
        indexMap.put(o1, y);
    }

}
