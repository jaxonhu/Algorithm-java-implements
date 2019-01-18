package linklist;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/23
 * @Time: 下午10:41
 * @Project: Algorithm-Java-implements
 */
public class AddTwoNumbers {

    /**
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     * Explanation: 342 + 465 = 807.
     * 思路：先reverse 在相加 处理好进位
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        ListNode rl1 = reverseListNode(l1);
        ListNode rl2 = reverseListNode(l2);

        ListNode dummy = new ListNode(0);
        ListNode p = rl1, q = rl2, cur = dummy;
        int r = 0;
        while(p != null && q != null) {
            int n = (p.val + q.val + r) / 10;
            r = (p.val + q.val + r) % 10;
            cur.next = new ListNode(r);
            r = n;
            cur = cur.next;
            p = p.next;
            q = q.next;
        }
        while(p != null) {
            int n = (p.val + r) / 10;
            r = (p.val + r) % 10;
            cur.next = new ListNode(r);
            r = n;
            cur = cur.next;
            p = p.next;
        }
        while(q != null) {
            int n = (q.val + r) / 10;
            r = (q.val + r) % 10;
            cur.next = new ListNode(r);
            r = n;
            cur = cur.next;
            q = q.next;
        }
        if(r != 0) {
            cur.next = new ListNode(r);
            cur.next.next = null;
        }
        return dummy.next;
    }

    public ListNode reverseListNode(ListNode l) {
        if(l == null || l.next == null)
            return l;
        ListNode p = null;
        ListNode q = l;
        while(p.next != null) {
            ListNode tmp = q.next;
            q.next = p;
            p = q;
            q = tmp;
        }
        return p;
    }


    /**
     * 方法二  simplicity 的解法
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        int cnt = 0;
        while(l1 != null || l2 != null) {
            int val = (cnt + (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val)) % 10;
            cnt = (cnt + (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val)) / 10;
            cur.next = new ListNode(val);
            cur = cur.next;
            if(l1 != null) {
                l1 = l1.next;
            }
            if(l2 != null) {
                l2 = l2.next;
            }
        }
        if(cnt != 0) {
            cur.next = new ListNode(cnt);
            cur.next.next = null;
        }
        return dummy.next;
    }

}
