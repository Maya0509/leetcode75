package backtracking;

import java.util.*;

public class Solution40CombinationSum2 {
    class Solution {
        // timeout 大小剪纸写法混乱
//        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//            List<List<Integer>> result = new ArrayList<>();
//            Arrays.sort(candidates);
//            Set<Integer> candidatesSet = new HashSet<>();
//            for (int candidate:
//                 candidates) {
//                candidatesSet.add(candidate);
//            }
//            backtracking(result,new ArrayList<>(),0,target,new ArrayList<>(candidatesSet));
//            return result;
//        }
//        private void backtracking(List<List<Integer>> result, List<Integer> temp, int start, int target,List<Integer> candidates){
//            for (int i = start; i < candidates.size(); i++) {
//                if(candidates.get(i)>target){
//                    return;
//                }
//                temp.add(candidates.get(i));
//                if (candidates.get(i)<target){
//
//                    backtracking(result,temp,i+1,target-candidates.get(i),candidates);
//                }else if (candidates.get(i)==target){
//                    result.add(temp);
//                    temp.remove(temp.size()-1);
//                    break;
//                }
//            }
//        }

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            int len = candidates.length;
            if (len==0) return result;
            Deque<Integer> path = new ArrayDeque<>();
            Arrays.sort(candidates);
            dfs(len,candidates,target,0,path,result);
            return result;
        }
        private void dfs(int len,
                         int[] candidates,
                         int target, int begin, Deque<Integer> path, List<List<Integer>> result){
            if (target==0){
                result.add(new ArrayList<>(path));
                return;
            }
            for (int i = begin; i < len; i++) {
                // 大剪枝
                if (candidates[i]>target){
                    break;
                }
                //特别注意i>begin，针对同一层的进行小剪枝
                if (i>begin && candidates[i]==candidates[i-1]){
                    continue;
                }
                // dfs前后对称操作
                path.addLast(candidates[i]);
                dfs(len,candidates,target-candidates[i],i+1,path,result);
                path.removeLast();
            }
        }
    }
}
