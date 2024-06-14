package com.wei.leetcode.f500;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Solution_139 {

    HashSet<String> wordDict;
    int[] memo;

    public boolean wordBreak(String s, List<String> wordDict) {
        this.wordDict = new HashSet<>(wordDict);
        this.memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return dp(s, 0);
    }

    private boolean dp(String s, int i) {
        if (i == s.length()) {
            return true;
        }
        if (memo[i] != -1) {
            return memo[i] != 0;
        }
        for (int j = 1; j + i <= s.length(); j++) {
            String substring = s.substring(i, i + j);
            if (wordDict.contains(substring)) {
                boolean dp = dp(s, i + j);
                if (dp) {
                    memo[i] = 1;
                    return true;
                }
            }
        }
        memo[i] = 0;
        return false;
    }
}