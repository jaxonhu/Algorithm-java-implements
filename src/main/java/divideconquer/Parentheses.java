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

    public static void main(String[] args) {
        String input = "11";
        List<Integer> res = diffWaysToCompute(input);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
