package dynamicprogramming;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/16
 * @Time: 下午9:21
 * @Project: Algorithm-Java-implements
 */
public class LongestCommonSequence {


    /**
     * 两个最长公共子序列的长度
     *
     * 可以从递归解决，也可以用状态转移方程解决
     */
    public int longestCommonSequence(String s1, String s2) {
        if(s1 == "" || s2 == "")
            return 0;
        char c1 = s1.charAt(s1.length()-1);
        char c2 = s2.charAt(s2.length()-1);
        if(c1 == c2)
            return 1 + longestCommonSequence(s1.substring(0, s1.length() - 2), s2.substring(0, s2.length() - 2));

        return Math.max(longestCommonSequence(s1.substring(0,s1.length() - 2), s2), longestCommonSequence(s1, s2.substring(0,s2.length() - 2)));
    }

    /**
     * 递归解法：
     *
     */

}
