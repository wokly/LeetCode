package Algorithm;

import java.util.Arrays;

public class DynamicProgram {
    /**
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/
     * 300. 最长上升子序列 O(N^2)
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if(nums[i]>nums[j]){
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
