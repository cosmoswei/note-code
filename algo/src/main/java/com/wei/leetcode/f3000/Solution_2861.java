package com.wei.leetcode.f3000;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution_2861 {

    public static void main(String[] args) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        Solution_2861 solution = new Solution_2861();
        Integer[] param = {5, 10, 1, 5, 2};
        List<Integer> maxHeights = Arrays.asList(param);
    }

    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int left = 1, right = 200000000, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            boolean valid = false;
            for (int i = 0; i < k; ++i) {
                long spend = 0;
                for (int j = 0; j < n; ++j) {
                    spend += Math.max((long) composition.get(i).get(j) * mid - stock.get(j), 0) * cost.get(j);
                }
                if (spend <= budget) {
                    valid = true;
                    break;
                }
            }
            if (valid) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}


