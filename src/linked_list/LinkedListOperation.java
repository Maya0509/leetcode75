package linked_list;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class LinkedListOperation {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    // 只想到笨方法
    static class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){this.val = val;}
        ListNode(int val, ListNode next){
            this.val = val;
            this.next = next;
        }
    }

    class Solution {
        public ListNode deleteMiddle(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            ListNode delPre = slow;
            while(fast!=null && fast.next!=null){
                fast = fast.next.next;
                delPre = slow;
                slow = slow.next;
            }
            if (delPre == null || delPre.next == null) return null;
            delPre.next = delPre.next.next;
            return head;
        }

        public ListNode oddEvenList(ListNode head) {
            if (head==null || head.next==null) return head;
            ListNode odd = head;
            ListNode even = head.next;
            ListNode firstEven = even;
            while(odd.next!=null && odd.next.next!=null){
                odd.next = even.next;
                odd = odd.next;
                if (odd.next!=null){
                    even.next = odd.next;
                    even = even.next;
                }
            }
            // attention！！一开始没想到将最后的even指向null，导致链表中有cycle
            even.next = null;
            odd.next = firstEven;
            return head;
            /**
             * 下面一种写法更简洁
             * if(head == null) return head;
             * ListNode odd = head;
             * ListNode even = odd.next;
             * ListNode firstEven = even;
             * while(even!=null && even.next!=null){
             *  odd.next = even.next;
             *  odd = odd.next;
             *  even.next = odd.next;
             *  even = even.next;
             * }
             * odd.next = firstEven;
             */
        }
        // recursive method
        private ListNode tempHead = null;
        public ListNode reverseList(ListNode head) {
            if (head == null) return null;
            if (head.next == null){
                head.next = tempHead;
                return head;
            }
            ListNode suf = head.next;
            head.next = tempHead;
            tempHead = head;
            return reverseList(suf);
        }

        // iterative method
        public ListNode reverseListIterative(ListNode head){
            if (head == null || head.next == null) return head;
            ListNode pre = head,mid = head.next, suf = mid.next;
            head.next = null;
            while(mid!=null){
                mid.next = pre;
                pre = mid;
                mid = suf;
                if (mid!=null) suf = suf.next;
            }
            return pre;
        }

        // recursive method2
        public ListNode reverseListRecursive(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode newHead = reverseListRecursive(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
        }

        // 借用deque翻转前半链表，找到最大孪生和
        public int pairSum1(ListNode head) {
            Deque<ListNode> firstHalfStack = new ArrayDeque<>();
            ListNode front = head, rear = head;
            while(rear!=null){
                firstHalfStack.addLast(front);
                front = front.next;
                rear = rear.next.next;
            }
            head = front;
            int max = Integer.MIN_VALUE;
            while (!firstHalfStack.isEmpty()){
                max = Math.max(max,firstHalfStack.removeLast().val+ head.val);
                head = head.next;
            }
            return max;
        }

        // 使用反转链表方法反转后半部分链表
        public int pairSum(ListNode head) {
            ListNode slow = head, fast = head;
            while(fast!=null){
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode rear = reverseListRecursive(slow);
            ListNode front = head;
            int max = Integer.MIN_VALUE;
            while (rear!=null){
                max = Math.max(front.val+ rear.val,max);
                front = front.next;
                rear = rear.next;
            }
            return max;
        }

        // 关于left与right的特判太多了，应当简化
        // 复制了节点，浪费空间
        public ListNode reverseBetween1(ListNode head, int left, int right) {
            if(left == right) return head;
            int p = 1;
            ListNode leftNodesTail = head;
            while(p < left-1){
                leftNodesTail = leftNodesTail.next;
                p++;
            }
            ListNode midHead;
            if(left == 1){
                p = 0;
                leftNodesTail=null;
                midHead = head;
            } else {
                midHead = leftNodesTail.next;
                leftNodesTail.next = null;
            }
            ListNode midTail = new ListNode(midHead.val);
            ListNode pre = midTail;
            p++;
            ListNode nextNode = midHead.next;
            ListNode newHead = midHead;
            while(p < right){
                ListNode newNode = new ListNode(nextNode.val);
                newNode.next = pre;
                pre = newNode;
                if(nextNode.next == null){
                    nextNode = nextNode.next;
                    newHead = pre;
                    break;
                }else{
                    nextNode = nextNode.next;
                }
                if(p == right-1) newHead = pre;
                p++;
            }
            if(leftNodesTail!=null){
                leftNodesTail.next = newHead;
            }else{
                head = newHead;
            }
            midTail.next = nextNode;
            return head;
        }

        public ListNode reverseBetween(ListNode head, int left, int right){
            if(left == right) return head;
            ListNode dummy = new ListNode(0,head);
            ListNode pre = dummy;
            for(int i = 0; i < left-1; i++){
                pre = pre.next;
            }
            ListNode cur = pre.next;
            ListNode prev = null;
            for(int i = 0; i< right-left+1; i++){
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            pre.next.next = cur;
            pre.next = prev;
            return dummy.next;
        }

        // 有额外存储，时间复杂度虽未O(n)，但是运行也不快
        public void reorderList1(ListNode head) {
            if (head==null) return;
            Deque<ListNode> nodeDeque = new LinkedList<>();
            ListNode p = head;
            while(p!=null){
                nodeDeque.addLast(p);
                p = p.next;
            }
            insertTail(nodeDeque);
        }
        private void insertTail(Deque<ListNode> nodeDeque){
            if (nodeDeque.size()<3) return;
            ListNode head = nodeDeque.removeFirst();
            ListNode tail = nodeDeque.removeLast();
            head.next = tail;
            ListNode nextHead = nodeDeque.getFirst();
            ListNode nextTail = nodeDeque.getLast();
            tail.next = nextHead;
            nextTail.next = null;
            insertTail(nodeDeque);
        }

        //没有额外存储的递归做法
        public void reorderList(ListNode head){
            if(head==null||head.next==null||head.next.next==null){
                return;
            }
            int len = 0;
            ListNode p = head;
            // calculate length of the linkedlist
            while(p!=null){
                len++;
                p = p.next;
            }
            reorderList(head,len);
        }
        private ListNode reorderList(ListNode head, int len){
            if (len==1){
                ListNode outTail = head.next;
                head.next = null;
                return outTail;
            }
            if (len==2){
                ListNode outTail = head.next.next;
                head.next.next = null;
                return outTail;
            }
            ListNode subHead = head.next;
            ListNode tail = reorderList(subHead,len-2);
            ListNode outTail = tail.next;
            tail.next = subHead;
            head.next = tail;
            return outTail;
        }

        // we can combine slow fast pointer and recursive method to solve the reorderList problem
        // , but too complex for me to understand so far

        // pdd主管面我这么写的，主管没说啥，但是后来复盘发现小错误，而且时间复杂度为O(n^2)，现在结果还没出来有点忐忑
        private void reorderListO2(ListNode head){
            if(head==null||head.next==null||head.next.next==null){
                return;
            }
            ListNode nxtHead = head.next;
            ListNode tail = findTail(head);
            head.next = tail;
            tail.next = nxtHead;
            // 这个递归应该用处不大，都是O(n^2),qwq
            reorderListO2(nxtHead);
        }
        private ListNode findTail(ListNode head){
            // 面试的时候没有重新使用一个局部变量p，直接使用head操作的，应该会出错
            ListNode p = head;
            while(p.next.next!=null){
                p = p.next;
            }
            ListNode tail = p.next;
            p.next = null;
            return tail;
        }


        // wrong
//        public ListNode reverseKGroup(ListNode head, int k) {
//            if (head == null || head.next == null) return head;
//            ListNode pre = head;
//            ListNode next = head.next;
//            ListNode nxtNext = next.next;
//            ListNode nextHead = head;
//            ListNode lastHead = head;
//            ListNode reverseKHead = head;
//            int cnt = k;
//            while(--cnt>=1){
//                nextHead = nextHead.next;
//
////                if (nextHead==null) break;
//                if (cnt == 1){
//                    if(nextHead==null) break;
//                    if(reverseKHead == head) reverseKHead = nextHead;
//                    cnt = k;
//
//                    nextHead = nextHead.next;
//                    lastHead.next = nextHead;
//                    for (int i = 0; i < k-1; i++) {
//                        next.next = pre;
//                        pre = next;
//                        next = nxtNext;
//                        nxtNext = nxtNext.next;
//                    }
//
//                    lastHead = nextHead;
//                    if (nextHead == null || nextHead.next == null) break;
//                    pre = nextHead;
//                    next = nextHead.next;
//                    nxtNext = next.next;
//                }
//            }
//            return reverseKHead;
//        }

        public ListNode reverseKGroup(ListNode head, int k){
            ListNode dummy = new ListNode(0,head);
            ListNode pre = dummy;
            ListNode end = dummy;

            while (end.next!=null){
                for(int i = 0; i<k && end!=null; i++) end = end.next;
                if(end==null) break;

                ListNode next = end.next;
                end.next = null;

                ListNode start = pre.next;
                pre.next = reverse(start);
                start.next = next;

                pre = start;
                end = pre;
            }
            return dummy.next;
        }
        private ListNode reverse(ListNode head){
            if (head==null) return head;
            ListNode next = head;
            ListNode pre = head;
            while(next!=null){
                ListNode nxtnext = next.next;
                next.next = pre;
                pre = next;
                next = nxtnext;
            }
            head.next = null;
            return pre;
        }

        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1==null) return list2;
            if (list2==null) return list1;
            if (list1.val < list2.val){
                ListNode next = list1.next;
                list1.next = mergeTwoLists(next,list2);
                return list1;
            }else{
                ListNode next = list2.next;
                list2.next = mergeTwoLists(next,list1);
                return list2;
            }
        }
    }
}