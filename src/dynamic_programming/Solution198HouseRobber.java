package dynamic_programming;

public class Solution198HouseRobber {
    class Solution {
        public int rob(int[] nums) {
            int len = nums.length;
            if(len==0) return 0;
            int[] result = new int[len];
            result[0] = nums[0];
            if(len==1) return result[0];
            result[1] = Math.max(nums[0],nums[1]);
            for(int i = 2; i<len; i++){
                result[i] = Math.max(result[i-2]+nums[i],result[i-1]);
            }
            return result[len-1];
        }

        // make the space complexity pressed to O(1)
    }
}
