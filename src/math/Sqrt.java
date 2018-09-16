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


    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        System.out.println(sqrt.sqrt(10));
    }
}
