package stringtype;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/16
 * @Time: 上午9:13
 * @Project: Algorithm-Java-implements
 */
public class KMP {

    /**
     * KMP 字符子串匹配算法
     */

    public static int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0, k = -1;
        while(j < p.length - 1) {
            if(k == -1 || p[j] == p[k]) {
                next[++j] = ++ k;
            }else {
                k = next[k];
            }
        }
        return next;
    }


    public static int KMP(String ts, String ps) {
        char[] t = ts.toCharArray();
        char[] p = ps.toCharArray();
        int i = 0, j = 0;
        int[] next = getNext(ps);
        while(i < t.length && j < p.length) {
            if(j == -1 || t[i] == p[j]) {
                ++i;
                ++j;
            }else {
                j = next[j];
            }
        }
        if(j == p.length) {
            //返回在ts中的index
            return i - j;
        }else {
            return -1;
        }
    }

    /**
     * KMP 算法原理：
     */
    public static int[] genNext(String ps) {
        int n = ps.length();
        int[] next = new int[n];
        char[] psChars = ps.toCharArray();
        int k = -1;
        int j = 0;
        next[0] = -1;
        while(j < n - 1) {
            if(k == -1 || psChars[j] == psChars[k]) {
                next[++j] = ++k;
            }else {
                k = next[k];
            }
        }
        return next;
    }

    public int KMP2(String ts, String ps) {
        char[] tsChars = ts.toCharArray();
        char[] psChars = ts.toCharArray();
        int m = tsChars.length;
        int n = psChars.length;
        int[] next = genNext(ps);
        int i = 0 , j = 0;
        while(i < m  && j < n) {
            if(j == -1 || tsChars[i] == psChars[j]) {
                ++i;
                ++j;
            }else {
                j = next[j];
            }
        }
        if(j == n) {
            return i - j;
        }
        return -1;
    }

}
