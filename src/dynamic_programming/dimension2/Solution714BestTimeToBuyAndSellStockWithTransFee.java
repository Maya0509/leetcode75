package dynamic_programming.dimension2;

public class Solution714BestTimeToBuyAndSellStockWithTransFee {
    class Solution {
//        public int maxProfit(int[] prices, int fee) {
//            int dayNum = prices.length;
//            int[] maxProfit = new int[dayNum];
//            for (int i = 1; i < dayNum; i++) {
//                int x = prices[i];
//                for (int j = i+1; j >= 0; j--) {
//                    int y = prices[j];
//                    if (j==i+1) maxProfit[i] = y>x+fee ? y-x-fee : 0;
//                    else{
//                        maxProfit[i] = Math.max(maxProfit[j]+maxProfit[i+1],y-x-fee);
//                    }
//                }
//            }
//            return maxProfit[0];
//        }

//        public int maxProfit(int[] prices, int fee){
//            int dayNum = prices.length;
//            int maxProfit = 0, buy = prices[0];
//            for (int i = 1; i < dayNum; i++) {
//                maxProfit = Math.max(maxProfit,maxProfit+prices[i]-buy-fee);
//                if (prices[i]-buy-fee>0) buy = prices[i];
//                else buy = Math.min(buy,prices[i]);
//            }
//            return maxProfit;
//        }

        public int maxProfit(int[] prices, int fee){
            int dayNum = prices.length;
            int sell = 0, buy = -prices[0];
            for (int i = 1; i < dayNum; i++) {
                sell = Math.max(sell,buy+prices[i]-fee);
                buy = Math.max(sell-prices[i],buy);
            }
            return sell;
        }

        // 解法二，贪心
        public int maxProfit2(int[] prices, int fee){
            int profit = 0, buy = prices[0]+fee;
            int dayNum = prices.length;
            for (int i = 1; i < dayNum; i++) {
                if (buy>prices[i]+fee) buy = prices[i]+fee;
                else if (prices[i] > buy) {
                    profit += prices[i]-buy;
                    buy = prices[i];
                }
            }
            return profit;
        }
    }
}
