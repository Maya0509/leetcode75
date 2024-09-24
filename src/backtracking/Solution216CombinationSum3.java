package backtracking;

import java.util.ArrayList;
import java.util.List;

public class Solution216CombinationSum3 {
    static class Solution {
        public static List<List<Integer>> combinationSum3_backtrack1(int k, int n) {
            List<List<Integer>> result = new ArrayList<>();
            backtrack1(k,n,1, result,new ArrayList<>());
            return result;
        }
        private static void backtrack1(int k, int n, int start,
                               List<List<Integer>> result, List<Integer> cur){
            if (k==0 && n==0){
                result.add(new ArrayList<>(cur));
            }else if (k>0 && n>0){
                for (int i = start; i <= 9; i++) {
                    if (n >= i){
                        cur.add(i);
                        backtrack1(k - 1, n - i, i+1, result, cur);
                        cur.remove(cur.get(cur.size()-1));
                    }else break;
                }
            }
        }

        //dfs?
        public List<List<Integer>> combinationSum3(int k, int n){
            List<List<Integer>> result = new ArrayList<>();
            return result;
        }

        public static void main(String[] args){
            System.out.println(combinationSum3_backtrack1(3,10));
        }
    }
}
