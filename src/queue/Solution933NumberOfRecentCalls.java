package queue;

import java.util.ArrayDeque;
import java.util.Deque;

class RecentCounter {
    private final Deque<Integer> calls;
    public RecentCounter() {
        calls = new ArrayDeque<>();
    }

    public int ping(int t) {
        calls.addLast(t);
        while(!calls.isEmpty() && calls.peekFirst() < t-3000){
            calls.removeFirst();
        }
        return calls.size();
    }
}
