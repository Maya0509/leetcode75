package map;

import java.util.*;

public class Solution1657DetermineIf2StrsAreClose {
    public static boolean closeStrings(String word1, String word2) {
        if (word1.length()!=word2.length()) return false;
        Map<Character,Integer> alphabetFreqMap1 = new HashMap<>(word1.length());
        Map<Character,Integer> alphabetFreqMap2 = new HashMap<>(word2.length());
        for(char c:word1.toCharArray()){
            alphabetFreqMap1.put(c, alphabetFreqMap1.getOrDefault(c,0)+1);
        }
        for(char c:word2.toCharArray()){
            alphabetFreqMap2.put(c, alphabetFreqMap2.getOrDefault(c,0)+1);
        }
        if (!alphabetFreqMap1.keySet().equals(alphabetFreqMap2.keySet())){
            return false;
        }else {
            int num = alphabetFreqMap1.size();
            // 先排序，再比较
            Integer[] freq1 = new Integer[num];
            alphabetFreqMap1.values().toArray(freq1);
            Arrays.sort(freq1);

            Integer[] freq2 = new Integer[num];
            alphabetFreqMap2.values().toArray(freq2);
            Arrays.sort(freq2);

            return Arrays.equals(freq1, freq2);
        }
    }
    public static void main(String[] args){
        System.out.println(closeStrings("cabbba","abbccc"));
    }
}
