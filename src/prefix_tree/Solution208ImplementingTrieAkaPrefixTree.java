package prefix_tree;

import java.util.HashMap;
import java.util.Map;

public class Solution208ImplementingTrieAkaPrefixTree {

    class Trie {

//        char val;
        // node do not need val(char)
        // 可以使用Trie[26] 代替Map，但是我觉得会浪费较大的空间
        Map<Character,Trie> next;
        boolean isEnd;

        public Trie() {
//            val = '0';
            next = new HashMap<>();
            isEnd = false;
        }

        public void insert(String word) {
            char[] chars = word.toCharArray();
            Map<Character,Trie> next = this.next;
            for (int i=0; i<chars.length; i++) {
                char c = chars[i];
                if (!next.containsKey(c)) {
                    next.put(c, new Trie());
                }
                if (i== chars.length-1) next.get(c).isEnd = true;
                next = next.get(c).next;
            }
        }

        // 可以进一步对search()以及startWith()方法进行抽象，减少代码重复
        public boolean search(String word) {
            Map<Character,Trie> next = this.next;
            char[] chars = word.toCharArray();
            Trie lastNode;
            for (int i=0; i<chars.length; i++){
                char c = chars[i];
                if (!next.containsKey(c)) return false;
                else if (i== chars.length-1 && !next.get(c).isEnd) return false;
                next = next.get(c).next;
            }
            return true;
        }

        public boolean startsWith(String prefix) {
            Map<Character,Trie> next = this.next;
            char[] chars = prefix.toCharArray();
            for (char c:chars){
                if (!next.containsKey(c)) return false;
                next = next.get(c).next;
            }
            return true;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
}
