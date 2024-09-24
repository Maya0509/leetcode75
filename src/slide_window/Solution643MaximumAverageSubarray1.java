package slide_window;

public class Solution643MaximumAverageSubarray1 {
    public double findMaxAverage(int[] nums, int k) {
        int left = 0, right = k-1;
        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }
        left++;
        right++;
        while (right<nums.length){
            int temp = sum;
            temp -= nums[left-1];
            temp += nums[right];
            sum = Math.max(sum,temp);
            right++;
            left++;
        }
        return (double) sum/k;
    }
}
