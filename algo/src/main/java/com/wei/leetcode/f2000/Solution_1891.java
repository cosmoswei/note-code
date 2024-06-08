package com.wei.leetcode.f2000;

class Solution_1891 {

    public static void main(String[] args) {
        System.out.println("new Solution_1891().maxProductAfterCutting(8) = "
                + new Solution_1891().maxProductAfterCutting(5));
    }

    public int maxProductAfterCutting(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }
        int[] dp = new int[length + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        int max;
        for (int i = 4; i <= length; ++i) {
            max = 0;
            for (int j = 1; j <= i / 2; ++j) {
                int product = dp[j] * dp[i - j];
                if (max < product) {
                    max = product;
                }
                dp[i] = max;
            }
        }
        max = dp[length];
        return max;
    }


    public int maxProductAfterCutting2(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }

        int timesOf3 = length / 3;
        if (length % 3 == 1) {
            timesOf3 -= 1;
        }
        int timesOf2 = (length - timesOf3 * 3) / 2;

        return (int) (Math.pow(3, timesOf3) * Math.pow(2, timesOf2));
    }
}

