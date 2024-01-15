package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

public class Solution_82 {

    public static void main(String[] args) {
        Solution_82 solution = new Solution_82();
        int[] nums = {1, 2, 3, 3, 4, 4, 5};
        ListNode listNode = LeetCodeUtils.arrayToList(nums);
        ListNode listNode1 = solution.deleteDuplicates(listNode);
        LeetCodeUtils.printList(listNode1);
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 头节点重复
        if (head.val == head.next.val) {
            int val = head.val;
            while (head != null && head.val == val) {
                head = head.next;
            }
            return deleteDuplicates(head);
        }
        // 头节点不重复
        head.next = deleteDuplicates(head.next);
        return head;
    }
}
