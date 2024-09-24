package lcci;

import java.util.*;

public class IsUniqueLCCI {
    class Solution {
        public boolean isUnique(String astr) {
            char[] chars = astr.toCharArray();
            Set<Character> charSet = new HashSet<>();
            for (char c:chars) {
                charSet.add(c);
            }
            return chars.length==charSet.size();
        }
    }
}
