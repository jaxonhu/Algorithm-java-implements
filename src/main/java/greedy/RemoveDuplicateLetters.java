package greedy;

import java.util.Stack;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/5/13
 * @ Time: 5:58 下午
 * @ Project: Algorithm-Java-implements
 */
public class RemoveDuplicateLetters {

    /**
     * 给你一个仅包含小写字母的字符串，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: "bcabc"
     * 输出: "abc"
     * 示例 2:
     *
     * 输入: "cbacdcbc"
     * 输出: "acdb"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    /**
     *
     *  解题思路：
     *      如果当前的 栈顶元素 比当前的元素字典序 大,且 当前元素的位置后面还有 栈顶元素, 将栈顶元素出栈, 将当前元素入栈, 这样来找到最优的排列,
     *      例如 dcd , 先入栈 d , 然后入栈c 时, 栈顶元素大于 c , 而且 c 后面的位置. 那么就将 d 出栈抛弃,然后把 c 入栈, 到d的时候 c小于d,
     *      从而得到结果 cd
     *      时间复杂度: O(nLogn)
     */

    public String removeDuplicateLetters(String s) {
        int n  = s.length();
        int[] pos = new int[26];
        Stack<Character> stack = new Stack<>();
        if(s.equals("")) return "";
        for(int i = 0 ; i < n ; i ++) {
            char ch = s.charAt(i);
            pos[ch - 'a'] = Math.max(pos[ch - 'a'], i);
        }
        stack.push(s.charAt(0));
        for(int i = 1 ; i < n ; i ++) {
            char ch = s.charAt(i);
            if(stack.contains(ch)) continue;
            while(!stack.isEmpty() && ch < stack.peek() && pos[stack.peek() - 'a'] > i) stack.pop();
            stack.push(ch);
        }
        String res = "";
        while(!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        return res;
    }

}
