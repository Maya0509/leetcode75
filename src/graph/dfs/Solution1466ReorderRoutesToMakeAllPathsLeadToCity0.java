package graph.dfs;

import java.util.*;

public class Solution1466ReorderRoutesToMakeAllPathsLeadToCity0 {
//    int edit = 0;
//    List<int[]> paths;
//    List<Integer> ends;
//    Set<Integer> reached = new HashSet<>();
//    public int minReorder(int n, int[][] connections) {
//        paths = new ArrayList<>(n-1);
//        paths.addAll(Arrays.asList(connections));
//        ends = new ArrayList<>(n);
//        for (int i=0; i<n;i++){
//            ends.add(-1);
//        }
//        for (int[] path:connections){
//            if (ends.get(path[0]) == -1){
//                ends.add(path[0],path[1]);
//            }else {
//                ends.add(path[1],path[0]);
//            }
//        }
//        dfs(0);
//        return edit;
//    }
//    private void dfs(int cityId){
//        reached.add(cityId);
//        int nextEnd = ends.get(cityId);
//        if (nextEnd == -1) return;
//        if (!reached.contains(nextEnd)){
//            if (check(paths,new int[]{cityId,nextEnd})) edit++;
//            dfs(nextEnd);
//        }
//
//    }
//    private boolean check(List<int[]> lists,int[] list){
//        for (int[] l:lists){
//            if (Arrays.equals(l,list)) return true;
//        }
//        return false;
//    }
    int ans = 0;
    public int minReorder(int n, int[][] connections) {
        List<int[]>[] nodes = new List[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int[] path:connections){
            nodes[path[0]].add(new int[]{path[1],1});
            nodes[path[1]].add(new int[]{path[0],0});
        }
        dfs(nodes,0,new boolean[n]);
        return ans;
    }
    public void dfs(List<int[]>[] nodes, int i, boolean[] visited){
        visited[i] = true;
        List<int[]> nextNodes = nodes[i];
        for (int[] next:nextNodes){
            if (!visited[next[0]]){
                ans += next[1];
                dfs(nodes,next[0],visited);
            }
        }
    }
    public static void main(String[] args){
        Solution1466ReorderRoutesToMakeAllPathsLeadToCity0 s = new Solution1466ReorderRoutesToMakeAllPathsLeadToCity0();
        System.out.println(s.minReorder(5,new int[][]{{1,0},{1,2},{3,2},{3,4}}));
    }
}
