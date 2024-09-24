package programmer_code_interview_guide;

public class MinimumPathSum {
    class Solution {
        public int minPathSum(int[][] grid) {
            int lenx = grid.length;
            int leny = grid[0].length;
            int[] temp = new int[lenx];
            for (int j = 0; j < leny; j++) {
                for (int i = 0; i < lenx; i++) {
                    if (i==0) temp[i] += grid[i][j];
                    else if (j==0) temp[i] = temp[i-1]+grid[i][j];
                    else temp[i] = Math.min(temp[i-1],temp[i])+grid[i][j];
                }
            }
            return temp[lenx-1];
        }
    }
}
