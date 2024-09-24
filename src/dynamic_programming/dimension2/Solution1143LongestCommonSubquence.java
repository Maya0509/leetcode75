package dynamic_programming.dimension2;

import java.util.*;

public class Solution1143LongestCommonSubquence {
    class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            int len1 = text1.length();
            // toCharArray() makes later visiting char at specific index quicker
            char[] str1 = text1.toCharArray();
            int len2 = text2.length();
            char[] str2 = text2.toCharArray();
            int[][] dp = new int[len1+1][len2+1];
//            Arrays.fill(dp,0);二维数组不可以用fill函数
            int maxLen = 0;
            for (int i = 1; i < len1+1; i++) {
                for (int j = 1; j < len2+1; j++) {
                    if (str1[i-1]==str2[j-1]) dp[i][j] = dp[i-1][j-1]+1;
                    else dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                    maxLen = Math.max(dp[i][j], maxLen);
                }
            }
            return maxLen;
        }
    }
}
