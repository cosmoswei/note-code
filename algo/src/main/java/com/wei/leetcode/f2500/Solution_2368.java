package com.wei.leetcode.f2500;

import java.util.ArrayList;
import java.util.List;

class Solution_2368 {
    int cnt = 0;

    /*
    作者：力扣官方题解
    链接：https://leetcode.cn/problems/reachable-nodes-with-restrictions/solutions/2654302/shou-xian-tiao-jian-xia-ke-dao-da-jie-di-9qee/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        boolean[] isrestricted = new boolean[n];
        for (int x : restricted) {
            isrestricted[x] = true;
        }

        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int[] v : edges) {
            g[v[0]].add(v[1]);
            g[v[1]].add(v[0]);
        }
        dfs(0, -1, isrestricted, g);
        return cnt;
    }

    public void dfs(int x, int f, boolean[] isrestricted, List<Integer>[] g) {
        cnt++;
        for (int y : g[x]) {
            if (y != f && !isrestricted[y]) {
                dfs(y, x, isrestricted, g);
            }
        }
    }
}
