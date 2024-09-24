package graph;

import java.util.*;

public class Solution741KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Deque<Integer> keys = new ArrayDeque<>(rooms.get(0));
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while (!keys.isEmpty()){
            int roomNum = keys.removeFirst();
            visited.add(roomNum);
            for (int key:rooms.get(roomNum)){
                if (!keys.contains(key) && !visited.contains(key)) keys.addLast(key);
            }
        }
        return visited.size() == rooms.size();
    }

    // dfs version
    int roomNum;
    boolean[] visited;
    public boolean canVisitAllRoomsDfs(List<List<Integer>> rooms) {
        visited = new boolean[rooms.size()];
        roomNum = 0;
        dfs(rooms,0);
        return roomNum == rooms.size();
    }
    private void dfs(List<List<Integer>> rooms,int roomId){
        if (!visited[roomId]){
            roomNum ++;
            visited[roomId] = true;
        }else return;
        for (int key:rooms.get(roomId)){
            dfs(rooms,key);
        }
    }
}
