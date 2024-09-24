package array;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class SparseSimilarityLCCI {
    // 还是有超时的
    public List<String> computeSimilarities(int[][] docs) {
        int len = docs.length;
//        HashSet<Integer>[] docSetList = new HashSet[len];
        // 以下为安全的写法
        @SuppressWarnings("unchecked")
        HashSet<Integer>[] docSetList = (HashSet<Integer>[]) new HashSet<?>[len];

        List<String> result = new ArrayList<>();
        for(int i=0; i<len; i++){
            // attention!! initialization
            docSetList[i] = new HashSet<>();
            for(int word:docs[i]){
                docSetList[i].add(word);
            }
        }
        for (int i=0; i<len-1; i++){
            for (int j = i+1; j < len; j++) {
                int inter = calculateIntersection(docSetList[i],docSetList[j]);
                if(inter==0) continue;
                result.add(printResult(i,j,calculateSimilarity(inter,calculateUnion(docSetList[i],docSetList[j]))));
            }
        }
        return result;
    }
    private int calculateUnion(Set<Integer> set1, Set<Integer> set2){
        Set<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        return union.size();
    }
    private int calculateIntersection(Set<Integer> set1, Set<Integer> set2){
        int result = 0;
        for(int i:set1){
            if (set2.contains(i)) result++;
        }
        return result;
    }
    private String calculateSimilarity(int intersection, int union){
        return String.format("%.4f",(double)intersection/union);
//        DecimalFormat decimalFormat = new DecimalFormat("0.0000");
//        return decimalFormat.format((double) intersection/union);
        // wrong
//        BigDecimal bigDecimal = new BigDecimal(intersection);
//        double similarity = bigDecimal.divide(new BigDecimal(union),4).doubleValue();
//        return String.valueOf(similarity);
    }
    private String printResult(int id1, int id2, String similarity){
        StringBuilder result = new StringBuilder();
        if (id1<id2){
            result.append(id1).append(",").append(id2);
        }else{
            result.append(id2).append(",").append(id1);
        }
        result.append(": ").append(similarity);
        return result.toString();
    }


    class Solution {

        // 来自网友
        public List<String> computeSimilarities1(int[][] docs) {
            List<String> res = new ArrayList<>();
            int n = docs.length;
            Map<Integer, List<Integer>> word_doc = new HashMap<>();
            Map<List<Integer>, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                for (int word : docs[i]) {
                    word_doc.computeIfAbsent(word, v -> new ArrayList<>()).add(i);
                }
            }
            for (Integer word : word_doc.keySet()) {
                List<Integer> val = word_doc.get(word);
                int size = val.size();
                if (size < 2) continue;
                for (int i = 0; i < size - 1; i++) {
                    Integer base = val.get(i);
                    for (int j = i + 1; j < size; j++) {
                        map.merge(Arrays.asList(base, val.get(j)), 1, (oldVal, newVal) -> oldVal + newVal);
                    }
                }
            }
            for (Map.Entry<List<Integer>, Integer> entry : map.entrySet()) {
                List<Integer> pair = entry.getKey();
                double a = entry.getValue();
                double b = docs[pair.get(0)].length + docs[pair.get(1)].length - a;
                res.add(String.format("%d,%d: %.4f", pair.get(0), pair.get(1), a / b));
            }
            return res;
        }

        public List<String> computeSimilarities(int[][] docs) {
            List<String> result = new ArrayList<>();
            int n = docs.length;
            Map<Integer,List<Integer>> wordMap = new HashMap<>();
            int[][] matrix = new int[n][n];

            for (int i = 0; i < n; i++) {
                int len = docs[i].length;
                for (int j = 0; j < len; j++) {
                    // fancy style
                    if (wordMap.containsKey(docs[i][j])){
                        for(int articleIdx:wordMap.get(docs[i][j])){
                            matrix[i][articleIdx] ++;
                        }
                    }
                    wordMap.computeIfAbsent(docs[i][j],v->new ArrayList<>()).add(i);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j]!=0){
                        double intersection = matrix[i][j];
                        double union = docs[i].length+docs[j].length-intersection;
                        result.add(String.format("%d,%d: %.4f",j,i,intersection/union));
                    }
                }
            }
            return result;
        }
    }
}
