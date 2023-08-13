package com.wei;

class NumMatrix {
    // 定义：preSum[i][j] 记录 matrix 中⼦矩阵 [0, 0, i-1, j-1] 的元素和 
    private int[][] preSum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        if (m == 0 || n == 0) return;
        // 构造前缀和矩阵 
        preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 计算每个矩阵 [0, 0, i, j] 的元素和 
                preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] + matrix[i - 1][j - 1] - preSum[i-1][j-1];
            }
        }
    }

    // 计算⼦矩阵 [x1, y1, x2, y2] 的元素和 
    public int sumRegion(int x1, int y1, int x2, int y2) {
        // ⽬标矩阵之和由四个相邻矩阵运算获得 
        return preSum[x2+1][y2+1] - preSum[x1][y2+1] - preSum[x2+1][y1] + preSum[x1][y1];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[5][5];
        NumMatrix numMatrix = new NumMatrix(matrix);
        int i = numMatrix.sumRegion(2, 1, 4, 3);
    }
} 