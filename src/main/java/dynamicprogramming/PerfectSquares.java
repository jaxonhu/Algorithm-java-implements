package dynamicprogramming;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/9
 * @Time: 下午3:59
 * @Project: Algorithm-Java-implements
 */
public class PerfectSquares {


    /**
     * 至少用多少个完全平方数
     * 空间换时间，用长度为n的数组记录到位置i的所用的最小完全平方数的个数
     * j表示是从1的基础上依次加上每个整数的完全平方 dp[i+j*j] = Math.min(i+1, dp[i+j*j])
     */
    public int numSquares(int n) {
        if(n == 0) return 0;
        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int i = 1 ; i <= n ; ++ i) dp[i] = Integer.MAX_VALUE;
        for(int i = 0 ; i < n ; ++ i) {
            int j = 1;
            while( i + j * j <= n) {
                dp[i+j*j] = Math.min(dp[i]+1, dp[i+j*j]);
                j += 1;
            }
        }
        return dp[n];
    }

}
