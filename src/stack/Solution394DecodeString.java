package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Solution394DecodeString {
    // exp.1 3[a2[c]]ef
    // exp.2 2[abc]3[cd]ef
    // exp.3 ad3[a2[c]ef]
    // 非常错误的双栈写法
    public static String decodeStringError(String s) {
        StringBuilder result = new StringBuilder();
        int len = s.length();
        Deque<Integer> intStk = new ArrayDeque<>(len);
        Deque<String> strStk = new ArrayDeque<>(len);
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int start = i,end = start+1;
            if (c>='0' && c<='9'){
                while (end<len && s.charAt(end)>'0' && s.charAt(end)<'9'){
                    end++;
                }
                String intStr = s.substring(start,end);
                int repeatTime = Integer.parseInt(intStr);
                intStk.addFirst(repeatTime);
                i = end-1;
            }else if (c>='a' && c<='z'){
                while (end<len && s.charAt(end)>'a' && s.charAt(end)<'z'){
                    end++;
                }
                String alphabetStr = s.substring(start,end);
                strStk.addFirst(alphabetStr);
                i = end-1;
            }else if (c=='['){
                strStk.addFirst(c+"");
            }else if (c==']'){
                String repeatedStr = strStk.removeFirst();
                int number = 1;
                if (!strStk.isEmpty() && strStk.peekFirst().equals("[")){
                    number = intStk.removeFirst();
                    strStk.removeFirst();
                }
                StringBuilder stringBuilder = new StringBuilder();
                while (number-- > 0){
                    stringBuilder.append(repeatedStr);
                }
                if (strStk.isEmpty()){
                    result.append(stringBuilder);
                }else {
                    String lastStr = strStk.removeFirst();
                    lastStr+=stringBuilder.toString();
                    strStk.addFirst(lastStr);
                }
            }
        }
        if (!strStk.isEmpty()){
            result.append(strStk.removeFirst());
        }
        return result.toString();
    }

    public static String decodeString1(String s){
        int len = s.length();
        Deque<String> strDeque = new ArrayDeque<>(len);
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int start = i, end = start+1;
            if (Character.isDigit(c)){
                while(end<len && Character.isDigit(s.charAt(end))){
                    end++;
                }
                String numStr = s.substring(start,end);
                strDeque.addFirst(numStr);
                i = end-1;
            }else if (Character.isLetter(c)){
                while(end<len && Character.isLetter(s.charAt(end))){
                    end++;
                }
                String alphabetStr = s.substring(start,end);
                strDeque.addFirst(alphabetStr);
                i = end-1;
            }else if (c=='['){
                strDeque.addFirst(c+"");
            }else if (c==']'){
                StringBuilder alphaStrBuilder = new StringBuilder();
                while(!strDeque.isEmpty() && !strDeque.peekFirst().equals("[")){
                    alphaStrBuilder.insert(0,strDeque.removeFirst());
                }
                strDeque.removeFirst();
                int number = Integer.parseInt(strDeque.removeFirst());
                StringBuilder stringBuilder = new StringBuilder();
                while (number-- > 0){
                    stringBuilder.append(alphaStrBuilder);
                }
                strDeque.addFirst(stringBuilder.toString());
            }
        }
        StringBuilder result = new StringBuilder();
        while (!strDeque.isEmpty()){
            result.append(strDeque.removeLast());
        }
        return result.toString();
    }

    // method2: recursive
    public static String decodeString2(String s){
        return dfs(s,0)[0];
    }

    private static String[] dfs(String s,int i){
        StringBuilder result = new StringBuilder();
        int multi = 0;
        while (i < s.length()){
            char c = s.charAt(i);
            if (c >= '0' && c <= '9'){
                multi = multi*10 + c-'0';
            }else if (c == '['){
                String[] inside = dfs(s,i+1);
                while (multi > 0){
                    result.append(inside[0]);
                    multi--;
                }
                i = Integer.parseInt(inside[1]);
            }else if (c == ']'){
                return new String[]{result.toString(),String.valueOf(i)};
            }else {
                result.append(c);
            }
            i++;
        }
        return new String[]{result.toString()};
    }

    // method3: double stack, use linkedlist
    public static String decodeString(String s){
        // result用于存储中间及最后结果
        StringBuilder result = new StringBuilder();
        // multi_stack是存储字符串重复次数的栈
        LinkedList<Integer> multi_stack = new LinkedList<>();
        // result_stack是每次遇到'['暂存result结果的栈，因此遇到']'时，该栈一定非空
        LinkedList<String> result_stack = new LinkedList<>();
        int multi = 0;
        int i = 0;
        while (i < s.length()){
            char c = s.charAt(i);
            if (c >= '0' && c <= '9'){
                // 计算multi时，已有数字进一位，再加上当前位
                multi = multi*10 + c-'0';
            }else if (c == '['){
                // 当前multi读取已结束，存入multi栈
                multi_stack.addLast(multi);
                // 前面的字符串拼接工作告一段落，暂存进result栈，进行括号内的读取工作
                result_stack.addLast(result.toString());
                // 重置multi以及result
                multi = 0;
                result = new StringBuilder();
            }else if (c == ']'){
                // 当前括号内的字符串已存入result中，将result对应的multi从栈中弹出
                int num = multi_stack.removeLast();
                // 该']'对应的'['前面的字符串即为result_stack的栈顶字符串
                StringBuilder aheadStr = new StringBuilder(result_stack.removeLast());
                // 将前缀字符串与当前括号内的字符串(result)拼接起来
                while (num > 0){
                    aheadStr.append(result);
                    num--;
                }
                // 更新result
                result = aheadStr;
            }else {
                // 遇到letter字符则直接接在当前result中
                result.append(c);
            }
            // 更新索引i
            i++;
        }
        // 返回最终的result
        return result.toString();
    }

    public static void main(String[] args){
        System.out.println(decodeString("3[a2[c]]ef"));;
    }
}
