package heap_priorityqueue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solution347TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        int len = nums.length;
        TreeMap<Integer,Integer> freqMap = new TreeMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(freqMap::get));
        for (int key: freqMap.keySet()){
            priorityQueue.offer(key);
            if (priorityQueue.size()>k){
                priorityQueue.poll();
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[k-1-i] = priorityQueue.poll();
        }
        return result;
    }
}
