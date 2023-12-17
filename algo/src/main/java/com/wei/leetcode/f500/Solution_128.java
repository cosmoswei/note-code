package com.wei.leetcode.f500;

import java.util.HashSet;
import java.util.Set;

class Solution_128 {
    public int longestConsecutive(int[] nums) {

        Set<Integer> set = new HashSet();
        for (int num : nums) {
            set.add(num);

        }
        int res = 0;
        for (int num : set) {
            int cur = num;
            // 只有当num-1不存在时，才开始向后遍历num+1，num+2，num+3......
            if (!set.contains(cur - 1)) {
                while (set.contains(cur + 1)) {
                    cur++;
                }
            }
            // [num, cur]之间是连续的，数字有cur - num + 1个
            res = Math.max(res, cur - num + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution_128 solution128 = new Solution_128();
        int[] nums = {100, 4, 200, 1, 3, 2};
        int i = solution128.longestConsecutive(nums);
        System.out.println(i);
    }
}