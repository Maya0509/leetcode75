package backtracking;

import java.util.*;

public class Solution46Permutations {
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            int len = nums.length;
            List<List<Integer>> result = new ArrayList<>();
            if (len==0) return result;
//            Arrays.sort(nums);
            dfs(nums,len,0,new boolean[len],result,new ArrayDeque<>());
            return result;
        }
        private void dfs(int[] nums, int len, int start, boolean[] used, List<List<Integer>> result,
                         Deque<Integer> path){
            if (path.size()==len){
                result.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i < start+len; i++) {
                int idx = i%len;
                if (used[idx]) continue;
                path.addLast(nums[idx]);
                used[idx] = true;
                dfs(nums,len,start+1,used,result,path);
                used[idx] = false;
                path.removeLast();
            }
        }
    }
}
