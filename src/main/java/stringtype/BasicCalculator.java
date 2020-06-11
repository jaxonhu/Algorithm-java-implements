package stringtype;

import org.junit.Test;

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
    // 1+2*5
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


    /**
     * leetcode 224 基本计算器
     *
     * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
     *
     * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
     *
     * 示例 1:
     *
     * 输入: "1 + 1"
     * 输出: 2
     * 示例 2:
     *
     * 输入: " 2-1 + 2 "
     * 输出: 3
     * 示例 3:
     *
     * 输入: "(1+(4+5+2)-3)+(6+8)"
     * 输出: 23
     * 说明：
     *
     * 你可以假设所给定的表达式都是有效的。
     * 请不要使用内置的库函数 eval。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/basic-calculator
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    public int calculate3(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        int flag = 1;
        int cur = 0;
        int res = 0;
        char op = '+';
        for(int i = 0 ; i < s.length() ; i ++) {
            char ch = s.charAt(i);
            if(ch == ' ') continue;
            if(ch >= '0' && ch <= '9' ) {
                cur = cur * 10 + (ch - '0');
                continue;
            }
            if(ch == '+' || ch == '-') {
                cur = cur * flag;
                if(ch == '+') flag = 1;
                if(ch == '-') flag = -1;
                op = ch;
                res += cur;
                cur = 0;
                continue;
            }
            if(ch == '(') {
                opStack.push(op);
                numStack.push(res);
                res = 0;
                cur = 0;
                flag = 1;
                continue;
            }
            if(ch == ')') {
                char opt = opStack.pop();
                int numt = numStack.pop();
                if(opt == '+') res = numt + (res + cur * flag);
                if(opt == '-') res = numt - (res + cur * flag);
                cur = 0;
            }
        }
        return res+ (cur * flag);
    }


    /**
     * dfs做法
     */

    public int calculate4(String s) {
        return dfs(s);
    }

    int dfs(String s) {
        int res  = 0;
        int op = 1;
        int cur = 0;
        for(int i = 0 ; i < s.length() ; i ++) {
            char ch = s.charAt(i);
            if(ch == ' ') continue;
            if(ch >= '0' && ch <= '9') {
                cur = cur*10 + (ch - '0');
                continue;
            }
            if(ch == '+' || ch == '-') {
                res += op * cur;
                if(ch == '+') op = 1;
                if(ch == '-') op = -1;
                cur = 0;
                continue;
            }
            if(ch == '(') {
                int j = i;
                int cnt = 1;
                while(s.charAt(j) != ')' || cnt != 0) {  //这里处理多个左括号的情况
                    j++;
                    if(s.charAt(j) == '(') cnt ++;
                    if(s.charAt(j) == ')') cnt --;
                };
                String subStr = s.substring(i+1,j);
                res += op * dfs(subStr);
                i = j;
                continue;
            }
        }
        return res + (op * cur);
    }


    /**
     *
     * 面试题 16.26. 计算器
     *
     * 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
     *
     * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
     *
     * 示例 1:
     *
     * 输入: "3+2*2"
     * 输出: 7
     * 示例 2:
     *
     * 输入: " 3/2 "
     * 输出: 1
     * 示例 3:
     *
     * 输入: " 3+5 / 2 "
     * 输出: 5
     * 说明：
     *
     * 你可以假设所给定的表达式都是有效的。
     * 请不要使用内置的库函数 eval。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/calculator-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */


    public int calculate5(String s) {
        Stack<Integer> nstack = new Stack<>();
        int op = 1;
        int res = 0;
        int cur = 0;
        for(int i = 0 ; i < s.length() ; i ++) {
            char ch = s.charAt(i);
            if(ch == ' ') continue;
            if(ch >= '0' && ch <= '9') {
                cur = cur * 10 + (ch - '0');
                continue;
            }
            if(ch == '+' || ch == '-') {
                cur = cur * op;
                nstack.push(cur);
                if(ch == '+') op = 1;
                if(ch == '-') op = -1;
                cur = 0;
                continue;
            }
            if(ch == '*' || ch == '/') {
                int j = i+1;
                int n = 0;
                while(j < s.length() && ((s.charAt(j) >= '0' && s.charAt(j) <= '9') || s.charAt(j) == ' ')) {
                    if(s.charAt(j) == ' ') {j ++;continue;}
                    n = n *10 + (s.charAt(j) - '0');
                    j++;
                }
                if(ch == '*') cur = cur * n * op;
                if(ch == '/') cur = cur / n * op;
                i = j-1;
                op = 1;
                continue;
            }
        }
        nstack.push(cur * op);
        while(nstack.size() > 0) {
            res += nstack.pop();
        }
        return res;
    }


    /**
     *此题主要通过如下两个步骤来完成：
     * 1.将输入的中缀表达式转为后缀表达式（即逆波兰表达式）
     * 2.计算逆波兰表达式
     * 用到数据结构主要是栈（stack).
     *
     * 中缀表达式转后缀表达式（逆波兰表达式）
     *
     * （1）变量及函数说明
     *
     * getPriority(char ch): 返回运算符的优先级
     * vector<char> ans: 存储逆波兰表达式
     * stack<char> op: 暂存未输出的运算符
     * （2）步骤
     * i. 如果s[i]为数字，则一直向后寻找到第一个非数字字符，并将之前的数字字符放入ans
     * ii. 如果s[i]为运算符，则分为以下三种情况：
     * A. 如果op为空，则直接将运算符s[i]压入栈op；
     * B. 如果栈op非空，且运算符s[i]的优先级高于栈顶元素的优先级，则直接将元素符s[i]压入栈op；
     * C. 如果栈op非空，且运算符s[i]的优先级低于或等于栈顶元素的优先级，则弹出栈顶元素，并将其放入ans，直到运算符s[i]的优先级高于栈顶元素的优先级，此时将运算符s[i]压入栈op.
     *
     * 计算后缀表达式（逆波兰表达式）
     *
     * （1）变量说明：
     *
     * stack<int> value: 存储操作数以及中间运算结果
     * （2）步骤：
     * 1. 如果遇到数字，则将其压入栈value;
     * 2. 如果遇到运算符，则从栈value从弹出两个操作数，计算结果，并将结果压入栈value
     * 3. 遍历完逆波兰表达式后，返回value栈顶元素
     *
     *  如果题目中有( )的话，对括号特殊处理，（ 直接入栈，) 出栈直到遇到 (
     *
     *  ps: 逆波兰式的方法在leetcode的最后一个测试用例会超时
     *
     */

    public int calculate6(String s) {
        String rpn = toRPN(s);
        Stack<Integer> stack = new Stack<>();
        for(int i = 0 ; i < rpn.length() ; i ++) {
            char ch = rpn.charAt(i);
            if(ch == ' ') continue;
            if(ch >= '0' && ch <= '9') {
                int j = i+1;
                while(j < rpn.length() && rpn.charAt(j) >= '0' && rpn.charAt(j) <= '9') j++;
                stack.push(Integer.valueOf(rpn.substring(i,j)));
                i = j;
                continue;
            }
            if(ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                int n1 = stack.pop();
                int n2 = stack.pop();
                if(ch == '+') stack.push(n2 + n1);
                if(ch == '-') stack.push(n2 - n1);
                if(ch == '*') stack.push(n2 * n1);
                if(ch == '/') stack.push(n2 / n1);
            }
        }
        int r = stack.pop();
        System.out.println(rpn);
        return  r;
    }

    String toRPN(String s) {
        String res = "";
        Stack<Character> opstack = new Stack<>();
        for(int i = 0 ; i < s.length() ; i ++) {
            char ch = s.charAt(i);
            if(ch == ' ') continue;
            if(ch >= '0' && ch <= '9') {
                int j = i + 1 ;
                while( j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') j++;
                res += s.substring(i,j) + " ";
                i = j-1;
                continue;
            }
            if(ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                if(opstack.isEmpty()) opstack.push(ch);
                else {
                    if(getPriority(opstack.peek()) < getPriority(ch)) {
                        opstack.push(ch);
                    } else {
                        while(!opstack.isEmpty() && getPriority(opstack.peek()) >= getPriority(ch)) {
                            res += String.valueOf(opstack.pop()) + " ";
                        }
                        opstack.push(ch);
                    }
                }
                continue;
            }
            if(ch == '(') {
                opstack.push(ch);
                continue;
            }
            if(ch == ')') {
                char ch2 = ' ';
                while(!opstack.isEmpty() && opstack.peek()!='(') {
                    ch2 = opstack.pop();
                    res += String.valueOf(ch2) + " ";
                }
                if(!opstack.isEmpty()) opstack.pop();
                continue;
            }
        }
        while(!opstack.isEmpty()) {
            res += " " + String.valueOf(opstack.pop());
        }
        return res;
    }

    int getPriority(char ch) {
        switch(ch) {
            case '+': return 1;
            case '-': return 1;
            case '*': return 2;
            case '/': return 2;
            default: return 0;
        }
    }

    @Test
    public void testCalculate6() {
        System.out.println(calculate6("1+(5*6+3)/2"));
    }

}
