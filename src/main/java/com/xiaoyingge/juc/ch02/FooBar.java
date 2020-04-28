package com.xiaoyingge.juc.ch02;

/**
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 *
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-foobar-alternately
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Xiaoyingge
 * @description
 * @date 2020/4/28 10:44
 */
public class FooBar {

    private static int n = 0;

    public FooBar (int n) {
        FooBar.n = n;
    }

    private static volatile boolean printFooFlag = true;

    public static void foo (Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (!printFooFlag) {
                Thread.yield();
            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            printFooFlag = false;
        }
    }

    public static void bar (Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (printFooFlag) {
                Thread.yield();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            printFooFlag = true;
        }
    }

    public static void main (String[] args) {
        FooBar fooBar = new FooBar(100);
        Thread a = new Thread(() -> {
            try {
                foo(new PrintFooThread());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread b = new Thread(() -> {
            try {
                bar(new PrintBarThread());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        a.start();
        b.start();
    }
}
