package arraytype;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/1/9
 * @ Time: 8:17 PM
 * @ Project: Algorithm-Java-implements
 */
public class RangeSum2D {

    /**
     *Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper
     * left corner (row1, col1) and lower right corner (row2, col2).
     *
     * Range Sum Query 2D
     * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
     *
     *  Given matrix = [
     *   [3, 0, 1, 4, 2],
     *   [5, 6, 3, 2, 1],
     *   [1, 2, 0, 1, 5],
     *   [4, 1, 0, 1, 7],
     *   [1, 0, 3, 0, 5]
     * ]
     *
     * sumRegion(2, 1, 4, 3) -> 8
     * sumRegion(1, 1, 2, 2) -> 11
     * sumRegion(1, 2, 2, 4) -> 12
     *
     *
     */

    int[][] matrix = null;
    public RangeSum2D(int[][] matrix) {
        int m = matrix.length;
        if(m == 0) return;
        int n = matrix[0].length;
        this.matrix= new int[m+1][n+1];
        for(int i = 1 ; i <= m ; i ++) {
            int sum = 0;
            for(int j = 1 ; j <= n ; j ++) {
                sum += matrix[i-1][j-1];
                this.matrix[i][j] = sum + this.matrix[i-1][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        if(this.matrix == null) return sum;
        row1++;col1++;row2++;col2++;
        int x1 = row2;
        int y1 = col1-1;
        int x2 = row1-1;
        int y2 = col2;
        return this.matrix[row2][col2] - matrix[x1][y1] - matrix[x2][y2] + matrix[x2][y1];
    }
}
