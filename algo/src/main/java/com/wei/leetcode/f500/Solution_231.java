package com.wei.leetcode.f500;

public class Solution_231 {

    public static void main(String[] args) {
        Solution_231 solution = new Solution_231();
        boolean i = solution.isPowerOfTwo(16);
        System.out.println("i = " + i);
    }

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }
}
