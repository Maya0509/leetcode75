package stack;

import java.util.Deque;
import java.util.LinkedList;

public class Solution32LongestValidParentheses {
    public int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        int longestLen = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < len; i++) {
            if (chars[i]=='('){
                stack.push(i);
            }else{
                int idx = stack.pop();
                if (stack.isEmpty()) stack.push(i);
                else longestLen = Math.max(longestLen,i-stack.peek());
            }
        }
        return longestLen;
    }

    // can also be saved using dp
    // space cost can be reduced to O(1)
}
