package linklist;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/20
 * @Time: 下午10:01
 * @Project: Algorithm-Java-implements
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {

        if(head == null)
            return false;
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                return true;
            }
        }
        return false;
    }


    /**
     * 找到环的起始节点
     * 思路：还是快慢指针
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null)
            return null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow)
                break;
        }
        if(fast == null || fast.next == null) return null;
        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
