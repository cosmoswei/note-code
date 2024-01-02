package com.wei.leetcode.f500;


import com.wei.leetcode.ListNode;

public class Solution_2 {

    public static void main(String[] args) {

        Solution_2 leetCode1 = new Solution_2();
        int[] nums = {2, 7, 11, 15};
        int[] nums2 = {2, 4, 3};
        int[] num3 = {5, 6, 4};
        ListNode listNode2 = getListNode(nums2);
        System.out.println(listNode2);
        ListNode listNode3 = getListNode(num3);
        System.out.println(listNode3);
        Integer i2 = Integer.valueOf(listNode2.toString());
        Integer i3 = Integer.valueOf(listNode3.toString());
        int i4 = i2 + i3;
        String s = String.valueOf(i4);
        int[] num4 = new int[s.length()];

        ListNode listNode1 = constructListNode(s);
        ListNode listNode = constructListNode(num4);
        System.out.println(listNode1);
        System.out.println(leetCode1.addTwoNumbers(listNode2, listNode3));
    }

    public static ListNode constructListNode(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        String listStr = str.replaceAll(" ", "");
        String[] numbersStrArray;
        if (listStr.charAt(0) == '[' && listStr.charAt(listStr.length() - 1) == ']') {
            listStr = listStr.substring(1, listStr.length() - 1);
            numbersStrArray = listStr.split(",");
        } else if (listStr.contains("->")) {
            numbersStrArray = listStr.split("->");
        } else {
            numbersStrArray = new String[1];
            numbersStrArray[0] = listStr;
        }

        int numLength = 0;
        if (numbersStrArray.length > 1) {
            if (numbersStrArray[numbersStrArray.length - 1].equalsIgnoreCase("null")) {
                numLength = numbersStrArray.length - 1;
            } else {
                numLength = numbersStrArray.length;
            }
        } else {
            numLength = 1;
        }

        int[] numbers = new int[numLength];
        for (int i = 0; i < numLength; i++) {
            numbers[i] = Integer.parseInt(numbersStrArray[i]);
        }

        return constructListNode(numbers);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return new ListNode();
    }

    public static ListNode constructListNode(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return null;
        }

        ListNode dummyNode = new ListNode(-1);
        ListNode preNode = dummyNode;
        for (int i = 0; i < numbers.length; i++) {
            ListNode currNode = new ListNode(numbers[i]);
            preNode.next = currNode;
            preNode = preNode.next;
        }

        return dummyNode.next;
    }

    private static ListNode toListNode(int[] nums) {
        ListNode listNode = new ListNode();
        listNode.val = nums[0];
        for (int i = 0; i < nums.length - 1; i++) {
            listNode.next = new ListNode(nums[i + 1]);
        }
        return listNode;
    }

    private static ListNode getListNode(int[] nums) {
        // 临界值判断
        if (nums == null || nums.length == 0) {
            return null;
        }

        // 第一个元素作为头节点
        ListNode head = new ListNode(nums[0]);
        // 保存当前节点引用
        ListNode p = head;
        // 从第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            // 创建节点
            ListNode tmp = new ListNode(nums[i]);
            // 关联到上个节点
            p.next = tmp;

            // 更新下次关联的节点
            p = tmp;
        }
        // 最终返回头节点, 而不是临时的 p
        return head;

    }

}
