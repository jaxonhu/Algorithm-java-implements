package dynamicprogramming;

import java.util.Arrays;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/16
 * @Time: 下午1:50
 * @Project: Algorithm-Java-implements
 */
public class CoinChange {

    /**
     *  leetcode 322  Coin Change ，完全背包
     *  钱币：[1,2,5]  target  11   最少3个(5 + 5 + 1)
     *
     *  看不出来哪里不对啊
     */

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        if(n == 0) return -1;
        Arrays.sort(coins);
        int[][] dp = new int[n][amount+1];

        for(int j = 0 ; j <= amount ; ++ j) {
            if((j % coins[0]) == 0) {
               dp[0][j] = j / coins[0];
            }else
                dp[0][j] = Integer.MAX_VALUE;
        }
        for(int i = 1 ; i < n ; ++ i) {
            for(int j = 0 ; j <= amount ; ++ j) {
                dp[i][j] = dp[i-1][j];
                for(int k = 0 ; k <= amount/coins[i] ; ++ k) {
                    if(j >= k * coins[i] && dp[i-1][j - k * coins[i]] != Integer.MAX_VALUE)
                        dp[i][j] = Math.min(dp[i-1][j - k * coins[i]] + k, dp[i-1][j]);
                }
            }
        }
        if(dp[n-1][amount] == Integer.MAX_VALUE) return -1;
        return dp[n-1][amount];
    }


    /**
     *  写成这样就可以ac，上面就不对，比较费解，晚上再看看
     */
    public int coinChange2(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        for(int i = 0 ; i <= amount ; ++ i) {
            if((i % coins[0]) == 0)
                dp[i] = i / coins[0];
            else
                dp[i] = Integer.MAX_VALUE;
        }

        for(int i = 1 ; i < n ; ++ i ) {
            for(int j = amount ; j >= 0 ; -- j) {
                for(int k = 0 ; k <= j/coins[i] ; ++ k) {
                    if(j >= k * coins[i] && dp[j - k*coins[i]] != Integer.MAX_VALUE) {
                        dp[j] = Math.min(dp[j], dp[j - k*coins[i]] + k);
                    }
                }
            }
        }
        if(dp[amount] == Integer.MAX_VALUE) return -1;
        return dp[amount];
    }



    public static void main(String[] args) {
        int[] coins = {1,2};
        int amount = 2;
        CoinChange cc = new CoinChange();
        System.out.println(cc.coinChange(coins, amount));
    }


}
