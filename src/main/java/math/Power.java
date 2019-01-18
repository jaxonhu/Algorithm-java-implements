package math;

public class Power {

    /**
     * 描述：给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     */
    public double Power(double base, int exponent) {
        boolean flag = true;
        if(exponent < 0) flag = false;
        double res = base;
        exponent = Math.abs(exponent);
        if(doubleCompare(base, 0)) {
            if(exponent == 0)
                return -1.0;
            return 0.0;
        }else {
            if(exponent == 0)
                return 1.0;
            for(int i = 1 ; i < exponent ; i ++) {
                res = res * base;
            }
        }
        if(!flag)
            res = 1 / res;
        return res;
    }

    private boolean doubleCompare(double d1, double d2) {
        double delta = Math.abs(d1 - d2);
        if(delta < 0.0000001)
            return true;
        return false;
    }

    /**
     * 可以有递归的写法
     */

    public static  void main(String[] args) {
        Power p = new Power();
        System.out.println(p.Power(1.1, 2));
    }
}
