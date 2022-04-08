package com.xiaoyingge.algorithm.part01;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author XiaoYingGee
 * @date 2022/3/12 2:10
 */
public class Question0004 {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(new Question0004().findMedianSortedArrays(nums1, nums2));
    }

    /**
     * 首先 m+n 已知 ，假设和为k
     * <p>   k%2 == 1  那么 midIndex = k/2 </p>
     * <p>   k%2 == 0  那么 midIndex = k/2 和 k/2 -1 </p>
     * <p>所以其实就是找第  k/2 或第 k/2 -1大的数</p>
     * 如果值相等，说明这就是结果
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //获取数组长度
        int m = nums1.length;
        int n = nums2.length;
        int k = m + n;

        //需要找到的小于中位数的数
        //比如长度是5，中位数索引为2，需要去掉0，1两位数  （5-1）/2 =2
        //比如长度是6，中位数索引为2，3，也是需要去掉0，1 两位数  （6-1）/2 =2
        if (k % 2 == 1) {
            return process(nums1, nums2, (k >> 1) + 1);
        } else {
            return (process(nums1, nums2, (k >> 1)) + process(nums1, nums2, (k >> 1) + 1)) / 2d;
        }
    }

    /**
     * 在两个序列中找到第k大小的数
     *
     * @param nums1
     * @param nums2
     * @param k
     */
    private double process(int[] nums1, int[] nums2, int k) {
        int length1 = nums1.length;
        int length2 = nums2.length;

        //分别代表已经去掉的边界
        int left1 = 0;
        int left2 = 0;

        while (true) {
            //即已经有数组出现了越界，那么就把另一个数组取出第k个数 ，即索引为k-1
            if (left1 == length1) {
                return (double) nums2[left2 + k - 1];
            }
            if (left2 == length2) {
                return (double) nums1[left1 + k - 1];
            }
            //走到这里说明没有越界，两个数组都有值
            //那么只需要再找最后一个数的时候，比较左边界的最小值即可
            if (k == 1) {
                return (double) Math.min(nums1[left1], nums2[left2]);
            }
            //长度为6的时候 k=3 mid =1
            int mid = k >> 1;

            int newLeft1 = Math.min(left1 + mid, length1) - 1;
            int newLeft2 = Math.min(left2 + mid, length2) - 1;

            //数组一的值大于数组二的值，舍弃数组2的左边
            if (nums1[newLeft1] >= nums2[newLeft2]) {
                //舍弃从起点到终点的有效值
                k -= (newLeft2 - left2 + 1);
                //左边界移到第一个有效值
                left2 = newLeft2 + 1;

            } else {
                k -= (newLeft1 - left1 + 1);
                left1 = newLeft1 + 1;
            }
        }

    }
}
