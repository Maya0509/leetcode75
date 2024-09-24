package backtracking;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution93RestoreIpAddresses {
    class Solution {
        public List<String> restoreIpAddresses(String s) {
            char[] chars = s.toCharArray();
            List<String> result = new ArrayList<>();
            int len = chars.length;
            if(len<4||len>12) return result;
            dfs(result,len,chars,new ArrayDeque<>(),0,0);
            return result;
        }
        private void dfs(List<String> result, int len, char[] chars, Deque<String> path,
                         int start, int segNum){
            if(start == len){
                if(segNum==4){
                    result.add(String.join(".",path));
                }
                return;
            }

            for (int i = 0; i < Math.min(3,len-start); i++) {
                StringBuilder seg = new StringBuilder();
                for (int j = start; j <= start+i ; j++) {
                    seg.append(chars[j]);
                }
                // 注意segNum的取值
                if (!checkSeg(seg,len,segNum+1,start+i)){
                    continue;
                }
                path.addLast(seg.toString());
                segNum++;

                dfs(result,len,chars,path,start+i+1,segNum);
                path.removeLast();
                segNum--;
            }
        }
        private boolean checkSeg(StringBuilder seg,int len, int segNum, int right){
            boolean isLegal = true;
            int restCharNum = len-right-1;
            if (seg.charAt(0)=='0'&&seg.length()>1) isLegal=false;
            else if (restCharNum<4-segNum || restCharNum>3*(4-segNum)) isLegal=false;
            else{
                int num = Integer.parseInt(seg.toString());
                if (num>255) isLegal = false;
            }
            return isLegal;
        }
    }
}
