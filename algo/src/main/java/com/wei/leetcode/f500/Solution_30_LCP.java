package com.wei.leetcode.f500;

import java.util.PriorityQueue;

public class Solution_30_LCP {

    public static void main(String[] args) {
        Solution_30_LCP solution = new Solution_30_LCP();
        int[] int1 = {100, 100, 100, -250, -60, -140, -50, -50, 100, 150};
        int ints = solution.magicTower(int1);
        System.out.println("ints = " + ints);
    }

    /*
    作者：灵茶山艾府
    链接：https://leetcode.cn/problems/p0NxJO/solutions/2633172/fan-hui-tan-xin-fu-ti-dan-pythonjavacgoj-hxup/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int magicTower(int[] nums) {
        long sum = 0;
        for (int x : nums) {
            sum += x;
        }
        if (sum < 0) {
            return -1;
        }

        int ans = 0;
        long hp = 1;
        PriorityQueue<Integer> h = new PriorityQueue<>();
        for (int x : nums) {
            if (x < 0) {
                h.offer(x);
            }
            hp += x;
            if (hp < 1) {
                // 这意味着 x < 0，所以前面必然会把 x 入堆
                // 所以堆必然不是空的，并且堆顶 <= x
                hp -= h.poll();
                ans++;
            }
        }
        return ans;
    }
}