package Tree;

import java.util.*;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/5
 * @Time: 上午10:11
 * @Project: Algorithm-Java-implements
 */
public class BinaryTreeLevelOrderTraversal {

    /**
     *  二叉树的层序遍历，可以用BFS和DFS解决
     */


    /**
     * BFS
     */

    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        TreeNode left = null;
        TreeNode right = null;
        int current = 0;
        int count = 0;
        int previous = 0;
        if(root == null) return res;
        queue.add(root);
        while(queue.size() > 0){
            TreeNode r = queue.removeFirst();
            list.add(r.val);
            if(r.left != null){
                queue.addLast(r.left);
                current++;
            }
            if(r.right != null){
                queue.addLast(r.right);
                current++;
            }
            if(count == previous){
                previous = current;
                res.add(list);
                list = new ArrayList<Integer>();
            }
            count++;
        }
        return res;
    }

    /**
     * DFS
     */
    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if(root == null) return list;
        helper(list, root, 0);
        return list;
    }

    public void helper(List<List<Integer>> result, TreeNode root, int depth) {
        if(root == null) return;
        if(root!= null) {
            if(result.size() <= depth) {
                List<Integer> temp = new ArrayList<>();
                temp.add(root.val);
                result.add(temp);
            }else {
                List<Integer> temp = result.get(depth);
                temp.add(root.val);
                result.set(depth, temp);
            }
        }

        helper(result, root.left, depth+1);
        helper(result, root.right, depth +1);
    }


    /**
     * binary Tree Level Order Traversal II
     * 变种，要求字底向上层序遍历
     * 思路：也可以先用BFS解决
     */

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if(root == null) return result;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i = 0 ; i < size ; ++ i) {
                TreeNode node = queue.pollFirst();
                if(node.left != null) {
                    queue.offerLast(node.left);
                }
                if(node.right != null) {
                    queue.offerLast(node.right);
                }
            }
            result.addFirst(temp);
        }
        return result;
    }

    /**
     * binary Tree Level Order Traversal II
     * 变种，要求字底向上层序遍历
     * 思路：也可以用DFS解决
     */

    public List<List<Integer>> levelOrderBottomDFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        helperDFS(result, root, 0);
        return result;
    }

    public void helperDFS(List<List<Integer>> result, TreeNode root, int depth) {
        if(root == null) return;
        if(depth >= result.size()) {
            List<Integer> temp = new ArrayList<>();
            result.add(temp);
        }
        helper(result, root.left, depth+1);
        helper(result, root.right, depth+1);
        result.get(result.size() - depth - 1).add(root.val);
    }

}
