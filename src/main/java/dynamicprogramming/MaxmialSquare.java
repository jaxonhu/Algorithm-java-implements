package dynamicprogramming;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/11/12
 * @ Time: 4:30 PM
 * @ Project: Algorithm-Java-implements
 */
public class MaxmialSquare {


    /**
     *  Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
     */
    public static int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if(m == 0) return 0;
        int n = matrix[0].length;
        int[][] dp = new int[2][n];
        int max = 0;
        for(int i = 0 ; i < n ; i ++) {
            if(matrix[0][i] == '1') {
                dp[0][i] = 1;
                max = 1;
            }
        }
        for(int j = 0 ; j < m ; j ++) {
            if(matrix[j][0] == '1'){
                max = 1;
            }
        }
        for(int j = 1 ; j < m ; j ++) {
            if(matrix[j][0] == '1') {dp[j%2][0] = 1;}
            else dp[j%2][0] = 0;
            for(int i = 1 ; i < n ; i ++) {
                if(matrix[j][i] == '0') dp[j%2][i] = 0;
                else {
                    dp[j%2][i] = Math.min(Math.min(dp[(j-1)%2][i], dp[j%2][i-1]), dp[(j-1)%2][i-1])  + 1;
                    max = Math.max(max, dp[j%2][i]);
                }
            }

        }
        return max * max;
    }


    public static void main(String[] args) {

        char[][] input = {
                { '1', '1', '1', '1', '1', '1', '1', '1'},
                { '1', '1', '1', '1', '1', '1', '1', '0'},
                { '1', '1', '1', '1', '1', '1', '1', '0'},
                { '1', '1', '1', '1', '1', '0', '0', '0'},
                { '0', '1', '1', '1', '1', '0', '0', '0'},
        };
        System.out.println(maximalSquare(input));
    }
}
