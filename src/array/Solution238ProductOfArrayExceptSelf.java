package array;

public class Solution238ProductOfArrayExceptSelf {
    // O(n), no division
    public int[] productExceptSelf(int[] nums) {
        int[] productList = new int[nums.length];
        productList[0] = 1;
        int pre = 1, suf = 1;
        for (int i = 1; i < nums.length; i++) {
            pre *= nums[i-1];
            productList[i] = pre;
        }

        for (int i = nums.length-2; i >= 0; i--){
            suf *= nums[i+1];
            productList[i] *= suf;
        }
        return productList;
    }
}
