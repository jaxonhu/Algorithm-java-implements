package stringtype;

import java.util.*;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/6/19
 * @Time: 下午3:06
 * @Project: Algorithm-Java-implements
 */
public class IsIsomorphic {

    // 思路： LinkedHashMap
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for(int i = 0 ; i < s.length() ; ++i) {
            if(map1.put((Character) s.charAt(i), 1) != map2.put(t.charAt(i), 1)) {
               return false;
            }
        }
        return true;
    }

}
