package string_operation;

class SolutionMerge2WordsAlternately {
    public String mergeAlternately(String word1, String word2) {
        int minLen = Math.min(word1.length(),word2.length());
        int i = 0;
        StringBuilder s = new StringBuilder();
        while(i<minLen){
            s.append(word1.charAt(i)).append(word2.charAt(i));
            i++;
        }
        while(i<word1.length()){
            s.append(word1.charAt(i));
            i++;
        }
        while(i<word2.length()){
            s.append(word2.charAt(i));
            i++;
        }
        return s.toString();
    }
}