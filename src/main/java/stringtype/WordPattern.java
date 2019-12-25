package stringtype;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/12/26
 * @ Time: 12:03 AM
 * @ Project: Algorithm-Java-implements
 */
public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (Integer i=0; i<words.length; ++i)
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }


}
