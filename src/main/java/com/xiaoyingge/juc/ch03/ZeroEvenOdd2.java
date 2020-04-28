package com.xiaoyingge.juc.ch03;

/**
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 *
 * 线程 A 将调用 zero()，它只输出 0 。
 * 线程 B 将调用 even()，它只输出偶数。
 * 线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-zero-even-odd
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Xiaoyingge
 * @description
 * @date 2020/4/28 11:23
 */
public class ZeroEvenOdd2 {

    private int n;

    public ZeroEvenOdd2 (int n) {
        this.n = n;
    }

    private volatile boolean one = true;

    private volatile boolean two = false;

    private volatile boolean three = false;

    public void zero (IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!one) {
                Thread.yield();
            }
            printNumber.accept(0);
            if ((i & 1) == 0) {
                two = true;
                three = false;
            } else {
                two = false;
                three = true;
            }
            one = false;
        }
    }

    public void even (IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < (n + 1) / 2; i++) {
            while (!two) {
                Thread.yield();
            }
            printNumber.accept(i * 2 + 1);
            one = true;
            two = false;
        }
    }

    public void odd (IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n / 2; i++) {
            while (!three) {
                Thread.yield();
            }
            printNumber.accept((i + 1) * 2);
            one = true;
            three = false;
        }
    }

}
