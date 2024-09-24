package dynamic_programming.dimension2;

import java.util.Arrays;

public class Solution62UniquePaths {
    class Solution {
        // 空间优化，精妙
        public int uniquePaths(int m, int n) {
            int[] currRow = new int[n];
            Arrays.fill(currRow,1);
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    currRow[j] += currRow[j-1];
                }
            }
            return currRow[n-1];
        }

        public int uniquePaths2(int m, int n){
            int[] pre = new int[n], cur = new int[n];
            Arrays.fill(pre,1);
            Arrays.fill(cur,1);
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    cur[j] = cur[j-1]+pre[j];
                }
                pre = cur.clone();
            }
            return cur[n-1];
        }
    }
}
