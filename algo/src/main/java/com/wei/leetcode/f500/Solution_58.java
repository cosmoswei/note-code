package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

public class Solution_58 {

    ListNode successor = null; // 后驱节点

    public static void main(String[] args) {
        Solution_58 solution19 = new Solution_58();
        int[][] ints = solution19.generateMatrix(3);
        LeetCodeUtils.printArray2(ints);
    }

    int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int upBound = 0;
        int lowBound = n - 1;
        int leftBound = 0;
        int rightBound = n - 1;
        int num = 1;
        while (num <= n * n) {
            // 左-》右
            if (upBound <= lowBound) {
                for (int i = leftBound; i <= rightBound; i++) {
                    matrix[upBound][i] = num++;
                }
                upBound++;
            }
            // 上 -》 下
            if (leftBound <= rightBound) {
                for (int i = upBound; i <= lowBound; i++) {
                    matrix[i][rightBound] = num++;
                }
                rightBound--;
            }
            // 右 -》 左
            if (upBound <= lowBound) {
                for (int i = rightBound; i >= leftBound; i--) {
                    matrix[lowBound][i] = num++;
                }
                lowBound--;
            }

            // 下 》 上
            if (leftBound <= rightBound) {
                for (int i = lowBound; i >= upBound; i--) {
                    matrix[i][leftBound] = num++;
                }
                leftBound++;
            }
        }
        return matrix;
    }

    int[][] generateMatrix3(int n) {
        int[][] matrix = new int[n][n];
        int upper_bound = 0, lower_bound = n - 1;
        int left_bound = 0, right_bound = n - 1;
        // 需要填入矩阵的数字
        int num = 1;

        while (num <= n * n) {
            if (upper_bound <= lower_bound) {
                // 在顶部从左向右遍历
                for (int j = left_bound; j <= right_bound; j++) {
                    matrix[upper_bound][j] = num++;
                }
                // 上边界下移
                upper_bound++;
            }

            if (left_bound <= right_bound) {
                // 在右侧从上向下遍历
                for (int i = upper_bound; i <= lower_bound; i++) {
                    matrix[i][right_bound] = num++;
                }
                // 右边界左移
                right_bound--;
            }

            if (upper_bound <= lower_bound) {
                // 在底部从右向左遍历
                for (int j = right_bound; j >= left_bound; j--) {
                    matrix[lower_bound][j] = num++;
                }
                // 下边界上移
                lower_bound--;
            }

            if (left_bound <= right_bound) {
                // 在左侧从下向上遍历
                for (int i = lower_bound; i >= upper_bound; i--) {
                    matrix[i][left_bound] = num++;
                }
                // 左边界右移
                left_bound++;
            }
        }
        return matrix;
    }
}
