package backtracking;

import java.util.*;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/1/12
 * @ Time: 12:48 PM
 * @ Project: Algorithm-Java-implements
 */
public class RemoveInvalidParentheses {


    /**
     *
     * 宽度优先遍历
     *
     * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
     *
     * Note: The input string may contain letters other than the parentheses ( and ).
     *
     * Input: "()())()"
     * Output: ["()()()", "(())()"]
     *
     * Input: "(a)())()"
     * Output: ["(a)()()", "(a())()"]
     *
     * Input: ")("
     * Output: [""]
     *
     */

    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> set =new HashSet<>();
        boolean found = false;
        queue.offer(s);
        while(!queue.isEmpty()) {
            String str = queue.poll();
            if(str == null) continue;
            if(isVaild(str)) {
                res.add(str);
                found = true;
            }
            if(found) continue;
            for(int i = 0; i< str.length() ; i ++) {
                char c = str.charAt(i);
                if(c != '(' && c != ')') continue;
                String temp = str.substring(0, i) + str.substring(i+1, str.length());
                if(!set.contains(temp)) {
                    queue.offer(temp);
                    set.add(temp);
                }
            }
        }
        return res;
    }


    /**
     *  Brute force
     */
    public List<String> removeInvalidParentheses2(String s) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        set.add(s);
        while(!set.isEmpty()) {
            Set<String> temp = new HashSet<>();
            for(String str : set) {
                if(isVaild(str)) {
                    res.add(str);
                }
                if(!res.isEmpty()) continue;
                for(int i = 0 ; i < str.length() ; i ++) {
                    char c = str.charAt(i);
                    if(c != '(' && c != ')') continue;
                    temp.add(str.substring(0, i) + str.substring(i+1));
                }
            }
            if(!res.isEmpty()) return res;
            set = temp;
        }
        return res;
    }


    private boolean isVaild(String s) {
        int cnt = 0;
        for(int i = 0 ;  i < s.length() ; i ++) {
            char c = s.charAt(i);
            if(c == '(') cnt ++;
            if(c == ')' && (--cnt < 0)) return false;
        }
        return cnt == 0;
    }


    public static void main(String[] args) {
        String input = "()())()";
        RemoveInvalidParentheses handler = new RemoveInvalidParentheses();
        List<String> res = handler.removeInvalidParentheses2(input);
        System.out.println(res);

    }

}
