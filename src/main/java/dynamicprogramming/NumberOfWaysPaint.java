package dynamicprogramming;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/12
 * @ Time: 3:26 下午
 * @ Project: Algorithm-Java-implements
 */
public class NumberOfWaysPaint {


    /**
     *
     * 你有一个 n x 3 的网格图 grid ，你需要用 红，黄，绿 三种颜色之一给每一个格子上色，且确保相邻格子颜色不同（也就是有相同水平边或者垂直边的格子颜色不同）。
     *
     * 给你网格图的行数 n 。
     *
     * 请你返回给 grid 涂色的方案数。由于答案可能会非常大，请你返回答案对 10^9 + 7 取余的结果。
     *
     *
     * 题解：
     *  每一行分为 ABA(使用AB两种颜色) 和 ABC(使用三种颜色) 这样的两种情况， 且仅有这两种情况
     *  在ABA的情况下下一行有 三色情况：CAB,ABC 双色情况：BAB,CAC,CBC这样总共5种情况
     *  在ABC的情况下下一样有 三色情况：CAB,BCA 双色情况：BAB,BCB这样总共4种情况，
     *  dp数组中dp[i][0]存当前行双色数量 dp[i][1]存当前行三色数量 因此有如下公式：
     *  dp[i][0] = dp[i-1][0]*3+dp[i-1]*2;
     *  dp[i][1] = dp[i-1][0]*2+dp[i-1]*2;
     *  第一行有6种三色 6种双色 作为初始行
     *

     *
     *
     */

    public int numOfWays(int n) {
        long[][] dp = new long[n][2]; // 0->2   1->3 ;
        dp[0][0] = 6 ;
        dp[0][1] = 6;
        for(int i = 1;i<n;i++){
            dp[i][0] = (dp[i-1][0]*3+dp[i-1][1]*2)%(1000000007);
            dp[i][1] = (dp[i-1][0]*2+dp[i-1][1]*2)%(1000000007);
        }
        return (int) ((dp[n-1][0]+dp[n-1][1])%(1000000007));
    }

}
