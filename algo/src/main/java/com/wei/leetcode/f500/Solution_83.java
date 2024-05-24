package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

public class Solution_83 {

    public static void main(String[] args) {
        Solution_83 solution26 = new Solution_83();
        int[] nums = {1, 2, 3, 4, 5, 5, 6, 7, 7, 7, 7, 8, 8, 8, 9};
        ListNode listNode = LeetCodeUtils.arrayToList(nums);
        ListNode res = solution26.deleteDuplicates(listNode);
        LeetCodeUtils.printList(res);
    }

    ListNode deleteDuplicates(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (null != fast) {
            if (fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }
}
