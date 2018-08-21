package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/5
 * @Time: 下午6:56
 * @Project: Algorithm-Java-implements
 */

class WrapperTreeNode {
    public TreeNode treeNode;
    public boolean visited;
    public WrapperTreeNode(TreeNode t) {
        treeNode = t;
        visited = false;
    }

}
public class BinaryTreeTraversal {

    /**
     * 二叉树的遍历，中序 先序  后序
     * 都是非递归写法
     */


    /**
     *  二叉树的前序遍历
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root == null) return result;
        stack.push(root);
        while(!stack.empty()) {
            TreeNode t = stack.pop();
            result.add(t.val);
            if(t.right != null) {
                stack.push(t.right);
            }
            if(t.left != null) {
                stack.push(t.left);
            }
        }
        return result;
    }

    /**
     *  二叉树的中序遍历
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root == null) return result;
        TreeNode p = root;
        while(!stack.empty() || p != null) {
            while(p != null) {
                stack.push(p);
                p = p.left;
            }

            if(!stack.empty()) {
                p = stack.pop();
                result.add(p.val);
            }
            p = p.right;
        }
        return result;
    }


    /**
     *  二叉树的后序遍历
     *
     *  思路： 需要知道根节点的左右子节点是否被访问过，因此需要对节点数据结构稍加改造
     *
     */

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<WrapperTreeNode> stack = new Stack<>();
        if(root == null) return result;
        WrapperTreeNode p = new WrapperTreeNode(root);
        stack.push(p);
        while(!stack.empty()) {
            while((p = stack.peek()).treeNode != null) {
                p = new WrapperTreeNode(p.treeNode.left);
                stack.push(p);
            }
            //顶端是null
            stack.pop();
            p = stack.peek();
            //为什么要用一个while循环呢？
            while(p.treeNode != null) {
                if(p.visited) {
                    //后序 访问
                    result.add(p.treeNode.val);
                    stack.pop();
                    if(stack.isEmpty()) {
                        return result;
                    }
                    p = stack.peek();
                }else {
                    p.visited = true;
                    p = new WrapperTreeNode(p.treeNode.right);
                    stack.push(p);
                    break;
                }
            }
        }
        return result;
    }

}
