package dynamic_programming;

import java.util.Arrays;
import java.util.Comparator;

public class PileBoxLCCI {
    class Solution {
        // wrong answer
//        public int pileBox(int[][] box) {
//            if (box.length==0) return 0;
//            int num = box.length;
//            int[] result = new int[num+1];
//            Arrays.sort(box, (o1, o2) -> {
//                if (o1[0]!=o2[0]) return o2[0]-o1[0];
//                else {
//                    if (o1[1]!=o2[1]) return o2[1]-o1[1];
//                    else{
//                        if (o1[2]!=o2[2]) return o2[2]-o1[2];
//                        else return 0;
//                    }
//                }
//            });
//            result[1] = box[0][2];
//            int[][] minSize = new int[num+1][3];
//            minSize[0][0] = Integer.MAX_VALUE;
//            minSize[0][1] = Integer.MAX_VALUE;
//            minSize[0][2] = Integer.MAX_VALUE;
//            minSize[1] = box[0];
//            for (int i = 2; i < num+1; i++) {
//                int withCurHeight = box[i-1][2];
//                for (int j = i-1; j >= 0; j--) {
//                    if (minSize[j][0]>box[i-1][0]&&minSize[j][1]>box[i-1][1]&&minSize[j][2]>box[i-1][2]){
//                        withCurHeight+=result[j];
//                        break;
//                    }
//                }
//                if (withCurHeight>result[i-1]){
//                    result[i] = withCurHeight;
//                    minSize[i] = box[i-1];
//                }else{
//                    result[i] = result[i-1];
//                    minSize[i] = minSize[i-1];
//                }
//            }
//            return result[num];
//        }
        public int pileBox(int[][] box){
            // 进一步排序可以减少运行时间
            Arrays.sort(box, Comparator.comparingInt(a -> a[0]));
            int[] dp = new int[box.length];
            int result = 0;
            for (int i = 0; i < box.length; i++) {
                for (int j = i-1; j >= 0; j--) {
                    if (box[i][0]>box[j][0] && box[i][1]>box[j][1] && box[i][2]>box[j][2]){
                        dp[i] = Math.max(dp[i],dp[j]+box[i][2]);
                    }
                }
                result = Math.max(result,dp[i]);
            }
            return result;
        }
    }
}
