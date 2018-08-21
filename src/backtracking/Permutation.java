package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/12
 * @Time: 下午9:51
 * @Project: Algorithm-Java-implements
 */
public class Permutation {

    /**
     * 求数组的全排列
     */

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length == 0) return result;
        boolean[] visited = new boolean[nums.length];
        recursive(result, new ArrayList<>(), nums, visited);
        return result;
    }

    public void recursive(List<List<Integer>> result, List<Integer> inner, int[] nums, boolean[] visited) {
        if(inner.size() == nums.length) {
            result.add(new ArrayList<>(inner));
            return;
        }
        for(int i = 0 ; i < nums.length ; ++ i) {
            if(!visited[i]) {
                inner.add(nums[i]);
                boolean[] temp = Arrays.copyOf(visited, visited.length);
                temp[i] = true;
                recursive(result, inner, nums, temp);
                inner.remove(inner.size() - 1);
            }
        }
    }

    /**
     * 数组中有重复，在循环的时候跳过重复的
     */

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if(n == 0) return result;
        boolean[] visited = new boolean[n];
        Arrays.sort(nums); // 应该先进行排序
        recursive2(result, new ArrayList<>(), nums, visited);
        return result;
    }

    public void recursive2(List<List<Integer>> result, List<Integer> inner, int[] nums, boolean[] visited) {
        if(inner.size() == nums.length) {
            result.add(new ArrayList<>(inner));
            return;
        }
        for(int i = 0 ; i < nums.length ; ++ i) {
            if(!visited[i]) {
                if(i > 0 && (nums[i-1] == nums[i]) && !visited[i-1]) continue;
                inner.add(nums[i]);
                boolean[] temp = Arrays.copyOf(visited, visited.length);
                temp[i] = true;
                recursive2(result, inner, nums, temp);
                inner.remove(inner.size() - 1);

            }
        }
    }

    /**
     *  这道题不是免费题目，直观思路是先求全排列，然后判断全排列是否是回文的
     *
     * s = "aabb" return ["abba", "baab"]
     *
     * s= "abc" return []
     *
     * 晚上回来再写吧
     */
    public List<String> generatePalindromes(String s) {
        List<String> result = new ArrayList<>();
        char[] schars = s.toCharArray();
        int n = schars.length;
        if(n == 0) return result;
        boolean[] visited = new boolean[n];
        Arrays.sort(schars);
        recursive(result, "", schars, visited);
        return result;
    }

    public void recursive(List<String> result, String inner, char[] schars, boolean[] visited) {
        if(inner.length() == schars.length && isPalindromes(inner)) {
            result.add(inner);
            return;
        }
        for(int i = 0 ; i < schars.length ; ++ i) {
            if(!visited[i]) {
                if(i > 0 && schars[i] == schars[i-1] && !visited[i-1]) continue;
                inner = inner + schars[i];
                visited[i] = true;
                recursive(result, inner, schars, visited);
                visited[i] = false;
                inner = inner.substring(0, inner.length()-1);
            }
        }
    }

    public boolean isPalindromes(String s) {
        if(s == "")
            return false;
        String s1 = new StringBuilder(s).reverse().toString();
        if(s1.equals(s))
            return true;
        return false;
    }


}
