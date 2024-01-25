package com.wei.leetcode.f3000;


import java.util.Arrays;
import java.util.List;

public class Solution_2859 {

    public static void main(String[] args) {
        Solution_2859 solution = new Solution_2859();
        Integer[] param = {5, 10, 1, 5, 2};
        List<Integer> maxHeights = Arrays.asList(param);
        long i = solution.sumIndicesWithKSetBits(maxHeights, 1);
        System.out.println("i = " + i);
    }

    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (bitCount(i) == k) {
                ans += nums.get(i);
            }
        }
        return ans;
    }

    public int bitCount(int x) {
        int cnt = 0;
        while (x != 0) {
            cnt += (x % 2);
            x /= 2;
        }
        return cnt;
    }

    public int bitCount2(int x) {
        x = (x & 0b0101010101) + ((x & 0b1010101010) >> 1);
        x = ((x & 0b0011001100) >> 2) + (x & 0b1100110011);
        x = (x >> 8) + ((x >> 4) & 0b1111) + (x & 0b1111);
        return x;
    }


}


