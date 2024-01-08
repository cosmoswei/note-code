package com.wei.leetcode.f500;

import com.wei.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_54 {

    ListNode successor = null; // 后驱节点

    public static void main(String[] args) {
        Solution_54 solution19 = new Solution_54();
        int[][] nums = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        List<Integer> integers = solution19.spiralOrder(nums);
        System.out.println("integers = " + integers);
    }


    List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int upBound = 0;
        int lowBound = m - 1;
        int leftBound = 0;
        int rightBound = n - 1;
        while (res.size() < m * n) {
            // 在顶部从左向右遍历
            if (upBound <= lowBound) {
                for (int j = leftBound; j <= rightBound; j++) {
                    res.add(matrix[upBound][j]);
                }
                upBound++;
            }
            // 在右侧从上向下遍历
            if (leftBound <= rightBound) {
                for (int i = upBound; i <= lowBound; i++) {
                    res.add(matrix[i][rightBound]);
                }
                rightBound--;
            }

            // 在下部侧从右向左遍历
            if (upBound <= lowBound) {
                for (int j = rightBound; j >= leftBound; j--) {
                    res.add(matrix[lowBound][j]);
                }
                lowBound--;
            }

            // 在左侧从下向上遍历
            if (leftBound <= rightBound) {
                for (int i = lowBound; i >= upBound; i--) {
                    res.add(matrix[i][leftBound]);
                }
                leftBound++;
            }
        }

        return res;
    }

    // 将二维矩阵原地顺时针旋转 90 度
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 先沿对角线镜像对称二维矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // swap(matrix[i][j], matrix[j][i]);
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 然后反转二维矩阵的每一行
        for (int[] row : matrix) {
            reverse(row);
        }
    }

    // 反转一维数组
    void reverse(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (j > i) {
            // swap(arr[i], arr[j]);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }


}
