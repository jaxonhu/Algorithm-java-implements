package stringtype;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimilaryString {


    /**
     * 相似字符串
     *
     * 通过率 75%
     * 还可以继续优化
     */

    static boolean match(String subS, String T) {

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for(int i = 0 ; i < subS.length() ; ++i) {
            if(map1.put((Character) subS.charAt(i), 1) != map2.put(T.charAt(i), 1)) {
                return false;
            }
        }
        return true;

    }

    static int solve(String S, String T) {

        int res = 0;
        int l1 = S.length();
        int l2 = T.length();
        if(l2 > l1) return 0;

        for( int i = 0 ; i < l1 - l2 + 1 ; i += 1) {
            String s = S.substring(i, i + l2);
            if(match(s, T))
                res += 1;
        }
        return res;
    }


    public static  void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String S = in.next();
        String T = in.next();

        int res = solve(S, T);

        System.out.println(res);
    }
}
