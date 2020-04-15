package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/15
 * @ Time: 5:09 下午
 * @ Project: Algorithm-Java-implements
 */
public class LexicographicalNumbers {

    /**
     *  给定一个整数 n, 返回从 1 到 n 的字典顺序。
     *
     * 例如，
     *
     * 给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
     *
     * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lexicographical-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *  题解：
     *      重点是搞清楚什么是字典序，从起始字符开始比较大小，大的大小的小，如果相同比较下一个字符，字符从0开始
     *      因此，如果当前位置是0，更长的字符串更大； 题目给的用例不是特别好，体现不出字典序的规则
     *
     *      按字典序比较的话：
     *      10009 接下来应该是 1001，10010...
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        dfs(result, 1, n);
        return result;
    }

    void dfs(List<Integer> result, int current, int max) {
        if(current > max) return;
        int r = current % 10;
        for(int i = 0 ; i <= 9 ; i ++) {
            int temp = current + i;
            if(temp <= max && (i+r) < 10) {
                result.add(temp);
                dfs(result, temp*10, max);
            }
        }
    }

}
