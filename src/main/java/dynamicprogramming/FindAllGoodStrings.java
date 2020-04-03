package dynamicprogramming;

import java.util.Arrays;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/3/29
 * @ Time: 5:48 PM
 * @ Project: Algorithm-Java-implements
 */
public class FindAllGoodStrings {


    /**
     *
     * 数位dp leetcode 周赛
     *
     * 给定两个字符串s1 s2和evil串, 求s1 s2 之间的不符合pattern的所有字符串数量；
     *
     */

    String limitStr = "";
    String evilStr = "";
    int mod = (int)1e9+7;
    int[][] dp;
    int[] next;
    int m, len;
    public int findGoodStrings(int n, String s1, String s2, String evil) {
        m = evil.length();
        len = n;
        evilStr = evil;
        dp = new int[550][55];
        next = getNext(evil);
        limitStr = s1;
        int ret = dfs(0,0,true);
        limitStr = s2;
        ret = (dfs(0,0,true) - ret + mod) % mod;
        if(!s1.contains(evilStr)) ret++;
        return ret;
    }

    int dfs(int x, int match, boolean flag) {
        if(match >= m) return 0;
        if(x >= len) return 1;
        if(!flag && dp[x][match] != 0) return dp[x][match];
        char lim = 'z';
        if(flag) lim = limitStr.charAt(x);
        int ret = 0;
        for(char c = 'a' ; c <= lim ; c++) {
            int nxt = match;
            while(nxt > 0 && evilStr.charAt(nxt) != c) nxt = next[nxt];
            if(nxt >=0 && c == evilStr.charAt(nxt)) nxt++;
            ret = (ret + dfs(x+1,nxt, flag && (c == lim))) % mod;
        }
        if(!flag) dp[x][match] = ret;
        return ret;
    }

    int[] getNext(String evilStr) {
        int n = evilStr.length();
        int[] next = new int[n+1];
        next[0] = -1;
        int i = 0, j = -1;
        while(i < n) {
            if(j == -1 || evilStr.charAt(i) == evilStr.charAt(j)) {
                ++i;
                ++j;
                next[i] = j;
            } else
                j = next[j];
        }
        return next;
    }


    public static void main(String[] args) {
        FindAllGoodStrings instance = new FindAllGoodStrings();
        System.out.println(instance.findGoodStrings(4, "abab", "abdd", "ac"));
    }

}

