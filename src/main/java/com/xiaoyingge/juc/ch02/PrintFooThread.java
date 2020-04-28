package com.xiaoyingge.juc.ch02;

/**
 * @author Xiaoyingge
 * @description
 * @date 2020/4/28 11:02
 */
public class PrintFooThread implements Runnable {

    @Override
    public void run () {
        System.out.print("foo");
    }
}
