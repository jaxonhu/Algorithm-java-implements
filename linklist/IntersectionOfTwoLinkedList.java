package linklist;

import java.util.Stack;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/20
 * @Time: 下午10:20
 * @Project: Algorithm-Java-implements
 */
public class IntersectionOfTwoLinkedList {

    /**
     *  两个链表的交点
     *  思路：用栈先push  这个思路太low了就不写了
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode result = null;
        if(headA == null || headB == null)
            return result;
        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();

        return null;
    }

    /**
     *  思路2：先让A遍历到结尾，然后指向B，形成一个环，那么就转化为了找一个有环链表的起始环点
     *  懒，我就先不写了
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {

        return null;
    }



}
