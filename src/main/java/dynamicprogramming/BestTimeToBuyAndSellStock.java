package dynamicprogramming;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/8
 * @Time: 下午3:29
 * @Project: Algorithm-Java-implements
 */
public class BestTimeToBuyAndSellStock {


    /**
     * 限制最多只有两次交易，定义local[i][j]为局部最优，表示到第i天交易j次所能获得的最大利润，且在第i天完成最后一次交易。
     * 定义global[i][j]为全局最优，表示到第i天交易j次所能获得的最大利润。
     */

    public int maxProfit(int[] prices){
        int profit = 0;
        int n = prices.length;
        int[][] local = new int[n][3];
        int[][] global = new int[n][3];

        for(int i = 1 ; i < n ; ++ i) {
            int diff = prices[i] - prices[i-1];
            for(int j = 1 ; j < 3 ; ++ j) {
                local[i][j] = Math.max(local[i-1][j-1] + Math.max(diff, 0), local[i-1][j] + diff);
                global[i][j] = Math.max(local[i][j], global[i-1][j]);
            }
        }
        return global[n-1][2];
    }
}
