package dynamicprogramming;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/25
 * @ Time: 11:21 上午
 * @ Project: Algorithm-Java-implements
 */
public class LCOF {



    /**
     *
     * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
     *
     * 示例 1:
     *
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * 示例 2:
     *
     * 输入:
     * s = "aa"
     * p = "a*"
     * 输出: true
     * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     * 示例 3:
     *
     * 输入:
     * s = "ab"
     * p = ".*"
     * 输出: true
     * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     * 示例 4:
     *
     * 输入:
     * s = "aab"
     * p = "c*a*b"
     * 输出: true
     * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
     * 示例 5:
     *
     * 输入:
     * s = "mississippi"
     * p = "mis*is*p*."
     * 输出: false
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    /**
     *  迭代做法
     */
    public boolean isMatch(String s, String p) {
        int l1 = s.length();
        int l2 = p.length();
        dp = new boolean[l1+1][l2+1];
        for(int i = 0 ; i <= l1 ; i ++) {
            for(int j = 0 ; j <= l2 ; j ++) {
                if(j == 0) {
                    dp[i][j] = i == 0;
                } else {
                    if(p.charAt(j-1) == '*') {
                        if(j >= 2) {
                            dp[i][j] = dp[i][j-2];
                        }
                        if(i > 0 && j >= 2 && (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.')) {
                            dp[i][j] = dp[i][j] | dp[i-1][j];
                        }
                    } else {
                        if(i > 0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')) {
                            dp[i][j] = dp[i-1][j-1];
                        }
                    }
                }
            }
        }
        return dp[l1][l2];
    }



    /**
     *  递归+记忆化搜索解法
     *  可以看出，对于从前向后迭代的问题，通常可以从后向前递归写出
     */

    boolean[][] dp;
    boolean[][] visited;
    public boolean isMatch2(String s, String p) {
        int l1 = s.length();
        int l2 = p.length();
        dp = new boolean[l1+1][l2+1];
        visited = new boolean[l1+1][l2+1];
        dp[0][0] = true;
        visited[0][0] = true;
        boolean res = dfs(l1,l2,s,p);
        return res;
    }

    boolean dfs(int i, int j, String s, String p) {
        if(visited[i][j]) return dp[i][j];
        if(j > 0 && p.charAt(j-1) == '*') {
            if(j > 1) {
                dp[i][j] = dfs(i,j-2,s,p);
            }
            if(i > 0 && j > 1 && (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.')) {
                dp[i][j] |= dfs(i-1, j, s, p);
            }
        } else {
            if(i > 0 && j > 0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')) {
                dp[i][j] = dfs(i-1,j-1,s,p);
            }
        }
        visited[i][j] = true;
        return dp[i][j];
    }

}
