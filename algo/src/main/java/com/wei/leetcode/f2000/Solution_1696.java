package com.wei.leetcode.f2000;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution_1696 {

    public static void main(String[] args) {
        int[] nums = {10, -5, -2, 4, 0, 3};
        Solution_1696 solution = new Solution_1696();
        int i = solution.maxResult(nums, 3);
        System.out.println("i = " + i);
    }

    /*
    给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
    一开始你在下标 0 处。每一步，你最多可以往前跳 k 步，但你不能跳出数组的边界。也就是说，你可以从下标 i 跳到 [i + 1， min(n - 1, i + k)] 包含 两个端点的任意位置。
    你的目标是到达数组最后一个位置（下标为 n - 1 ），你的 得分 为经过的所有数字之和。
    请你返回你能得到的 最大得分 。
    输入：nums = [1,-1,-2,4,-7,3], k = 2
    输出：7
    解释：你可以选择子序列 [1,-1,4,3] （上面加粗的数字），和为 7 。
     */
    public int maxResult(int[] nums, int k) {
        // int[] nums = {10, -5, -2, 4, 0, 3};
        int length = nums.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            // from i to i + k;
            int p = k;
            int max = Integer.MIN_VALUE + 1;
            if (i == 0) {
                max = 0;
            }
            for (int j = i - 1; j > i - p - 1 && j >= 0; j--) {
                if (j > length - 1) {
                    break;
                }
                if (max < dp[j]) {
                    max = dp[j];
                }
            }
            System.out.println("max = " + max);
            dp[i] = nums[i] + max;
            System.out.println("dp = " + dp[i]);
        }
        return dp[length - 1];
    }

    public int maxResult2(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offerLast(0);
        for (int i = 1; i < n; i++) {
            while (queue.peekFirst() < i - k) {
                queue.pollFirst();
            }
            dp[i] = dp[queue.peekFirst()] + nums[i];
            while (!queue.isEmpty() && dp[queue.peekLast()] <= dp[i]) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        return dp[n - 1];
    }


    public int maxResult3(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> q = new ArrayDeque<>();
        q.add(0);
        for (int i = 1; i < n; i++) {
            // 1. 出
            if (q.peekFirst() < i - k) {
                q.pollFirst();
            }
            // 2. 转移
            nums[i] += nums[q.peekFirst()];
            // 3. 入
            while (!q.isEmpty() && nums[i] >= nums[q.peekLast()]) {
                q.pollLast();
            }
            q.add(i);
        }
        return nums[n - 1];
    }


}

