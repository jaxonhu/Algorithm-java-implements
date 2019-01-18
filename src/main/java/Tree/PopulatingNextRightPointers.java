package Tree;

import java.util.LinkedList;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/5
 * @Time: 下午10:04
 * @Project: Algorithm-Java-implements
 */


class TreeLinkNode {
      int val;
      TreeLinkNode left, right, next;
      TreeLinkNode(int x) { val = x; }
  }


public class PopulatingNextRightPointers {

    //用层序遍历的方法做,比较通用，但是注意题目要求是否是常数空间
    public void connect(TreeLinkNode root) {
        LinkedList<TreeLinkNode> queue = new LinkedList<>();
        int count = 0	;
        int last = 0;
        int index = 0;
        int first = 0;
        if(root == null)
            return;
        queue.add(root);
        TreeLinkNode p = null;
        while(!queue.isEmpty()){
            TreeLinkNode r = queue.removeFirst();

            if(r.left != null){
                queue.add(r.left);
                count ++;
            }
            if(r.right != null){
                queue.add(r.right);
                count ++;
            }
            //last node in a row
            if(index == last){
                if(p != null)
                    p.next = r;
                r.next = null;
                first = last + 1;
                last = count;
            }else if(index == first){
                p = r;
            }else{
                p.next = r;
                p = r;
            }
            index ++;
        }
    }

    //由于题目中是一颗满二叉树，所以有简单解法
    /**
     * first记录每一层的第一个节点
     * 如果p有左子节点，则p.left.next = p.right
     * 如果p有next节点，则p.right.next = p.next.left;
     * 然后 p = p.next
     */

    public void connect2(TreeLinkNode root) {
        if(root == null)
            return;
        TreeLinkNode p = root;
        TreeLinkNode first = null;
        p.next = null;
        while(p != null) {
            if(first == null) {
                first = p.left;
            }
            if(p.left != null) {
                p.left.next = p.right;
            }else {
                //遍历到叶子节点，结束
                break;
            }
            if(p.next != null) {
                p.right.next = p.next.left;
                p = p.next;
                continue;
            }else {
                p = first;
                first = null;
            }
        }
    }

    /**
     *  下面是对这道题的变种，不再是一颗满二叉树，因此需要用两个指针 first 和 last, first 指向每一层的第一个节点
     *  last指向一层中链表的最后一个
     */

    public void connect3(TreeLinkNode root) {
        if(root == null) return;
        TreeLinkNode p = root;
        TreeLinkNode first = null;
        TreeLinkNode last = null;
        while(p != null) {
            if(first == null) {
                if(p.left != null){
                    first = p.left;
                }else if(p.right != null) {
                    first = p.right;
                }
            }
            //左节点不为空，则为左节点的next赋值，左节点的可能是第一个，此时last为空
            if(p.left != null) {
                if(last != null) {
                    last.next = p.left;
                }else {
                    last = p.left;
                }
            }
            //右节点也是同理
            if(p.right != null) {
                if(last != null) {
                    last.next = p.left;
                }else {
                    last = p.right;
                }
            }
            if(p.next != null) {
                p = p.next;
            }else {
                p = first;
                first = null;
                last = null;
            }
        }

    }
}
