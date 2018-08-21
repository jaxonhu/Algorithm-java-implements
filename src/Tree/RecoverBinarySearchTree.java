package Tree;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/6
 * @Time: 上午11:14
 * @Project: Algorithm-Java-implements
 */
public class RecoverBinarySearchTree {

    /**
     * 首先介绍一下 Morris Traversal
     */

    public void inorderMorriesTraversal(TreeNode root) {
        if(root == null) return;
        TreeNode cur = root;
        TreeNode prev = null;
        while(cur != null) {
            if(cur.left == null) {
                System.out.println(cur.val);
                cur = cur.right;
            }else {
                prev = cur.left;
                while(prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }
                if(prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                }else {
                    prev.right = null;
                    System.out.println(cur.val);
                    cur = cur.right;
                }
            }
        }
    }

    /**
     * 对Morris Traversal稍加改造，用first second记录需要交换位置的两个节点
     * prev记录前驱节点
     */
    public void recoverTree(TreeNode root) {
        if(root == null)
            return;
        TreeNode prev = root;
        TreeNode cur = root;
        TreeNode first = null, second = null, temp = null;
        while(cur != null) {
            if(cur.left == null) {
                if(prev != null && prev.val > cur.val) {
                    if(first == null) {
                        first = prev;
                        second = cur;
                    }else {
                        second = cur;
                    }
                }
                prev = cur;
                cur = cur.right;
            }else {
                //找前驱节点
                temp = root.left;
                while(temp != null && temp.right != cur) {
                    temp = temp.right;
                }
                if(temp == null) {
                    temp.right = cur;
                    cur = cur.left;
                }else {
                    //判断
                    if(temp != null && temp.val > cur.val) {
                        if(first == null){
                            first = temp;
                            second = cur;
                        }else {
                            second = cur;
                        }
                    }
                    prev = cur;
                    temp.right = null;
                    cur = cur.right;
                }
            }
        }

        if(first != null && second != null) {
            int t = first.val;
            first.val = second.val;
            second.val = t;
        }
    }


}
