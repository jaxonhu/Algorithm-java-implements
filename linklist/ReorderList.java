package linklist;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/23
 * @Time: 下午9:56
 * @Project: Algorithm-Java-implements
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        if(head == null || head.next == null)
            return;

        ListNode slow = head, fast = head;
        while(fast != null && fast.next !=  null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode h = slow.next;
        slow.next = null;
        ListNode dummy = new ListNode(0);
        while(h != null) {
            ListNode next = h.next;
            ListNode tmp = dummy.next;
            dummy.next = h;
            h.next = tmp;
            h = next;
        }

        fast = dummy.next;
        slow = head;
        while(slow != null) {
            if(fast != null) {
                ListNode m = slow.next;
                slow.next = fast;
                ListNode n = fast.next;
                fast.next = m;
                slow = m;
                fast = n;
            }else
                break;
        }
    }
}
