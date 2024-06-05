package com.wei.leetcode.f500;

import java.util.LinkedList;
import java.util.List;

public class Solution_207 {

    public static void main(String[] args) {
    }

    boolean[] onPath;

    boolean[] visited;

    boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }
        return !hasCycle;
    }

    private void traverse(List<Integer>[] graph, int i) {
        if (onPath[i]) {
            hasCycle = true;
        }
        if (visited[i] || hasCycle) {
            return;
        }
        visited[i] = true;
        onPath[i] = true;
        for (Integer t : graph[i]) {
            traverse(graph, t);
        }
        onPath[i] = false;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            graph[from].add(to);
        }
        return graph;
    }
}
