package com.wei.leetcode.f500;

import java.util.Arrays;

public class Solution_322 {

    public static void main(String[] args) {
        Solution_322 solution = new Solution_322();
        int[] nums = {1, 5, 10, 20};
        int target = 100;
        System.out.println(solution.coinChange2(nums, target));
    }

    int[] memo;

    int coinChange(int[] coins, int amount) {
        memo = new int[amount + 1];
        // 备忘录初始化为一个不会被取到的特殊值，代表还未被计算
        Arrays.fill(memo, -666);
        // 题目要求的最终结果是 dp(amount)
        return dp2(coins, amount);
    }

    int coinChange2(int[] coins, int amount) {
        // 题目要求的最终结果是 dp(amount)
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    int dp2(int[] coins, int amount) {
        System.out.println("amount = " + amount);
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int i = dp2(coins, amount - coin);
            if (i == -1) {
                continue;
            }
            res = Math.min(res, i + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // 定义：要凑出金额 n，至少要 dp(coins, n) 个硬币
    int dp(int[] coins, int amount) {
        // base case
        if (amount == 0) return 0;
        if (amount < 0) return -1;
        // 查备忘录，防止重复计算
        if (memo[amount] != -666)
            return memo[amount];

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            // 计算子问题的结果
            int subProblem = dp(coins, amount - coin);
            // 子问题无解则跳过
            if (subProblem == -1) continue;
            // 在子问题中选择最优解，然后加一
            res = Math.min(res, subProblem + 1);
        }

        // 把计算结果存入备忘录
        memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
        return memo[amount];
    }
}
