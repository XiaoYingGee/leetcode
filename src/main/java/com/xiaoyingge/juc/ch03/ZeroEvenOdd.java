package com.xiaoyingge.juc.ch03;

import java.util.concurrent.locks.LockSupport;

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
public class ZeroEvenOdd {

    private static int n;

    private static Thread a;

    private static Thread b;

    private static Thread c;

    public ZeroEvenOdd (int n) {
        ZeroEvenOdd.n = n;
    }

    public void zero (IntConsumer printNumber) throws InterruptedException {
        printNumber.accept(0);
    }

    public void even (IntConsumer printNumber) throws InterruptedException {
        printNumber.accept(++currentIndex);
    }

    public void odd (IntConsumer printNumber) throws InterruptedException {
        printNumber.accept(++currentIndex);
    }

    private static class IntConsumer {

        void accept (int i) {
            System.out.println(i);
        }
    }

    private static volatile int currentIndex = 0;

    public static void main (String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(10);
        IntConsumer consumer = new IntConsumer();
        a = new Thread(() -> {
            try {
                for (int i = 0; i < n; i++) {
                    zeroEvenOdd.zero(consumer);
                    if ((i & 1) == 0) {
                        LockSupport.unpark(b);
                    } else {
                        LockSupport.unpark(c);
                    }
                    LockSupport.park();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        b = new Thread(() -> {
            try {
                for (int i = 0; i < n / 2; i++) {
                    LockSupport.park();
                    zeroEvenOdd.even(consumer);
                    LockSupport.unpark(a);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        c = new Thread(() -> {
            try {
                for (int i = 0; i < n / 2; i++) {
                    LockSupport.park();
                    zeroEvenOdd.odd(consumer);
                    LockSupport.unpark(a);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        a.start();
        b.start();
        c.start();
    }

}
