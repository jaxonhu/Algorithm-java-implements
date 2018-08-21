package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/11
 * @Time: 下午4:32
 * @Project: Algorithm-Java-implements
 */
public class PalindromePartitioning {


    /**
     * 求字符串的回文子串集合 典型的回溯法
     * 诶，晚上再写吧
     */

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        if(s.length() == 0) return result;
        for(int i = 0 ; i < s.length() ; ++ i) {
            recursive(result, new ArrayList<>(), s, i);
        }
        return result;
    }

    public void recursive(List<List<String>> result, List<String> inner, String s, int index) {
        int n = s.length();
        if(n == 0) {
            result.add(new ArrayList<>(inner));
            return;
        }

        String s1 = s.substring(0,index+1);
        String s2 = s.substring(index+1, s.length());
        if(isPalindrome(s1)) {
            inner.add(s1);
            if(s2.length() == 0) {
                result.add(inner);
                return;
            }
            for(int j = 0 ; j < s2.length() ; ++ j) {
                recursive(result, new ArrayList<>(inner), s2, j);
            }
        }
    }

    public boolean isPalindrome(String s) {
        if(s == null) return false;
        String s2 = new StringBuilder(s).reverse().toString();
        if(s2.equals(s))
            return true;
        return false;
    }


    /**
     * Palindrome Partition II   这次是统计最少切割次数
     * 很明显的动态规划
     *  好好分析，很重要的一点是找到重叠子问题
     */

    // 写错了
    public int minCut4(String s) {
        int n = s.length();
        if(n == 0) return 0;
        if(n == 1) return 0;
        int[][] dp = new int[n][n];

        for(int i = 0 ; i < n ; ++ i) {
            dp[i][i] = 0;
        }
        for(int j = 1 ; j < n ; ++ j) {
            for(int i = 0 ; i < j ; ++ i) {
                if(s.charAt(i) == s.charAt(j)) {
                    if(j - i == 1) dp[i][j] = 0;
                    else dp[i][j] = dp[i+1][j-1];
                }else {
                    dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) + 1;
                }
            }
        }
        return dp[0][s.length() - 1];
    }


    /**
     *  终于理解了  状态转义矩阵，dp[j][i]意思是从j到i是一个回文字符串
     */
    public int minCut(String s) {
        int[] result = new int[s.length() + 1];
        result[0] = -1;
        int temp = 0, min = Integer.MIN_VALUE;

        boolean[][] dp = new boolean[s.length()][s.length()];
        // i : exclusive
        for(int i = 1 ; i <= s.length() ; ++ i) {
            min = Integer.MIN_VALUE;
            for(int j  = 0 ; j < i ; ++ j) {
                if(s.charAt(j) == s.charAt(i-1) && (i - j <= 2  || dp[j+1][i-2]) ) {
                    dp[j][i-1] = true;
                    temp = result[j] + 1;
                    min = Math.min(min, temp);
                }
            }
            result[i] = min;
        }
        return s.length() == 0 ? 0 : result[s.length()];
    }

}
