package linklist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/14
 * @ Time: 1:13 上午
 * @ Project: Algorithm-Java-implements
 */
public class MergeKSortedLists {


    /**
     *
     * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     *
     * Example:
     *
     * Input:
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * Output: 1->1->2->3->4->4->5->6
     *
     *
     *
     */

    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if(n == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(n, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode n1, ListNode n2) {
                return n1.val - n2.val;
            }
        });
        for(int i = 0 ; i < n ; i ++) queue.offer(lists[i]);
        while(!queue.isEmpty()) {
            ListNode p = queue.poll();
            if(p.next != null) {
                queue.offer(p);
            }
            dummy.next = p;
            dummy = dummy.next;
        }
        return head.next;
    }



}
