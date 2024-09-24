package graph.dfs;

import java.util.*;

public class Solution210CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
//        Map<Integer, List<Integer>> sonMap = new HashMap<>();
        Deque<Integer> toSelect = new LinkedList<>();
        int[] result = new int[numCourses];
        int cnt = 0;
        for (int i = 0; i < numCourses; i++) {
            toSelect.add(i);
        }
        Map<Integer, List<Integer>> fatherMap = new HashMap<>();
        for(int[] pair:prerequisites){
//            if(!sonMap.containsKey(pair[1])){
//                sonMap.put(pair[1],new ArrayList<>());
//            }
//            sonMap.get(pair[1]).add(pair[0]);
            if(!fatherMap.containsKey(pair[0])){
                fatherMap.put(pair[0],new ArrayList<>());
            }
            fatherMap.get(pair[0]).add(pair[1]);
            toSelect.remove(pair[0]);
        }

        while (!toSelect.isEmpty()){

            int fatherCourse = toSelect.removeFirst();
            result[cnt] = fatherCourse;
            for (int son:fatherMap.keySet()){
                if(fatherMap.get(son).contains(fatherCourse)){
                    fatherMap.get(son).remove(new Integer(fatherCourse));
                    if (fatherMap.get(son).size()==0) toSelect.addLast(son);
                }
            }
            cnt++;
        }
        if (cnt<numCourses) return new int[0];
        return result;
    }

    // 应该使用邻接表（sonMap）+入度数组，更加合理
}
