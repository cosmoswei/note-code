package com.wei.leetcode.f3000;

import java.util.Arrays;

class Solution_2952 {
    public int minimumAddedCoins(int[] coins, int target) {
        Arrays.sort(coins);
        int ans = 0;
        int x = 1;
        int length = coins.length, index = 0;
        while (x <= target) {
            if (index < length && coins[index] <= x) {
                x += coins[index];
                index++;
            } else {
                x *= 2;
                ans++;
            }
        }
        return ans;
    }
}