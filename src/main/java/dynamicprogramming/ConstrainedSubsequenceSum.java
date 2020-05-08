package dynamicprogramming;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/5/8
 * @ Time: 1:41 下午
 * @ Project: Algorithm-Java-implements
 */
public class ConstrainedSubsequenceSum {

    /**
     *
     * 给你一个整数数组 nums 和一个整数 k ，请你返回 非空 子序列元素和的最大值，子序列需要满足：子序列中每两个 相邻 的整数 nums[i] 和 nums[j] ，它们在原数组中的下标 i 和 j 满足 i < j 且 j - i <= k 。
     *
     * 数组的子序列定义为：将数组中的若干个数字删除（可以删除 0 个数字），剩下的数字按照原本的顺序排布。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [10,2,-10,5,20], k = 2
     * 输出：37
     * 解释：子序列为 [10, 2, 5, 20] 。
     * 示例 2：
     *
     * 输入：nums = [-1,-2,-3], k = 1
     * 输出：-1
     * 解释：子序列必须是非空的，所以我们选择最大的数字。
     * 示例 3：
     *
     * 输入：nums = [10,-2,-10,-5,20], k = 2
     * 输出：23
     * 解释：子序列为 [10, -2, -5, 20] 。
     *  
     *
     * 提示：
     *
     * 1 <= k <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     *
     *
     */

    /**
     * 题解：
     *
     *  定义状态dp[i]为以i结尾的的最大子序和，那么当考虑第i+1个的时候，由于相邻两个下标差距不大于k且非空，所以有以下状态转移方程
     *
     * dp[i+1] = max(nums[i+1], dp[i+1-j] + nums[i+1])
     * dp[i+1]=max(nums[i+1],dp[i+1−j]+nums[i+1])
     *
     * for 1 <= j <= k
     * for1<=j<=k
     *
     * 如果使用蛮力法的话，时间复杂度O(nk)O(nk)，会超时。所以需要优化。
     *
     * 由于当前时刻只依赖于前k个时刻的状态，所以快速找到前k个状态中的最大的即可。这个时候联想到滑动窗口最大的题目。
     *
     * 题目链接
     *
     * 使用单调栈来进行优化，最终的时间复杂度为O(n)O(n)
     *
     *
     */

    static class Pair {
        public int val;
        public int idx;
        public Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    int[] dp;
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        dp = new int[n+1];
        int res = nums[0];
        Deque<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(nums[0], 0));
        for(int i = 1 ; i < n ; i ++) {
            if(!queue.isEmpty() && queue.peekFirst().idx < i - k) queue.removeFirst();
            dp[i] = Math.max(nums[i], queue.peekFirst().val + nums[i]);
            while(!queue.isEmpty() && queue.peekLast().val <= dp[i]) queue.removeLast();
            queue.offer(new Pair(dp[i], i));
            res = Math.max(dp[i], res);
        }
        return res;
    }

}
