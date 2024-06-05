package com.wei.leetcode.f1000;

import java.util.LinkedList;
import java.util.List;

class Solution_797 {


    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, 0, path);
        return res;
    }

    private void traverse(int[][] graph, int i, LinkedList<Integer> path) {
        path.addLast(i);
        int n = graph.length;
        if (i == n - 1) {
            res.add(new LinkedList<>(path));
        }
        for (int v : graph[i]) {
            traverse(graph, v, path);
        }
        path.removeLast();
    }
}