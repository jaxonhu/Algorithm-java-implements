package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/15
 * @Time: 下午1:11
 * @Project: Algorithm-Java-implements
 */
public class SubSets {


    /**
     *
     * SubSet I
     * 返回所有的子集
     * 思路：长度为n，则分别找长度为0、1、2、3...n的子集合
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if(n == 0)
            return result;
        Arrays.sort(nums);
        recursive(result, new ArrayList<>(), nums, 0);
        return result;
    }

    public void recursive(List<List<Integer>> result, List<Integer> inner,int[] nums, int index) {
        if(index > nums.length)
            return;
        result.add(inner);
        for(int i = index;  i < nums.length ; ++ i) {
            inner.add(nums[i]);
            recursive(result, new ArrayList<>(inner), nums, i+1);
            inner.remove(inner.size() - 1);
        }
    }

    /**
     * subSet II
     *
     * 子集中包含重复元素 比如 [1,2,2]的子集
     *
     * 思路：用一个visited布尔类型的数组保存当前层集合元素的访问情况
     */

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if(n == 0) return result;
        boolean[] visited = new boolean[n];
        Arrays.sort(nums);
        recursive(result, new ArrayList<>(), nums, visited, 0);
        return result;
    }

    public void recursive(List<List<Integer>> result, List<Integer> inner, int[] nums, boolean[] visited, int index) {
        if(index > nums.length)
            return;
        result.add(inner);
        for(int i = index ; i < nums.length ; ++ i) {
            if(!visited[i]) {
                if(i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
                inner.add(nums[i]);
                visited[i] = true;
                recursive(result, new ArrayList<>(inner), nums, visited, i+1);
                inner.remove(inner.size() - 1);
                visited[i] = false;
            }
        }
    }

}
