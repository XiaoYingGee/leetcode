package com.xiaoyingge.algorithm.part01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author XiaoYingGee
 * @date 2022/4/12 20:58
 */
public class Question0056 {

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }
        PriorityQueue<Range> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.start));
        for (int i = 0; i < intervals.length; i++) {
            queue.add(new Range(intervals[i]));
        }
        List<Range> result = new ArrayList<>();
        Range tmp = queue.poll();
        Range next = null;
        while (!queue.isEmpty()) {
            next = queue.poll();
            if (isConnected(tmp, next)) {
                //如果最后一步是合并，那么tmp包含next
                tmp = merge(tmp, next);
                continue;
            }
            //如果最后一步是分离，那么tmp被合进去之后，指向了next;
            result.add(tmp);
            tmp = next;
        }
        result.add(tmp);
        int[][] ans = new int[result.size()][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = result.get(i).parse();
        }
        return ans;
    }

    private Range merge(Range tmp, Range next) {
        int start = Math.min(tmp.start, next.start);
        int end = Math.max(tmp.end, next.end);
        return new Range(start, end);
    }

    private boolean isConnected(Range tmp, Range next) {
        if (next.start > tmp.end || tmp.start > next.end) {
            return false;
        }
        return true;
    }

    public class Range {

        int start;
        int end;

        Range(int s, int e) {
            start = s;
            end = e;
        }

        Range(int[] nums) {
            start = nums[0];
            end = nums[1];
        }

        public int[] parse() {
            return new int[]{start, end};
        }
    }

}
