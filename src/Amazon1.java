import java.util.*;

public class Amazon1 {



    static class Result {

        /*
         * Complete the 'groupSort' function below.
         *
         * The function is expected to return a 2D_INTEGER_ARRAY.
         * The function accepts INTEGER_ARRAY arr as parameter.
         */

        public static List<List<Integer>> groupSort(List<Integer> arr) {
            // Write your code here
            Map<Integer,Integer> freqMap = new HashMap<>();
            for (int i:arr){
                freqMap.put(i, freqMap.getOrDefault(i, 0)+1);
            }
            List<List<Integer>> result = new ArrayList<>(freqMap.size());
            for (int key:freqMap.keySet()){
                List<Integer> list = new ArrayList<>();
                list.add(key);
                list.add(freqMap.get(key));
                result.add(list);
            }
            result.sort((o1, o2) -> {
//                if (o1.get(1) > o2.get(1)) {
//                    return -1;
//                } else if (o1.get(1).equals(o2.get(1))) {
//                    return o1.get(0) > o2.get(0) ? 0 : -1;
//                } else return 0;
                return o1.get(1).equals(o2.get(1)) ? o1.get(0).compareTo(o2.get(0)):
                        o2.get(1).compareTo(o1.get(1));
            });
            return result;
        }

        //练习2：正则，匹配由'a'/'b'组成的字符串，字符串第一个字符与最后一个字符相同 '([ab])([ab]*\\1)?'
        public static void main(String[] args){
            String s = "ab";
            String regex = "^([ab])([ab]*\\1)?$";
            System.out.println(s.matches(regex));
        }

        public static long getMinDistance(List<Integer> center, List<Integer> destination) {
            // Write your code here
            center.sort(Integer::compareTo);
            destination.sort(Integer::compareTo);
            int result= 0;
            for (int i=0; i<center.size();i++){
                result+=Math.abs(center.get(i)-destination.get(i));
            }return result;
        }


        public static List<String> implementAPI(List<String> logs) {
            // Write your code here
            Map<String,String> register = new HashMap<>();
            Set<String> login = new HashSet<>();
            int queryNum = logs.size();
            List<String> result = new ArrayList<>(queryNum);
            for (String log : logs) {
                String[] words = log.split(" ");
                if (words.length == 2) {
                    String username = words[1];
                    if (login.contains(username)) {
                        login.remove(username);
                        result.add("Logged Out Successfully");
                    } else {
                        result.add("Logout Unsuccessful");
                    }
                } else {
                    String username = words[1];
                    String password = words[2];
                    if (words[0].equals("register")) {
                        if (register.containsKey(username)) {
                            result.add("Username already exists");
                        } else {
                            register.put(username, password);
                            result.add("Registered Successfully");
                        }
                    } else {
                        if (!register.containsKey(username) ||
                                !register.get(username).equals(password)) {
                            result.add("Login Unsuccessful");
                        } else {
                            if (login.contains(username)){
                                result.add("Login Unsuccessful");
                            }else {
                                login.add(words[1]);
                                result.add("Logged In Successfully");
                            }
                        }
                    }
                }
            }
            return result;
        }

    }

}
