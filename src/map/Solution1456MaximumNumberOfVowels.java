package map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Solution1456MaximumNumberOfVowels {
    // 对于元音字母的识别与数量存储可以化为元音加1，辅音加0，利用大小为26的数组存储01
    public int maxVowels(String s, int k) {
//        int[] charMap = new int[26];
//        charMap['a'-'a'] = 1;
//        charMap['e'-'a'] = 1;
//        charMap['i'-'a'] = 1;
//        charMap['o'-'a'] = 1;
//        charMap['u'-'a'] = 1;
//        int temp = 0;
//        char[] chars = s.toCharArray();
//        for (int i = 0; i<k; i++){
//            temp += charMap[chars[i]-'a'];
//        }

        Set<Character> vowels = new HashSet<>(5);
        vowels.add('a');vowels.add('e');vowels.add('i');vowels.add('o');vowels.add('u');
        int num = 0, max = 0;
        for (int i = 0; i <= k-1; i++) {
            if (vowels.contains(s.charAt(i))){
                num++;
            }
        }
        max = num;
        for (int i = k; i < s.length(); i++) {
            if (max == k) return k;
            char del = s.charAt(i-k);
            char add = s.charAt(i);
            if (vowels.contains(del)) num--;
            if (vowels.contains(add)) num++;
            max = Math.max(max,num);
        }
        return max;
    }
}
