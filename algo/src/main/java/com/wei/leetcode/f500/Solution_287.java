package com.wei.leetcode.f500;

import java.util.Arrays;

public class Solution_287 {


    public static void main(String[] args) {
        Solution_287 solution = new Solution_287();
        int[] num = {1, 3, 4, 2, 2};
        System.out.println(solution.findDuplicate(num));
    }

    public int findDuplicate(int[] num) {
        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            if (num[i] == num[i + 1]) {
                return num[i];
            }
        }
        return -1;
    }

    /**
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/find-the-duplicate-number/solutions/261119/xun-zhao-zhong-fu-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int findDuplicate2(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }


}

