package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

public class NowcoderTest1StringSort1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        in.nextLine();
        String[] strs = in.nextLine().split(" ");
//        Arrays.sort(strs, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return 0;
//            }
//        });

//        Arrays.sort(strs,String.CASE_INSENSITIVE_ORDER);
        Stream<String> stringStream = Arrays.stream(strs).sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int idx = 0;
                while(idx<o1.length()&&idx<o2.length()){
                    if (o1.charAt(idx)!=o2.charAt(idx)) return o1.charAt(idx)<o2.charAt(idx) ? -1:1;
                    idx++;
                }
                if (o1.length()==o2.length()) return 0;
                else return (o1.length()<o2.length() ? -1:1);
            }
        });
        String[] strings = stringStream.toArray(String[]::new);
        System.out.print(strings[0]);
        for (int i = 1;i< strings.length;i++){
            System.out.print(" "+strings[i]);
        }
        System.out.println();
    }
}
