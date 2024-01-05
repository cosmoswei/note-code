package com.wei.leetcode.f500;


public class Solution_304 {

    private int[][] preSum;

//    public Solution_304(int[][] matrix) {
//        int m = matrix.length, n = matrix[0].length;
//        if (m == 0 || n == 0) return;
//        // 构造前缀和矩阵
//        preSum = new int[m + 1][n + 1];
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                // 计算每个矩阵 [0, 0, i, j] 的元素和
//                preSum[i][j] = preSum[i - 1][j]
//                        + preSum[i][j - 1]
//                        + matrix[i - 1][j - 1]
//                        - preSum[i - 1][j - 1];
//            }
//        }
//    }

    public Solution_304(int[][] matrix) {
        int x = matrix.length;
        int y = matrix[0].length;
        if (x == 0 || y == 0) {
            return;
        }
        preSum = new int[x + 1][y + 1];
        for (int i = 1; i <= x; i++) {
            for (int j = 1; j <= y; j++) {
                preSum[i][j] = matrix[i - 1][j - 1] // 自身值
                        + preSum[i - 1][j]          // 左边矩阵值
                        + preSum[i][j - 1]          // 上面矩阵值
                        - preSum[i - 1][j - 1];     // 左上的公共部分
            }
        }
    }

    // 计算子矩阵 [x1, y1, x2, y2] 的元素和
    public int sumRegion(int x1, int y1, int x2, int y2) {
        // 目标矩阵之和由四个相邻矩阵运算获得
        return preSum[x2 + 1][y2 + 1] - preSum[x1][y2 + 1] - preSum[x2 + 1][y1] + preSum[x1][y1];
    }


    public static void main(String[] args) {
        int[][] nums = {{3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        Solution_304 solution303 = new Solution_304(nums);
        System.out.println("sumRange = " + solution303.sumRegion(2, 1, 4, 3));
    }
}

