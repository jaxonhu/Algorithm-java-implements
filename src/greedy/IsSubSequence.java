package greedy;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/16
 * @Time: 下午10:03
 * @Project: Algorithm-Java-implements
 */
public class IsSubSequence {

    /**
     * 392. Is Subsequence
     * 判断s是否是t的子串
     * 思路很简单：两个指针O(n^2)的复杂度
     *
     * 这个我真没看出来是贪心
     */


    public boolean isSubsequence(String s, String t) {
        int n = s.length();
        int m = t.length();
        if(n > m) return false;
        int i = 0, j = 0;
        while(i < n && j < m) {
            if(s.charAt(i) == t.charAt(j)) {
                ++i;
            }
            ++j;
        }
        if(i == n) return true;
        return false;
    }
}
