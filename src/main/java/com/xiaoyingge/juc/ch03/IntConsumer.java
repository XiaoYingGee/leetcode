package com.xiaoyingge.juc.ch03;

/**
 * @author Xiaoyingge
 * @description
 * @date 2020/4/28 15:04
 */
public class IntConsumer {

    public void accept (int i) {
        System.out.println(Thread.currentThread().getName() + i);
    }
}
