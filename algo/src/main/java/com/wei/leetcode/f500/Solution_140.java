package com.wei.leetcode.f500;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

class Solution_140 {

    HashSet<String> wordDict;
    List<String>[] memo;


    public List<String> wordBreak(String s, List<String> wordDict) {
        this.wordDict = new HashSet<>(wordDict);
        this.memo = new List[s.length()];
        return dp(s, 0);
    }

    private List<String> dp(String s, int i) {
        List<String> res = new LinkedList<>();
        if (i == s.length()) {
            res.add("");
            return res;
        }
        if (memo[i] != null) {
            return memo[i];
        }
        for (int len = 1; i + len <= s.length(); len++) {
            String sub = s.substring(i, i + len);
            if (wordDict.contains(sub)) {
                List<String> dp = dp(s, i + len);
                for (String string : dp) {
                    if (string.isEmpty()) {
                        res.add(sub);
                    } else {
                        res.add(sub + " " + sub);
                    }
                }
            }
        }
        memo[i] = res;
        return res;
    }
}