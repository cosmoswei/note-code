package com.wei.vjudge;

import java.util.Arrays;

public class Contest_601670 {
    public static void main(String[] args) {
        int[] nums = {232, 124, 456, 119, 19876};
        int solution = solution(5, 4, nums);
        System.out.println(solution);
    }

    private static int solution(int n, int k, int[] nums) {
        int res = 0;
        if (null == nums || nums.length == 0) {
            return res;
        }
        int sum = Arrays.stream(nums).sum() / n;
        for (int i = sum; i >= 1; i--) {
            int len = 0;
            for (int j = 0; j < nums.length; j++) {
                int res1 = nums[j] / i;
                len += res1;
            }
            if (len == k) {
                return i;
            }
        }
        return res;
    }
}
