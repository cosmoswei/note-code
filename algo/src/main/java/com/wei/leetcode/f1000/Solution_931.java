package com.wei.leetcode.f1000;

import java.util.Arrays;

public class Solution_931 {

    public static void main(String[] args) {
    }

    int[][] memo;

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int res = Integer.MAX_VALUE;
        // 备忘录里的值初始化为 66666
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], 66666);
        }
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp(matrix, n - 1, i));
        }
        return res;
    }

    private int dp(int[][] matrix, int i, int j) {
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) {
            return 666666;
        }
        if (i == 0) {
            return matrix[i][j];
        }

        if (memo[i][j] != 66666) {
            return memo[i][j];
        }

        memo[i][j] = matrix[i][j] + min(dp(matrix, i - 1, j + 1),
                dp(matrix, i - 1, j),
                dp(matrix, i - 1, j - 1));
        return memo[i][j];
    }

    int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
