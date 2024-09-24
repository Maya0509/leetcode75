package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution47Permutations2 {
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            int len = nums.length;
            List<List<Integer>> result = new ArrayList<>();
            dfs(nums,new boolean[len],0,result,new ArrayList<>(),len,0);
            return result;
        }
        private void dfs(int[] nums,boolean[] used,int k,List<List<Integer>> result,List<Integer> temp,
                         int len, int start){
            if (k==len){
                result.add(new ArrayList<>(temp));
                return;
            }
            for (int i = start; i < start+len; i++) {
                int idx = i%len;
                if (used[idx]) continue;
                if (idx>0 && nums[idx]==nums[idx-1] && !used[idx-1]) continue;
                temp.add(nums[idx]);
                used[idx] = true;
                dfs(nums,used,k+1,result,temp,len,start+1);
                temp.remove(temp.size()-1);
                used[idx]=false;
            }
        }
    }
}
