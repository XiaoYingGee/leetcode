package com.xiaoyingge.juc.ch01;

/**
 * @author Xiaoyingge
 * @description
 * @date 2020/4/28 10:47
 */
public class Second implements Runnable {

    private Foo foo;

    public Second (Foo foo) {
        this.foo = foo;
    }

    @Override
    public void run () {
        foo.two();
    }
}
