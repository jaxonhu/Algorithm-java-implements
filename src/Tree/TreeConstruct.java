package Tree;

import java.util.HashMap;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/4
 * @Time: 下午11:18
 * @Project: Algorithm-Java-implements
 */
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }
public class TreeConstruct {

    /**
     * 1. 根据中序和后序遍历构造B树
     *    前序遍历： 1245367
     *    中序遍历： 4251637
     *    后序遍历： 4526731
     */

    /**
     *  直接
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        int n = inorder.length;
        int m = postorder.length;
        int[] preIndex = new int[]{0};
        preIndex[0] = m-1;

        if(m == 0)
            return null;

        return helper(0,m-1,0,m-1,inorder,postorder);
    }

    public TreeNode helper(int post_begin,int post_end,int in_begin,int in_end,int[] inorder,int[] postorder){

        int postIndex = post_end;

        if(post_begin > post_end || in_begin > in_end)
            return null;

        if(post_begin == post_end){
            TreeNode t = new TreeNode(postorder[post_end]);
            t.left = null;
            t.right = null;
            return t;
        }

        TreeNode t = new TreeNode(postorder[postIndex]);

        int i = in_begin;
        for(; i <= in_end ; i++){
            if(inorder[i] == postorder[postIndex])
                break;
        }

        t.left = helper(post_begin,post_begin + i - in_begin - 1,in_begin,i-1,inorder,postorder);
        t.right = helper(post_end - (in_end - i),post_end - 1,i+1,in_end,inorder,postorder);
        return t;
    }


    /**
     * 可以用HashMap来存储中序遍历的索引
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        int m = postorder.length;
        if(n != m) return null;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0 ; i < n ; ++ i) {
            map.put(inorder[i], i);
        }
        return helper(map, 0, n, 0, n, inorder, postorder);
    }

    public TreeNode helper(HashMap<Integer, Integer> map, int post_begin, int post_end, int in_begin, int in_end, int[] inorder, int[] postorder) {
        if(post_begin > post_end || in_begin > in_end)
            return null;
        if(post_begin == post_end) {
            TreeNode t = new TreeNode(postorder[post_end]);
            t.left = null;
            t.right = null;
            return t;
        }
        int rootVal = postorder[post_end];
        int in_index = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        root.left = helper(map, post_begin, post_begin + in_index - in_begin - 1, in_begin, in_index - 1,  inorder, postorder);
        root.right = helper(map, post_begin + in_index - in_begin, post_end - 1,in_index + 1, in_end,  inorder, postorder);
        return root;
    }

    /**
     *  也有非递归实现，但是没看懂。。
     */


    /**
     * 1. 根据前序和中序遍历构造B树
     *    前序遍历： 1245367
     *    中序遍历： 4251637
     *    后序遍历： 4526731
     *
     *    preorder = [3,9,20,15,7]
     *    inorder = [9,3,15,20,7]
     */




}
