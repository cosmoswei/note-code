package com.wei.leetcode.f1000;

import com.wei.leetcode.LeetCodeUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_870 {

    public static void main(String[] args) {
        Solution_870 solution876 = new Solution_870();
        int[] nums1 = {2, 7, 11, 15}, nums2 = {1, 10, 4, 11};
        int[] res = solution876.advantageCount(nums1, nums2);
        LeetCodeUtils.printArray(res);
    }

    public int[] advantageCount(int[] nums1, int[] nums2) {
        // 给 nums1 升序排序
        Arrays.sort(nums1);
        int n = nums1.length;
        PriorityQueue<int[]> maxpq = new PriorityQueue<>(
                (int[] pair1, int[] pair2) -> pair2[1] - pair1[1]
        );
        for (int i = 0; i < n; i++) {
            maxpq.offer(new int[]{i, nums2[i]});
        }
        Arrays.sort(nums1);
        // nums1[left] 是最小值，nums1[right] 是最大值
        int left = 0, right = n - 1;
        int[] res = new int[n];

        while (!maxpq.isEmpty()) {
            int[] pair = maxpq.poll();
            // maxval 是 nums2 中的最大值，i 是对应索引
            int i = pair[0], maxval = pair[1];
            if (maxval < nums1[right]) {
                // 如果 nums1[right] 能胜过 maxval，那就自己上
                res[i] = nums1[right];
                right--;
            } else {
                // 否则用最小值混一下，养精蓄锐
                res[i] = nums1[left];
                left++;
            }
        }
        return res;
    }
}
