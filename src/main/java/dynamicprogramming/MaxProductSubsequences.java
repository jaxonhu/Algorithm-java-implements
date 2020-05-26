package dynamicprogramming;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/5/26
 * @ Time: 9:13 下午
 * @ Project: Algorithm-Java-implements
 */
public class MaxProductSubsequences {

    /**
     *
     * 给你两个数组 nums1 和 nums2 。
     *
     * 请你返回 nums1 和 nums2 中两个长度相同的 非空 子序列的最大点积。
     *
     * 数组的非空子序列是通过删除原数组中某些元素（可能一个也不删除）后剩余数字组成的序列，但不能改变数字间相对顺序。比方说，[2,3,5] 是 [1,2,3,4,5] 的一个子序列而 [1,5,3] 不是。
     *
     *
     *
     * 示例 1：
     *
     * 输入：nums1 = [2,1,-2,5], nums2 = [3,0,-6]
     * 输出：18
     * 解释：从 nums1 中得到子序列 [2,-2] ，从 nums2 中得到子序列 [3,-6] 。
     * 它们的点积为 (2*3 + (-2)*(-6)) = 18 。
     * 示例 2：
     *
     * 输入：nums1 = [3,-2], nums2 = [2,-6,7]
     * 输出：21
     * 解释：从 nums1 中得到子序列 [3] ，从 nums2 中得到子序列 [7] 。
     * 它们的点积为 (3*7) = 21 。
     * 示例 3：
     *
     * 输入：nums1 = [-1,-1], nums2 = [1,1]
     * 输出：-1
     * 解释：从 nums1 中得到子序列 [-1] ，从 nums2 中得到子序列 [1] 。
     * 它们的点积为 -1 。
     *
     */

    /**
     *  这道题思路和编辑距离很像
     */
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[][] dp = new int[n][m];
        dp[0][0] = nums1[0] * nums2[0];
        for(int i = 1 ; i < m ; i ++) {
            dp[0][i] = Math.max(dp[0][i-1], nums1[0] * nums2[i]);
        }
        for(int i = 1 ; i < n ; i ++) {
            dp[i][0] = Math.max(dp[i-1][0], nums1[i] * nums2[0]);
        }

        for(int i = 1 ; i < n ; i ++) {
            for(int j = 1 ; j < m ; j ++) {
                int multi = nums1[i] * nums2[j];
                dp[i][j] = Math.max(dp[i-1][j-1] + multi, multi);
                dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
                dp[i][j] = Math.max(dp[i][j], dp[i][j-1]);
            }
        }
        return dp[n-1][m-1];
    }
}
