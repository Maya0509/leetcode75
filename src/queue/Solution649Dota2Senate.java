package queue;

import java.util.*;

public class Solution649Dota2Senate {
    // 没做出来的一题，卡在了模拟投票的方法上，总是想着用额外存储的flag来模拟，太麻烦且没想出好方法
    // 应当按照整数的性质进行分簇，模拟投票的轮次
    // 投过票的进入下一轮，次序保持相对不变，被淘汰的只需要进行remove操作
    // 保证下一个投票人只在两个队列的头部产生
    public String predictPartyVictory(String senate) {
        int num = senate.length();
        Deque<Integer> rDeque = new LinkedList<>();
        Deque<Integer> dDeque = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            char c = senate.charAt(i);
            if (c=='R') {
                rDeque.add(i);
            }else {
                dDeque.add(i);
            }
        }

        while (!rDeque.isEmpty() && !dDeque.isEmpty()){
            int r = rDeque.removeFirst();
            int d = dDeque.removeFirst();
            if (r<d) {
                rDeque.addLast(r+num);
            }else {
                dDeque.addLast(d+num);
            }
        }
        return rDeque.isEmpty()?"Dire":"Radiant";
    }
}
