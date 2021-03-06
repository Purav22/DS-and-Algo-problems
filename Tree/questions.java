package Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;



public class questions {
    public class TreeNode {
             int val;
             TreeNode left;
            TreeNode right;
             TreeNode() {}
             TreeNode(int val) { this.val = val; }
             TreeNode(int val, TreeNode left, TreeNode right) {
                 this.val = val;
                 this.left = left;
                 this.right = right;
             }
    }

    public int size(TreeNode root){
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public int height(TreeNode root){
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1; // This height is in term of edge
        // If we want to find height in term of node then height(root) + 1 is answer.
    }

    public int maximun(TreeNode root){
        if(root == null)
            return -(int)1e9;

            int left = maximun(root.left);
            int right = maximun(root.right);

            return Math.max(Math.max(left, right), root.val);
    }
    public int min(TreeNode root){
        if(root == null)
            return -(int)1e9;

            int left = maximun(root.left);
            int right = maximun(root.right);

            return Math.min(Math.min(left, right), root.val);
    }


    public boolean find(TreeNode root, int data){
        if(root == null)
            return false;

        if(root.val == data){
            return true;
        }

        return find(root.left, data) || find(root.right, data);
    }
   
    public boolean rootToNodePath(TreeNode root, TreeNode data, ArrayList<TreeNode> list){
        if(root == null)
            return false;

            if(root == data){
                list.add(root);
                return true;
            }

        boolean ans = rootToNodePath(root.left, data, list) || rootToNodePath(root.right, data, list);
        if(ans)
            list.add(root);

        
        return ans;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> list1 = new ArrayList<>();
        ArrayList<TreeNode> list2 = new ArrayList<>();
        
        rootToNodePath(root, p, list1);
        rootToNodePath(root, q, list2);
        
        int i = list1.size() - 1;
        int j = list2.size() - 1;

        TreeNode LCA = null;

        while(i >= 0 && j >= 0){
            if(list1.get(i) != list2.get(j))
                break;

            LCA = list1.get(i);
            i--;
            j--;
        }
        return LCA;
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        ArrayList<TreeNode> list1 = new ArrayList<>();
        rootToNodePath(root, target, list1);
        List<Integer> ans = new ArrayList<>();

        TreeNode block = null;
        
        for(int i = 0; i < list1.size(); i++){
            printKDown(list1.get(i), K - i, ans, block);
            block = list1.get(i);
        }
        return ans;
    }
    public int distanceK2(TreeNode node, TreeNode target, int k, List<Integer> ans){
        if(node == null) return -1;

        if(node == target){
            printKDown(node, k, ans, null);
        }

        int left = distanceK2(node.left, target, k, ans);
        if(left != -1){
            printKDown(node, k - left, ans, node.left);
            return left + 1;
        }
        int right = distanceK2(node.right, target, k, ans);
        if(right != -1){
            printKDown(node, k - right, ans, node.right);
            return right + 1;
        }
        return -1;
    }

    public int rootToNodeDistance(TreeNode node, TreeNode data){
        if(node == null) return -1;
        
        if(node == data) return 0;

        int left = rootToNodeDistance(node.left, data);
        if(left != -1){
            return left + 1;
        }
        int right = rootToNodeDistance(node.right, data);
        if(right != -1){
            return right + 1;
        }
        return -1;
    }

    public void printKDown(TreeNode node, int depth, List<Integer> ans, TreeNode block){
        if(node == null || depth < 0 || node == block)
            return;
        
            if(depth == 0){
                ans.add(node.val);
                return;
            }
        
        printKDown(node.left, depth - 1, ans, block);
        printKDown(node.right, depth - 1, ans, block);
    }
   
    public int diameterOfBinaryTree_01(TreeNode root) {
        if(root == null){
            return -1;
        }
        int leftDia = diameterOfBinaryTree_01(root.left);
        int rightDia = diameterOfBinaryTree_01(root.right);

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);


        return Math.max(Math.max(leftDia, rightDia), leftHeight + rightHeight + 2);
        
    }
    public int[] diameterOfBinaryTree_02(TreeNode root) {
        if(root == null){
            return new int[]{-1, -1};
        }
        int leftAns[] = diameterOfBinaryTree_02(root.left);
        int rightAns[] = diameterOfBinaryTree_02(root.right);

        int ans[] = new int[2];
        ans[0] = Math.max(Math.max(leftAns[0], rightAns[0]), leftAns[1] + rightAns[1] + 2);
        ans[1] = Math.max(leftAns[1] ,rightAns[1]) + 1;
        

        return ans;
        
    }
    int maxDia = 0;
    public int diameterOfBinaryTree_03(TreeNode root) {
        if(root == null){
            return -1;
        }
        int lh = diameterOfBinaryTree_03(root.left);
        int rh = diameterOfBinaryTree_03(root.right);
        maxDia = Math.max(maxDia, lh + rh + 2);
        return Math.max(lh, rh) + 1;
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null){
            return(targetSum -root.val == 0);
        }
        
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null){
            return new ArrayList<>();
        }
        List<List<Integer>> list = new ArrayList<>();
        pathSum(root, sum, list, new ArrayList<>());
        return list;
        
    
    }
    
    public void pathSum(TreeNode root, int tar, List<List<Integer>> ans, List<Integer> list){
        
        if(root == null){
            return;
        }
        
       
        if(root.left == null && root.right == null){
            if(tar - root.val == 0){
                List<Integer> base = new ArrayList<>(list);
                base.add(root.val);
                ans.add(base);
            }
            return;
        }
        
        list.add(root.val);
        pathSum(root.left, tar - root.val, ans, list);
        pathSum(root.right, tar - root.val, ans, list);
        list.remove(list.size() - 1);
    }
    

    public int maxPathBetweenTwoLeaves(TreeNode root, int ans[]){

        if(root == null)
            return 0;

        int left = maxPathBetweenTwoLeaves(root.left, ans);
        int right = maxPathBetweenTwoLeaves(root.right, ans);

        ans[0] = Math.max(ans[0], left + right + root.val);
        return left + right + root.val;
    }
    public int maxPathSum(TreeNode root) {
        if(root == null)
            return 0;
        maxPathSum_(root);
      return maxNTN;
    }
    int maxNTN = -(int)1e9;
    int maxPathSum_(TreeNode root){
         
        if(root == null)
            return 0;
            
      
        int left = maxPathSum_(root.left);
        int right = maxPathSum_(root.right);
        
        int maxSumTillRoot = Math.max(left, right) + root.val;
        maxNTN = Math.max(Math.max(maxNTN, maxSumTillRoot), Math.max(root.val, left + right+ root.val));
        
        return Math.max(maxSumTillRoot, root.val);
    }

    public class BST{
        boolean isBST = true;
        long max = -(long)1e13;
        long min = (long)1e13;

        BST(){

        }

        BST(boolean isBST, long max, long min){
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public BST isvalidBST_(TreeNode root){
        if(root == null)
            return new BST();

        BST left = isvalidBST_(root.left);
        BST right = isvalidBST_(root.right);

        BST myAns = new BST();
        myAns.isBST = left.isBST && right.isBST && root.val > left.max && root.val < right.min;
        if(myAns.isBST == false){
            return myAns;
        }
        myAns.max = Math.max(right.max, root.val);
        myAns.min = Math.min(left.min, root.val);

        return myAns;
    }
    public boolean isValidBST(TreeNode root) {
        return isvalidBST_(root).isBST;
    }
    public void recoverTree(TreeNode root) {
        recoverTree_(root);
        if(a != null){
            int temp = a.val;
            a.val = b.val;
            b.val = temp;
        }
        
    }
    TreeNode a = null, b = null, prev1 = null;
    public boolean recoverTree_(TreeNode root) {
        if(root == null)
            return true;

            if(!recoverTree_(root.left))
                return false;

            if(prev1 != null && prev1.val > root.val){
                b = root;
                if(a == null) a = prev1;
                else 
                    return false;
            }

            prev1 = root;
            if(!recoverTree_(root.right))
                return false;
            
            return true;
    }
    // T -> avg : O(nlogn), worst : O(n^2).
    // psi = pre starting index, isi = in-order starting index.
    public TreeNode preInTree(int[] preorder, int psi, int pei, int[] inorder, int isi, int iei) {
        if (psi > pei)
            return null;

        TreeNode node = new TreeNode(preorder[psi]);

        int idx = isi;
        while (inorder[idx] != preorder[psi])
            idx++;

        int tnel = idx - isi; // total no of elements.

        node.left = preInTree(preorder, psi + 1, psi + tnel, inorder, isi, idx - 1);
        node.right = preInTree(preorder, psi + tnel + 1, pei, inorder, idx + 1, iei);

        return node;
    }

    // public TreeNode buildTree(int[] preorder, int[] inorder) {
    //     return preInTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    // }

    // 106
    public TreeNode postInTree(int[] post, int psi, int pei, int[] in, int isi, int iei) {
        if (psi > pei)
            return null;

        TreeNode node = new TreeNode(post[pei]);
        int idx = isi;
        while (in[idx] != post[pei])
            idx++;

        int tnel = idx - isi;

        node.left = postInTree(post, psi, psi + tnel - 1, in, isi, idx - 1);
        node.right = postInTree(post, psi + tnel, pei - 1, in, idx + 1, iei);

        return node;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = postorder.length;
        return postInTree(postorder, 0, n - 1, inorder, 0, n - 1);
    }

    // 889
    public TreeNode postPreTree(int[] post, int ppsi, int ppei, int[] pre, int psi, int pei) {
        if (psi > pei)
            return null;

        TreeNode node = new TreeNode(pre[psi]);

        if (psi == pei)
            return node;

        int idx = ppsi;
        while (post[idx] != pre[psi + 1])
            idx++;

        int tnel = idx - ppsi + 1;
        node.left = postPreTree(post, ppsi, idx, pre, psi + 1, psi + tnel);
        node.right = postPreTree(post, idx + 1, ppei - 1, pre, psi + tnel + 1, pei);

        return node;
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int n = post.length;

        return postPreTree(post, 0, n - 1, pre, 0, n - 1);
    }

    // 114, T -> O(n^2)
    public TreeNode getTail(TreeNode root) {
        if (root == null)
            return null;
        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }

        return curr;

    }

    public void flatten(TreeNode root) {
        if (root == null)
            return;

        flatten(root.left);
        flatten(root.right);

        TreeNode tail = getTail(root.left);
        if (tail != null) {
            tail.right = root.right;
            root.right = root.left;
            root.left = null;
        }
    }

    // T -> O(n)
    public TreeNode flattern_(TreeNode node) {
        if (node == null || (node.left == null && node.right == null))
            return node;

        TreeNode leftTail = flattern_(node.left);
        TreeNode rightTail = flattern_(node.right);

        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        return rightTail != null ? rightTail : leftTail;
    }

    public void flatten(TreeNode root) {
        if (root == null)
            return;
        flattern_(root);
    }

    Node dummy = new Node(-1);
    Node prev = dummy;

    public void treeToDoublyList_(Node root) {
        if (root == null)
            return;

        treeToDoublyList_(root.left);

        prev.right = root;
        root.left = prev;

        prev = root;

        treeToDoublyList_(root.right);

    }

    public Node treeToDoublyList(Node root) {

        if (root == null)
            return root;
        treeToDoublyList_(root);

        Node head = dummy.right;
        head.left = null;
        dummy.right = null;

        prev.right = head;
        head.left = prev;
        return head;

    }

    int idx = 0;

    public TreeNode createTree(int[] arr) {
        if (idx == arr.length || arr[idx] == -1) {
            idx++;
            return null;
        }
        TreeNode node = new TreeNode(arr[idx++]);
        node.left = createTree(arr);
        node.right = createTree(arr);

        return node;
    }

    public void serializeTree(TreeNode node, ArrayList<Integer> arr) {
        if (node == null) {
            arr.add(-1);
            return;
        }

        arr.add(node.val);
        serializeTree(node.left, arr);
        serializeTree(node.right, arr);
    }

    public static TreeNode rightMostNode(TreeNode next, TreeNode curr) {
        while (next.right != null && next.right != curr)
            next = next.right;

        return next;
    }

    public static void morrisInOrderTraversal(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode next = curr.left;
            if (next == null) {
                System.out.print(curr.val + " ");
                curr = curr.right;
            } else {
                TreeNode rightMost = rightMostNode(next, curr);
                if (rightMost.right == null) { // thread create
                    rightMost.right = curr;
                    curr = curr.left;
                } else { // thread break
                    rightMost.right = null;
                    System.out.print(curr.val + " ");
                    curr = curr.right;
                }
            }
        }
    }

    public static void morrisPreOrderTraversal(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode next = curr.left;
            if (next == null) {
                System.out.print(curr.val + " ");
                curr = curr.right;
            } else {
                TreeNode rightMost = rightMostNode(next, curr);
                if (rightMost.right == null) { // thread create
                    rightMost.right = curr;
                    System.out.print(curr.val + " ");
                    curr = curr.left;
                } else { // thread break
                    rightMost.right = null;
                    curr = curr.right;
                }
            }
        }
    }

    public static class tPair {
        TreeNode node = null;
        boolean selfDone = false;
        boolean leftDone = false;
        boolean rightDone = false;

        tPair(TreeNode node, boolean selfDone, boolean leftDone, boolean rightDone) {
            this.node = node;
            this.selfDone = selfDone;
            this.leftDone = leftDone;
            this.rightDone = rightDone;
        }
    }

    public static void IterTraversalInorder(TreeNode root) {
        LinkedList<tPair> ll = new LinkedList<>();
        ll.addFirst(new tPair(root, false, false, false));
        while (ll.size() != 0) {
            tPair pair = ll.getFirst();
            if (!pair.leftDone) {
                pair.leftDone = true;
                if (pair.node.left != null)
                    ll.addFirst(new tPair(pair.node.left, false, false, false));

            } else if (!pair.selfDone) {
                pair.selfDone = true;
                System.out.print(pair.node.val + " ");
            } else if (!pair.rightDone) {
                pair.rightDone = true;
                if (pair.node.right != null)
                    ll.addFirst(new tPair(pair.node.right, false, false, false));
            } else {
                ll.removeFirst();
            }
        }
    }

    public static void IterTraversalPostOrder(TreeNode root) {
        LinkedList<tPair> ll = new LinkedList<>();
        ll.addFirst(new tPair(root, false, false, false));
        while (ll.size() != 0) {
            tPair pair = ll.getFirst();
            if (!pair.leftDone) {
                pair.leftDone = true;
                if (pair.node.left != null)
                    ll.addFirst(new tPair(pair.node.left, false, false, false));

            } else if (!pair.rightDone) {
                pair.rightDone = true;
                if (pair.node.right != null)
                    ll.addFirst(new tPair(pair.node.right, false, false, false));
            } else if (!pair.selfDone) {
                pair.selfDone = true;
                System.out.print(pair.node.val + " ");
                ll.removeFirst();
            } 
        }
    }
    public static void IterTraversalLevelOrder(TreeNode root) {
        LinkedList<tPair> ll = new LinkedList<>();
        ll.addFirst(new tPair(root, false, false, false));
        while (ll.size() != 0) {

            tPair pair = ll.removeFirst();
            if (!pair.selfDone) {
                pair.selfDone = true;
                System.out.print(pair.node.val + " ");
                //ll.removeFirst();
            }
            if (!pair.leftDone) {
                pair.leftDone = true;
                if (pair.node.left != null)
                    ll.addLast(new tPair(pair.node.left, false, false, false));

            } if (!pair.rightDone) {
                pair.rightDone = true;
                if (pair.node.right != null)
                    ll.addLast(new tPair(pair.node.right, false, false, false));
                
            }
        }
    }
    int idxx = 0;
    public TreeNode createTreeLevelOrder(int[] arr) {
        LinkedList<TreeNode> ll = new LinkedList<>();
        TreeNode root = new TreeNode(arr[idxx++]);
        ll.addLast(root);

        while(ll.size() != 0 && idxx < arr.length){
            TreeNode node = ll.removeFirst();
            if(arr[idxx] != -1){
                node.left = new TreeNode(arr[idxx++]);
                ll.addLast(node.left);
            }else{
                node.left = null;
            }
            if(arr[idxx] != -1){
                node.right = new TreeNode(arr[idxx++]);
                ll.addLast(node.right);
            }else{
                node.right = null;
            }
        }
        return root;
    }


    //166 Leetcodex

    public Node connect(Node root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        while(que.size() != 0){

            int sz = que.size();
            while(sz-- > 0){
                TreeNode node = que.removeFirst();
                if(sz == 1){
                    node.next = null;
                }
                else{
                    node.next = que.getFirst();
                }

                if(node.left != null){
                    que.addLast(node.left);
                }if(node.right != null){
                    que.addLast(node.right);
                }
            }
        }
        return root;
    }

    //968
    // -1  I need camera
    
    // 0 I am covered by child
    
    // 1 I am camera
    public int minCameraCover(TreeNode root) {
        
        if(solve(root) == -1)
            camera++;
        return camera;
    }
    int camera = 0;
    
    public int solve(TreeNode root){
        if(root == null){
            return 0;
        }
        
        int left = solve(root.left);
        int right = solve(root.right);
        
        if(left == -1 || right == -1){
            camera++;
            return 1;
        }
        if(left == 1 || right == 1){
            return 0;
        }
        return -1;
    }


    //337
    public int rob(TreeNode root) {
        if(root == null)
            return 0;
        int ans[] = rob_(root);
        return Math.max(ans[0], ans[1]);
    }

    public int[] rob_(TreeNode root){
        if(root == null)
            return new int[]{0,0};

        int[] left = rob_(root.left);
        int[] right = rob_(root.right);

        int[] myAns = new int[2];
        myAns[0] = left[1] + root.val + right[1];
        myAns[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return myAns;
    }

    //230
    public void addleftMost(LinkedList<TreeNode> st, TreeNode root){
        while(root != null){
            st.addLast(root);
            root = root.left;
        }
    }
    public int kthSmallest(TreeNode root, int k) {
             LinkedList<TreeNode> st = new LinkedList<>();
             addleftMost(st,root);
             while(k-- > 1){
                 TreeNode node = st.removeLast();
                 addleftMost(st, node.right);
             }
             return st.removeLast().val;
    }

    //437
    int ans = 0;
    public int pathSum_(TreeNode root, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        pathSumIII(root, map, sum, 0);
       return ans;
    }

    private void pathSumIII(TreeNode root, HashMap<Integer, Integer> map, int tar, int prefixSum) {

        if(root == null) return;

        prefixSum += root.val;

        ans += map.getOrDefault(prefixSum - tar, 0);

        map.put(prefixSum, map.getOrDefault(prefixSum,0) + 1);

        pathSumIII(root.left, map, tar, prefixSum);
        pathSumIII(root.right, map, tar, prefixSum);

        map.put(prefixSum, map.get(prefixSum) - 1);
        if(map.get(prefixSum) == 0){
            map.remove(prefixSum);
        }
    } 

    //653
    public void addAllRight(LinkedList<TreeNode> rs, TreeNode root) {

        while(root != null){
            rs.addLast(root);
            root = root.right;
        }
    }
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;

        LinkedList<TreeNode> ls = new LinkedList<>();
        LinkedList<TreeNode> rs = new LinkedList<>();

        addleftMost(ls, root);
        addAllRight(rs, root);

        while(ls.getFirst().val < rs.getFirst().val){
            int sum = ls.getFirst().val + rs.getFirst().val;

            if(sum == k){
                return true;
            }
            else if(sum < k){
                TreeNode node = ls.removeFirst();
                addleftMost(ls, root.right);
            }else{
                TreeNode node = rs.removeFirst();
                addAllRight(rs, root.left);
            }

        }
        return false;
        
    }

    //1372

    class Pair{
        int left;
        int right;
        int max;

        Pair(){

        }

        Pair(int left, int right, int max){
            this.left = left;
            this.right = right;
            this.max = max;
            
        }
    }

    public Pair solveZigZag(TreeNode root){
        if(root == null){
            return new Pair(-1,-1,-1);
        }
        Pair left = solveZigZag(root.left);
        Pair right = solveZigZag(root.right);

        Pair myAns = new Pair();
        myAns.left = left.right + 1;
        myAns.right = right.left + 1;
        myAns.max = Math.max(myAns.left, Math.max(myAns.right, Math.max(left.max, right.max)));

        return myAns;
    }
    public int longestZigZag(TreeNode root) {
         Pair ans = solveZigZag(root);
         return Math.max(ans.left, Math.max(ans.right, ans.max));
    }

    //508
    HashMap<Long, Integer> map = new HashMap<>();
    public int[] findFrequentTreeSum(TreeNode root) {
        findFrequentTreeSum_(root);
        ArrayList<Integer> list = new ArrayList<>();
        
        for(long num : map.keySet()){
            if(map.get(num) == max){
                list.add((int)num);
            }
        }
        int ans[] = new int[list.size()];
        int idx = 0;
        for(int num : list){
            ans[idx++] = num;
        }
        return ans;
    }
    int max = 0;
    
    public long findFrequentTreeSum_(TreeNode root){
        if(root == null){
            return 0;
        }


        long l = findFrequentTreeSum_(root.left);
        long r = findFrequentTreeSum_(root.right);

        long temp = l + r + root.val;
        map.put(temp, map.getOrDefault(temp, 0) + 1);
        if(map.get(temp) > max){
            max = map.get(temp);
        }
        return temp;
    }

    //1104
    List<Integer> list;
    public List<Integer> pathInZigZagTree(int label) {
        list = new ArrayList<>();
        ZigZagTree(label);
        Collections.reverse(list);
        return list;
               
    }
    
    public void ZigZagTree(int label) {
         if(label==1){
             list.add(label);
             return;
         }
         list.add(label);
         int level = (int)(Math.log(label)/Math.log(2))+1;
         System.out.print("level "+ level+ " ");
         int previousLevel = (1<<(level-1));
         System.out.print("previousLevel "+previousLevel +" ");
         int difference = label-previousLevel;
         System.out.print("difference "+ difference);
         System.out.println();
         ZigZagTree(previousLevel-1-difference/2);
     }


    //1110

    public List<TreeNode> delNodes(TreeNode root, int[] arr) {
        List<TreeNode> ans = new ArrayList<>();
        if(arr.length == 0){
            ans.add(root);
            return ans;
        }
        boolean vis[] = new boolean[1000 + 1];
        filltheArray(vis, arr);
        solve(root, vis, ans);
        for(int num : arr){
            if(num == root.val){
                return ans;
            }
        }
        ans.add(0,root);
        return ans;
        
    }
    public void filltheArray(boolean[] vis, int[] arr){
        
        for(int i = 0; i < arr.length; i++){
            vis[arr[i]] = true;    
        }
        
    }
    
    public void solve(TreeNode root, boolean[] vis, List<TreeNode> ans){
        if(root == null) return;
        if(vis[root.val]){
            if(root.left != null){
                if(!vis[root.left.val]){
                    ans.add(root.left);
                    solve(root.left, vis,ans);
                }else{
                    solve(root.left, vis, ans);
                    root.left = null;
                }
            }
            if(root.right != null){
                if(!vis[root.right.val]){
                    ans.add(root.right);
                    solve(root.right, vis,ans);
                }else{
                    solve(root.right, vis, ans);
                    root.right = null;
                }
                
            }
        
        }else{
            solve(root.left, vis, ans);
            if(root.left != null){
                if(vis[root.left.val]) root.left = null;
            }
            solve(root.right, vis, ans);
            if(root.right != null){
                if(vis[root.right.val]) root.right = null;
            }
        }
        
    }

    //1443
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        boolean[] vis = new boolean[n];
        for(int i = 0; i < n; i++) map.put(i, new ArrayList<>());
        
        
        for(int[] e : edges){
            map.get(e[0]).add(e[1]);    
            map.get(e[1]).add(e[0]);
        }
        
        for(int i = 0; i < n; i++){
            if(!vis[i]){
                solve_miTime(map, hasApple, n, i, vis);
            }
        }
        return ans;
        
    }
    int ans = 0;
    public boolean solve_miTime(HashMap<Integer, ArrayList<Integer>> map, List<Boolean> hasApple, int n, int idx, boolean vis[]){
        
        ArrayList<Integer> list = map.get(idx);
        vis[idx] = true;
        boolean call = false;
        for(Integer num : list){
            if(!vis[num]){
                if(solve_miTime(map, hasApple, n, num, vis)){
                        ans += 2;
                        call = true;
                    
                }
            }
        }
        if(hasApple.get(idx)){
            
                call = true;
            
        }
        return call;
    }

    //1315

    class pair{
        TreeNode node;
        TreeNode par;
        TreeNode gpar;
        
        pair(TreeNode node, TreeNode par, TreeNode gpar){
            this.node = node;
            this.par = par;
            this.gpar = gpar;
        }
        
    }
    int ans = 0;
    public int sumEvenGrandparent(TreeNode root) {
        sumEvenGrandparent_(root, new pair(null, null, null));
        return ans;
    }
    
    public void sumEvenGrandparent_(TreeNode root, pair par){
        if(root == null) return;
        pair p = new pair(root, par.node, par.par);
        
        if(p.gpar != null && p.gpar.val % 2 == 0) ans += p.node.val;
        
        sumEvenGrandparent_(root.left, p);
        sumEvenGrandparent_(root.right, p);
        return;
    }

    //1448

    public int goodNodes(TreeNode root) {
        return goodNodes(root, root.val);
    }
    
    public int goodNodes(TreeNode root, int max){
        
        if(root == null) return 0;
        int count = 0;
        if(root.val >= max){
            count++;
            max = root.val;
        }
        
        count += goodNodes(root.left, max);
        count += goodNodes(root.right, max);
        
        return count;
        
    }
    
}
