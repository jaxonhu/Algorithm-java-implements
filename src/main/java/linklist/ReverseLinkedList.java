package linklist;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/6/19
 * @Time: 下午4:11
 * @Project: Algorithm-Java-implements
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class ReverseLinkedList {


    /*
        思路： 用迭代的方法，类似冒泡排序思想，可以在O(n^2)时间内
        双指针，可以在O(n)时间内
        递归的话，
    */
    //先写非递归实现
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode p = head, q = head.next;
        while(q != null) {
            ListNode tmp = q.next;
            q.next = p;
            p = q;
            q = tmp;
        }
        head.next = null;
        return p;
    }

    /**
     *  递归实现
     *
     */
    public ListNode reverseList2(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode head_next = head.next;
        ListNode head_new = reverseList2(head_next);
        head_next.next = head;
        head.next = null;
        return head_new;
    }

}
