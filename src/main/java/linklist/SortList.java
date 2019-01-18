package linklist;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/22
 * @Time: 下午10:00
 * @Project: Algorithm-Java-implements
 */
public class SortList {

    /**
     * 链表排序
     * 思路：链表的快排
     */
    public ListNode sortList(ListNode head) {
        if(head == null) return head;
        quickSort(head, null);
        return head;
    }

    public void quickSort(ListNode start, ListNode end) {
        if(start == null || start.next == null)
            return;
        ListNode small = start;
        ListNode p = small.next;
        while(p != end) {
            if(p.val >= start.val) {
                p = p.next;
            }else {
                small = small.next;
                int t = p.val;
                p.val = small.val;
                small.val = t;
                p = p.next;
            }
        }
        int t = start.val;
        start.val = small.val;
        small.val = t;
        quickSort(start, small);
        quickSort(small.next, end);
    }

    /**
     * 重写一遍，链表快速排序
     */

    public void listQuickSort(ListNode head) {

    }

    public void quickSort2(ListNode start, ListNode end) {
        if(start == end)
            return;
        ListNode p = start;
        ListNode small = start;
        int key = p.val;
        while(p != end) {
            if(p.val >= key) {
                p = p.next;
            }else {
                small = small.next;
                int temp = small.val;
                small.val = p.val;
                p.val = temp;
                p = p.next;
            }
        }
        int temp = start.val;
        start.val = small.val;
        small.val = temp;
        quickSort(start, small);
        quickSort(small.next, end);
    }
}
