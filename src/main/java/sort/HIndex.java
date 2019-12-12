package sort;

import java.util.Arrays;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/12/12
 * @ Time: 4:16 PM
 * @ Project: Algorithm-Java-implements
 */
public class HIndex {

    /**
     * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
     *
     * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each,
     * and the other N − h papers have no more than h citations each."
     *
     *
     *
     * idea:
     * The idea is to see that the result can only range from 0 to the length of the array
     * (because we can't have h-index greater than the total papers published).
     * So we create an array "arr" which acts like a HashMap (using pigeon hole principle) and loop backwards from the highest element,
     * then we find "tot" which is the total number of papers that has more than i citations,
     * and we stop when tot>=i (total number of papers with more than i citations >= i).
     * We don't need to keep going because we are trying the biggest i possible, we we stop and return the result.
     *
     *
     */

    /**
     *  排序
     * @param citations
     * @return
     */
    public static int hIndex(int[] citations) {
        int n = citations.length;
        if(n == 0) return 0;
        Arrays.sort(citations);
        int mid = n-1;
        int sum = 0;
        do {
            for(int i = 0 ; i < mid ; i++) {
                sum += citations[i];
            }
            int rightCount = n - mid;
            if(sum <= rightCount)
                break;
            mid--;
            sum = 0;
        } while(mid >= 0);
        if(citations[mid] == 0)
            return 0;
        return n - mid;
    }

    /**
     * 桶排序
     * @param citations
     * @return
     */
    public int hIndex2(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n+1];
        for(int c : citations) {
            if(c >= n) {
                buckets[n]++;
            } else {
                buckets[c]++;
            }
        }
        int count = 0;
        for(int i = n; i >= 0; i--) {
            count += buckets[i];
            if(count >= i) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] input = {3, 4, 6, 1, 5};
        int res = hIndex(input);
        System.out.println(res);
    }

}
