import java.math.BigDecimal;
import java.util.Scanner;

public class XiaoHongShuPractice {
    public double probabilityOfRemovingHeadAndTail(int num){
        double p1 = (double)1/num;
        double p2 = (double)1/(num-1);
        return BigDecimal.valueOf(p1).multiply(BigDecimal.valueOf(p2)).doubleValue();
    }
    public static void minOldAccountsUsed(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        int[][] dp = new int[k + 1][2];
        for (int i = 0; i <= k ; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = Integer.MAX_VALUE;
        }
        dp[0][0] = 0;
        dp[0][1] = 0;
        for (int i = 0; i < n; i++) {
            int act = nums[i] / 2;
            for (int j = k; j >= act ; j--) {
                if (dp[j - act][0] != Integer.MAX_VALUE){
                    dp[j][0] = Math.min(dp[j][0], dp[j - act][0] + 1);
                    dp[j][1] = Math.min(dp[j][1], dp[j - act][1] + 1);
                }
                if (j - nums[i] >= 0 && dp[j - nums[i]][0] != Integer.MAX_VALUE){
                    dp[j][1] = Math.min(dp[j][1], dp[j - nums[i]][0] + 1);
                }

            }
        }
        System.out.println(Math.min(dp[k][0], dp[k][1]));
    }
    public static void main(String[] args) {
        minOldAccountsUsed();
    }
}
