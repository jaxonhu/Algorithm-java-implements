package math;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/3
 * @ Time: 5:21 下午
 * @ Project: Algorithm-Java-implements
 */
public class FastPower {

    /**
     *   快速幂算法 计算 a^n
     *
     *   普通思路：
     *      依次计算 a^1  a^2 a^4 .... a^n/2  算法复杂度仍然是线性o(n/2)
     *
     *   快速幂思路：
     *      如果能找到2^k = n，那么只需要计算(((a^2)^2)^2)... 这样复杂度就降到O(logN)
     *      但是2^k很难找，所以可以找2^k1 + 2^k2 + 2^k3 + ... + 2^km = n
     *
     *      所以可以通过二进制把n转化为2^km序列之和
     *      如此一来，就只需要计算 a, a^2, a^4, a^8,...,a^2km的乘积即可
     *
     */
    long mod = (long)1e7;
    long fastPower(long a, long n) {
        long res = 1;
        while(n > 0) {
            if((n&1) == 1) {
                res = (res * a) % mod;
            }
            n = n >> 1;
            a = (a*a) % mod;
        }
        return res % mod;
    }


    /**
     *  普通版本的幂计算方法;
     *  可以对递归子集的结果记忆化，减少递归次数;
     */
    long power(long a, long b) {
        if(b == 0)
            return 1;
        if(b == 1)
            return a;
        return power(a, b/2) * power(a,b/2 + (b % 2));
    }

    /**
     * 非递归的写法
     */

    long powerIterative(long a, long b) {
        long res = a;
        while(b > 0) {
            if((b % 2) == 0) {
                b = b / 2;
                res = res * res;
            } else {
                b = b - 1;
                res = res * b;
            }
        }
        return res;
    }



    /**
     *
     * 矩阵快速幂, 算法与上面基本一致
     *  N*N 矩阵
     */

    int[][] matrixMultiple(int[][] A, int[][] B) {
        int N = A.length;
        int[][] res=  new int[N][N];
        for(int i = 0 ; i < N ; i ++) {
            for(int j = 0 ; j < N ; j ++) {
                int temp = 0;
                for(int k  = 0 ; k < N ; k ++) {
                    temp += A[i][k] * B[k][j];
                }
                res[i][j] = temp;
            }
        }
        return res;
    }

    int[][] powMatrix(int[][] A, int k) {
        int n = A.length;
        int[][] res = new int[n][n];
        for(int i = 0 ; i < n ; i ++) res[i][i] = 1;
        while(k > 0) {
            if((k&1) == 1) {
                res = matrixMultiple(res, A);
            }
            A = matrixMultiple(A, A);
            k >>= 1;
        }
        return res;
    }

    @Test
    public void testPowMatrix() {
        int[][] A  = new int[][] {
                {1, 1},
                {1, 1}
        };
        int[][] res = powMatrix(A, 3);
        for(int i = 0 ; i < A.length ; i ++) {
            System.out.println(Arrays.toString(res[i]));
        }
    }


}
