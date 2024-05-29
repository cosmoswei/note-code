package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

public class Solution_25 {

    public static void main(String[] args) {
        Solution_25 solution19 = new Solution_25();
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        ListNode listNode = LeetCodeUtils.arrayToList(nums);
        ListNode res = solution19.reverseKGroup2(listNode, 3);
        LeetCodeUtils.printList(res);
    }


    public ListNode reverseKGroup2(ListNode head, int k) {
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next)
            ++n; // 统计节点个数

        ListNode dummy = new ListNode(0, head), p0 = dummy;
        ListNode pre = null, cur = head;
        for (; n >= k; n -= k) {
            for (int i = 0; i < k; ++i) { // 同 92 题
                ListNode nxt = cur.next;
                cur.next = pre; // 每次循环只修改一个 next，方便大家理解
                pre = cur;
                cur = nxt;
            }

            // 见视频
            ListNode nxt = p0.next;
            p0.next.next = cur;
            p0.next = pre;
            p0 = nxt;
        }
        return dummy.next;
    }

    ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    /**
     * 反转区间 [a, b) 的元素，注意是左闭右开
     */
    ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;
        nxt = a;
        // while 终止的条件改一下就行了
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }
}
