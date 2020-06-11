package stringtype;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/5/27
 * @ Time: 3:28 下午
 * @ Project: Algorithm-Java-implements
 */
public class ValidNumber {

    /**
     * 验证给定的字符串是否可以解释为十进制数字。
     *
     * 例如:
     *
     * "0" => true
     * " 0.1 " => true
     * "abc" => false
     * "1 a" => false
     * "2e10" => true
     * " -90e3   " => true
     * " 1e" => false
     * "e3" => false
     * " 6e-1" => true
     * " 99e2.5 " => false
     * "53.5e93" => true
     * " --6 " => false
     * "-+3" => false
     * "95a54e53" => false
     *
     * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
     *
     * 数字 0-9
     * 指数 - "e"
     * 正/负号 - "+"/"-"
     * 小数点 - "."
     * 当然，在输入中，这些字符的上下文也很重要。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    /**
     *
     */
    public boolean isNumber(String s) {
        if(s==null||s.length()==0) return false;
        boolean numSeen=false;
        boolean dotSeen=false;
        boolean eSeen=false;
        char arr[]=s.trim().toCharArray();
        for(int i=0; i<arr.length; i++){
            if(arr[i]>='0'&&arr[i]<='9'){
                numSeen=true;
            }else if(arr[i]=='.'){
                if(dotSeen||eSeen){
                    return false;
                }
                dotSeen=true;
            }else if(arr[i]=='E'||arr[i]=='e'){
                if(eSeen||!numSeen){
                    return false;
                }
                eSeen=true;
                numSeen=false;
            }else if(arr[i]=='+'||arr[i]=='-'){
                if(i!=0&&arr[i-1]!='e'&&arr[i-1]!='E'){
                    return false;
                }
            }else{
                return false;
            }
        }
        return numSeen;
    }


    /**
     *  这种方式可以将string转换为double
     */
    int pointer;
    boolean isNumber2(String s) {
        if(s=="" )return false;
        scanSpace(s);
        boolean numeric = scanInteger(s);
        if(s.charAt(pointer)=='.'){
            ++pointer;
            numeric = scanUnsignedInteger(s)|| numeric;
            //用||因为整数、小数部分有一即可
        }
        if(s.charAt(pointer)=='e'|| s.charAt(pointer)=='E'){
            ++pointer;
            numeric=numeric&&scanInteger(s);
        }
        scanSpace(s);
        return numeric && s.charAt(pointer)=='\0';
    }

    void scanSpace(String s) {

    }

    boolean scanInteger(String s) {
        return true;
    }

    boolean scanUnsignedInteger(String s) {
        return true;
    }




}
