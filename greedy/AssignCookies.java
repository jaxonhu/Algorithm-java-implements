package greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/16
 * @Time: 下午9:48
 * @Project: Algorithm-Java-implements
 */
public class AssignCookies {

    public int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0;
        int j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
            }
            j++;
        }
        return i;
    }
}
