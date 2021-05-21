import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class questions {
    class Node
{
    int data;
    Node left,right;
    Node(int d)
    {
        data=d;
        left=right=null;
    }
}
    //https://practice.geeksforgeeks.org/problems/clone-a-binary-tree/1#
    //Random pointer
    class Tree{
        int data;
        Tree left,right,random;
        Tree(int d){
            data=d;
            left=null;
            right=null;
            random=null;
        }
    }
    public static Tree cloneTree(Tree tree){
        // add code here.


        // if(tree == null) return null;
        // Tree ans = new Tree(tree.data);
        // ans.random = tree.random;
        // ans.left = cloneTree(tree.left);
        // ans.right = cloneTree(tree.right);
        
        // return ans;


        // OR


        Tree ans = createTree(tree);
        setRandom(ans, tree,ans);
        
        return ans;
      }
      public static void setRandom(Tree root1, Tree root2,Tree oroot){
         if(root2 == null) return;
         if(root2.random == null){
             root1.random = null;
         }
         else
             root1.random = findRandom(oroot, root2.random.data);
         
         setRandom(root1.left, root2.left, oroot);
         setRandom(root1.right, root2.right, oroot);
      }
      
      public static Tree findRandom(Tree tree, int val){
          if(tree == null) return null;
          if(tree.data == val) return tree;
          
          Tree left = findRandom(tree.left, val);
          if(left != null) return left;
          
          Tree right = findRandom(tree.right, val);
          
          return right;
          
      }
      public static Tree createTree(Tree root1){
         
         if(root1 == null) return null;
          Tree root = new Tree(root1.data);
          root.left = createTree(root1.left);
          root.right = createTree(root1.right);
          
          return root;
      }



    //   https://practice.geeksforgeeks.org/problems/count-number-of-subtrees-having-given-sum/1
    int countSubtreesWithSumX(Node root, int X)
    {
	    //Add your code here.
	    countTree(root, X);
	    return count;
    }
    int count = 0;
    int countTree(Node root, int X){
        if(root == null) return 0;
        
        int left = countTree(root.left, X);
        int right = countTree(root.right, X);
        
        int num = left + right + root.data;
        if(num == X) count++;
        return num;
    }




    //https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
    //Inorder without rec
    Node root;
    void inorder()
    {
        if (root == null)
            return;
 
 
        Stack<Node> s = new Stack<Node>();
        Node curr = root;
 
        // traverse the tree
        while (curr != null || s.size() > 0)
        {
 
            /* Reach the left most Node of the
            curr Node */
            while (curr !=  null)
            {
                /* place pointer to a tree node on
                   the stack before traversing
                  the node's left subtree */
                s.push(curr);
                curr = curr.left;
            }
 
            /* Current must be NULL at this point */
            curr = s.pop();
 
            System.out.print(curr.data + " ");
 
            /* we have visited the node and its
               left subtree.  Now, it's right
               subtree's turn */
            curr = curr.right;
        }
    }


    // https://www.geeksforgeeks.org/iterative-postorder-traversal/
    static void postOrderIterative(Node root)
    {
        // Create two stacks
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
  
        if (root == null)
            return;
  
        // push root to first stack
        s1.push(root);
  
        // Run while first stack is not empty
        while (!s1.isEmpty()) {
            // Pop an item from s1 and push it to s2
            Node temp = s1.pop();
            s2.push(temp);
  
            // Push left and right children of
            // removed item to s1
            if (temp.left != null)
                s1.push(temp.left);
            if (temp.right != null)
                s1.push(temp.right);
        }
  
        // Print all elements of second stack
        while (!s2.isEmpty()) {
            Node temp = s2.pop();
            System.out.print(temp.data + " ");
        }
    }


    // https://practice.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1
    public ArrayList<Integer> diagonal(Node root)
     {
           //add your code here.
           solve(root, 0);
           ArrayList<Integer> a = new ArrayList<>();
           
           for(ArrayList<Integer> temp : ans){
               for(Integer num : temp)
                a.add(num);
           }
           
           return a;
     }
     
     ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
     
     public void solve(Node root, int val){
         if(root == null) return;
         if(ans.size() == val){
             ans.add(new ArrayList<>());
             
        
         }
         
         ans.get(val).add(root.data);
         solve(root.left, val + 1);
         solve(root.right, val);
     }



    //  https://practice.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/1
    public static class verticalPair {
        Node node = null;
        int hl = 0; // horizontal Level

        verticalPair(Node node, int hl) {
            this.node = node;
            this.hl = hl;
        }
    }
    static ArrayList <Integer> verticalOrder(Node root)
    {
        // add your code here
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        
        List<List<Integer>> list = verticalOrderTraversal2(root);
        
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.get(i).size(); j++){
                ans.add(list.get(i).get(j));
            }
        }
        return ans;
    }
     public static List<List<Integer>> verticalOrderTraversal2(Node root) {
        LinkedList<verticalPair> que = new LinkedList<>();

        int[] minMax = new int[2];
        width(root, 0, minMax);
        int length = minMax[1] - minMax[0] + 1;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < length; i++)
            ans.add(new ArrayList<>());

        que.addLast(new verticalPair(root, -minMax[0]));

        while (que.size() != 0) {
            int size = que.size();
            while (size-- > 0) {
                verticalPair rp = que.removeFirst();

                ans.get(rp.hl).add(rp.node.data);

                if (rp.node.left != null)
                    que.addLast(new verticalPair(rp.node.left, rp.hl - 1));

                if (rp.node.right != null)
                    que.addLast(new verticalPair(rp.node.right, rp.hl + 1));
            }
        }

        return ans;
    }
    public static void width(Node root, int hl, int[] ans) {
        if (root == null)
            return;

        ans[0] = Math.min(hl, ans[0]);
        ans[1] = Math.max(hl, ans[1]);

        width(root.left, hl - 1, ans);
        width(root.right, hl + 1, ans);
    }



    //https://practice.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1
    ArrayList <Integer> printBoundary(Node node)
	{
	   ArrayList<Integer> ans = new ArrayList<>();
       if(node == null) return ans;

        ans.add(node.data);
        if(node.left == null && node.right == null) {
            return ans;
        }
        
        leftBoundary(node.left, ans);
        leafNodes(node, ans);
        rightBoundary(node.right, ans);
        return ans;
	}

    public void leftBoundary(Node node, ArrayList<Integer> ans){
        if(node == null) return;

        if(node.left != null || node.right != null) ans.add(node.data);
        if(node.left != null){
            leftBoundary(node.left, ans);
        }else if(node.right != null)
            leftBoundary(node.right, ans);

    }
    public void rightBoundary(Node node, ArrayList<Integer> ans){
        if(node == null) return;

        
        if(node.right != null){
            rightBoundary(node.right, ans);
        }else if(node.left != null)
            rightBoundary(node.left, ans);

        if(node.left != null || node.right != null) ans.add(node.data);

       
    }
    public void leafNodes(Node node, ArrayList<Integer> ans){
        if(node == null) return;
        
        leafNodes(node.left, ans);
        if(node.left == null && node.right == null) ans.add(node.data);
        leafNodes(node.right, ans);
        

    }


    //https://www.geeksforgeeks.org/perfect-binary-tree-specific-level-order-traversal/


    /* The standard level order traversal idea will slightly change here. 
        Instead of processing ONE node at a time, we will process TWO nodes at a time.
        And while pushing children into queue, the enqueue order will be: 1st node’s left child, 
        2nd node’s right child, 1st node’s right child and 2nd node’s left child. */


    void printSpecificLevelOrder(Node node) 
    {
        if (node == null)
            return;
   
        // Let us print root and next level first
        System.out.print(node.data);
   
        //  Since it is perfect Binary Tree, right is not checked
        if (node.left != null)
            System.out.print(" " + node.left.data + " " + node.right.data);
   
        // Do anything more if there are nodes at next level in
        // given perfect Binary Tree
        if (node.left.left == null)
            return;
   
        // Create a queue and enqueue left and right children of root
        Queue<Node> q = new LinkedList<Node>();
        q.add(node.left);
        q.add(node.right);
   
        // We process two nodes at a time, so we need two variables
        // to store two front items of queue
        Node first = null, second = null;
   
        // traversal loop
        while (!q.isEmpty()) 
        {
            // Pop two items from queue
            first = q.peek();
            q.remove();
            second = q.peek();
            q.remove();
   
            // Print children of first and second in reverse order
            System.out.print(" " + first.left.data + " " +second.right.data);
            System.out.print(" " + first.right.data + " " +second.left.data);
   
            // If first and second have grandchildren, enqueue them
            // in reverse order
            if (first.left.left != null) 
            {
                q.add(first.left);
                q.add(second.right);
                q.add(first.right);
                q.add(second.left);
            }
        }
    }
    public static Node createTree(int arr[], int N)
    {
        //Your code here
        HashMap<Integer, Node> map = new HashMap<>();
        map.put(-1, new Node(-1));
        for(int i = 0; i < N; i++){
            map.put(i, new Node(i));
            
        }
        
        int temp = 0;
        for(int i = 0; i < N; i++){
            Node curr = map.get(arr[i]);
            if(arr[i] == -1){
                temp = i;
            }
            if(curr.left == null){
                curr.left = map.get(i);
            }else{
                curr.right = map.get(i);
            }
        }
        return map.get(temp);
    }
}
