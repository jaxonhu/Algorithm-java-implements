package Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/5
 * @Time: 下午10:35
 * @Project: Algorithm-Java-implements
 */
public class PathSum {


    /**
     * PathSum II
     *
     */


    public List<List<Integer>> pathSum(TreeNode root, int target) {

        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        List<Integer> cur = new ArrayList<>();
        helper(result, cur, root, 0, target);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> cur, TreeNode root, int sum, int target) {
        if(root == null)
            return;
        if(root.left == null && root.right == null && sum == target) {
            result.add(new ArrayList<>(cur));
            return;
        }
        cur.add(root.val);
        helper(result, cur, root.left, sum + root.val, target);
        cur.remove(cur.size() - 1);
        helper(result, cur, root.right, sum + root.val, target);
    }




}
