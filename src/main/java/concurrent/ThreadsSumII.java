package concurrent;

import java.util.concurrent.*;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/6/11
 * @ Time: 11:04 下午
 * @ Project: Algorithm-Java-implements
 */
public class ThreadsSumII {

    private static int[] target;

    public static void setTarget(int[] nums) {
        target = nums;
    }

    public static class RunnableTask implements Callable<Integer> {

        int begin;
        int end;

        public RunnableTask(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for(int i = begin ; i < end && i < target.length;  i ++) {
                sum += target[i];
            }
            return sum;
        }
    }


    public int getSum(int[] nums, int threadsNum) {
        int sum = 0;
        setTarget(nums);
        int len = nums.length / threadsNum;
        int left = nums.length % threadsNum;
        Future<Integer>[] futures = new Future[threadsNum];
        ExecutorService executor = Executors.newFixedThreadPool(threadsNum);
        for(int i = 0 ; i < threadsNum ; i ++) {
            int begin = i * len;
            int end = (i+1)*len + (i == threadsNum-1 ? left:0);
            futures[i] = executor.submit(new RunnableTask(begin, end));
        }
        for(int i = 0 ; i < threadsNum ; i ++) {
            try{
                sum += futures[i].get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ThreadsSumII threadsSumII = new ThreadsSumII();
        System.out.println(threadsSumII.getSum(nums, 3));

    }

}
