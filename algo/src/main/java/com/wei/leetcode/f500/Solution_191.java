package com.wei.leetcode.f500;

public class Solution_191 {

    public static void main(String[] args) {
    }

    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n &= n - 1;
            res++;
        }
        return res;
    }
}
