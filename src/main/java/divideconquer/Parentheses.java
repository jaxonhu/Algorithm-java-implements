package divideconquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/12/11
 * @ Time: 2:13 PM
 * @ Project: Algorithm-Java-implements
 */
public class Parentheses {

    public static List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<>();
        char[] c_str = input.toCharArray();
        int number = 0;
        for(int i = 0 ; i < c_str.length ; i ++) {
            if(c_str[i] == '+' || c_str[i] == '-' || c_str[i] == '*' ) {
                List<Integer> left = diffWaysToCompute(input.substring(0,i));
                List<Integer> right = diffWaysToCompute(input.substring(i+1, c_str.length));
                for(int m = 0 ; m < left.size() ; m ++) {
                    for(int n = 0 ; n < right.size() ; n ++) {
                        switch(c_str[i]) {
                            case '+':
                                result.add(left.get(m) + right.get(n));
                                break;
                            case '-':
                                result.add(left.get(m)- right.get(n));
                                break;
                            case '*':
                                result.add(left.get(m) * right.get(n));
                                break;
                        }
                    }
                }
            }
            number += number * 10 + (c_str[i] - '0');
        }
        if(result.size() == 0) {
            result.add(number);
        }
        return result;
    }

    /**
     *  dp算法: 核心就是填递推表格
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute2(String input) {
        List<Integer> result=new ArrayList<>();
        if(input==null||input.length()==0)  return result;
        List<String> ops=new ArrayList<>();
        for(int i=0; i<input.length(); i++){
            int j=i;
            while(j<input.length()&&Character.isDigit(input.charAt(j)))
                j++;
            ops.add(input.substring(i, j));
            if(j!=input.length())   ops.add(input.substring(j, j+1));
            i=j;
        }
        int N=(ops.size()+1)/2; //num of integers
        ArrayList<Integer>[][] dp=(ArrayList<Integer>[][]) new ArrayList[N][N];
        for(int d=0; d<N; d++){
            if(d==0){
                for(int i=0; i<N; i++){
                    dp[i][i]=new ArrayList<>();
                    dp[i][i].add(Integer.valueOf(ops.get(i*2)));
                }
                continue;
            }
            for(int i=0; i<N-d; i++){
                dp[i][i+d]=new ArrayList<>();
                for(int j=i; j<i+d; j++){
                    ArrayList<Integer> left=dp[i][j], right=dp[j+1][i+d];
                    String operator=ops.get(j*2+1);
                    for(int leftNum:left)
                        for(int rightNum:right){
                            if(operator.equals("+"))
                                dp[i][i+d].add(leftNum+rightNum);
                            else if(operator.equals("-"))
                                dp[i][i+d].add(leftNum-rightNum);
                            else
                                dp[i][i+d].add(leftNum*rightNum);
                        }
                }
            }
        }
        return dp[0][N-1];
    }

    public static void main(String[] args) {
        String input = "11";
        List<Integer> res = diffWaysToCompute(input);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
