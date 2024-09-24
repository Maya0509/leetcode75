package heap_priorityqueue;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution2462TotalCostToHireKWorkers {
    class Solution {
        public long totalCost(int[] costs, int k, int candidates) {
            int len = costs.length;
            long answer = 0;
            if(candidates*2>=len){
                Arrays.sort(costs);
                for (int i = 0; i < k; i++) {
                    answer += costs[i];
                }
                return answer;
            }

            PriorityQueue<Integer> indexPriorityQueue = new PriorityQueue<>(
                    (i1,i2) -> {
                        if (costs[i1]!=costs[i2]) return costs[i1]-costs[i2];
                        else return i1-i2;
                    }
            );
            for (int i = 0; i < candidates; i++) {
                indexPriorityQueue.offer(i);
                indexPriorityQueue.offer(len-1-i);
            }

            for (int l=candidates-1,r=len-candidates; k>0; k--){
                int selectIdx = indexPriorityQueue.poll();
                answer+=costs[selectIdx];
                if(l+1<r){
                    indexPriorityQueue.offer(selectIdx<=l ? ++l:--r);
                }
            }
            return answer;
        }
    }
}
