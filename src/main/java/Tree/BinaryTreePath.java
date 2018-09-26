package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/6
 * @Time: 下午2:17
 * @Project: Algorithm-Java-implements
 */
public class BinaryTreePath {


    //这道题非递归的话应该用后序遍历
    //先序应该也可以啊 不对  可以用两个栈

    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        Stack<String> sstack = new Stack<>();
        TreeNode p = root;
        stack.push(root);
        sstack.push("");
        while(!stack.isEmpty()) {
            p = stack.pop();
            String cur = sstack.pop();
            if(p.left == null && p.right == null) {
                result.add(cur + p.val);
            }
            if(p.right != null) {
                stack.push(p.right);
                sstack.push(cur + p.val + "->");
            }
            if(p.left != null) {
                stack.push(p.left);
                sstack.push(cur + p.val + "->");
            }

        }
        return result;
    }

    //DFS 方法
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root == null) return result;
        dfs(root, result, "" + root.val);
        return result;
    }
    public void dfs(TreeNode root,List<String> result, String s) {
        if(root == null) return;
        if(root.left == null && root.right == null) {
            result.add(s);
            return;
        }
        if(root.left != null)
            dfs(root.left, result, s + "->" + root.left.val);
        if(root.right != null)
            dfs(root.right, result, s + "->" + root.right.val);
    }

}
