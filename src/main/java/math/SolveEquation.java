package math;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/28
 * @ Time: 12:08 下午
 * @ Project: Algorithm-Java-implements
 */
public class SolveEquation {

    /**
     *
     * 求解一个给定的方程，将x以字符串"x=#value"的形式返回。该方程仅包含'+'，' - '操作，变量 x 和其对应系数。
     *
     * 如果方程没有解，请返回“No solution”。
     *
     * 如果方程有无限解，则返回“Infinite solutions”。
     *
     * 如果方程中只有一个解，要保证返回值 x 是一个整数。
     *
     * 示例 1：
     *
     * 输入: "x+5-3+x=6+x-2"
     * 输出: "x=2"
     * 示例 2:
     *
     * 输入: "x=x"
     * 输出: "Infinite solutions"
     * 示例 3:
     *
     * 输入: "2x=x"
     * 输出: "x=0"
     * 示例 4:
     *
     * 输入: "2x+3x-6x=x+2"
     * 输出: "x=-1"
     * 示例 5:
     *
     * 输入: "x=x+2"
     * 输出: "No solution"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/solve-the-equation
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     *
     */

    public String solveEquation(String equation) {
        int xs = 0;
        int con = 0;
        int c = 0;
        int flagx = 1;
        int flagc = -1;
        char op = '+';
        for(int i = 0  ;i < equation.length() ; i ++) {
            char ch = equation.charAt(i);
            if(ch == '=') {
                if(op == '+') con += flagc * c;
                if(op == '-') con += flagc * c * -1;
                c = 0;
                flagx = -1;
                flagc = 1;
                op = '+';
                continue;
            }
            if(ch >= '0' && ch <= '9') {
                c = c * 10 + (ch - '0');
                continue;
            }
            if(ch == 'x') {
                int p = 1;
                if(op == '+') p = 1;
                if(op == '-') p = -1;
                if(i>0 && equation.charAt(i-1) == '0') p = 0; //"0x=0" 这种边界情况特殊处理一下
                if(c == 0) xs += flagx * 1 * p;
                else xs += flagx * c * p;
                c = 0;
                continue;
            }
            if(ch == '+' || ch == '-') {
                if(op == '+') con += flagc * c;
                if(op == '-') con += flagc * c * -1;
                op = ch;
                c = 0;
            }
        }
        if(c != 0) {
            int p = 1;
            if(op == '+') p = 1;
            if(op == '-') p = -1;
            con += flagc * c * p;
            c = 0;
        }
        if(xs == 0 && con != 0) return "No solution";
        if(xs == 0 && con == 0) return "Infinite solutions";
        return "x=" +  (con / xs);
    }

}
