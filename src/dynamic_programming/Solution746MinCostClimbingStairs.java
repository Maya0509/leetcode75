package dynamic_programming;

public class Solution746MinCostClimbingStairs {
    class Solution {
        //写复杂了
        public int minCostClimbingStairs(int[] cost) {
            if (cost.length==0) return 0;
            int[] result = new int[cost.length];
            result[0] = 0;
            for (int i = 1; i < result.length; i++) {
                if (i==1) result[i]=Math.min(cost[0],cost[1]);
                else{
                    result[i] = Math.min(result[i-2]+cost[i-1],result[i-2]+cost[i-2]+cost[i]);
                    result[i] = Math.min(result[i], result[i-1]+cost[i]);
                }
            }
            return result[result.length-1];
        }
    }
}
