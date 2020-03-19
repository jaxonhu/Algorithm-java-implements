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
        int n = prices.length;
        int[][] local = new int[n][3];
        int[][] global = new int[n][3];

        for(int i = 1 ; i < n ; ++ i) {
            int diff = prices[i] - prices[i-1];
            for(int j = 1 ; j < 3 ; ++ j) {
                local[i][j] = Math.max(global[i-1][j-1] + Math.max(diff, 0), local[i-1][j] + diff); //make the deal
                global[i][j] = Math.max(local[i][j], global[i-1][j]);// maybe not make the deal
            }
        }
        return global[n-1][2];
    }


    /**
     * leetcode 309: Best Time to Buy and Sell Stock with Cooldown
     *
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
     *
     * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
     * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
     *
     * 限制两次交易之间必须间隔一天
     *
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75927/Share-my-thinking-process
     *
     */

    public int maxProfit2(int[] prices) { // 动规一定要注意边界的处理
        int n = prices.length;
        if(n == 0 || n == 1) return 0;
        int[] buys = new int[n+1];
        int[] sells = new int[n+1];
        buys[1] = -prices[0];
        for(int i = 2  ; i <= n ; i ++) {
            int price = prices[i-1];
            buys[i] = Math.max(buys[i-1], sells[i-2] - price);
            sells[i] = Math.max(sells[i-1], buys[i-1] + price);
        }
        return sells[n];
    }

    public static void main(String[] args) {
        int[] input = {1,2,4};
        BestTimeToBuyAndSellStock instance = new BestTimeToBuyAndSellStock();
        System.out.println(instance.maxProfit2(input));
    }
}
