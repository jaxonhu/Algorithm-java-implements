package dynamicprogramming;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/10/30
 * @ Time: 9:37 AM
 * @ Project: Algorithm-Java-implements
 */
public class HouseRobber {

    /**
     213. House Robber II
    You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
    All houses at this place are arranged in a circle.
    That means the first house is the neighbor of the last one. Meanwhile,
    adjacent houses have security system connected and it will automatically contact the police
    if two adjacent houses were broken into on the same night.

    Given a list of non-negative integers representing the amount of money of each house,
    determine the maximum amount of money you can rob tonight without alerting the police.

     */

    public static int rob(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        if(len == 1) return nums[0];
        if(len == 2) return Math.max(nums[0], nums[1]);
        int[][] dp = new int[len][len];
        for(int i = 0 ; i < len ; i ++) {
            dp[i][i] = nums[i];
        }
        for(int i = 0 ; i < len; i ++) {
            for(int j = 0 ; j <= i ; j ++) {
                dp[j][i] = Math.max( i - 1 >= j ? dp[j][i - 1] : 0, (i - 2 >= j ? dp[j][i - 2] : 0) + nums[i]);
            }
        }
        return Math.max(dp[0][len-2], dp[1][len-1]);
    }

    public static void main(String[] args) {
        int[] nums = {1 ,2 ,3 ,1};
        System.out.println(rob(nums));
    }
}
