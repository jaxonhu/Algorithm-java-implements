package backtracking;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/5/18
 * @ Time: 10:52 上午
 * @ Project: Algorithm-Java-implements
 */
public class KthSmallestLexicographicalOrder {

    /**
     *
     * 给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
     *
     * 注意：1 ≤ k ≤ n ≤ 109。
     *
     * 示例 :
     *
     * 输入:
     * n: 13   k: 2
     *
     * 输出:
     * 10
     *
     * 解释:
     * 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    /**
     * 思路：  https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/solution/ben-ti-shi-shang-zui-wan-zheng-ju-ti-de-shou-mo-sh/
     *
     *     10叉树的遍历
     *
     */

    public int findKthNumber(int n, int k) {
        int val = 0;
        int count = 1;
        int prefix = 1;
        while(count < k) {
            int nodes = getSteps(n, prefix, prefix+1);
            if( (count + nodes) > k) {
                prefix *= 10;
                count += 1;
            } else {
                count += nodes;
                prefix += 1;
            }
        }
        return prefix;
    }

    private int getSteps(int n, long curr, long next) {
        int steps = 0;
        while (curr <= n) {
            steps += Math.min(n + 1, next) - curr;
            curr *= 10;
            next *= 10;
        }
        return steps;
    }

}
