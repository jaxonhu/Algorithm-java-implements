package stringtype;

import java.util.Arrays;

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
     *  aacecaaa#aaacecaa
     *            aacecaaa#aaacecaa
     */
    public static String shortestPalindrome(String s) {
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int l = temp.length();
        int[] prefix = new int[l+1];
        prefix[0] = -1;
        int j = -1;
        int i = 0;
        while( i < temp.length()) {
            if(j == -1 || temp.charAt(i) == temp.charAt(j)) {
                ++i;
                ++j;
                prefix[i] = j;
            } else
                j = prefix[j];
        }
        System.out.println(Arrays.toString(prefix));
        String s2 = s.substring(prefix[temp.length()]);
        return new StringBuilder(s2).reverse().toString() + s;
    }

    public static void main(String[] args) {
        String target = "aaacecaaa";
        System.out.println(shortestPalindrome(target));
    }
}
