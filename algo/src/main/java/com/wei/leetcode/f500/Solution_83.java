package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

public class Solution_83 {

    public static void main(String[] args) {
        Solution_83 solution26 = new Solution_83();
        int[] nums = {1, 2, 3, 4, 5, 5, 6, 7, 7, 7, 7, 8, 8, 8, 9};
        ListNode listNode = LeetCodeUtils.arrayToList(nums);
        ListNode res = solution26.deleteDuplicates1(listNode);
        LeetCodeUtils.printList(res);
    }


    ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode slow = head, fast = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                // nums[slow] = nums[fast];
                slow.next = fast;
                // slow++;
                slow = slow.next;
            }
            // fast++
            fast = fast.next;
        }
        // 断开与后面重复元素的连接
        slow.next = null;
        return head;
    }

    ListNode deleteDuplicates1(ListNode head) {
        if (head == null) return null;
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
