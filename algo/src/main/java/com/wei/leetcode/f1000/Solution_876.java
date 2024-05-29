package com.wei.leetcode.f1000;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

public class Solution_876 {

    public static void main(String[] args) {
        Solution_876 solution876 = new Solution_876();
        int[] nums = {1, 2, 3, 4, 5, 6};
        ListNode listNode = LeetCodeUtils.arrayToList(nums);
        ListNode res = solution876.middleNode(listNode);
        LeetCodeUtils.printList(res);
    }

    ListNode middleNode(ListNode head) {
        // 快慢指针初始化指向 head
        ListNode slow = head, fast = head;
        // 快指针走到末尾时停止
        while (fast != null && fast.next != null) {
            // 慢指针走一步，快指针走两步
            slow = slow.next;
            fast = fast.next.next;
        }
        // 慢指针指向中点
        return slow;
    }

}
