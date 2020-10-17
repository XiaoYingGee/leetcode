package com.xiaoyingge.juc.ch01;

/**
 * 三个不同的线程将会共用一个 Foo 实例。
 *
 * 线程 A 将会调用 one() 方法
 * 线程 B 将会调用 two() 方法
 * 线程 C 将会调用 three() 方法
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Xiaoyingge
 * @description
 * @date 2020/4/28 10:33
 */
public class Foo {

    public void one () {
        print("one");
    }

    public void two () {
        print("two");
    }

    public void three () {
        print("three");
    }

    public Foo () {

    }

    private void print (String str) {
        System.out.println(str);
    }

    private static volatile int index = 1;

    public static void first (Runnable printFirst) throws InterruptedException {
        printFirst.run();
        index = index + 1;
    }

    public static void second (Runnable printSecond) throws InterruptedException {
        while (index != 2) {

        }
        printSecond.run();
        index = index + 1;
    }

    public static void third (Runnable printThird) throws InterruptedException {
        while (index != 3) {

        }
        printThird.run();
    }

    public static void main (String[] args) {
        Foo foo = new Foo();
        Thread a = new Thread(() -> {
            try {
                first(new First(foo));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread b = new Thread(() -> {
            try {
                second(new Second(foo));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread c = new Thread(() -> {
            try {
                third(new Third(foo));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        c.start();
        b.start();
        a.start();

    }
}


