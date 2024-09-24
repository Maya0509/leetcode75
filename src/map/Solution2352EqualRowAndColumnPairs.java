package map;

import java.util.*;

public class Solution2352EqualRowAndColumnPairs {
    public static int equalPairs(int[][] grid) {
        int len = grid.length;
        int cnt = 0;
        Map<int[],Integer> rowCntMap = new HashMap<>(len);
        for (int[] ints : grid) {
            int[] list = new int[len];
            System.arraycopy(ints, 0, list, 0, len);
            rowCntMap.put(list,rowCntMap.getOrDefault(list,0)+1);
        }
        for (int c = 0; c < len; c++){
            int[] list = new int[len];
            for (int r = 0; r < len; r++){
                list[r] = grid[r][c];
            }

            for (int[] key:rowCntMap.keySet()){
                //list.equals(key);
                if (Arrays.equals(list, key)) cnt += rowCntMap.get(key);
            }
//            cnt += rowCntMap.getOrDefault(list,0);
        }
        return cnt;
    }
    public static int equalPairs2(int[][] grid){
        int len = grid.length;
        Map<List<Integer>,Integer> rowCntMap = new HashMap<>(len);
        for (int[] ints : grid) {
            List<Integer> list = new ArrayList<>(len);
            for (int c = 0; c < len; c++) {
                list.add(ints[c]);
            }
            rowCntMap.put(list, rowCntMap.getOrDefault(list, 0) + 1);
        }
        int cnt = 0;
        for (int c = 0; c < len; c++){
            List<Integer> list = new ArrayList<>(len);
            for (int[] ints : grid) {
                list.add(ints[c]);
            }
            cnt += rowCntMap.getOrDefault(list,0);
        }
        return cnt;
    }
    public static void main(String[] args){
        int[][] matrix = new int[3][];
        matrix[0] = new int[]{3,2,1};
        matrix[1] = new int[]{1,7,6};
        matrix[2] = new int[]{2,7,7};
        System.out.println(equalPairs2(matrix));;
    }
}
