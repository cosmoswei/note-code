package com.wei.leetcode.f500;

import com.wei.leetcode.ListNode;

class Solution_237 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

