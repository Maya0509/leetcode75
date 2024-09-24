package array;

import java.util.*;

public class Solution15ThreeSum {
    class Solution {
//       timeout for some large data usecase
        // probably not suitable to use backtracking

//        public List<List<Integer>> threeSum(int[] nums) {
//            List<List<Integer>> result = new ArrayList<>();
//            int len = nums.length;
//            if (len==0) return result;
//            Arrays.sort(nums);
//            dfs(nums,len,0,new ArrayDeque<>(),result,0,3);
//            return result;
//        }
//        private void dfs(int[] nums, int len, int start, Deque<Integer> path, List<List<Integer>> result,int target,
//                         int vacancy){
//            //
//            if (!path.isEmpty() && target == 0 && vacancy==0){
//                result.add(new ArrayList<>(path));
//                return;
//            }
//            for (int i = start; i < len; i++) {
//                if ((nums[i]>=0 && target<0) || vacancy==0){
//                    break;
//                }
//                if (i>start && nums[i-1]==nums[i]) continue;
//                path.addLast(nums[i]);
//                dfs(nums,len,i+1,path,result,target-nums[i],vacancy-1);
//                path.removeLast();
//            }
//        }
//    }

        // 运行超市，可能得 优化最右侧指针的遍历fangxiang
//        public List<List<Integer>> threeSum(int[] nums) {
//
//            List<List<Integer>> result = new ArrayList<>();
//            int len = nums.length;
//            if (len==0) return result;
//            Arrays.sort(nums);
//            Deque<Integer> path;
//
//            for (int i = 0; i < len; i++) {
//                if (i>0 && nums[i]==nums[i-1]) continue;
//                path = new ArrayDeque<>(3);
//                path.addLast(nums[i]);
//                int sum = nums[i];
//                for (int j = i+1; j < len; j++) {
//                    if(j>i+1 && nums[j]==nums[j-1]) continue;
//                    if (sum+nums[j]*2>0) continue;
//                    path.addLast(nums[j]);
//                    sum+=nums[j];
//                    for (int k = j+1; k < len; k++) {
//
//                        if (sum+nums[k]>0) {
//                            int numj = path.removeLast();
//                            sum-=numj;
//                            break;
//                        }
//                        path.addLast(nums[k]);
//                        sum+=nums[k];
//
//                        if (path.size()==3 && sum!=0){
//                            int toDel = path.removeLast();
//                            sum-=toDel;
//                            if(k==len-1){
//                                int numj = path.removeLast();
//                                sum-=numj;
//                            }
//                            continue;
//                        }
//                        if (path.size()==3 && sum==0){
//                            result.add(new ArrayList<>(path));
//                            path.removeLast();
//                            path.removeLast();
//                            sum = path.getFirst();
//                            break;
//                        }
//
//                    }
//                }
//            }
//            return result;
//        }

        public List<List<Integer>> threeSum(int[] nums){
            List<List<Integer>> result = new ArrayList<>();
            int len = nums.length;
            if (len==0) return result;
            Arrays.sort(nums);
            Deque<Integer> path = new ArrayDeque<>(3);

            for (int i = 0; i < len; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                path.clear();
                path.addLast(nums[i]);

                for (int j = i+1; j < len; j++) {
                    if(j>i+1 && nums[j]==nums[j-1]) continue;
                    int k = len-1;
                    while(k>j && nums[k]+nums[j]+nums[i]>0){
                        k--;
                    }
                    if (k==j) break;
                    if (nums[k]+nums[j]+nums[i]==0){
                        path.addLast(nums[j]);
                        path.addLast(nums[k]);
                        result.add(new ArrayList<>(path));
                        path.removeLast();
                        path.removeLast();
                    }
                }
            }
            return result;
        }
    }
}
