package concurrent;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/6/10
 * @ Time: 9:33 上午
 * @ Project: Algorithm-Java-implements
 */
public class ThreadsSum {

    /**
     *
     * 面试一道手写volatile的例题，当时很奇怪加不加volatile关键字 除了第一次运行，都能得到正确的结果
     * 当时怎么改线程数、循环数都不行，现在看来，可能是牛客网锁了线程数/核心数。
     *
     */

    public static  int sum = 0;

    static class RunnableTask implements Runnable {

        @Override
        public void run() {
            for(int i = 0 ; i < 10000 ; i ++) {
                sum += 1;
            }
        }
    }



    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for(int i = 0 ; i < 10 ; i ++) {
            threads[i] = new Thread(new RunnableTask());
            threads[i].start();
        }

        for(int i = 0 ; i < 10 ; i ++) {
            threads[i].join();
        }
        System.out.println(sum);
    }

}
