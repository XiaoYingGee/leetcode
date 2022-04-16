package com.xiaoyingge.algorithm.part08;

/**
 * @author XiaoYingGee
 * @date 2022/4/9 13:38
 */
public class Question0780 {

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) {
            return true;
        }
        return process(sx, sy, tx, ty);
    }

    public boolean process(int sx, int sy, int tx, int ty) {
        while (tx > sx && ty > sy) {
            if (tx > ty) {
                tx = tx % ty;
            } else {
                ty = ty % tx;
            }
        }
        return (tx == sx && ty >= sy && (ty - sy) % sx == 0) || (ty == sy && (tx >= sx) && (tx - sx) % sy == 0);

    }

    public static void main(String[] args) {
        new Question0780().reachingPoints(35, 13, 455955547, 420098884);
    }
}
