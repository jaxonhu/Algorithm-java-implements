package stringtype;

import java.util.HashSet;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/7/2
 * @ Time: 10:49 下午
 * @ Project: Algorithm-Java-implements
 */
public class CheckStringContainsBinaryCodes {


    /**
     * 给你一个二进制字符串 s 和一个整数 k 。
     *
     * 如果所有长度为 k 的二进制字符串都是 s 的子串，请返回 True ，否则请返回 False 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "00110110", k = 2
     * 输出：true
     * 解释：长度为 2 的二进制串包括 "00"，"01"，"10" 和 "11"。它们分别是 s 中下标为 0，1，3，2 开始的长度为 2 的子串。
     * 示例 2：
     *
     * 输入：s = "00110", k = 2
     * 输出：true
     * 示例 3：
     *
     * 输入：s = "0110", k = 1
     * 输出：true
     * 解释：长度为 1 的二进制串包括 "0" 和 "1"，显然它们都是 s 的子串。
     * 示例 4：
     *
     * 输入：s = "0110", k = 2
     * 输出：false
     * 解释：长度为 2 的二进制串 "00" 没有出现在 s 中。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public boolean hasAllCodes(String s, int k) {
        HashSet<String> set = new HashSet<>();
        int cur = 0;
        for(int i = k ; i <= s.length() ; i ++) {
            set.add(s.substring(i-k, i));
        }
        return set.size() == Math.pow(2, k);
    }


    /**
     * 解法二
     *
     */

    public boolean hasAllCodes2(String s, int k) {
        int n = 1<<k;
        if(n >= s.length())
            return false;
        boolean nums[] = new boolean[n];
        char[] ch = s.toCharArray();
        int temp = 0;
        for(int i=0;i<k;i++){
            temp = (temp<<1) + ch[i] - '0';
        }
        nums[temp] = true;
        for(int i=k;i<ch.length;i++){
            temp =( (temp <<1)& (n-1)) + ch[i] - '0';
            nums[temp] = true;
        }
        for(int i=0;i<n;i++){
            if(!nums[i])
                return false;
        }
        return true;
    }


}
