package dynamicprogramming;

import java.util.Arrays;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/16
 * @Time: 下午4:07
 * @Project: Algorithm-Java-implements
 */
public class TargetSum {

    /**
     * leetcode 494
     * 输入：[1,1,1,1,1] target : 3
     * 每个元素可以用正负号修饰，只能使用一次
     * -1 +1  +1 +1 +1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * 思路： 可以先用记忆化搜索的方法去求解
     * 构造一下状态转移方程：dp[i][j] = dp[i-1][j-nums[i]] + dp[i-1][j+nums[i]]
     * 先求正数和，再求负数和，然后算一个映射函数
     *
     * 终于调对了, 注意有0的情况 +0 和-0 相同
     */

    public int findTargetSumWays(int[] nums, int S) {

        int maxsum = 0;
        int minsum = 0;
        if(nums.length == 0) return 0;
        Arrays.sort(nums);
        for(int i = 0 ; i < nums.length ; ++ i) {
            if(nums[i] > 0){
                maxsum += nums[i];
                minsum -= nums[i];
            }else {
                maxsum -= nums[i];
                minsum += nums[i];
            }
        }
        int len = 0 - minsum;
        int[][] memo = new int[nums.length][maxsum - minsum+1];
        for(int i = 0 ; i < nums.length ; ++ i) {
            Arrays.fill(memo[i], -1);
        }
        return getSubWays(nums, memo, 0, 0, S, len);
    }

    public int getSubWays(int[] nums, int[][] memo, int index, int S, int target, int len) {
        if(index == nums.length) {
            if(S == target)
                return 1;
            else
                return 0;
        }

        int elem = nums[index];
        int res1, res2;
        //这里总是报错
        if(memo[index][S + elem + len] != -1)
            res1 = memo[index][S + elem + len];
        else
            res1 = getSubWays(nums, memo, index + 1, S + elem, target, len);

        if(elem == 0) {
            memo[index][S + len] = res1;
            return res1;
        }

        if(memo[index][S - elem + len] != -1)
            res2 = memo[index][S + elem + len];
        else
            res2 = getSubWays(nums, memo, index + 1, S - elem, target,len);
        memo[index][S + len] = res1 + res2;
        return res1 + res2;
    }

    /**
     * 由记忆化搜索改动态规划就比较好改了
     */
    public int findTargetSumWays2(int[] nums, int S) {

        return 0;
    }


    /**
     * 递归
     */

    int cnt;
    public int findTargetSumWays3(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0;
        for (int num : nums) sum += num;
        count(nums, 0, S, sum);
        return cnt;
    }

    private void count(int[] nums, int pos, int rem, int curMax) {
        if (rem > curMax || rem < -curMax) return; //pruning, no need to continue search under current subdomain
        if (pos == nums.length && rem == 0) cnt++;
        if (pos == nums.length) return;
        count(nums, pos + 1, rem - nums[pos], curMax - nums[pos]);
        count(nums, pos + 1, rem + nums[pos], curMax - nums[pos]);
    }
}
