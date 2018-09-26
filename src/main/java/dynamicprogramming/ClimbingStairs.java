package dynamicprogramming;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/9
 * @Time: 上午11:12
 * @Project: Algorithm-Java-implements
 */
public class ClimbingStairs {

    /**
     *  一次可以爬1阶或者2阶，问爬到n阶可以有几种方式
     *
     *  先来个递归版本
     */

    public int climbStairs(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        return climbStairs(n-1) + climbStairs(n-2);
    }

    /**
     * 改造成记忆化搜索
     */

    public int climbStairs2(int n) {
        int[] memo = new int[n+1];
        return recursive(n, memo);
    }

    public int recursive(int n, int[] memo) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        int res = 0;
        if(memo[n-1] != 0)
            res += memo[n-1];
        else
            res += recursive(n-1, memo);

        if(memo[n-2] != 0)
            res += memo[n-2];
        else
            res += recursive(n-2, memo);
        if(memo[n] == 0) memo[n] = res;
        return res;
    }

    /**
     * 改造成动态规划
     */

    public int climbStairs3(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for(int i = 2 ; i < n ; ++ i) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }

    /**
     * 再次优化，其实用三个变量就可以表示了
     */

    public int climbStairs4(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        int f1 = 1;
        int f2 = 2;
        int fn = 0;
        for(int i = 2 ; i < n ; ++ i) {
            fn = f1 + f2;
            f1 = f2;
            f2 = fn;
        }
        return fn;
    }
}
