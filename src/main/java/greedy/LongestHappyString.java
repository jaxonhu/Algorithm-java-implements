package greedy;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/7
 * @ Time: 3:59 下午
 * @ Project: Algorithm-Java-implements
 */


/**
 *
 * 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 *
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 *
 * s 是一个尽可能长的快乐字符串。
 * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
 * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：a = 1, b = 1, c = 7
 * 输出："ccaccbcc"
 * 解释："ccbccacc" 也是一种正确答案。
 * 示例 2：
 *
 * 输入：a = 2, b = 2, c = 1
 * 输出："aabbc"
 * 示例 3：
 *
 * 输入：a = 7, b = 1, c = 0
 * 输出："aabaa"
 * 解释：这是该测试用例的唯一正确答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-happy-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LongestHappyString {

    int[] A = new int[4];
    char[] C = new char[] {' ', 'a', 'b', 'c'};
    boolean flag = false; // false if not two
    int prev = 0;
    int getNext() {
        int idx = getMaxIndex();
        if(idx == -1) return -1;
        A[idx] --;
        if(prev == idx) {
            flag = true;
        } else {
            prev = idx;
            flag = false;
        }
        return idx;
    }

    int getMaxIndex() {
        int idx = -1;
        int max = 0;
        for(int i = 1 ; i <= 3; i ++) {
            if(flag && prev == i) continue;
            if(A[i] > max) {
                idx = i;
                max = A[i];
            }
        }
        return idx;
    }

    public String longestDiverseString(int a, int b, int c) {
        String res = "";
        int k = 0;
        A[1] = a; A[2] = b; A[3] = c;
        while((k = getNext()) != -1) {
            res += C[k];
        }
        return res;
    }
}
