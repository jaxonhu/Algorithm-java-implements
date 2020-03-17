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
        int[] next = new int[ps.length()+1];
        int i = 0;
        int j = -1;
        next[0] = -1;
        while(i < ps.length()) {
           if(j == -1  || ps.charAt(i) == ps.charAt(j)) {
               ++i;
               ++j;
               next[i] = j;
           } else {
               j = next[j];
           }
        }
        return next;
    }


    public static int KMP(String ts, String ps) {
      int i = 0;
      int j = 0;
      int[] next = getNext(ps);
      while(i < ts.length() && j < ps.length()) {
          if(j == -1 || ts.charAt(i) == ps.charAt(j)) {
              i++;
              j++;
          } else
              j = next[j];
      }
      if(j == ps.length())
          return i - j;
      else return -1;
    }


    public static void main(String[] args) {
        String target = "abbca";
        String pattern = "abc";
        System.out.println(KMP(target, pattern));
    }

}
