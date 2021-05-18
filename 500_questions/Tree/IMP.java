
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class IMP{

    static class Node { 
        int key; 
        ArrayList<Node> child;
        Node(){
            child = new ArrayList<>();
        }
    }

    //https://www.geeksforgeeks.org/node-maximum-sum-immediate-children-n-ary-tree/
    static int maxsum;
  
    // resultant node with max sum of children 
    // and node 
    static Node resNode;
    
    // Helper function to find the node 
    static void maxSumUtil(Node root) 
    { 
        // Base Case 
        if (root == null) 
            return; 
    
        // curr contains the sum of the root and 
        // its children 
        int currsum = root.key; 
    
        // total no of children 
        int count = root.child.size(); 
    
        // for every child call recursively 
        for (int i = 0; i < count; i++)
        { 
            currsum += root.child.get(i).key; 
            maxSumUtil(root.child.get(i)); 
        } 
    
        // if curr is greater than sum, update it 
        if (currsum > maxsum)
        { 
    
            // resultant node 
            resNode = root; 
            maxsum = currsum; 
        } 
        return; 
    } 
    
    // Function to find the node having max sum of 
    // children and node 
    static int maxSum(Node root) 
    { 
        
        // sum of node and its children 
        int maxsum = 0; 
    
        maxSumUtil(root); 
    
        // return the key of resultant node 
        return resNode.key; 
    } 



    //https://www.geeksforgeeks.org/given-n-ary-tree-count-number-nodes-number-children-parent/
    // function to count number of nodes
    // which has more children than its parent
    static int countNodes(Vector<Integer> adj[], int root)
    { 
        int count = 0;
    
        // queue for applying BFS
        Queue<Integer> q = new LinkedList<>();
    
        // BFS algorithm
        q.add(root);
        
        while (!q.isEmpty())
        {
            int node = q.peek();
            q.remove();
    
            // traverse children of node
            for( int i=0;i<adj[node].size();i++)
            { 
                // children of node
                int children = adj[node].get(i);
    
                // if number of childs of children
                // is greater than number of childs
                // of node, then increment count
                if (adj[children].size() > adj[node].size())
                    count++;
                q.add(children);
            }
        }
        return count;
    }
}