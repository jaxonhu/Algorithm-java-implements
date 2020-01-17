package backtracking;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/12/26
 * @ Time: 11:25 AM
 * @ Project: Algorithm-Java-implements
 */
public class NimGame {

    /**
     *
     *  You are playing the following Nim Game with your friend: There is a heap of stones on the table,
     *  each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner.
     *  You will take the first turn to remove the stones.
     *
     * Both of you are very clever and have optimal strategies for the game.
     * Write a function to determine whether you can win the game given the number of stones in the heap.
     *
     * Input: 4
     * Output: false
     * Explanation: If there are 4 stones in the heap, then you will never win the game;
     *              No matter 1, 2, or 3 stones you remove, the last stone will always be
     *              removed by your friend.
     *
     */

    public boolean canWinNim(int n) {
        return n%4 == 0? false: true;
    }


    public boolean canWinNim2(int n) {
        // 1 2 3 (4) 5 6 7 (8) 9 10 11 (12)
        if(n <= 0) return false;
        if(n == 1 || n == 2 || n== 3) return true;
        if(canWinNim(n-1) && canWinNim(n-2) && canWinNim(n-3)) return false;
        return true;
    }


    public boolean canWinNim3(int n) {
        // 1 2 3 (4) 5 6 7 (8) 9 10 11 (12)
        // add this line to pass large test case
        if(n >= 134882061) return n%4 != 0;
        boolean res = true;
        boolean fir = true;
        boolean sec = true;
        boolean thir = true;
        for(int i=4;i<=n;i++){
            res = (fir && sec && thir) ? false:true;
            fir = sec;
            sec = thir;
            thir = res;
        }
        return res;
    }

}