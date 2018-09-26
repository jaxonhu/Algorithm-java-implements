package dynamicprogramming;

import java.util.List;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/9
 * @Time: 上午11:32
 * @Project: Algorithm-Java-implements
 */
public class Triangle {

    /**
     *  求三角形中从顶到底最小路径和
     *  dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1];
     *  要求使用O(n)的空间大小
     *  自上而下
     */

    public int minimumTotal(List<List<Integer>> triangle){
        int n = triangle.size();
        if(n == 0) return 0;

        int[] dp = new int[n];
        for(int i = 0 ; i < n ; ++ i) dp[i] = Integer.MAX_VALUE;
        dp[0] = triangle.get(0).get(0);
        for(int i = 1 ; i < n ; ++ i) {
            for(int j = i ; j >= 0 ; -- j) {
                if(j == 0) {
                    dp[j] = dp[j] + triangle.get(i).get(j);
                }else {
                    dp[j] = Math.min(dp[j],dp[j-1]) + triangle.get(i).get(j);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0 ; i < n ; ++ i) {
            min = Math.min(min, dp[i]);
        }
        return min;
    }


    /**
     *  用二维dp数组 自下而上， 懒得写了，有时间再补充吧
     */

    public int minmumTotal3(List<List<Integer>> triangle) {
        int res = 0;


        return res;
    }

    /**
     * 如果采用自下而上的呢，状态转移会是设么样
     * dp[m][n] = Math.min(dp[m+1][n], dp[m+1][n+1] + triangle[m][n]
     *
     * 为什么这个必须从左向右计算了？
     * 因为对右边有依赖
     */

    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        if(n == 0) return 0;

        int[] dp = new int[n];
        for(int i = 0 ; i < n ; ++ i) dp[i] = triangle.get(n-1).get(i);
        for(int i = n - 2 ; i >= 0 ; -- i) {
            for(int j = i ; j >= 0 ; -- j) {
                dp[j] = Math.min(dp[j],dp[j+1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }


}
