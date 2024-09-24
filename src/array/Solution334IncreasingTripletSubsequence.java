package array;

public class Solution334IncreasingTripletSubsequence {
    public static boolean increasingTriplet(int[] nums) {
        int max = nums[0], min = nums[0];
        int step = 1;
        for (int num:nums) {
            if (num>max){
                step++;
                max = num;
                if (step==3) return true;
            }else{
                if (step==1){
                    max = num;
                    min = num;
                }
                if (step==2){
                    min = Math.min(min,num);
                    if (num>min){
                        max = num;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        System.out.println((int)Math.pow(2,32)==Integer.MAX_VALUE);
        System.out.println(increasingTriplet(new int[]{5,1,5,5,2,5,4}));
    }

    public boolean increasingTriplet2(int[] nums){
        // 方法一，生成前缀最小值数组与后缀最大值数组
        int[] preMinList = new int[nums.length];
        int[] sufMaxList = new int[nums.length];
        int preMin = Integer.MAX_VALUE;
        int sufMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            preMinList[i] = preMin;
            preMin = Math.min(preMin,nums[i]);
        }
        for (int i = nums.length-1; i >= 0; i--) {
            sufMaxList[i] = sufMax;
            sufMax = Math.max(sufMax,nums[i]);
        }

        for (int i = 1; i < nums.length-1; i++) {
            if (preMinList[i] < nums[i] && nums[i] < sufMaxList[i]) return true;
        }
        return false;
    }
}
