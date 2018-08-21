package stringtype;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/29
 * @Time: 下午9:53
 * @Project: Algorithm-Java-implements
 */
public class AddBinary {

    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int n = a.length();
        int m = b.length();
        int i = n-1, j = m-1;
        int plus = 0;
        while( i >= 0 && j >= 0) {
            int r = ((a.charAt(i) - '0') + (b.charAt(j) - '0') + plus) % 2;
            plus = ((a.charAt(i) - '0') + (b.charAt(j) - '0') + plus) / 2;
            res.append(r);
            i -= 1;
            j -= 1;
        }
        while(i >= 0) {
            int r = (a.charAt(i) - '0' + plus) % 2;
            plus = (a.charAt(i) - '0' + plus) / 2;
            res.append(r);
            i--;
        }
        while(j >= 0) {
            int r = (b.charAt(j) - '0' + plus) % 2;
            plus = (b.charAt(j) - '0' + plus) / 2;
            res.append(r);
            j--;
        }
        if(plus > 0)
            res.append(plus);
        return res.reverse().toString();
    }
}
