package dynamicprogramming;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/21
 * @ Time: 11:55 下午
 * @ Project: Algorithm-Java-implements
 */
public class BuildArrayMaximumExactlyKComparisons {

    /**
     * 1420. 生成数组
     *
     * 请你生成一个具有下述属性的数组 arr ：
     *
     * arr 中有 n 个整数。
     * 1 <= arr[i] <= m 其中 (0 <= i < n) 。
     * 将上面提到的算法应用于 arr ，search_cost 的值等于 k 。
     * 返回上述条件下生成数组 arr 的 方法数 ，由于答案可能会很大，所以 必须 对 10^9 + 7 取余。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/build-array-where-you-can-find-the-maximum-exactly-k-comparisons
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     * 题解：根据题目条件，数组中如果当前元素比之前的元素最大值更大，则代价+1，否则代价不变
     *  dp[i][k][m] 表示当前数组i个元素，总的代价为k，到第i个元素为止最大元素为m
     *
     *  则暴力穷举i k m 即可，
     *  状态转移方程如下
     *      假设，当前枚举到第i个元素，枚举到第j个代价，前一个元素值为p
     *      则当前元素值枚举范围为1~m, 假设为q，则
     *          如果 q > p, 则，更新最大值为q
     *              dp[i][j][q] = dp[i][j][q] + dp[i][j-1][p];
     *          如果 q <=p, 则，不用更新
     *              dp[i][j][p] = dp[i][j][p] + dp[i-1][j][p];
     *
     */

    int[][][] dp; // dp[i][k][m] 表示当前数组i个元素，总的代价为k，到第i个元素为止最大元素为m
    int mod = 1000000007;
    public int numOfArrays(int n, int m, int k) {
        dp = new int[n+1][k+1][m+1];
        int ans = 0;
        dp[0][0][0] = 1;
        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= k ; j ++) {
                for(int p = 0 ; p <= m ; p ++) {
                    for(int q = 1 ; q <= m ; q ++) {
                        if(q > p) {
                            dp[i][j][q] = (dp[i][j][q] + dp[i-1][j-1][p]) % mod;
                        } else {
                            dp[i][j][p] = (dp[i][j][p] + dp[i-1][j][p]) % mod;
                        }
                    }
                }
            }
        }
        for(int i = 1 ; i <= m ; i ++) {
            ans = (ans + dp[n][k][i]) % mod;
        }
        return ans;
    }
}
