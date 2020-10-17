package com.xiaoyingge.juc.ch01;

/**
 * @author Xiaoyingge
 * @description
 * @date 2020/4/28 10:47
 */
public class First implements Runnable {

    private Foo foo;

    public First (Foo foo) {
        this.foo = foo;
    }

    @Override
    public void run () {
        foo.one();
    }
}
