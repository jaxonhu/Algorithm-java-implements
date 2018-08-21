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

}
