package graph.bfs;

public class Solution994RottingOranges {

    //wrong, should not use dfs
    int time;
    public int orangesRotting(int[][] grid) {
        time = 0;
        int xlen = grid.length;
        int ylen = grid[0].length;
        while(true){
            int change = 0;
            time ++;
            for (int i = 0; i < xlen; i++){
                for (int j = 0; j < ylen; j++) {
                    if (grid[i][j] == 2){
                        dfs(grid,i,j);
                    }else if (grid[i][j] == -1){
                        grid[i][j] = 2;
                        change ++;
                    }
                }
            }
            if (change == 0){
                time --;
                for (int[] ints : grid) {
                    for (int j = 0; j < ylen; j++) {
                        if (ints[j] == 1) {
                            return -1;
                        }
                    }
                }
                break;
            }
        }
        return time;
    }
    private void dfs(int[][] grid,int xpos, int ypos){
        if (xpos < 0 || xpos > grid.length-1 || ypos < 0 || ypos > grid[0].length-1) return;
        if (grid[xpos][ypos] == 1) {
            grid[xpos][ypos] = -1;
            return;
        }
        dfs(grid,xpos-1,ypos);
        dfs(grid,xpos+1,ypos);
        dfs(grid,xpos,ypos-1);
        dfs(grid,xpos,ypos+1);
    }

    // use bfs
}
