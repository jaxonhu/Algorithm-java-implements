package dynamicprogramming;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/16
 * @Time: 下午3:30
 * @Project: Algorithm-Java-implements
 */
public class OnesAndZeros {

    /**
     *  leetcode 474  这道题题目比较长，就不再赘述
     *  思路：{"10", "0001", "111001", "1", "0"} 相当于物品
     *  5个0，3个1相当于背包 dp[i][j]表示i个1，j个0
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length][m+1][n+1];

        for(int i = 0 ; i <= m ; ++ i) {
            for(int j = 0 ; j <= n ; ++ j) {
                int[] cnt = w(strs[0]);
                if(cnt[0] <= i && cnt[1] <= j)
                    dp[0][i][j] = 1;
            }
        }

        for(int i = 1 ; i < strs.length ; ++ i) {
            int[] cnt = w(strs[i]);
            for(int mi = 0 ; mi <= m ; mi ++) {
                for(int ni = 0 ; ni <= n ; ni ++ ) {
                    if(mi == 0 && ni == 0)
                        dp[i][m][n] = 0;
                    else if(cnt[0] <= mi && cnt[1] <= ni) {
                        dp[i][mi][ni] = Math.max(1 + dp[i-1][mi-cnt[0]][ni - cnt[1]], dp[i-1][mi][ni]);
                    }else {
                        dp[i][mi][ni] = dp[i-1][mi][ni];
                    }
                }
            }
        }
        return dp[strs.length-1][m][n];
    }

    int[] w(String s) {
        int[] i = new int[2];
        for(char c: s.toCharArray()) {
            i[(c == '0' ? 0 : 1)]++;
        }

        return i;
    }


    /**
     * 我也觉得用二维dp就可以解决了，下面答案是从leetcode粘来的
     */

    public int findMaxForm2(String[] strs, int m, int n) {
        int[][] table = new int[m+1][n+1];
        for (String s : strs) {
            int z = 0;
            for (int i = 0; i < s.length(); i++)
                if (s.charAt(i) == '0')
                    z++;
            int o = s.length() - z;
            // System.out.println(z + " " + o);
            for (int i = m; i >= z; i--) {
                for (int j = n; j >= o; j--) {
                    table[i][j] = max(table[i][j], 1 + table[i-z][j-o]);
                }
            }
        }
        return table[m][n];
    }
    int max(int a, int b) {
        return a>b?a:b;
    }


}
