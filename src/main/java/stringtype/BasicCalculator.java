package stringtype;

import java.util.Stack;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/29
 * @Time: 下午10:16
 * @Project: Algorithm-Java-implements
 */
public class BasicCalculator {

    /**
     * 一个计算器，识别加减乘除表达式，没有括号
     * 思路：用栈，两个栈，一个运算数栈，一个操作符栈，然后运算符入栈时比较优先级
     * 思路2：循环遍历字符串，先计算乘除再计算加减
     */
    public int calculate(String s) {
        int num = 0;
        char  op = '+';
        int top = 0;
        int[] stack = new int[s.length()+1];

        for(int i = 0 ; i < s.length() ; ++ i) {
            if(s.charAt(i) == ' ') continue;
            while(i < s.length() && Character.isDigit(s.charAt(i))) {
               num = num * 10 + (s.charAt(i) - '0');
               i += 1;
            }

            switch(op){
                case '+':
                    stack[++top] = num;
                    num = 0;
                    break;
                case '-':
                    stack[++top] = -num;
                    num = 0;
                    break;
                case '*':
                    stack[top] = stack[top] * num;
                    num = 0;
                    break;
                case '/':
                    stack[top] = stack[top] / num;
                    num = 0;
                    break;
            }
            if(i < s.length()) op = s.charAt(i);
        }
        int sum = 0;
        for(int i  = 0 ; i <= s.length() ; ++ i) {
            sum += stack[i];
        }
        return sum;
    }

    public int calculate2(String s) {

        return 0;
    }
}
