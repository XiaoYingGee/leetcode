package com.xiaoyingge.interview;

import com.xiaoyingge.util.PrintUtil;

/**
 * 尝试实现线段树
 *
 * @author XiaoYingGee
 * @date 2022/5/2 17:41
 */
public class SegmentTree {

    int length;
    int[] sum;
    int[] addCache;
    Integer[] updateCache;

    SegmentTree(int[] arr) {
        length = arr.length;
        //升级为四倍大小
        sum = new int[(++length) << 2];
        addCache = new int[length << 2];
        updateCache = new Integer[length << 2];
        buildTree(arr, 1, --length, 1);
    }

    public void add(int left, int right, int val) {
        add(left, right, val, 1, length, 1);
    }

    public void update(int left, int right, int val) {
        update(left, right, val, 1, length, 1);
    }

    public long query(int left, int right) {
        return query(left, right, 1, length, 1);
    }

    /**
     * 代表在一个范围内进行累加
     *
     * @param left             添加任务的左边界
     * @param right            添加任务的右边界
     * @param val              添加任务的值
     * @param segmentStart     当前的线段树起始索引
     * @param segmentEnd       当前的线段树结束索引
     * @param segmentRootIndex 当前线段树区间的根地址索引
     */
    private void add(int left, int right, int val, int segmentStart, int segmentEnd, int segmentRootIndex) {
        //区域被任务完全包括了
        if (segmentStart >= left && segmentEnd <= right) {
            sum[segmentRootIndex] += val * (segmentEnd - segmentStart + 1);
            addCache[segmentRootIndex] += val;
            return;
        }
        //将当前节点的任务下推
        int mid = (segmentEnd + segmentStart) >> 1;
        pushDown(segmentRootIndex, mid - segmentStart + 1, segmentEnd - mid);
        //如果任务的左边界小于等于mid,说明在左半区有分片，向左半区推送
        if (left <= mid) {
            add(left, right, val, segmentStart, mid, segmentRootIndex << 1);
        }
        //如果任务的右边界大于mid,说明在右半区有分片，向右半区推送
        if (right > mid) {
            add(left, right, val, mid + 1, segmentEnd, segmentRootIndex << 1 | 1);
        }
        pushUp(segmentRootIndex);
    }

    /**
     * 代表将某个范围全部更新成某个值
     *
     * @param left             更新任务的左边界
     * @param right            更新任务的右边界
     * @param val              更新值
     * @param segmentStart     当前的线段树起始索引
     * @param segmentEnd       当前的线段树结束索引
     * @param segmentRootIndex 当前线段树区间的根地址索引
     */
    public void update(int left, int right, int val, int segmentStart, int segmentEnd, int segmentRootIndex) {
        //包住的情况下
        if (left <= segmentStart && right >= segmentEnd) {
            updateCache[segmentRootIndex] = val;
            sum[segmentRootIndex] = val * (segmentEnd - segmentStart + 1);
            addCache[segmentRootIndex] = 0;
            return;
        }
        //没有包住
        int mid = (segmentEnd + segmentStart) >> 1;
        pushDown(segmentRootIndex, mid - segmentStart + 1, segmentEnd - mid);
        if (left <= mid) {
            update(left, right, val, segmentStart, mid, segmentRootIndex << 1);
        }
        if (right > mid) {
            update(left, right, val, mid + 1, segmentEnd, segmentRootIndex << 1 | 1);
        }
        pushUp(segmentRootIndex);
    }

    /**
     * 因左右大小可能划的不均，所以需要左右大小都传
     *
     * @param segmentRootIndex 当前要下发的的根索引
     * @param leftNodes        左侧的节点个数
     * @param rightNodes       右侧的节点个数
     */
    private void pushDown(int segmentRootIndex, int leftNodes, int rightNodes) {
        if (updateCache[segmentRootIndex] != null) {
            //任务下推
            updateCache[segmentRootIndex << 1] = updateCache[segmentRootIndex];
            updateCache[segmentRootIndex << 1 | 1] = updateCache[segmentRootIndex];
            //缓存清空
            addCache[segmentRootIndex << 1] = 0;
            addCache[segmentRootIndex << 1 | 1] = 0;
            //修改源数组
            sum[segmentRootIndex << 1] = updateCache[segmentRootIndex] * leftNodes;
            sum[segmentRootIndex << 1 | 1] = updateCache[segmentRootIndex] * rightNodes;
            updateCache[segmentRootIndex] = null;
        }
        if (addCache[segmentRootIndex] != 0) {
            //下发到左节点
            addCache[segmentRootIndex << 1] += addCache[segmentRootIndex];
            //下发到右节点
            addCache[segmentRootIndex << 1 | 1] += addCache[segmentRootIndex];
            //下发时结算sum
            sum[segmentRootIndex << 1] += leftNodes * addCache[segmentRootIndex];
            sum[segmentRootIndex << 1 | 1] += rightNodes * addCache[segmentRootIndex];
            addCache[segmentRootIndex] = 0;
        }
    }

    /**
     * 查询区间结果
     *
     * @param left             左起点
     * @param right            右起点
     * @param segmentStart     线段区间起点
     * @param segmentEnd       线段区间终点
     * @param segmentRootIndex 线段区间根索引
     * @return
     */
    private long query(int left, int right, int segmentStart, int segmentEnd, int segmentRootIndex) {
        if (left <= segmentStart && right >= segmentEnd) {
            return sum[segmentRootIndex];
        }
        int mid = (segmentEnd + segmentStart) >> 1;
        pushDown(segmentRootIndex, mid - segmentStart + 1, segmentEnd - mid);
        long ans = 0;
        if (left <= mid) {
            ans += query(left, right, segmentStart, mid, segmentRootIndex << 1);
        }
        if (right > mid) {
            ans += query(left, right, mid + 1, segmentEnd, segmentRootIndex << 1 | 1);
        }
        return ans;
    }

    /**
     * 构建树
     *
     * @param arr       源数据
     * @param left      用来生成树的淅数组左边界
     * @param right     用来生成树的淅数组右边界
     * @param rootIndex 当前边界下的根在哪个位置 左边就在 2*rootIndex  右边在2*rootIndex+1
     */
    private void buildTree(int[] arr, int left, int right, int rootIndex) {
        if (left == right) {
            sum[rootIndex] = arr[left - 1];
            return;
        }
        int mid = (left + right) >> 1;
        //左半边的根在 2*i
        buildTree(arr, left, mid, rootIndex << 1);
        //右半边的桶在  2*i+1
        buildTree(arr, mid + 1, right, rootIndex << 1 | 1);
        pushUp(rootIndex);
    }

    private void pushUp(int start) {
        sum[start] = sum[start << 1] + sum[start << 1 | 1];
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4};
        SegmentTree segmentTree = new SegmentTree(ints);
        System.out.println(segmentTree.query(1, 4));
        print(segmentTree);
        //2 3 4 5
        segmentTree.add(1, 4, 1);
        System.out.println(segmentTree.query(1, 4));
        print(segmentTree);
        // 4  5  6 5
        segmentTree.add(1, 3, 2);
        System.out.println(segmentTree.query(1, 4));
        print(segmentTree);
        segmentTree.update(1, 4, 1);
        System.out.println(segmentTree.query(1, 4));
        print(segmentTree);
        segmentTree.update(3, 4, 10);
        System.out.println(segmentTree.query(1, 4));
        print(segmentTree);
        segmentTree.add(3, 3, 2);
        System.out.println(segmentTree.query(1, 4));
        print(segmentTree);
    }

    public static void print(SegmentTree segmentTree) {
        System.out.println("=========================");
        System.out.print("SUM:");
        PrintUtil.print(segmentTree.sum);
        System.out.print("ADD:");
        PrintUtil.print(segmentTree.addCache);
        System.out.print("UPDATE:");
        PrintUtil.print(segmentTree.updateCache);
        System.out.println("=========================");
    }
}
