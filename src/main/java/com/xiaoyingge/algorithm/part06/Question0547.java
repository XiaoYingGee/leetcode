package com.xiaoyingge.algorithm.part06;

/**
 * @author XiaoYingGee
 * @date 2022/3/30 23:40
 */
public class Question0547 {

    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }
        UnionSearch unionSearch = new UnionSearch(isConnected.length);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    unionSearch.union(i, j);
                }
            }
        }
        return unionSearch.size;
    }

    public class UnionSearch {

        int[] parentMap;
        int[] sizeMap;
        int[] help;
        int size;

        public UnionSearch(int n) {
            parentMap = new int[n];
            sizeMap = new int[n];
            help = new int[n];
            size = n;
            for (int i = 0; i < n; i++) {
                parentMap[i] = i;
                sizeMap[i] = 1;
            }
        }

        public boolean isSameCollection(int a, int b) {
            int aP = getRoot(a);
            int bP = getRoot(b);

            return aP == bP;
        }

        public void union(int a, int b) {
            int aP = getRoot(a);
            int bP = getRoot(b);
            if (aP == bP) {
                return;
            }
            int aSize = sizeMap[aP];
            int bSize = sizeMap[bP];
            if (aSize > bSize) {
                unionCollection(aP, bP);
            } else {
                unionCollection(bP, aP);
            }

        }

        private int getRoot(int key) {
            int index = 0;
            while (parentMap[key] != key) {
                help[index++] = key;
                key = parentMap[key];
            }
            for (index--; index >= 0; index--) {
                parentMap[help[index]] = key;
            }
            return key;
        }

        private void unionCollection(int largeRoot, int smallRoot) {
            parentMap[smallRoot] = largeRoot;
            sizeMap[largeRoot] = sizeMap[largeRoot] + sizeMap[smallRoot];
            size--;
        }
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                new int[]{1, 1, 0},
                new int[]{1, 1, 0},
                new int[]{0, 0, 1}
        };
        int circleNum = new Question0547().findCircleNum(nums);
        System.out.println(circleNum);
    }
}
