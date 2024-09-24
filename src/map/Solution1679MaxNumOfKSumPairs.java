package map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution1679MaxNumOfKSumPairs {
    private static void quickSort(int[] nums, int low, int high){
        if (low>=high) return;
        int pivotPos = partition(nums,low,high);
        quickSort(nums,low,pivotPos-1);
        quickSort(nums,pivotPos+1,high);
    }
    private static int partition(int[] nums, int low, int high){
        int pivotPos = low;
        int pivot = nums[pivotPos];
        for (int i = low+1; i <= high; i++) {
            if (nums[i] < pivot){
                pivotPos++;
                if (pivotPos != i) swap(nums,pivotPos,i);
            }
        }
        nums[low] = nums[pivotPos];
        nums[pivotPos] = pivot;
        return pivotPos;
    }
    private static void swap(int[] nums, int idx1, int idx2){
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
    public int maxOperations2(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = nums.length-1;
        int maxOpNum = 0;
        while(left < right){
            int sum = nums[left]+nums[right];
            if (sum == k){
                maxOpNum++;
                left++;
                right--;
            }else if (sum < k){
                left++;
            }else{
                right--;
            }
        }
        return maxOpNum;
    }
    public int maxOperations3(int[] nums,int k){
        Map<Integer,Integer> cntMap = new HashMap<>();
        for (int num:nums) {
            if (cntMap.containsKey(num)){
                cntMap.replace(num,cntMap.get(num)+1);
            }else {
                cntMap.put(num,1);
            }
        }
        int maxOpNum = 0;
        for (int key:cntMap.keySet()){
            if (k == 2*key){
                maxOpNum+=cntMap.get(key)/2;
            }
            else if (cntMap.containsKey(k-key) && cntMap.get(key)!=0){
                maxOpNum+=Math.min(cntMap.get(key),cntMap.get(k-key));
                cntMap.replace(k-key,0);
            }
        }
        return maxOpNum;
    }
    public int maxOperations(int[] nums, int k){
        int cnt = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int num:nums){
            if (map.containsKey(k-num) && map.get(k-num)>0){
                cnt++;
                map.replace(k-num,map.get(k-num)-1);
            }else{
                map.put(num,map.getOrDefault(num,0)+1);
            }
        }
        return cnt;
    }
    public static void main(String[] args){
        int[] nums = new int[]{1,2,7,3};
        quickSort(nums,0,3);
        System.out.println(Arrays.toString(nums));
    }
}
