package slide_window;

import java.util.Deque;
import java.util.LinkedList;

public class Solution239SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] result = new int[len-k+1];
        Deque<Integer> idxQueue = new LinkedList<>();
        for (int j = 0; j < k; j++) {
            if (!idxQueue.isEmpty()) {
                while (!idxQueue.isEmpty() && nums[idxQueue.peekLast()]<nums[j]){
                    idxQueue.removeLast();
                }
            }
            idxQueue.addLast(j);
        }
        result[0] = nums[idxQueue.getFirst()];
        for (int i = 1; i < len-k+1; i++) {
            int idx = k-1+i;
            while(!idxQueue.isEmpty() && idxQueue.peekFirst()<=i-1){
                idxQueue.removeFirst();
            }
            while(!idxQueue.isEmpty() && nums[idxQueue.peekLast()]<nums[idx]){
                idxQueue.removeLast();
            }
            idxQueue.offerLast(idx);
            if(!idxQueue.isEmpty()) result[i] = nums[idxQueue.peekFirst()];
            else result[i] = Integer.MIN_VALUE;
        }
        return result;
    }
}
