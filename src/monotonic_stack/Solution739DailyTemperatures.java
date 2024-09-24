package monotonic_stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution739DailyTemperatures {
    class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int dayNum = temperatures.length;
            int[] result = new int[dayNum];
            Deque<Integer> mono_stack = new ArrayDeque<>(dayNum);
            for (int i = 0; i < dayNum; i++) {
                int temp = temperatures[i];
                if (mono_stack.isEmpty() || temperatures[mono_stack.peek()]>=temp){
                    mono_stack.push(i);
                }else {
                    while (!mono_stack.isEmpty() && temperatures[mono_stack.peek()]<temp){
                        int idx = mono_stack.pop();
                        result[idx] = i-idx;
                    }
                    mono_stack.push(i);
                }
            }
            return result;
        }
    }
}
