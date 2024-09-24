package monotonic_stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution901OnlineStockSpan {
    class StockSpanner {

        Deque<int[]> stock_stack;
        public StockSpanner() {
            stock_stack = new ArrayDeque<>();
        }

        public int next(int price) {
            int span = 1;
            if(stock_stack.isEmpty() || stock_stack.peek()[0] > price) stock_stack.push(new int[]{price,span});
            else {
                while (!stock_stack.isEmpty() && stock_stack.peek()[0]<=price){
                    span += stock_stack.pop()[1];
                }
                stock_stack.push(new int[]{price,span});
            }
            return span;
        }
    }

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
}
