package com.xiaoyingge.juc.ch03;

/**
 * @author Xiaoyingge
 * @description
 * @date 2020/4/28 15:09
 */
public class Main {

    public static void main (String[] args) {

        ZeroEvenOdd2 zeroEvenOdd2 = new ZeroEvenOdd2(3);
        IntConsumer consumer = new IntConsumer();
        Thread a = new Thread(() -> {
            try {
                zeroEvenOdd2.zero(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        a.setName("a->");
        Thread b = new Thread(() -> {
            try {
                zeroEvenOdd2.even(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        b.setName("b->");
        Thread c = new Thread(() -> {
            try {
                zeroEvenOdd2.odd(consumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        c.setName("c->");
        a.start();
        b.start();
        c.start();
    }

}
