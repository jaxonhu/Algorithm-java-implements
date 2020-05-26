package Tree;

import java.util.*;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/18
 * @Time: 上午9:17
 * @Project: Algorithm-Java-implements
 */
public class LowestCommonAncestorOfABinaryTree {

    /**
     *  两个节点的最低公共祖先
     *  思路：非递归深度后序遍历，访问到特定节点的时候打印栈到一个存储空间
     *  转化为求两个链表的公共祖先
     */

    /**
     * 我这个遍历写的不对，如何修改？我写成了中序遍历，其实应该用后序遍历
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> listp = null, listq = null;
        if(root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode t = root;
        stack.push(t);
        while(t != null || !stack.empty()) {
            while(t != null) {
                stack.push(t);
                t = t.left;
            }
            if(!stack.empty()) {
                t = stack.pop();
                System.out.println(t.val);
            }
            if(t != null) {
               if(t.val == p.val)
                   listp = printStackDetails(stack);
               if(t.val == q.val)
                   listq = printStackDetails(stack);
               t = t.right;
            }
        }

        TreeNode res = getCommonNodes(listp, listq);

        return res;
    }

    public List<TreeNode> printStackDetails(Stack<TreeNode> stack) {
        List<TreeNode>  list = new ArrayList<>();
        Iterator<TreeNode> iter = stack.iterator();
        while(iter.hasNext()) {
            list.add(iter.next());
            System.out.println(list.get(list.size() - 1).val);
        }
        return list;
    }

    public TreeNode getCommonNodes(List<TreeNode> listp, List<TreeNode> listq) {

        int i = 0 , j = 0;
        int n = listp.size();
        int m = listq.size();
        TreeNode res = null;
        while(i < n && j < m) {
            if(listp.get(i).val == listq.get(j).val) {
                res = listp.get(i);
            }else
                break;
        }
        return res;
    }


    /**
     * 递归写法
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return root;
        if(root.val == p.val || root.val == q.val)
            return root;
        TreeNode one = lowestCommonAncestor(root.left, p, q);
        TreeNode two = lowestCommonAncestor(root.right, p, q);
        if(one != null && two != null)
            return root;
        return one == null ? two : one;
    }

    /**
     * 用hashmap配合hashset的一种做法
     *
     */

    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        parent.put(root, null);
        stack.push(root);

        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                parent.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                stack.push(node.right);
            }
        }
        Set<TreeNode> ancestors = new HashSet<>();
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        while (!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }


    /**
     *  变种，如果原来的树是一棵二叉搜索树
     */

    public TreeNode lowestCommonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val > q.val) return lca(root, q, p);
        return lca(root, p, q);
    }

    public TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val < p.val) {
            return lca(root.right, p, q);
        }else if(root.val > q.val) {
            return lca(root.right, p, q);
        }else {
            return root;
        }
    }

    /**
     *  这样写好一点
     */
    public TreeNode lowestCommonAncestor5(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q || p == null || q == null)
            return root;

        if(p.val > q.val) {
            TreeNode temp = q;
            q = p;
            p = temp;
        }
        if(root.val > q.val) return lowestCommonAncestor5(root.left, p, q);
        if(root.val < p.val) return lowestCommonAncestor5(root.right, p, q);
        return root;
    }


}
