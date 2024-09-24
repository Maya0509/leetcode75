package backtracking;

import java.util.*;

public class Solution39CombinationSum {
    class Solution {
        // every candidate is unique
        // one element can be used more than one times
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            // 最好加上length判空
            Arrays.sort(candidates);
            List<List<Integer>> result = new ArrayList<>();
            backtracking(candidates,target,new ArrayDeque<>(), result,0,candidates.length);
            return result;
        }
        private void backtracking(int[] candidates, int target, Deque<Integer> path, List<List<Integer>> result,
                                  int start, int len){
            if (target==0){
                result.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i < len; i++) {
                if (candidates[i]>target) break;
                path.addLast(candidates[i]);
                // 注意参数传递传i还是传start
                backtracking(candidates,target-candidates[i],path,result,i,len);
                path.removeLast();
            }
        }
    }
}
