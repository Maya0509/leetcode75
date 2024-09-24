package slide_window;

public class Solution1493LongestSubarrayOf1AfterDeletingOneEle {
    public int longestSubarray(int[] nums) {
        int len = nums.length;
        int left = 0, right = left;
        int maxLen = 0, cnt = 0;
        while (right < len){
            cnt += nums[right];
            right ++;
            if (right-left > cnt+1){
                cnt -= nums[left];
                left ++;
            }
            if (right-left == cnt+1){
                maxLen = Math.max(maxLen,cnt);
            }else if (right-left == cnt){
                maxLen = Math.max(maxLen,cnt-1);
            }
        }
        return maxLen;
    }

    public int longestSubarray2(int[] nums) {
        int res = 0;
        int left = 0, lastzero = -1;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                if (lastzero != -1) {
                    left = lastzero+1;
                }
                lastzero = right;

            }
            res = Math.max(res,right-left);
        }
        return res;
    }
}
