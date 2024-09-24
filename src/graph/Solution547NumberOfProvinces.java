package graph;

public class Solution547NumberOfProvinces {
    boolean[] visited;
    public int findCircleNum(int[][] isConnected) {
        visited = new boolean[isConnected.length];
        int provinceNum = 0;
        for (int i = 0; i<visited.length; i++){
            if (!visited[i]){
                provinceNum ++;
                dfs(isConnected,i);
            }
        }
        return provinceNum;
    }
    private void dfs(int[][] isConnected, int index){
        visited[index] = true;
        for (int i = 0; i < isConnected[index].length; i++){
            if (!visited[i] && isConnected[index][i] == 1) {
                dfs(isConnected, i);
            }
        }
    }
}
