package arraytype;

import java.util.Arrays;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/1/6
 * @ Time: 11:00 AM
 * @ Project: Algorithm-Java-implements
 */
public class GameOfLife {

    /**
     *
     * According to the Wikipedia's article: "The Game of Life, also known simply as Life,
     * is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
     *
     * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
     * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
     * using the following four rules (taken from the above Wikipedia article):
     *
     * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
     * Any live cell with two or three live neighbors lives on to the next generation.
     * Any live cell with more than three live neighbors dies, as if by over-population..
     * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     *
     * Write a function to compute the next state (after one update) of the board given its current state.
     * The next state is created by applying the above rules simultaneously to every cell in the current state,
     * where births and deaths occur simultaneously.
     *
     * Input:
     * [
     *   [0,1,0],
     *   [0,0,1],
     *   [1,1,1],
     *   [0,0,0]
     * ]
     * Output:
     * [
     *   [0,0,0],
     *   [1,0,1],
     *   [0,1,1],
     *   [0,1,0]
     * ]
     *
     */

    public void gameOfLife(int[][] board) {
        int m = board.length;
        if(m == 0) return;
        int n = board[0].length;
        int[][] res = new int[m][n];
        for(int i = 0 ; i < m ; i ++) {
            for(int j  = 0 ; j < n ; j ++) {
                int sum = judge(i, j, board);
                if(board[i][j] == 1 && sum < 2)
                    res[i][j] = 0;
                else if(board[i][j] == 1 && (sum == 2 || sum == 3)) {
                    res[i][j] = 1;
                }
                else if(board[i][j] == 1 && sum > 3)
                    res[i][j] = 0;
                else if(board[i][j] == 0 && sum == 3)
                    res[i][j] = 1;
                else
                    res[i][j] = board[i][j];
            }
        }
        for(int i = 0 ; i < m ; i ++) {
            System.arraycopy(res[i], 0, board[i], 0, n);
        }
    }

    int judge(int i, int j, int[][] board) {

        int sum = 0;
        sum += readCell(i-1, j-1, board);
        sum += readCell(i-1, j, board);
        sum += readCell(i-1, j+1, board);
        sum += readCell(i, j-1, board);
        sum += readCell(i, j+1, board);
        sum += readCell(i+1, j-1, board);
        sum += readCell(i+1, j, board);
        sum += readCell(i+1,j+1, board);
        return sum;
    }

    int readCell(int i, int j, int[][] board) {

        int m = board.length;
        int n = board[0].length;
        if(i < 0 || i >=m ) return 0;
        if(j < 0 || j >=n ) return 0;
        return board[i][j];
    }


    /**
     * 也可以子原矩阵修改，增加两个状态变量-1 和 2
     * -1 表示cell从live转为dead   2 表示cell从dead转为live
     * 这样就可以表示未修改前的cell状态
     * 然后再同一修改为0 1
     */

    public void gameOfLife2(int[][] board) {
        int m = board.length;
        if(m == 0) return;
        int n = board[0].length;
        for(int i = 0 ; i < m ; i ++) {
            for(int j = 0 ; j < n ; j ++) {
                judge2(board, i, j);
            }
        }
        for(int i = 0 ; i < m ; i ++) {
            for(int j = 0 ; j < n ; j ++) {
                if(board[i][j] > 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }

    void judge2(int[][] board, int i, int j) {
        int sum = 0;
        sum += readCell2(i-1,j-1,board);
        sum += readCell2(i-1,j,board);
        sum += readCell2(i-1,j+1,board);
        sum += readCell2(i,j-1,board);
        sum += readCell2(i,j+1,board);
        sum += readCell2(i+1,j-1,board);
        sum += readCell2(i+1,j,board);
        sum += readCell2(i+1,j+1,board);
        if(board[i][j] == 1 && sum < 2) {
            board[i][j] = -1;
        } else if(board[i][j] == 1 && (sum == 2 || sum == 3)) {
            board[i][j] = 1;
        } else if(board[i][j] == 1 && sum > 3) {
            board[i][j] = -1;
        } else if(board[i][j] == 0 && sum == 3) {
            board[i][j] = 2;
        }
    }

    int readCell2(int i, int j, int[][] board) {
        int m = board.length;
        int n = board[0].length;
        if(i < 0 || i >= m) return 0;
        if(j < 0 || j >= n) return 0;
        if(board[i][j] == 1 || board[i][j] == -1) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        int[][] test = new int[][] {
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
            };

        GameOfLife gol = new GameOfLife();
        gol.gameOfLife(test);
        for(int i = 0 ; i < test.length ; i ++) {
            System.out.println(Arrays.toString(test[i]));
        }

    }
}
