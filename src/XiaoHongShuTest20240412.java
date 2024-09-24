import java.util.*;

class Main{
    // 网上人用dp做
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        long result = 0;
        char[] chars = new char[n-1];
        for(int i =0;i<n-1;i++){
            chars[i] = (char)in.nextByte();
        }
        Main main =new Main();
        main.dfs(0,n,result,m,chars,n);
        System.out.println(result%(10e9+7));
    }
    private void dfs(int start,int k,long result,int m,char[] chars,int n){
        if(k==0){
            result++;
            return;
        }
        if(start==m && chars[n-1-k]=='>') return;
        if(start==1 && chars[n-1-k]=='<') return;
        if(chars[n-1-k]=='='){
            dfs(start,k-1,result,m,chars,n);
        }
        if(chars[n-1-k]=='>'){
            for(int i=start+1; i<=m;i++){
                dfs(i,k-1,result,m,chars,n);
            }
        }
    }
}