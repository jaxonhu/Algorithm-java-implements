package Tree;

import java.util.Stack;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/6
 * @Time: 下午3:37
 * @Project: Algorithm-Java-implements
 */
public class SumRootToLeafNumbers {

    public int sumNumbers(TreeNode root) {
        int[] store = new int[1];
        if(root == null) return store[0];
        dfs(root, store, "" + root.val);
        return store[0];
    }

    public void dfs(TreeNode root, int[] store, String s) {
        if(root == null)
            return;
        if(root.left == null && root.right == null) {
            store[0] += Integer.valueOf(s);
        }
        if(root.left != null)
            dfs(root.left, store, s + root.left.val);
        if(root.right != null)
            dfs(root.right, store, s + root.right.val);
    }


    /**
     * 非递归，需要两个栈
     */

    public int sumNumbers2(TreeNode root) {
        int sum = 0;
        if(root == null) return sum;
        Stack<TreeNode> stack = new Stack<>();
        Stack<String> sstack = new Stack<>();
        stack.push(root);
        sstack.push("" + root.val);
        while(!stack.empty()) {
            TreeNode p = stack.pop();
            String scur = sstack.pop();
            if(p.left == null && p.right == null) {
                sum += Integer.valueOf(scur);
            }
            if(p.left != null) {
                stack.push(p.left);
                sstack.push(scur + p.left.val);
            }
            if(p.right != null) {
                stack.push(p.right);
                sstack.push(scur + p.right.val);
            }
        }
        return sum;
    }
}
