package dynamic_programming;

public class Solution53MaximumSubarray {
    class Solution {
        public int maxSubArray(int[] nums) {
            int result = Integer.MIN_VALUE, tempSum = 0;
            for(int i = 0;i<nums.length; i++){
                tempSum += nums[i];
                result = Math.max(result,tempSum);
                if (tempSum<0){
                    tempSum = 0;
                }
            }
            return result;
        }

        public int maxSubArrayDp(int[] nums){
            int pre = 0, result = 0;
            for (int i = 0; i < nums.length; i++) {
                pre = Math.max(pre+nums[i],nums[i]);
                result = Math.max(pre,result);
            }
            return result;
        }

        // 还可以使用分治法，还没学分治法
    }
}
