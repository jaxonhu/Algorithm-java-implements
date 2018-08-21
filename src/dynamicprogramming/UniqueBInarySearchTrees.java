package dynamicprogramming;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/9
 * @Time: 下午2:41
 * @Project: Algorithm-Java-implements
 */

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }
public class UniqueBInarySearchTrees {

    /**
     * 动态规划思路：
     * 对于根节点为i的BST来说，左子树的节点个数为i-1个，右子树的节点个数为n-i个
     * 总的二叉树的个数为 trees(i-1) * trees(n-i)
     */

    public int numTrees(int n) {
        if(n <= 1) return 1;

        int res = 0;
        for(int i = 1 ; i <= n ; ++ i) {
            res += numTrees(i-1) * numTrees(n - i);
        }
        return res;
    }

    /**
     * 改造成动态规划
     * dp[i] 表示以i为根节点的BST树个数
     */


    public int numTrees2(int n) {
        if(n <= 1) return 1;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2 ; i <= n ; ++ i) {
            for(int j = 0 ; j < i ; ++ j) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }


    /**
     *  UniqueBinarySearchTrees II : 需要打印所有的二叉树
     *  递归
     */

    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new ArrayList<TreeNode>();
        return recursive(1,n);
    }

    public List<TreeNode> recursive(int left, int right) {
        List<TreeNode>  list = new ArrayList<>();

        if(left > right){
            list.add(null);
            return list;
        }

        for(int i = left ; i <= right ; ++ i) {
            List<TreeNode> l = recursive(left, i-1);
            List<TreeNode> r = recursive(i+1, right);

            for(int j = 0 ; j < l.size() ; ++ j) {
                for(int k = 0 ; k < r.size() ; ++ k) {
                    TreeNode n = new TreeNode(i);
                    n.left = l.get(j);
                    n.right = r.get(k);
                    list.add(n);
                }
            }
        }
        return list;
    }
}
