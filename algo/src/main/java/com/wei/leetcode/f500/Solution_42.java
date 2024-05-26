package com.wei.leetcode.f500;

public class Solution_42 {

    public static void main(String[] args) {
        int[] nums = {4, 2, 0, 3, 2, 5};
        int trap = trap2(nums);
        System.out.println("trap = " + trap);
    }

    static public int trap(int[] height) {
        int n = height.length;
        // 计算前缀最大值
        int[] preMax = new int[n];
        preMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], height[i]);
        }
        // 计算后缀最大值
        int[] subMax = new int[n];
        subMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            subMax[i] = Math.max(subMax[i + 1], height[i]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.min(preMax[i], subMax[i]) - height[i];
        }
        // 结果 = 各节点的min(前缀最大值，后缀最大值)-数组高度之和
        return res;
    }

    static public int trap2(int[] height) {
        int n = height.length;
        int res = 0;
        // 计算前缀最大值
        int preMax = 0;
        int subMax = 0;
        int left = 0, right = n - 1;
        while (left < right) {
            preMax = Math.max(preMax, height[left]);
            subMax = Math.max(subMax, height[right]);
            res += preMax < subMax ? preMax - height[left++] : subMax - height[right--];
        }
        // 结果 = 各节点的min(前缀最大值，后缀最大值)-数组高度之和
        return res;
    }
}
