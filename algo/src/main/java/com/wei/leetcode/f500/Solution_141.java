package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

class Solution_141 {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (null != fast && null != fast.next) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution_141 solution = new Solution_141();
        int[] nums = {100, 4, 200, 1, 3, 2};
        boolean hasCycle = solution.hasCycle(LeetCodeUtils.arrayToList(nums));
        System.out.println("hasCycle = " + hasCycle);
    }
}