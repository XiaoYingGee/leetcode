package com.xiaoyingge.algorithm.part04;

/**
 * @author XiaoYingGee
 * @date 2022/4/30 12:22
 */
public class Question0343 {

    public int cuttingRope(int n) {

        int mod = n % 3;
        int pow = n / 3;
        if (mod == 0) {
            return (int) Math.pow(3D, pow);
        } else if (mod == 1) {
            return (int) Math.pow(3D, pow - 1) << 2;
        } else {
            return (int) Math.pow(3D, pow) << 1;
        }
    }

    public static void main(String[] args) {
        Question0343 question0343 = new Question0343();
        int i = question0343.cuttingRope(5);
        System.out.println(i);
    }
}
