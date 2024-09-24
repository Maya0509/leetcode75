import java.util.*;

public class MeituanTest1 {


    // 注意类名必须为 Main, 不要有任何 package xxx 信息
    static class Main {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            int q = in.nextInt();
            int sum = 0;
            int vaca = 0;
            in.nextLine();
            String numLine = in.nextLine();
            String[] nums = numLine.split(" ");
            for(String num:nums){
                int x = Integer.parseInt(num);
                sum+=x;
                if (x==0) vaca++;
            }
            // 注意 hasNext 和 hasNextLine 的区别
            for(int i=0;i<q;i++) { // 注意 while 处理多个 case
                int l = in.nextInt();
                int r = in.nextInt();
                System.out.print(sum+vaca*l);
                System.out.print(" ");
                System.out.println(sum+vaca*r);
            }
        }
    }
    //小美拿到了一个由正整数组成的数组，但其中有一些元素是未知的（用 0 来表示）。
    //现在小美想知道，如果那些未知的元素在区间[l,r]范围内随机取值的话，数组所有元素之和的最小值和最大值分别是多少？
    //共有q次询问。
    //!!!!注意取值范围，应该改成longlong
    static class Main2 {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            int q = in.nextInt();
            in.nextLine();
            int sum = 0;
            int vaca = 0;
            String numLine = in.nextLine();
            String[] nums = numLine.split(" ");
            for (String num : nums) {
                int x = Integer.parseInt(num);
                sum += x;
                if (x == 0) vaca++;
            }
            // 注意 hasNext 和 hasNextLine 的区别
            for (int i = 0; i < q; i++) { // 注意 while 处理多个 case
                int l = in.nextInt();
                int r = in.nextInt();
                System.out.print(sum + vaca * l);
                System.out.print(" ");
                System.out.println(sum + vaca * r);
            }
        }
    }
//小美拿到了一个n*n的矩阵，其中每个元素是 0 或者 1。
//小美认为一个矩形区域是完美的，当且仅当该区域内 0 的数量恰好等于 1 的数量。
//现在，小美希望你回答有多少个i*i的完美矩形区域。你需要回答1\leq i \leq n的所有答案。
    static class Main3 {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            // 注意 hasNext 和 hasNextLine 的区别
            int n = in.nextInt();
            in.nextLine();
            String[] strings = new String[n];
            for (int i = 0; i < n; i++) {
                strings[i] = in.nextLine();
            }
            System.out.println(0);
            for (int i = 2; i <= n; i+=2) {
                int find = 0;

                for (int j = 0; j <= n-i; j++) {

                    for (int k = 0; k <= n-i; k++) {
                        int sum = 0;
                        for (int l = 0; l < i; l++) {
                            for (int m = 0; m < i; m++) {
                                int x = j+l,y=k+l;
                                sum+=strings[x].charAt(y)-'0';
                            }
                        }
                        if (sum==i*i/2) find++;
                    }
                }
                System.out.println(find);
                if(i!=n || n%2==1) System.out.println(0);
            }
        }
    }

    //小美拿到了一个大小为n的数组，她希望删除一个区间后，使得剩余所有元素的乘积末尾至少有k个 0。小美想知道，一共有多少种不同的删除方案？
    static class Main4 {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            // 注意 hasNext 和 hasNextLine 的区别
            int n = in.nextInt(), k = in.nextInt();
            int[] nums = new int[n];
//            int num2 = 0, num5 = 0, num10 = 0;
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }


        }
    }

    //小美认为，在人际交往中，但是随着时间的流逝，朋友的关系也是会慢慢变淡的，最终朋友关系就淡忘了。
    //现在初始有一些朋友关系，存在一些事件会导致两个人淡忘了他们的朋友关系。小美想知道某一时刻中，某两人是否可以通过朋友介绍互相认识？
    //事件共有 2 种：
    //1 u v：代表编号 u 的人和编号 v 的人淡忘了他们的朋友关系。
    //2 u v：代表小美查询编号 u 的人和编号 v 的人是否能通过朋友介绍互相认识。
//    注：介绍可以有多层，比如 2 号把 1 号介绍给 3 号，然后 3 号再把 1 号介绍给 4 号，这样 1 号和 4 号就认识了。
    static class Main5{
//        5 3 5
//                1 2
//                2 3
//                4 5
//                1 1 5
//                2 1 3
//                2 1 4
//                1 1 2
//                2 1 3

        //按照网上的思路，先离线存储各个查询的内容，并存储除了要删掉的关系之外的关系
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            // 注意 hasNext 和 hasNextLine 的区别
            int peopleNum = in.nextInt(),iniAcqSetNum = in.nextInt(),queryNum = in.nextInt();
            UnionFind unionFind = new UnionFind(peopleNum+1);
            List<int[]> toQuery = new ArrayList<>();
//            List<int[]> toDel = new ArrayList<>();
            Map<int[],Integer> relations = new HashMap<>(iniAcqSetNum);
            for (int i = 0; i < iniAcqSetNum; i++) {
                relations.put(new int[]{in.nextInt(),in.nextInt()},0);
            }
            for (int i = 0; i < queryNum; i++) {
                int kind = in.nextInt();
                if (kind==1){
                    int del1 = in.nextInt(),del2 = in.nextInt();
                    toQuery.add(new int[]{del1,del2,1});
                    int[] del = new int[]{del1,del2};
//                    toDel.add(del);
                    for (int[] relation:relations.keySet()){
                        if(Arrays.equals(relation, del)) relations.replace(relation,1);
                    }
                }else {
                    toQuery.add(new int[]{in.nextInt(),in.nextInt(),2});
                }
            }
            for (int[] relation:relations.keySet()){
                if (relations.get(relation)==0) unionFind.union(relation[0],relation[1]);
            }
            List<String> ans = new ArrayList<>();
            for (int i = queryNum-1; i>=0; i--){
                int[] query = toQuery.get(i);
                if (query[2]==1){
                    unionFind.union(query[0],query[1]);
                }else {
                    ans.add(unionFind.isConnected(query[0],query[1]) ? "Yes":"No");
                }
            }
            for (int i = ans.size()-1; i >= 0; i--) {
                System.out.println(ans.get(i));
            }
        }
        private static class UnionFind{
            private final int[] parent;
            public UnionFind(int n) {
                this.parent = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }
            public void deunion(int x,int y){
                int rootX=find(x);
                int rootY=find(y);
                if (rootX!=rootY) return;
                if (rootX==y) parent[x] = x;
                if (rootY==x) parent[y] = y;
            }
            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX == rootY) {
                    return;
                }

                parent[rootX] = rootY;
            }

            public int find(int x) {
                if (x != parent[x]) {
                    parent[x] = find(parent[x]);
                }
                return parent[x];
            }

            public boolean isConnected(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX == rootY) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
}
