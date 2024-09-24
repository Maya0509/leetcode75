import sun.reflect.generics.tree.Tree;

import java.util.*;

public class BinaryTreeOperation {
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    class Solution {
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;
            return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
        }

        // 广度优先搜索
        public int maxDepthWithWidthFirstSearch(TreeNode root){
            if (root == null) return 0;
            int depth = 0;
            Deque<TreeNode> sameHeightNodes = new ArrayDeque<>();
            sameHeightNodes.addLast(root);
            while (!sameHeightNodes.isEmpty()){
                int size = sameHeightNodes.size();
                while (size > 0){
                    TreeNode node = sameHeightNodes.removeFirst();
                    if (node.left != null) sameHeightNodes.addLast(node.left);
                    if (node.right != null) sameHeightNodes.addLast(node.right);
                    size --;
                }
                depth ++;
            }
            return depth;
        }


        private List<Integer> getLeaves(TreeNode root){
            List<Integer> leaves = new ArrayList<>();
            if (root == null) return leaves;
            if (root.left != null) leaves.addAll(getLeaves(root.left));
            if (root.right != null) leaves.addAll(getLeaves(root.right));
            if (root.left == null && root.right == null) leaves.add(root.val);
            return leaves;
        }
        public boolean leafSimilar(TreeNode root1, TreeNode root2) {
            List<Integer> leaves1 = getLeaves(root1);
            List<Integer> leaves2 = getLeaves(root2);
            return leaves1.equals(leaves2);
        }


        private int countGoodNodes(TreeNode node, int maxAncestorVal){
            if (node == null) return 0;
            int cnt = (node.val >= maxAncestorVal) ? 1:0;
            if (cnt == 1) maxAncestorVal = node.val;
            cnt += countGoodNodes(node.left,maxAncestorVal);
            cnt += countGoodNodes(node.right,maxAncestorVal);
            return cnt;
        }
        public int goodNodes(TreeNode root) {
            return countGoodNodes(root,Integer.MIN_VALUE);
        }


        public int pathSum(TreeNode root, int targetSum) {
            if (root == null) return 0;
            int pathSum = 0;
            pathSum += pathWithNodeSum(root,targetSum);
            pathSum += pathSum(root.left,targetSum)+pathSum(root.right,targetSum);
            return pathSum;
        }
        private int pathWithNodeSum(TreeNode node, long sum){
            if (node == null) return 0;
            int pathSum = (node.val == sum) ? 1:0;
            pathSum += pathWithNodeSum(node.left,sum-node.val)+pathWithNodeSum(node.right,sum-node.val);
            return pathSum;
        }
    }
    // 前缀和写法
//    public int pathSum(TreeNode root, int targetSum) {
//        prefixSumTree(root);
//    }
//    private int pathSumFromRoot(TreeNode root, int targetSum){
//        if (root == null) return 0;
//        int pathSum = 0;
//        if (root.left != null) pathSum += (root.left.val == root.val+targetSum)?1:0;
//        if (root.right != null) pathSum += (root.right.val == root.val+targetSum)?1:0;
//        pathSum += pathSumFromRoot(root.left,targetSum-root.val)
//    }
//    private void prefixSumTree(TreeNode root){
//        if (root == null) return;
//        if (root.left != null) root.left.val += root.val;
//        if (root.right != null) root.right.val += root.val;
//        prefixSumTree(root.left);
//        prefixSumTree(root.right);
//    }

    public int pathSum(TreeNode root, int targetSum) {
        Map<Long,Integer> preSumMap = new HashMap<>();
//        preSumMap.put((long)0,1);
        preSumMap.put(0L,1);
        return prefixSumTreeWritingMap(preSumMap,0,root,targetSum);
    }
    private int prefixSumTreeWritingMap(Map<Long,Integer> preSumMap,long curr,TreeNode root,int targetSum){
        if (root == null) return 0;
        int result = 0;
        curr += root.val;
        result += preSumMap.getOrDefault(curr-targetSum,0);
        preSumMap.put(curr,preSumMap.getOrDefault(curr,0)+1);
        result += prefixSumTreeWritingMap(preSumMap,curr,root.left,targetSum)
                +prefixSumTreeWritingMap(preSumMap,curr,root.right,targetSum);
        preSumMap.put(curr,preSumMap.get(curr)-1);
        return result;
    }


// 超时
    //    public int longestZigZag(TreeNode root) {
//        int result = Math.max(zigzagPathWithFirstDirection(root,true),zigzagPathWithFirstDirection(root,false));
//        if (root == null)
//            return result;
//        result = Math.max(longestZigZag(root.left),result);
//        result = Math.max(longestZigZag(root.right),result);
//        return result;
//    }
//    private int zigzagPathWithFirstDirection(TreeNode root,boolean isLeft){
//        if (root == null) return 0;
//        if (isLeft && root.left != null) return 1+zigzagPathWithFirstDirection(root.left,false);
//        if (!isLeft && root.right != null) return 1+zigzagPathWithFirstDirection(root.right,true);
//        return 0;
//    }

    public int longestZigZag(TreeNode root) {
        // if(root == null) return 0;
        // 默认输入不为null，已有一个根节点，pathLen初始值为0
        return Math.max(dfs(root.left,1,0),dfs(root.right,-1,0));
    }
    private int dfs(TreeNode root, int direction, int len){
        // 若左节点或右节点不存在，则返回上一层的len值
        if (root == null) return len;
        // 若左节点或右节点存在，则len+1，进入下一层
        //如果本层方向为左（至左节点），则下一层方向为右（至左节点的右节点）；或放弃direction的取值（-1），从root处重新开始，到右节点
        if (direction == -1) return Math.max(dfs(root.left,1,len+1),dfs(root.right,-1,0));
        else return Math.max(dfs(root.right,-1,len+1),dfs(root.left,1,0));
    }


    // 想不出来的递归写法，非常精妙
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }


    // 返回一棵二叉树的右视图
    // bfs
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightSideNodes = new ArrayList<>();
        if (root == null) return rightSideNodes;
        Deque<TreeNode> nodesAtSameLevel = new ArrayDeque<>();
        nodesAtSameLevel.add(root);
        while (!nodesAtSameLevel.isEmpty()){
            int size = nodesAtSameLevel.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = nodesAtSameLevel.removeFirst();
                if (treeNode.left != null) nodesAtSameLevel.addLast(treeNode.left);
                if (treeNode.right != null) nodesAtSameLevel.addLast(treeNode.right);
                if (i == size-1) rightSideNodes.add(treeNode.val);
            }
        }
        return rightSideNodes;
    }
    // dfs
    Map<Integer,TreeNode> depthNodeMap = new HashMap<>();
    int maxDepth = 0;
    public List<Integer> rightSideViewDFS(TreeNode root){
        List<Integer> rightNodes = new ArrayList<>();
        dfs(root,0);
        for (int i = 0; i <= maxDepth; i++) {
            rightNodes.add(depthNodeMap.get(i).val);
        }
        return rightNodes;
    }
    private void dfs(TreeNode root, int depth){
        if (root == null) return;
        maxDepth = Math.max(maxDepth,depth);
        depthNodeMap.put(depth,root);
        dfs(root.left,depth+1);
        dfs(root.right,depth+1);
    }
    // more simplified dfs method
    public List<Integer> rightSideViewDFS2(TreeNode root){
        List<Integer> rightSideNodes = new ArrayList<>();
        dfs(root,0,rightSideNodes);
        return rightSideNodes;
    }
    private void dfs(TreeNode root, int depth, List<Integer> result){
        if (root == null) return;
        if (depth == result.size()) result.add(root.val);
        else result.set(depth,root.val);
        dfs(root.left,depth+1,result);
        dfs(root.right,depth+1,result);
    }
    // 上一种方法中dfs的写法还可以更精简一些：先遍历右子树，再遍历左子树
    private void dfs2(TreeNode root, int depth, List<Integer> result){
        if (root == null) return;
        if (depth == result.size()) result.add(root.val);
        dfs(root.right,depth+1,result);
        dfs(root.left,depth+1,result);
    }


    public int maxLevelSum(TreeNode root) {
        List<Integer> levelSum = new ArrayList<>();
        dfsEditingLevelSum(root,0,levelSum);
        int depth = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < levelSum.size(); i++) {
            int sum = levelSum.get(i);
            if (sum > max) {
                depth = i;
                max = sum;
            }
        }
        return depth+1;
    }
    private void dfsEditingLevelSum(TreeNode root, int depth, List<Integer> levelSum){
        if (root == null) return;
        if (depth == levelSum.size()) levelSum.add(root.val);
        else levelSum.set(depth,levelSum.get(depth)+root.val);
        dfsEditingLevelSum(root.left,depth+1,levelSum);
        dfsEditingLevelSum(root.right,depth+1,levelSum);
    }



    // BST

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
//        if (root.val < val) return searchBST(root.right,val);
//        else return searchBST(root.left,val);
        return searchBST(root.val > val ? root.left : root.right, val);
    }


    // 删除节点的错误写法，错误原因不明，似乎不应该通过调用函数的方式得到需要被删除的节点，并对这个引用进行操作
//    public TreeNode deleteNode(TreeNode root, int key) {
//        TreeNode toDelete = searchBST(root,key);
//        if (toDelete == null) return root;
//        if (toDelete.left == null && toDelete.right == null){
//            toDelete = null;
//        }else {
//            TreeNode substitute = (toDelete.left != null) ? findMaxNode(toDelete.left) : findMinNode(toDelete.right);
//            int val = substitute.val;
//            deleteNode(toDelete,val);
//            toDelete.val = val;
//        }
//        return root;
//    }
//    private TreeNode searchParentDefaultSelf(TreeNode root, int key){
//        if (root == null || root.left.val == key || root.right.val == key) return root;
//        return searchParentDefaultSelf(root.val > key ? root.left : root.right, key);
//    }
    private TreeNode findMinNode(TreeNode root){
        if (root == null || root.left == null) return root;
        return findMinNode(root.left);
    }
    // 只要一种就行
    private TreeNode findMaxNode(TreeNode root){
        if (root == null || root.right == null) return root;
        return findMaxNode(root.right);
    }

    public TreeNode deleteNode(TreeNode root, int key){
        if (root == null) return root;
        if (root.val < key) root.right = deleteNode(root.right, key);
        else if (root.val > key) root.left = deleteNode(root.left, key);
        else {
            if (root.left != null && root.right != null) {
                int substituteVal = findMinNode(root.right).val;
                root.val = substituteVal;
                root.right = deleteNode(root.right,substituteVal);
            }
            else {
                root = (root.left == null) ? root.right : root.left;
            }
        }
        return root;
    }

    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int leftChildLevels = countLevels(root.left);
        int rightChildLevels = countLevels(root.right);
        if (leftChildLevels==rightChildLevels){
            return countNodes(root.right)+(1<<leftChildLevels);
        }
        if (leftChildLevels>rightChildLevels){
            return countNodes(root.left)+(1<<rightChildLevels);
        }
        else return -1;
    }
    private int countLevels(TreeNode node){
        if(node == null) return 0;
        return Math.max(1+countLevels(node.left),1+countLevels(node.right));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> curLevel = new LinkedList<>();
        curLevel.addLast(root);
        while(!curLevel.isEmpty()){
            Deque<TreeNode> nxtLevel = new LinkedList<>();
            List<Integer> curLevelV = new ArrayList<>();
            while(!curLevel.isEmpty()){
                TreeNode p = curLevel.removeFirst();
                curLevelV.add(p.val);
                if(p.left!=null) nxtLevel.addLast(p.left);
                if(p.right!=null) nxtLevel.addLast(p.right);
            }
            if (curLevelV.size()>0){
                result.add(new ArrayList<>(curLevelV));
                curLevel = new LinkedList<>(nxtLevel);
            }else break;
        }
        return result;
    }
}
