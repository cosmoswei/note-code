package com.wei.leetcode.f500;


public class Solution_292 {

    public static void main(String[] args) {
        Solution_292 solution = new Solution_292();
        System.out.println("solution.canWinNim() = " + solution.canWinNim(10075));
    }

    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
