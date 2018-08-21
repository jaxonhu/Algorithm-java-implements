package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/16
 * @Time: 下午10:04
 * @Project: Algorithm-Java-implements
 */

   class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
  }
public class NonoverlappingIntervals {

    /**
     * 435
     *
     * 可以使用动态规划或者贪心算法
     */


    /**
     * 动态规划解法： 思路与最长上升子序列类似，按区间起点排序
     */

    class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval o1, Interval o2) {
            return (o1.start > o2.start ? -1 : (o1.start == o2.start ? 0 : 1));
        }
    }

    public int eraseOverlapIntervals(Interval[] intervals) {

        int n = intervals.length;
        if(n == 0) return 0;

        Arrays.sort(intervals, new IntervalComparator());
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for(int i = 0 ; i < n ; ++ i) {
            for(int j = 0 ; j < i ; ++ j) {
                if(intervals[j].end <= intervals[i].start) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return  n - dp[n-1];
    }

    /**
     * 贪心解法：
     */

    public int eraseOverlapIntervals2(Interval[] interval) {

        return 0;
    }


}
