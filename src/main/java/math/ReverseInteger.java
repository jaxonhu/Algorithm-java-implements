package math;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/29
 * @Time: 下午8:57
 * @Project: Algorithm-Java-implements
 */
public class ReverseInteger {

    public int reverse(int x) {
        if(x == 0) return 0;
        int reverse = 0;
        while(x != 0) {
            int r = x % 10;
            x = x / 10;
            if((reverse > (Integer.MAX_VALUE / 10)) || reverse < (Integer.MIN_VALUE/10))
                return 0;
            reverse = reverse * 10 + r;
        }
        return reverse;
    }

}
