package arraytype;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/12/12
 * @ Time: 3:27 PM
 * @ Project: Algorithm-Java-implements
 */
public class SlidingWindowMaximum {

    static Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    };
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if(nums.length == 0) return new int[0];
        PriorityQueue<Integer> queue = new PriorityQueue(comparator);
        int[] res = new int[nums.length - k + 1];
        int cnt = 0;
        for(int i = 0 ; i < k ; i ++) {
            queue.offer(nums[i]);
        }
        int head = 0;
        res[cnt++] = queue.peek();
        queue.remove(nums[head++]);
        while(cnt < res.length) {
            queue.offer(nums[head + k - 1]);
            res[cnt++] = queue.peek();
            queue.remove(nums[head++]);
        }
        return res;
    }

    public int[] maxSlidingWindow(int[] in, int w) {
        final int[] max_left = new int[in.length];
        final int[] max_right = new int[in.length];
        if(in.length == 0) return new int[0];
        max_left[0] = in[0];
        max_right[in.length - 1] = in[in.length - 1];
        for (int i = 1; i < in.length; i++) {
            max_left[i] = (i % w == 0) ? in[i] : Math.max(max_left[i - 1], in[i]);

            final int j = in.length - i - 1;
            max_right[j] = (j % w == 0) ? in[j] : Math.max(max_right[j + 1], in[j]);
        }

        final int[] sliding_max = new int[in.length - w + 1];
        for (int i = 0, j = 0; i + w <= in.length; i++) {
            sliding_max[j++] = Math.max(max_right[i], max_left[i + w - 1]);
        }

        return sliding_max;
    }


    /**
     *  可以用一个双端队列保存窗口内最大值的index
     *
     *  存index可以判断何时从窗口删除
     *
     *  双端队列内部从对头到队尾按照index对应数组中的值降序排列
     *
     *  每次移动窗口，分3个步骤：
     *
     *  1. 判断当前最大值是否需要移除
     *  2. 将新进入窗口的值从双端队列的队尾移入到何时位置：如过队尾元素的数组值小于新值，则从队尾移除该元素，将新进入窗口的值移入队尾
     *  3. 如果当前位置大于等于窗口长度，每次滑动将窗口中队首元素值移入结果数组
     *
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        Deque<Integer> queue = new LinkedList<>();
        int cnt = 0;
        int n = nums.length;
        int[] res = new int[n-k+1];
        int index = 0;
        while(cnt < n) {
            if(queue.size() !=0 && (cnt-k) == queue.peek()) queue.removeFirst();
            while(queue.size() != 0 && nums[cnt] > nums[queue.getLast()]) queue.removeLast();
            queue.addLast(cnt);
            if(cnt + 1 >= k && queue.size() != 0) {
                res[index++] = nums[queue.peek()];
            }
            cnt ++;
        }
        return res;
    }
}


