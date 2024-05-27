package com.wei.leetcode.f500;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution_77 {

    public static void main(String[] args) {
        System.out.println(new Solution_77().combine(4, 2));
    }

    List<List<Integer>> res = new ArrayList<>();

    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack(1, n, k);
        return res;
    }

    void backtrack(int start, int n, int k) {
        if (track.size() == k) {
            res.add(new ArrayList<>(track));
        }
        for (int i = start; i <= n; i++) {
            track.add(i);
            backtrack(i + 1, n, k);
            track.removeLast();
        }

    }
}
