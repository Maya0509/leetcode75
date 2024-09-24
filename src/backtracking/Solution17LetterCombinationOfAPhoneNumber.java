package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Solution17LetterCombinationOfAPhoneNumber {
    class Solution {
        String letters = "abcdefghijklmnopqrstuvwxyz";
        public List<String> letterCombinations(String digits) {
            List<String> result = new ArrayList<>();
            backtrack(digits,0,result,new StringBuilder());
            return result;
        }
        private void backtrack(String digits,int start, List<String> result, StringBuilder temp){
            // directly return when digits.length()==0
            if (digits.length()==0) return;
            if (start==digits.length() && temp.length() == digits.length()){
                result.add(temp.toString());
            }else if (start<digits.length() && temp.length()<digits.length()){
                // err:
                // java.security.AccessControlException: access denied ("java.util.PropertyPermission" "2" "read")
                // int digit = Integer.getInteger(digits.substring(start,start+1));
                // Integer.getInteger(String nm) : Determines the integer value of the system property with the specified name.
                // discriminate between Integer.getInteger(nm,val) and Integer.parseInt(s)
                int digit = Integer.parseInt(digits.substring(start,start+1));
                int left = (digit<=7) ? ((digit-2)*3) : ((digit==8)?19:22);
                int right = (digit<=6) ? ((digit-2)*3+2) : ((digit==7)? 18:(digit==8 ? 21:25) );
                // mind the bound!! i<=right not i<right(v.s. i<len)
                for (int i = left; i <= right; i++) {
                    temp.append(letters.charAt(i));
                    backtrack(digits,start+1,result,temp);
                    temp.deleteCharAt(temp.length()-1);
                }
            }
        }
    }
}
