package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/12/16
 * @ Time: 4:25 PM
 * @ Project: Algorithm-Java-implements
 */
public class ExpressionAddOperators {

    public static List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        helper(num, result, "", 0, target, 0, 0);
        return result;
    }

    static void helper(String num, List<String> paths, String path, int pos, int target, long eval, long multipled) {
        if(pos == num.length()) {
            if(eval == target) {
                paths.add(path);
            }
            return;
        }
        for(int i = pos ; i < num.length() ; i ++) {
            if(i != pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i+1));
            if(pos == 0){
                helper(num, paths, path + num.substring(pos, i+1), i+1, target, cur, cur);
            } else {
                helper(num, paths, path + '+' + num.substring(pos, i+1), i+1, target, eval + cur, cur);
                helper(num, paths, path + '-' + num.substring(pos, i+1), i+1, target, eval - cur, -cur);
                helper(num, paths, path + '*' + num.substring(pos, i+1), i+1, target, eval - multipled + multipled * cur, multipled * cur);
            }
        }
    }

    public static void main(String[] args) {
        String num = "3456237490";
        int target =  9191;
        List<String> res = addOperators(num, target);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
