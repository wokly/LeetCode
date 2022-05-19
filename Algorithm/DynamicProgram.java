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

    public int numDecodings(String s) {
        int n = s.length();
        // a = f[i-2], b = f[i-1], c=f[i]
        int a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; ++i) {
            c = 0;
            if (s.charAt(i - 1) != '0') {
                c += b;
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                c += a;
            }
            a = b;
            b = c;
        }
        return c;
    }

    public static void main(String[] args) {
        String s="12345600";
        DynamicProgram dynamicProgram = new DynamicProgram();
        int result = dynamicProgram.numDecodings(s);
        System.out.println("result = " + result);
    }
}
