package array;

public class Solution724FindPivotIndex {
    // 前缀和，后缀和
    public int pivotIndex(int[] nums) {
        int len = nums.length;
        int[] preSum = new int[len];
        int[] sufSum = new int[len];
        preSum[0] = 0;
        sufSum[len-1] = 0;
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i-1]+nums[i-1];
        }
        for (int i = len-2; i >= 0; i--){
            sufSum[i] = sufSum[i+1]+nums[i+1];
        }
        for (int i = 0; i < len; i++) {
            if (preSum[i] == sufSum[i]) return i;
        }
        return -1;
    }
}
