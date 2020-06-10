package concurrent;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/6/10
 * @ Time: 9:33 上午
 * @ Project: Algorithm-Java-implements
 */
public class ThreadsSum {


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
