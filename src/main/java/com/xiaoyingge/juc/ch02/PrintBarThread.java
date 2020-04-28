package com.xiaoyingge.juc.ch02;

/**
 * @author Xiaoyingge
 * @description
 * @date 2020/4/28 11:03
 */
public class PrintBarThread implements Runnable {

    @Override
    public void run () {
        System.out.println("bar");
    }
}
