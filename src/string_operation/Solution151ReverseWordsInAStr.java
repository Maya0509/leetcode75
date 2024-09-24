package string_operation;

public class Solution151ReverseWordsInAStr {
    public String reverseWords(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        int start = s.length()-1;
        int end = s.length();
        while(start>=0){
            if (s.charAt(start)==' ' && start!=s.length()-1 && s.charAt(start+1)!=' '){
                stringBuilder.append(s, start+1, end);
                stringBuilder.append(' ');
            }
            if (s.charAt(start)!=' ' && start!=s.length()-1 && s.charAt(start+1)==' '){
                end = start+1;
            }
            if (start==0 && s.charAt(start)!=' '){
                stringBuilder.append(s, start, end);
            }
            start--;
        }
        if (stringBuilder.charAt(stringBuilder.length()-1)==' ') stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    public String reverseWords1(String s) {
        return ""; // 使用C++可以达到O(n)时间复杂度，O(1)空间复杂度
    }
}
