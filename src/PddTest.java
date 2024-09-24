import java.util.*;

public class PddTest {
    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int round = in.nextInt();

        while (round>=1) { // 注意 while 处理多个 case
            int n = in.nextInt(), m = in.nextInt(), k = in.nextInt(), d = in.nextInt();
            round--;
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
            }

            Arrays.sort(nums);
            int last = n-1;
            for (int i = n-1; i>=n-d; i--){
                if (i-m>=0 && nums[i]>2*nums[i-m]) last--;
                else break;
            }
            long sum = 0;
            for (int i = 0; i <= last; i++) {
                if (i>=last-m+1) sum+= (long) nums[i] *(-k);
                else sum+=nums[i];
            }

            System.out.println(sum);
        }
    }
    public static void main2(String[] args){
        Scanner in = new Scanner(System.in);
        int lenA = in.nextInt(), lenB = in.nextInt();
        in.nextLine();
        String strA = in.nextLine(), strB = in.nextLine();
        Set<String> resultSet = new HashSet<>();
        char[] charsA = strA.toCharArray(), charsB = strB.toCharArray();
        for (int i = 0; i <= lenA-lenB; i++) {
            int num1 = 0;
            StringBuilder s = new StringBuilder(lenB);
            for (int j = i; j < i+lenB; j++) {
                s.append((charsA[j]==charsB[j-i])?'0':'1');
                if (charsA[j]!=charsB[j-i]) num1++;
            }
            if (num1%2==0){
                resultSet.add(s.toString());
            }
        }
        System.out.println(resultSet.size());
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt(), pathNum = in.nextInt();
        List<List<Integer>> asStart = new ArrayList<>(n), asEnd = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            asStart.add(new ArrayList<>());
            asEnd.add(new ArrayList<>());
        }
        for (int i = 0; i < pathNum; i++) {
            int start = in.nextInt()-1, end = in.nextInt()-1;
            asStart.get(start).add(end);
            asEnd.get(end).add(start);
        }
        int result = 0;
        for (int i = 0; i<n; i++){
            Set<Integer> all = new HashSet<>(n);

            all.addAll(asEnd.get(i));

            Deque<Integer> dests = new ArrayDeque<>(asStart.get(i));
            while(!dests.isEmpty()){
                int dest = dests.removeFirst();
                all.add(dest);
                dests.addAll(asStart.get(dest));
            }

            Deque<Integer> froms = new ArrayDeque<>(asEnd.get(i));
            while(!froms.isEmpty()){
                int from = froms.removeFirst();
                all.add(from);
                froms.addAll(asEnd.get(from));
            }

            if (all.size()==n-1) result++;
        }
        System.out.println(result);
    }
}
