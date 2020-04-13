package dynamicprogramming;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/13
 * @ Time: 4:14 下午
 * @ Project: Algorithm-Java-implements
 */
public class LongestPalindromicSubsequence {


    /**
     *
     * 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
     *
     * 示例 1:
     * 输入:
     *
     * "bbbab"
     * 输出:
     *
     * 4
     * 一个可能的最长回文子序列为 "bbbb"。
     *
     * 示例 2:
     * 输入:
     *
     * "cbbd"
     * 输出:
     *
     * 2
     * 一个可能的最长回文子序列为 "bb"。
     *
     *
     */

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            f[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return f[0][n - 1];
    }

    /**
     *
     * 对比两种dp的填表格方式，第一种是
     *
     *          *   *   *   *   *   *   *
     *          *   *   *   *   *   *   *
     *          *   *   *   *   *   *   *
     *          *   *   *   ^   ^   ^   ^
     *          *   *   *   *   ^   ^   ^
     *          *   *   *   *   *   ^   ^
     *          *   *   *   *   *   *   ^
     *
     * 第二种是， 我感觉第一种会更简单
     *          ^   *   *   *   *   *   *
     *          *   ^   *   *   *   *   *
     *          *   *   ^   *   *   *   *
     *          *   *   *   ^   *   *   *
     *          *   *   *   *   ^   *   *
     *          *   *   *   *   *   ^   *
     *          *   *   *   *   *   *   ^

     */

    public int longestPalindromeSubseq2(String s) {
        int n = s.length();
        if(n == 0) return 0;
        int[][] dp = new int[n][n];
        //dp[i][j]
        for(int k = 0 ; k < n ;k ++) {
            for(int m = 0; m < n - k ; m ++) {
                int i = m;
                int j = i+k;
                if(i == j) {
                    dp[i][j] = 1;
                    continue;
                }
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }


}
