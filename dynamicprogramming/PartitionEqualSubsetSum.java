package dynamicprogramming;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/9
 * @Time: 下午7:15
 * @Project: Algorithm-Java-implements
 */
public class PartitionEqualSubsetSum {

    /**
     * 思路： 先计算nums数组的和sum，然后转化为01背包问题，背包大小为sum/2
     * 状态转移方程为： f(i, sum) = f(i-1, sum) || f(i-1, sum - nums[i])
     */

    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i = 0 ; i < n ; ++ i) {
            sum += nums[i];
        }
        if(sum % 2 != 0)
            return false;
        return subPartition(nums, n-1, sum/2);
    }

    public boolean subPartition(int[] nums, int index, int sum) {
        if(sum == 0)
            return true;
        if(index < 0 || sum < 0)
            return false;
        return subPartition(nums, index-1, sum) || subPartition(nums, index - 1, sum - nums[index]);
    }

    /**
     *  直接递归导致超时，改造为记忆化搜索
     */

    public boolean canPartition2(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i = 0 ; i < n ; ++ i) {
            sum += nums[i];
        }
        int[][] memo = new int[n][sum+1];
        for(int i = 0 ; i < n ; ++ i)
            for(int j = 0 ; j <= sum ; ++ j)
                memo[i][j] = -1;
        if(sum % 2 != 0)
            return false;
        return subPartition(memo, nums, n-1, sum/2);
    }

    public boolean subPartition(int[][] memo, int[] nums, int index, int sum) {
        if(sum == 0)
            return true;
        if(index < 0 || sum < 0)
            return false;
        if(memo[index][sum] != -1)
            return memo[index][sum] == 0 ? false : true;

        memo[index][sum] =  (subPartition(nums, index-1, sum)
                || subPartition(nums, index - 1, sum - nums[index])) ? 1 : 0;
        return memo[index][sum] == 0 ? false : true;
    }

    /**
     * 自底向上动态规划
     *
     * dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i]]
     *
     * 思考？为什么这样写不对，想不通
     *
     */

    public boolean canPartition3(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i = 0 ; i < n ; ++ i) {
            sum += nums[i];
        }
        if(sum % 2  == 1)
            return false;
        int c = sum / 2;
        boolean[][] dp = new boolean[n][c+1];
        for(int i = 0 ; i <= c ; ++ i) {
            if(nums[0] == i) {
                dp[0][i] = true;
            }
        }
        for(int i = 1 ; i < n ; ++ i) {
            for(int j = c ; j >= nums[i] ; -- j) {
                dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i]];
            }
        }
        return dp[n-1][c];
    }

    /**
     * 用O(n)的空间
     */

    public boolean canPartition4(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i = 0 ; i < n ; ++ i) {
            sum += nums[i];
        }
        if(sum % 2  == 1)
            return false;
        int c = sum / 2;
        boolean[] dp = new boolean[c+1];
        for(int i = 0 ; i <= c ; ++ i) {
            if(nums[0] == i) {
                dp[i] = true;
            }
        }
        for(int i = 1 ; i < n ; ++ i) {
            for(int j = c ; j >= nums[i] ; -- j) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[c];
    }


    public static void main(String[] args) {
        PartitionEqualSubsetSum object = new PartitionEqualSubsetSum();
        int[] nums = {1,5,11,5};
        System.out.println(object.canPartition3(nums));
    }




}
