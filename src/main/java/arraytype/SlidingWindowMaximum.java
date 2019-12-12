package arraytype;

import java.util.Comparator;
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

}


