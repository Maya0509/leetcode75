package slide_window;

public class Solution1004MaxConsecutiveOnes3 {
    public int longestOnes2(int[] nums, int k) {
        int left = 0, right = left;
        int len = nums.length;
        int maxOneNum = 0, oneNum = 0;
        int jump = 0;
        int flipNum = k;
        while (left<len && right<len){
            if (nums[left]==0){
                left++;
                right = left;
            }else{
                if (right==left){
                    oneNum++;
                    right++;
                }else {
                    if (nums[right]==0){
                        if (flipNum==k) jump = right;
                        if (flipNum>0){
                            flipNum--;
                            right++;
                            oneNum++;
                        }else {
                            maxOneNum = Math.max(maxOneNum,oneNum);
                            left = jump;
                            right = left;
                            oneNum = 0;
                            flipNum = k;
                        }
                    }else{
                        oneNum++;
                        right++;
                    }
                }
            }
        }
        if (right==len && flipNum==0){
            maxOneNum = Math.max(maxOneNum,oneNum);
        }
        if (right==len && flipNum>0){
            while (left>=1 && flipNum>0){
                left--;
                if (nums[left]==0){
                    oneNum++;
                    flipNum--;
                }else {
                    oneNum++;
                }
            }
            maxOneNum = Math.max(maxOneNum,oneNum);
        }
        return maxOneNum;
    }
    public int longestOnes(int[] nums, int k){
        int len = nums.length;
        int left = 0, right = left;
        int maxOnes = 0, sum = 0;
        while (right < len){
            sum+=nums[right];
            right++;
            while(right-left > sum+k){
                sum-=nums[left];
                left++;
            }
            maxOnes = Math.max(maxOnes,right-left);
        }
        return maxOnes;
    }
    public static int longestOnes3(int[] nums, int k) {
        int l = 0;
        int r;
        int sum = 0;//记录当前维护的数组中有几个1
        for (r=0;r<nums.length;r++){
            sum+=nums[r];
            //当维护的长度大于符合题目要求的长度时,左指针+1
            if (r-l+1 > sum+k){
                sum-=nums[l];
                l++;
            }
        }
        return r-l;//最后的r-l即为答案所求长度
    }

    public static void main(String[] args){
        int[] nums = new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        Solution1004MaxConsecutiveOnes3 s = new Solution1004MaxConsecutiveOnes3();
        System.out.println(s.longestOnes(nums,3));
    }
}
