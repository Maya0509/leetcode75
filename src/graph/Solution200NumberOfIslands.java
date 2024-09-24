package graph;

public class Solution200NumberOfIslands {
    class Solution {
        int xLen;
        int yLen;
        public int numIslands(char[][] grid) {
            if (grid.length == 0) return 0;
            xLen = grid.length;
            yLen = grid[0].length;
            int islandNum = 0;
            for (int i = 0; i < xLen; i++) {
                for (int j = 0; j < yLen; j++) {
                    if (grid[i][j] == '1') {
                        islandNum ++;
                        searchAnIsland(grid,i,j);
                    }
                }
            }
            return islandNum;
        }
        private void searchAnIsland(char[][] grid, int x, int y){
            if(x < 0 || y < 0 || x == xLen || y == yLen || grid[x][y] != '1')
                return;
            grid[x][y] = '2';
            searchAnIsland(grid,x-1,y);
            searchAnIsland(grid,x,y-1);
            searchAnIsland(grid,x+1,y);
            searchAnIsland(grid,x,y+1);
        }
    }
}

class Solution463IslandPerimeter{
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        int xlen = grid.length;
        int ylen = grid[0].length;
        for (int i = 0; i < xlen; i++) {
            for (int j = 0; j < ylen; j++) {
                if (grid[i][j] == 0) continue;
                perimeter += ((i == 0)?1:0) + ((i == xlen-1)?1:0) + ((j == 0)?1:0) + ((j == ylen-1)?1:0)
                        + ((i>0 && grid[i-1][j] == 0)?1:0) + ((i<xlen-1 && grid[i+1][j] == 0)?1:0)
                        + ((j>0 && grid[i][j-1] == 0)?1:0) + ((j<ylen-1 && grid[i][j+1] == 0)?1:0);
            }
        }
        return perimeter;
    }
}

// s695 最大面积
class Solution695 {
    int size,xlen,ylen;
    public int maxAreaOfIsland(int[][] grid) {
        xlen = grid.length;
        ylen = grid[0].length;
        int maxSize = 0;
        for (int i = 0; i < xlen; i++) {
            for (int j = 0; j < ylen; j++) {
                if (grid[i][j] == 1){
                    size = 0;
                    dfs(grid,i,j);
                    maxSize = Math.max(maxSize, size);
                }
            }
        }
        return maxSize;
    }
    private void dfs(int[][] grid, int i, int j){
        if (i<0 || i>xlen-1 || j<0 || j>ylen-1 || grid[i][j]==0 || grid[i][j] == 2) return;
        if (grid[i][j] == 1){
            size ++;
            grid[i][j] = 2;
        }
        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j-1);
        dfs(grid,i,j+1);
    }
}