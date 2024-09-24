package dynamic_programming;

public class Solution70ClimbingStairs {
    class Solution {
        public int climbStairs(int n) {
            int pprev = 1, prev = 1;
            if (n==1) return prev;
            int curr = 0;
            for (int i = 2; i <= n; i++) {
                curr = pprev+prev;
                pprev = prev;
                prev = curr;
            }
            return curr;
        }
    }
}
