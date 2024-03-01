package com.wei.leetcode.f2500;

class Solution_2369 {

    /*
    作者：力扣官方题解
    链接：https://leetcode.cn/problems/check-if-there-is-a-valid-partition-for-the-array/solutions/2654315/jian-cha-shu-zu-shi-fou-cun-zai-you-xiao-8597/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            if (i >= 2) {
                dp[i] = dp[i - 2] && validTwo(nums[i - 2], nums[i - 1]);
            }
            if (i >= 3) {
                dp[i] = dp[i] || (dp[i - 3] && validThree(nums[i - 3], nums[i - 2], nums[i - 1]));
            }
        }
        return dp[n];
    }
    
    public boolean validTwo(int num1, int num2) {
        return num1 == num2;
    }
    
    public boolean validThree(int num1, int num2, int num3) {
        return (num1 == num2 && num1 == num3) || (num1 + 1 == num2 && num2 + 1 == num3);
    }
}

