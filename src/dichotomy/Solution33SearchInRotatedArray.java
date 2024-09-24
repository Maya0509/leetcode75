package dichotomy;

public class Solution33SearchInRotatedArray {
    class Solution {
        public int search(int[] nums, int target) {
            int left = 0,right = nums.length-1;
            boolean isLargerHalf = (target>nums[right] || nums[right] >= nums[left]);
            while(left<=right){
                int mid = (left+right)>>1;
                if (nums[mid]==target) return mid;
                if(nums[mid]<target){
                    if (nums[mid]>=nums[0]) left = mid+1;
                    else {
                        if (isLargerHalf) right = mid-1;
                        else left = mid+1;
                    }
                }
                else{
                    if (nums[mid]>=nums[0]){
                        if (isLargerHalf) right = mid-1;
                        else left = mid+1;
                    }else{
                        right = mid-1;
                    }
                }
            }
            return -1;
        }
    }
}
