package dynamicprogramming;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/7
 * @Time: 下午8:30
 * @Project: Algorithm-Java-implements
 */
public class BackPackage {

    /**
     * 记忆化搜索方法
     */
    public int bestValueDFS(int[] product, int[] weight, int bag) {

        return 0;
    }
    /**
     * 动态规划 01 背包问题  dp[i][j] 表示前i个物品，包大小为j
     */

    public int bestValue(int[] product, int[] weight, int bag) {
        int n = product.length;
        int[][] dp = new int[n][bag+1];
        if(n == 0) return 0;
        for(int i = 0 ; i <= bag ; ++ i) {
            if(i >= weight[0])
                dp[0][i] = product[0];
        }
        for(int i = 1 ; i < n ; ++ i) {
            for(int j = 0 ; j <= bag ; ++ j) {
                dp[i][j] = dp[i-1][j];
                if(weight[i] <= j) {
                    dp[i][j] = Math.max(dp[i][j], product[i] + dp[i-1][j - weight[i]]);
                }
            }
        }
        return dp[n-1][bag];
    }

    /**
     *  优化：O(n*m) -> O(2*m),因为只用到当前行和上一行，所以交替存储
     */
    public int bestValue2(int[] product, int[] weight, int bag) {
        int n = product.length;
        int[][] dp = new int[2][bag+1];
        for(int i = 0 ; i <= bag ; ++ i) {
            if(i >= weight[0])
                dp[0][i] = product[0];
        }
        for(int i = 1 ; i < n ; ++ i) {
            for(int j = 0 ; j <= bag ; ++ j) {
                dp[i%2][j] = dp[(i-1)%2][j];
                if(weight[i] <= j) {
                    dp[i%2][j] = Math.max(dp[i%2][j], product[i] + dp[(i-1)%2][j - weight[i]]);
                }
            }
        }
        return dp[(n-1)%2][bag];
    }

    /**
     * 优化：也可以只用O(m)的空间解决
     */

    public int bestValue4(int[] product, int[] weight, int bag) {
        int n = product.length;
        int[] dp = new int[bag+1];
        for(int i = 0 ; i <= bag ; ++ i) {
            if(i >= weight[0])
                dp[i] = product[0];
        }
        for(int i = 1 ; i < n ; ++ i) {
            for(int j = bag ; j >= 0 ; -- j) {
                if(weight[i] <= j) {
                    dp[j] = Math.max(dp[j], product[i] + dp[j - weight[i]]);
                }
            }
        }
        return dp[bag];
    }



    /**
     * 动态规划 完全背包 每个物品的个数有无限个
     */

    public int bestValue3(int[] product, int[] weight, int bag) {
        int n = product.length;
        int[][] dp = new int[n][bag+1];
        for(int i = 0 ; i < bag ; ++ i) {
            dp[0][i] = 0;
        }
        for(int i = 1 ; i < n ; ++ i) {
            for(int j = 0 ; j <= bag ; ++ j) {
                dp[i][j] = dp[i-1][j];
                for(int k = 0 ; k <= bag / weight[i] ; ++ k) {
                    if(j >= k * weight[i]) {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - k*weight[i]] + k*product[i]);
                    }
                }
            }
        }
        return dp[n-1][bag];
    }

    /**
     * 完全背包优化，不依赖于i-1的状态
     */

    public int bestValue5(int[] product, int[] weight, int bag) {
        int n = product.length;
        int[][] dp = new int[n][bag+1];
        for(int i = 0 ; i < bag ; ++ i) {
            dp[0][i] = 0;
        }
        for(int i = 0 ; i < n ; ++ i) {
            for(int j = 1 ; j <= bag ; ++ j) {
                if(weight[i] <= j) {
                    dp[i][j]  = Math.max(dp[i-1][j], dp[i][j - weight[i]] + product[i]);
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n-1][bag];
    }

    /**
     * 改为一维数组
     */

    public int bestValue6(int[] product, int[] weight, int bag) {
        int n = product.length;
        int[] dp = new int[bag+1];
        for(int i = 0 ; i < n ; ++ i) {
            for(int j = weight[i] ; j <= bag ; ++ j) {
                if(weight[i] < j) {
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + product[i]);
                }else {
                    dp[j] = dp[j];
                }
            }
        }
        return dp[bag];
    }

    /**
     * 多重背包问题：多重背包问题限制每一类物品的个数为nums[i]，与完全背包问题相比区别在于限制了个数不超过nums[i]
     */

    public int bestValue7(int[] product, int[] weight, int[] nums, int bag) {
        int n = product.length;
        int[][] dp = new int[n+1][bag+1];
        for(int i = 1 ; i <= n ; ++ i) {
            for(int j = 0 ; j  <= bag ; ++ j) {
                int max_num = Math.min(bag/weight[-1], nums[i-1]);
                for(int k = 0 ; k <= max_num ; ++ k) {
                    dp[i-1][j] = Math.max(dp[i-1][j], dp[i-1][j - k * weight[i-1]]);
                }
            }
        }
        return dp[n][bag];
    }

}
