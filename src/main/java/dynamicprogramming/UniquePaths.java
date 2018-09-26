package dynamicprogramming;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/8
 * @Time: 下午3:58
 * @Project: Algorithm-Java-implements
 */
public class UniquePaths {

    /**
     * 🤖 机器人 找路径 看有多少种
     */

    public int uniquePaths(int m, int n) {
        if(m == 0 || n  == 0)
            return 0;
        int[][] memo = new int[m][n];
        for(int i = 0 ; i < n ; ++ i) {
            memo[0][i] = 1;
        }
        for(int i = 0 ; i < m ; ++ i) {
            memo[i][0] = 1;
        }
        for(int i = 1 ; i < m ; ++ i) {
            for(int j = 1 ; j < n ; ++ j) {
                memo[i][j] = memo[i-1][j] + memo[i][j-1];
            }
        }
        return memo[m-1][n-1];
    }

    /**
     * 在格子中可以放置障碍物
     */

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        if(n == 0) return 0;
        int m = obstacleGrid[0].length;
        int[][] memo = new int[n][m];
        boolean flag = false;
        for(int i = 0 ; i < m ; ++ i) {
            if(flag) {
                memo[0][i] = 0;
            } else {
                if(obstacleGrid[0][i] == 1){
                    flag = true;
                    memo[0][i] = 0;
                }else {
                    memo[0][i] = 1;
                }
            }
        }
        flag = false;
        for(int i = 0 ; i < n ; ++ i) {
            if(flag) {
                memo[i][0] = 0;
            } else {
                if(obstacleGrid[i][0] == 1){
                    flag = true;
                    memo[i][0] = 0;
                }else {
                    memo[i][0] = 1;
                }
            }
        }
        for(int i = 1 ; i < n ; ++ i) {
            for(int j = 1 ; j < m ; ++ j) {
                if(obstacleGrid[i][j] == 1){
                    memo[i][j] = 0;
                }else {
                    memo[i][j] = memo[i-1][j] + memo[i][j-1];
                }
            }
        }
        return memo[n-1][m-1];
    }
}
