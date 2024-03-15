package com.wei.leetcode.f2500;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution_2312 {
    public long sellingWood(int m, int n, int[][] prices) {
        Map<Long, Integer> value = new HashMap<>();
        for (int[] price : prices) {
            value.put(pairHash(price[0], price[1]), price[2]);
        }

        long[][] memo = new long[m + 1][n + 1];
        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(m, n, value, memo);
    }

    public long dfs(int x, int y, Map<Long, Integer> value, long[][] memo) {
        if (memo[x][y] != -1) {
            return memo[x][y];
        }

        long key = pairHash(x, y);
        long ret = value.containsKey(key) ? value.get(key) : 0;
        if (x > 1) {
            for (int i = 1; i < x; i++) {
                ret = Math.max(ret, dfs(i, y, value, memo) + dfs(x - i, y, value, memo));
            }
        }
        if (y > 1) {
            for (int j = 1; j < y; j++) {
                ret = Math.max(ret, dfs(x, j, value, memo) + dfs(x, y - j, value, memo));
            }
        }
        memo[x][y] = ret;
        return ret;
    }

    public long pairHash(int x, int y) {
        return (long) x << 16 ^ y;
    }
}
