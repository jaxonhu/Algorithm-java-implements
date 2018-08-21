package linklist;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/23
 * @Time: 下午9:27
 * @Project: Algorithm-Java-implements
 */
public class RotateList {

    /**
     * 链表 右移k个元素
     * Input: 1->2->3->4->5->NULL, k = 2
     * Output: 4->5->1->2->3->NULL
     * 思路：先遍历一遍得到长度，然后计算每个节点移动的步长
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0)
            return head;
        int n = 0;
        ListNode p = head;
        while(p != null) {
            p = p.next;
            n += 1;
        }
        int index = n - (k % n);
        p = head;
        if(index == n)
            return head;
        while(index > 1) {
            p = p.next;
            index -= 1;
        }
        ListNode tmp = head;
        head = p.next;
        p.next = null;
        ListNode tail = head;
        while(tail.next != null)
            tail = tail.next;
        tail.next = tmp;
        return head;
    }


    /**
     * 思路二：
     * 基本步骤跟方法1差不多，在旋转的时候先想tail.next = head 形成一个环，之后就比较好找新的头节点了
     *
     */
}
