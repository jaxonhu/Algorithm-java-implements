package dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/11
 * @Time: 下午4:58
 * @Project: Algorithm-Java-implements
 */
public class WordBreak {


    /**
     *  思路 ，我首先想到了递归，因为确实发现有重叠子问题，有重叠子问题就一定
     *  可以用动态规划解决。
     */
    public boolean wordBreak1(String s, List<String> wordDict) {
        int n = s.length();
        if(n == 0) return false;

        boolean[] dp = new boolean[n+1];

        for(int i = 1 ; i < n ; ++ i) {
            for(int j = 0 ; j <= i ; ++ j) {
                String temp = s.substring(j,i);
                if(dp[j] && wordDict.contains(temp)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }


    /**
     * WordBreak II  这次是要给出解
     * 思路： 肯定是要用回溯了
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();

        return result;
    }
}
