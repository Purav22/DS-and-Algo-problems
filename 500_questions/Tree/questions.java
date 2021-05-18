import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    static void postOrderIterative(node root)
    {
        // Create two stacks
        s1 = new Stack<>();
        s2 = new Stack<>();
  
        if (root == null)
            return;
  
        // push root to first stack
        s1.push(root);
  
        // Run while first stack is not empty
        while (!s1.isEmpty()) {
            // Pop an item from s1 and push it to s2
            node temp = s1.pop();
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
            node temp = s2.pop();
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
}
