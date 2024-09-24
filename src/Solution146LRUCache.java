import java.util.*;

public class Solution146LRUCache
{
    // not O(1), wrong ans
    class LRUCache1 {
        private final Map<Integer,Integer> cache;
        private final int capacity;
        private final Deque<Integer> useFrequencyKeyDeque;
        public LRUCache1(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>(capacity);
            useFrequencyKeyDeque = new LinkedList<>();
        }

        public int get(int key) {
            if (cache.containsKey(key)){
                useFrequencyKeyDeque.remove(key);
                useFrequencyKeyDeque.addLast(key);
                return cache.get(key);
            }else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if (cache.containsKey(key)){
                cache.replace(key,value);
            }else {
                if (cache.size() < capacity){
                    cache.put(key,value);
                    useFrequencyKeyDeque.addLast(key);
                }else if (cache.size() == capacity){
                    int oldKey = useFrequencyKeyDeque.removeFirst();
                    useFrequencyKeyDeque.addLast(key);
                    cache.remove(oldKey);
                    cache.put(key,value);
                }
            }
        }
    }

    // O(1) hashmap and DLinkedNode


    class LRUCache{
        class DLinkedNode{
            int key;
            int val;
            DLinkedNode prev;
            DLinkedNode next;
            DLinkedNode(){}
            DLinkedNode(int _key, int _val){
                this.key = _key;
                this.val = _val;
            }

        }
        private final Map<Integer, DLinkedNode> cache = new HashMap<>();
        private int size;
        private final int capacity;
        private final DLinkedNode head;
        private final DLinkedNode tail;
        LRUCache(int capacity){
            this.head = new DLinkedNode();
            this.tail = new DLinkedNode();
            this.head.next = tail;
            this.tail.prev = head;
            this.capacity = capacity;
            this.size = 0;
        }
        public int get(int key){
            if(cache.containsKey(key)){
                DLinkedNode toDelete = remove(key);
                addLast(key,toDelete);
                return toDelete.val;
            }else {
                return -1;
            }
        }
        private DLinkedNode remove(int key){
            DLinkedNode toDelete = cache.get(key);
            toDelete.prev.next = toDelete.next;
            toDelete.next.prev = toDelete.prev;
            toDelete.prev = null;
            toDelete.next = null;
            this.size --;
            cache.remove(key);
            return toDelete;
        }
        private void addLast(int key,DLinkedNode node){
            if (this.size < this.capacity){
                node.prev = tail.prev;
                tail.prev.next = node;
                node.next = tail;
                tail.prev = node;
                cache.put(key,node);
                this.size++;
            }
        }
        public void put(int key, int value){
            DLinkedNode toPut = new DLinkedNode(key,value);
            if (cache.containsKey(key)){
                remove(key);
                addLast(key,toPut);
            }else {
                if (this.size < this.capacity){
                    addLast(key,toPut);
                }else {
                    int toDelKey = head.next.key;
                    remove(toDelKey);
                    addLast(key,toPut);
                }
            }
        }
    }
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
