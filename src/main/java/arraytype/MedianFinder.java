package arraytype;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/1/6
 * @ Time: 1:42 PM
 * @ Project: Algorithm-Java-implements
 */
public class MedianFinder {


    /*
     *
     * Median is the middle value in an ordered integer list. If the size of the list is even,
     * there is no middle value. So the median is the mean of the two middle value.
     *
     * For example,
     * [2,3,4], the median is 3
     *
     * [2,3], the median is (2 + 3) / 2 = 2.5
     *
     * Design a data structure that supports the following two operations:
     *
     * void addNum(int num) - Add a integer number from the data stream to the data structure.
     * double findMedian() - Return the median of all elements so far.
     *
     *
     */

    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(16, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    private int count = 0;
    /** initialize your data structure here. */
    public MedianFinder() {

    }

    public void addNum(int num) {
        if((count&1) == 0) {
           if(!minHeap.isEmpty() && num > minHeap.peek()) {
               minHeap.offer(num);
               num = minHeap.poll();
           }
           maxHeap.offer(num);
        }else {
            if(!maxHeap.isEmpty() && num < maxHeap.peek()) {
                maxHeap.offer(num);
                num = maxHeap.poll();
            }
            minHeap.offer(num);
        }
        count += 1;
    }

    public double findMedian() {
        if(minHeap.size() == maxHeap.size())
            return (float) (minHeap.peek() + maxHeap.peek()) / 2.0;
        else if(minHeap.size() > maxHeap.size())
            return (float) minHeap.peek() / 1.0;
        else
            return (float) maxHeap.peek() / 1.0;
    }

    public static void main(String[] args) {

    }

}
