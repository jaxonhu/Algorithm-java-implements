package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/10
 * @Time: 下午5:44
 * @Project: Algorithm-Java-implements
 */
public class CombineSum {

    /**
     *  输出Solution Set
     */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        recursive(result, list, candidates, target, 0);
        return result;
    }

    public void recursive(List<List<Integer>> result, List<Integer> list, int[] candidates, int target, int index) {
        if(target < 0) return;
        if(target == 0) {
            result.add(new ArrayList<>(list));
            return ;
        }else {
            for (int i = index; i < candidates.length; ++i) {
                list.add(candidates[i]);
                recursive(result, list, candidates, target - candidates[i], i);
                list.remove(list.size() - 1);
            }
        }
    }


    /**
     * combine Sum II  要求每个数只能出现一次
     * 如果直接将combine sum I拿来修改的话，会出现重复，为了避免重复，跳过
     */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        List<Integer> inner = new ArrayList<>();
        recursive2(result, inner, candidates, target, 0);
        return result;
    }

    public void recursive2(List<List<Integer>> result, List<Integer> inner, int[] cands, int target, int index) {
        if(target == 0) {
            result.add(new ArrayList<>(inner));
            return;
        }
        if(target < 0) return;
        for(int i = index ; i < cands.length ; ++ i ) {
            if(i > index && cands[i] == cands[i-1]) continue;
            inner.add(cands[i]);
            recursive(result, inner, cands, target - cands[i], i+1);
            inner.remove(inner.size() - 1);
        }
    }


    /**
     * combine Sum III , 给定n和k
     */

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if(k >= 10) return result;
        int sum = 0;
        for(int i = 10 ; i >= 10 - k + 1 ; -- i) {
            sum += i;
        }
        if(n > sum) return result;
        int[] cans = {1,2,3,4,5,6,7,8,9};
        recursive3(result, new ArrayList<Integer>(), cans, 0, n, k);
        return result;
    }

    void recursive3(List<List<Integer>> result, List<Integer> inner, int[] cans, int index, int target, int k) {

        if(target == 0 && inner.size() == k) {
            result.add(new ArrayList<>(inner));
            return;
        }
        if(target < 0) return;
        if(inner.size() > k) return;
        for(int i = index ; i < cans.length ; ++ i) {
            inner.add(cans[i]);
            recursive3(result, inner, cans, i + 1, target - cans[i], k);
            inner.remove(inner.size() - 1);
        }
    }


    /**
     * combine Sum IV
     * 思路： 只是求满足要求的顺序个数，所以不用保存
     *
     * 但是这种方法超时了
     *
     *
     */

    public int combinationSum5(int[] nums, int target) {
        int[] res = new int[1];
        recursive4(res, nums, target, 0);
        return  res[0];
    }

    public void recursive4(int[] res, int[] nums, int target, int index) {
        if(target < 0) return;
        if(target == 0)
            res[0] += 1;
        for(int i = index ; i < nums.length ; ++ i) {
            recursive4(res, nums, target - nums[i], 0);
        }
    }

    /**
     * 动态规划写法：注意是否顺序相关或者顺序无关
     */
    public int combinationSum4(int[] nums, int target) {
        int n = nums.length;
        if(n == 0) return 0;
        int[] dp = new int[target+1];
        //这里不是很懂，为啥dp[0] 为1
        dp[0] = 1;
        for(int i = 1 ; i <= target ; ++ i) {
            for(int j = 0 ; j < nums.length ; ++ j) {
                if(i - nums[j] >= 0)
                    dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }


}
