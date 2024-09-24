package bit_calculation;

public class Solution338CountingBits {
    class Solution {
        // O(nlogn); for int i, calculate if exists last 1 thru i &= (i-1)
        public int[] countBits(int n) {
            int[] cnts = new int[n+1];
            for (int i = 1; i <= n; i++) {
                cnts[i] = countOne(i);
            }
            return cnts;
        }
        private int countOne(int n){
            int oneNum = 0;
            while(n>0){
                n &= (n-1);
                oneNum++;
            }
            return oneNum;
        }

        // O(n)
        public int[] countBitsDp1(int n){
            int[] OneCnts = new int[n+1];
            int recentPowerOfTwo = 0;
            for (int i = 1; i <= n; i++) {
                if ((i & (i-1)) == 0) recentPowerOfTwo = i;
                OneCnts[i] = OneCnts[i-recentPowerOfTwo]+1;
            }
            return OneCnts;
        }
    }
}
