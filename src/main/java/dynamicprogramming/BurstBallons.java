package dynamicprogramming;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/3/24
 * @ Time: 9:10 PM
 * @ Project: Algorithm-Java-implements
 */
public class BurstBallons {


    /**
     *
     *  Leetcode 312.
     *
     *  Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
     *  You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
     *  Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
     *
     * Find the maximum coins you can collect by bursting the balloons wisely.
     *
     *
     * 题解：https://www.cnblogs.com/grandyang/p/5006441.html
     *
     *
     */

    public int maxCoins(int[] nums) {
        int  n = nums.length;
        if (n == 0) return 0;
        int[][] dp = new int[n][n];
        for(int k = 0 ; k < n ; k ++) {
            for(int i = 0 ; i < n - k ; i++) {
                int j = i + k;
                for(int m =  i ; m <= j ; m ++) {
                    dp[i][j] = Math.max(dp[i][j], get(nums, i-1) * get(nums, m) * get(nums, j+1) + (m == 0?0:dp[i][m-1]) + ((m == n-1)?0:dp[m+1][j]));
                }
            }
        }
        return dp[0][n-1];
    }

    int get(int[] nums, int index) {
        if(index < 0 || index > nums.length - 1) {
            return 1;
        }
        return nums[index];
    }
}
