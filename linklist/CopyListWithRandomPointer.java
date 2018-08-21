package linklist;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/24
 * @Time: 上午9:49
 * @Project: Algorithm-Java-implements
 */

  class RandomListNode {
      int label;
      RandomListNode next, random;
      RandomListNode(int x) { this.label = x; }
  }

public class CopyListWithRandomPointer {

    public RandomListNode copyRandomList(RandomListNode head) {

        if(head == null)
            return null;

        RandomListNode phead = head;
        while(phead != null) {
            RandomListNode rl = new RandomListNode(phead.label);
            RandomListNode next = phead.next;
            phead.next = rl;
            rl.next = next;
            phead = phead.next.next;
        }

        phead = head;
        while(phead != null) {
            if(phead.random != null) {
                phead.next.random = phead.random.next;
            }else {
                phead.next.random = null;
            }
            phead = phead.next.next;
        }

        phead = head;
        RandomListNode res = head.next;
        while(phead.next != null) {
            RandomListNode tmp = phead.next;
            phead.next = tmp.next;
            phead = tmp;
        }

        return  res;
    }

  }
