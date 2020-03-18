package Tree;

import java.util.Stack;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/6
 * @Time: 上午9:47
 * @Project: Algorithm-Java-implements
 */
public class FlattenBinaryTreeToLinkedList {


    /**
     * 将二叉树 扁平化
     * 先序遍历  O(n)空间
     */
    public void flatten(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if(root == null)
            return;
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode p = stack.pop();
            if(p.right != null) {
                stack.push(p.right);
            }
            if(p.left != null) {
                stack.push(p.left);
            }
            if(!stack.isEmpty()) {
                p.left = null;
                p.right = stack.peek();
            }
        }
    }
}
