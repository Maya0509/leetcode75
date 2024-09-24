package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Solution77Combinations {
    static class Solution {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> result = new ArrayList<>();
            backtrack(n,k,1,result,new ArrayList<>());
            return result;
        }
        private void backtrack(int n, int k, int start, List<List<Integer>> result, List<Integer> temp){
            // attention!!! judge when to add
            if (temp.size()==k){
                result.add(new ArrayList<>(temp));
            }else if (start<n+1 && temp.size()<k){
                for (int i = start; i < n+1; i++) {
                    temp.add(i);
                    // attention!!!!!! make sure the start is related to the local variable i
                    backtrack(n,k,i+1,result,temp);
                    temp.remove(temp.size()-1);
                }
            }
        }

        public static void main(String[] args){
            Solution s = new Solution();
            System.out.println(s.combine(4,2).toString());
        }
    }
}
