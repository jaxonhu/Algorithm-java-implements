package dynamicprogramming;

import java.util.Arrays;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/16
 * @Time: 下午4:54
 * @Project: Algorithm-Java-implements
 */
public class LongestIncreasingSubsequence {



    /**
     * 最长上升子序列的长度 时间复杂度O(n^2)
     *
     *   4 5 2 8 9 1 0
     *   1 1 1 1 1 1 1
     */

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for(int i = 0 ; i < n ; ++ i) {
            for(int j = 0 ; j < i ; ++ j) {
                if(nums[i] > nums[j])
                    dp[i] = Math.max(dp[i],dp[j] + 1);
            }
        }
        int res = 0;
        for(int i = 0 ; i < n ; ++ i) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
