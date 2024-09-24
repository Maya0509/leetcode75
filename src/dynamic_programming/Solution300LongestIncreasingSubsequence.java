package dynamic_programming;

public class Solution300LongestIncreasingSubsequence {
    class Solution {
        // t: O(n^2)
        public int lengthOfLIS1(int[] nums) {
            int[] dp = new int[nums.length];
            int result = 0;
            for (int i = 0; i < nums.length; i++) {

                for (int j = 0; j < i; j++) {
                    if (nums[j]<nums[i]) dp[i] = Math.max(dp[i], dp[j]);
                }
                dp[i] += 1;
                result = Math.max(dp[i], result);
            }
            return result;
        }

        // dp+dichotomy
        public int lengthOfLIS(int[] nums){
            int[] tail = new int[nums.length];
            tail[0] = nums[0];
            int maxLen = 1;
            for (int i = 1; i < nums.length; i++) {
                if (tail[maxLen-1]>nums[i]){
                    int l = 0, r = maxLen-1;
                    while(l<r){
                        int mid = (l+r)>>1;
                        if (tail[mid]<nums[i]) l = mid+1;
                        else r = mid;
                    }
                    tail[r] = nums[i];
                }
                // 注意单调递增的要求，此处if不能省略
                else if(tail[maxLen-1]<nums[i]){
                    maxLen++;
                    tail[maxLen-1] = nums[i];
                }
            }
            return maxLen;
        }
    }
}
