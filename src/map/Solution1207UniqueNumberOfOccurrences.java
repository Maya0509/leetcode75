package map;

import java.util.*;

public class Solution1207UniqueNumberOfOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer,Integer> occurrenceMap = new HashMap<>(arr.length);
        for (int num: arr) {
            occurrenceMap.put(num,occurrenceMap.getOrDefault(num,0)+1);
        }
        return new HashSet<>(occurrenceMap.values()).size()==occurrenceMap.keySet().size();
    }
}
