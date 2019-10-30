package stringtype;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/10/30
 * @ Time: 11:16 AM
 * @ Project: Algorithm-Java-implements
 */
public class ShortestPalindrom {

    /**
     *   Leetcode 214.
     *   Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it.
     *   Find and return the shortest palindrome you can find by performing this transformation.
     *   Input: "aacecaaa"
     *   Output: "aaacecaaa"
     *   Input: "abcd"
     *   Output: "dcbabcd"
     *
     */
    public static String shortestPalindrome(String s) {
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int l = temp.length();
        int[] prefix = new int[l];
        prefix[0] = 0;
        int len = 0;
        int i = 1;
        while( i < l) {
            if(temp.charAt(i) == temp.charAt(len)) {
                len ++;
                prefix[i++] = len;
            } else {
                if(len > 0)
                    len = prefix[len - 1];
                else
                    prefix[i++] = 0;
            }
        }
        int common = prefix[l - 1];
        String s2 = s.substring(common);
        return new StringBuilder(s2).reverse().toString() + s;
    }

    public static void main(String[] args) {
        String target = "aaba";
        System.out.println(shortestPalindrome(target));
    }
}
