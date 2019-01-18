package linklist;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/23
 * @Time: 下午10:21
 * @Project: Algorithm-Java-implements
 */
public class ParititionList {

    /**
     * 思路：原本打算写个快排的，但是看题解有更简单的解法，直接用两个链表p1和p2
     * p1 挂载比x小的，p2挂载比x大的,然后两个链表相连接
     */
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null)
            return head;
        ListNode p1 = new ListNode(0);
        ListNode p2 = new ListNode(0);

        ListNode q = head, f = p1, s = p2;
        while(q != null) {
            if(q.val < x) {
                f.next = q;
                f = f.next;
            }else {
                s.next = q;
                s = s.next;
            }
            q = q.next;
        }

        f.next = p2.next;
        s.next = null;
        return p1.next;
    }
}
