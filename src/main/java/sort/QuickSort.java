package sort;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/6/19
 * @Time: 上午9:23
 * @Project: Algorithm-Java-implements
 */

import java.util.Arrays;
import java.util.Stack;

/**
*   快速排序算法
*/

class Node {
    int start;
    int end;

    public Node(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

//链表的快速排序
class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

public class QuickSort {

    /**
    *   递归写法
    */
    public static  void quickSort(int[] nums, int start, int end) {
        if(start > end)
            return;
        int key = nums[start];
        int i = start;
        int j = end;
        int index = start;
        while(i < j){
            while(nums[j] > key && j > i) {
                j --;
            }
            nums[i] = nums[j];
            while(nums[i] <= key && j > i) {
                i ++;
            }
            nums[j] = nums[i];
        }
        nums[j] = key;

        quickSort(nums, start, j-1);
        quickSort(nums, j+1, end);
    }

    /**
    *   快排的非递归写法
     *   用栈存储start和end
    */



    public static void quickSortUndfs(int[] nums){
        int n = nums.length;
        Node node = new Node(0, n - 1);
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while(!stack.empty()) {
            Node node_cur = stack.pop();
            int start = node_cur.start;
            int end = node_cur.end;
            if(start > end) continue;
            int key = nums[start];
            int i = start, j = end;
            while(i < j) {
                while(key < nums[j] && i < j) {
                    j --;
                }
                nums[i] = nums[j];
                while(key >= nums[i] && i < j) {
                    i ++;
                }
                nums[j] = nums[i];
            }
            nums[j] = key;
            Node node1 = new Node(start, j-1);
            Node node2 = new Node(j+1, end);
            stack.push(node1);
            stack.push(node2);
        }
    }


    /**
     *  单链表的快速排序
     *  思路：
     *  1. 双指针从头到尾遍历，small指向比key值小的元素，p指向当前比较元素
     *  2. 如果p值小于中心值，则与small的下一个值（隐含大于中心值）进行交换，然后small指向small的下一个
     *  3. 当p指针遍历完毕后，将head与small指针指向的值进行交换
     *  4. 本质还是一个递归的过程
     */

    public static ListNode construct(){
        ListNode head = new ListNode(4);
        head.next = new ListNode(5);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(8);
        return head;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.print("\n");
    }

    public static void listQuickSort(ListNode head, ListNode end) {
        if(head == null || head == end)
            return;
        ListNode p = head.next;
        ListNode small = head;
        int t;
        while(p != end) {
            if(p.val < head.val) {
                small = small.next;
                t = small.val;
                small.val = p.val;
                p.val = t;
            }
            p = p.next;
        }
        t = head.val;
        head.val = small.val;
        small.val = t;
        listQuickSort(head, small);
        listQuickSort(small.next, end);
    }

    public static void main(String[] args){
        int[] nums = {8, 2, 6, 1, 6};
//        QuickSort.quickSort(nums, 0, 4);
//        QuickSort.quickSortUndfs(nums);
        ListNode head = construct();
        printList(head);
        ListNode end = head;
        while(end.next != null)
            end = end.next;
        listQuickSort(head, end);
        printList(head);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 链表的归并排序
     */
    public ListNode sortList2(ListNode head) {
        if(head == null || head.next == null)
            return head;
        //这里划分的有问题  如果是 1 ——》 2 只有两个元素的链表，则每次划分都是相同的，所以需要记录slow的前一个
        ListNode slow = head, fast = head, prev = null;
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;
        ListNode l1 = sortList2(head);
        ListNode l2 = sortList2(slow);
        ListNode result = merge(l1, l2);
        return result;
    }

    public ListNode merge(ListNode l1, ListNode l2){

        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                p.next = l1;
                l1 = l1.next;
            }else{
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        if(l1 != null)
            p.next = l1;
        if(l2 != null)
            p.next = l2;

        return dummy.next;
    }
}
