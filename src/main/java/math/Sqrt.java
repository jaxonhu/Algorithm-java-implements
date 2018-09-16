package math;

public class Sqrt {


    /**
     * 二分法：
     *
     */
    double FLT_MIN = 0.001;
    double sqrt(int x) {
        if ( x <= 0 ) return 0;
        double begin = 1.0;
        double end = x/2 + 1;
        double mid, lastmid;

        mid = begin + (end - begin) / 2;
        do {
            if(mid < x / mid) begin = mid;
            else end = mid;
            lastmid = mid;
            mid = begin + (end - begin) / 2;
        }while(Math.abs(lastmid - mid) > FLT_MIN);

        return mid;
    }


    /**
     * 牛顿迭代法
     */

    double sqrt2(int x) {
        if( x <= 0 ) return 0;
        double mid = 10.0;
        double lastmid;
        do{
            lastmid = mid;
            mid = (lastmid + x/mid) / 2;
        }while(Math.abs(lastmid - mid) > FLT_MIN);
        return 0;
    }


    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        long start1 = System.nanoTime();
        System.out.println(sqrt.sqrt(10));
        long end1 = System.nanoTime();
        long start2 = System.nanoTime();
        System.out.println(sqrt.sqrt(10));
        long end2 = System.nanoTime();

        System.out.println("duration sqrt1: " + (end1 - start1));
        System.out.println("duration sqrt2: " + (end2 - start2));
    }
}
