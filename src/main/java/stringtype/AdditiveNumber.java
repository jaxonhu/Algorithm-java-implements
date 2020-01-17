package stringtype;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/1/13
 * @ Time: 1:34 PM
 * @ Project: Algorithm-Java-implements
 */
public class AdditiveNumber {

    /**
     *
     * Additive number is a string whose digits can form additive sequence.
     *
     * A valid additive sequence should contain at least three numbers. Except for the first two numbers,
     * each subsequent number in the sequence must be the sum of the preceding two.
     *
     * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
     *
     * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
     *
     */


    /**
     *  解题思路： 首先很容易想到递归，本来也是用递归写的，但是碰到大数的问题只用基础数据类型无法解决，网上嫖了高票答案，发现是用BigInteger
     *  来处理的。而且，尾递归都可以改写为循环。
     *
     *  Math.max(j, i) <= n - i - j
     *
     *  (n-j-i)是剩下的sum部分，根据两数字相加，其和的位数等于两个加数之间最大的位数或最大的位+1，所以Math(j,i)一定小于等于n-i-j
     *
     *  只要确定了前两项，后面就可以验证了
     *
     *
     */

    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n / 2; ++i) {
            if (num.charAt(0) == '0' && i > 1) return false;
            BigInteger x1 = new BigInteger(num.substring(0, i));
            for (int j = 1; Math.max(j, i) <= n - i - j; ++j) {
                if (num.charAt(i) == '0' && j > 1) break;
                BigInteger x2 = new BigInteger(num.substring(i, i + j));
                if (isValid(x1, x2, j + i, num)) return true;
            }
        }
        return false;
    }
    private boolean isValid(BigInteger x1, BigInteger x2, int start, String num) {
        if (start == num.length()) return true;
        x2 = x2.add(x1);
        x1 = x2.subtract(x1);
        String sum = x2.toString();
        return num.startsWith(sum, start) && isValid(x1, x2, start + sum.length(), num);
    }


    public static void main(String[] args) {
        AdditiveNumber instance = new AdditiveNumber();
        String input = "8019823962";
        boolean res = instance.isAdditiveNumber(input);
        System.out.println(res);
    }

}
