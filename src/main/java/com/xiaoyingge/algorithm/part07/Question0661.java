package com.xiaoyingge.algorithm.part07;

/**
 * @author XiaoYingGee
 * @date 2022/3/24 0:34
 */
public class Question0661 {

    public static void main(String[] args) {
        int[][] img = new int[][]{
                new int[]{100, 200, 100},
                new int[]{200, 50, 200},
                new int[]{100, 200, 100}
        };
        int[][] smoother = new Question0661().imageSmoother(img);
        System.out.println("==");
    }

    public int[][] imageSmoother(int[][] img) {
        if (img == null) {
            return new int[1][1];
        }
        int width = img.length;
        int height = img[0].length;
        int[][] result = new int[width][height];
        if (width == 1 && height == 1) {
            result[0][0] = img[0][0];
            return result;
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int tmp = img[x][y];
                int count = 1;
                boolean top = false;
                boolean left = false;
                boolean right = false;
                boolean bottom = false;
                //说明左可达
                if (x - 1 >= 0) {
                    left = true;
                    tmp += img[x - 1][y];
                    count++;
                }
                //说明右可达
                if (x + 1 < width) {
                    right = true;
                    tmp += img[x + 1][y];
                    count++;
                }

                //说明上可达
                if (y - 1 >= 0) {
                    top = true;
                    tmp += img[x][y - 1];
                    count++;
                }
                //说明右可达
                if (y + 1 < height) {
                    bottom = true;
                    tmp += img[x][y + 1];
                    count++;
                }
                //如果有上
                if (top && left) {

                    tmp += img[x - 1][y - 1];
                    count++;
                }
                if (top && right) {
                    tmp += img[x + 1][y - 1];
                    count++;
                }

                if (bottom && left) {

                    tmp += img[x - 1][y + 1];
                    count++;
                }
                if (bottom && right) {
                    tmp += img[x + 1][y + 1];
                    count++;
                }
                result[x][y] = tmp / count;
            }
        }
        return result;
    }

}
