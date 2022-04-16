package com.xiaoyingge.offer;

/**
 * @author XiaoYingGee
 * @date 2022/4/12 22:11
 */
public class Offer11 {

    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return -1;
        }
        int start = numbers[0];
        if (numbers.length == 1) {
            return start;
        }
        int end = numbers[numbers.length - 1];
        if (start < end) {
            return start;
        } else {
            return process(numbers, 0, numbers.length - 1, start, end);
        }

    }

    private Integer process(int[] numbers, int left, int right, int start, int end) {
        if (left == right) {
            return numbers[left];
        }
        int mid = left + (right - left) / 2;
        if (numbers[mid] > start) {
            if (mid + 1 < numbers.length && numbers[mid] > numbers[mid + 1]) {
                return numbers[mid + 1];
            }
            return process(numbers, mid + 1, right, start, end);
        } else if (numbers[mid] < start) {
            if (mid - 1 >= 0 && numbers[mid - 1] > numbers[mid]) {
                return numbers[mid];
            }
            return process(numbers, left, mid, start, end);
        } else {
            return Math.min(process(numbers, mid + 1, right, start, end), process(numbers, left, mid, start, end));
        }
    }

    public static void main(String[] args) {
        int i = new Offer11().minArray(new int[]{2, 1, 2, 2, 2});
        System.out.println(i);
    }
}
