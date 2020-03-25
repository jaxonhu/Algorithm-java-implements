package dynamicprogramming;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/3/25
 * @ Time: 9:50 PM
 * @ Project: Algorithm-Java-implements
 */
public class EggsTest {

    /**
     *
     * N: 一共有N层楼
     *
     * K: 一共有K个鸡蛋
     *
     *  m 层扔
     *
     * dp[i][j] = Math.max(dp[i-1][j-1], dp[i][j-m]) + 1
     */


    static int resolve(int N, int K) {
        int[][] dp = new int[K+1][N+1];

        for(int i  = 1 ; i <= N ; i ++) {
            dp[0][i] = 0;
            dp[1][i] = i;
        }
        for(int i = 1 ; i <= K ; i ++) {
            dp[i][0] = 0;
            dp[i][1] = 1;
        }

        for(int i = 2 ; i <= K ; i++ ) {
            for(int j = 2 ; j <= N ; j ++ ) {
                int res = Integer.MAX_VALUE;
                for(int m = 2 ; m <= j ; m ++) {
                    res = Math.min(res, 1 + Math.max(dp[i-1][m-1], dp[i][j-m]));
                }
                dp[i][j] = res;
            }
        }
        return dp[K][N];
    }

    public static void main(String[] args) {
        System.out.println(resolve(100, 2));
    }

}
