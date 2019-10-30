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
        char[] pattern = ps.toCharArray();
        int[] next = new int[pattern.length];
        int j = 1, len = 0;
        next[0] = 0;
        while(j < pattern.length) {
            if(pattern[j] == pattern[len]) {
                next[j++] = ++len;
            }else {
                if(len > 0)
                    len = next[len - 1];
                else
                    next[j++] = len;
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
            if(t[i] == p[j]) {
                ++i;
                ++j;
            }else {
                if( j == 0) i ++; // 如果第一位不相等，i直接++
                else j = next[j - 1];
            }
        }
        return i - p.length;
    }


    public static void main(String[] args) {
        String target = "abbca";
        String pattern = "bc";
        System.out.println(KMP(target, pattern));
    }

}
