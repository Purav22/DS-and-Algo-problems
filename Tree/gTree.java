package Tree;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.LinkedList;

public class gTree {

    public static class Node {
        int val = 0;
        ArrayList<Node> childs;

        Node(int val) {
            this.val = val;
            this.childs = new ArrayList<>();
        }
    }

    public static int size(Node node) {
        // if (node == null)
        // return 0;

        int sz = 0;
        for (Node child : node.childs) {
            sz += size(child);
        }

        return sz + 1;
    }

    // Height on the basis of Edge
    public static int height(Node node) {
        int h = -1;
        for (Node child : node.childs) {
            h = Math.max(h, height(child));
        }

        return h + 1;
    }

    public static int maximum(Node node) {
        int maxEle = node.val;
        for (Node child : node.childs) {
            maxEle = Math.max(maxEle, maximum(child));
        }

        return maxEle;
    }

    public static boolean find(Node node, int data) {
        if (node.val == data)
            return true;

        for (Node child : node.childs) {
            if (find(child, data))
                return true;
        }

        return false;
    }

    public static int find01(Node node, int data) {
        if (node.val == data)
            return 0;

        int depth = -1;
        for (Node child : node.childs) {
            depth = find01(child, data);
            if (depth != -1)
                break;
        }

        if (depth != -1)
            depth++;

        return depth;
    }

    public static boolean rootToNodePath(Node root, int data, ArrayList<Node> ans) {
        if (root.val == data) {
            ans.add(root);
            return true;
        }

        boolean res = false;
        for (Node child : root.childs) {
            res = res || rootToNodePath(child, data, ans);
            // if(res) break;
        }

        if (res)
            ans.add(root);

        return res;
    }

    public static ArrayList<Node> rootToNodePath(Node root, int data) {
        ArrayList<Node> ans = new ArrayList<>();
        rootToNodePath(root, data, ans);

        return ans;

    }

    public static Node LCA(Node root, int d1, int d2) {
        ArrayList<Node> l1 = new ArrayList<>();
        rootToNodePath(root, d1, l1);

        ArrayList<Node> l2 = new ArrayList<>();
        rootToNodePath(root, d2, l2);

        int i = l1.size() - 1;
        int j = l2.size() - 1;

        Node LCA = null;
        while (i >= 0 && j >= 0) {
            if (l1.get(i) != l2.get(j))
                break;

            LCA = l1.get(i);
            i--;
            j--;
        }

        return LCA;
    }

    public void kDown(Node root, Node blockNode, int time, List<List<Integer>> ans) {
        if (root == blockNode)
            return;

        if (ans.size() == time)
            ans.add(new ArrayList<>());

        ans.get(time).add(root.val);
        for (Node child : root.childs)
            kDown(child, blockNode, time + 1, ans);

    }

    // Method_01
    public List<List<Integer>> burningTree_01(Node root, int target) {
        ArrayList<Node> list = new ArrayList<>();
        rootToNodePath(root, target, list);

        List<List<Integer>> ans = new ArrayList<>();
        Node blockNode = null;

        for (int i = 0; i < list.size(); i++) {
            kDown(list.get(i), blockNode, i, ans);
            blockNode = list.get(i);
        }

        return ans;
    }

    // Method_02
    public int burningTree_02(Node root, int target, List<List<Integer>> ans) {
        if (root.val == target) {
            kDown(root, null, 0, ans);
            return 1;
        }

        int time = -1;
        Node blockNode = null;
        for (Node child : root.childs) {
            time = burningTree_02(child, target, ans);
            if (time != -1) {
                blockNode = child;
                break;
            }
        }

        if (time != -1) {
            kDown(root, blockNode, time, ans);
            time++;
        }
        return time;
    }

    public static void lineWiseLevelOrder(Node root) {
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        int level = 0;

        while (que.size() != 0) {
            int sz = que.size();
            System.out.print("Level : " + level + " -> ");
            while (sz-- > 0) {
                Node rn = que.removeFirst();
                System.out.print(rn.val + ", ");

                for (Node child : rn.childs) {
                    que.addLast(child);
                }

            }

            level++;
            System.out.println();
        }
    }

    public static boolean isMirror(Node node1, Node node2) {
        if (node1.childs.size() != node2.childs.size())
            return false;
        if (node1.val != node2.val)
            return false;

        for (int i = 0, j = node1.childs.size() - 1; j >= 0; i++, j--) {
            Node child1 = node1.childs.get(i);
            Node child2 = node2.childs.get(j);
            if (!isMirror(child1, child2))
                return false;
        }

        return true;

    }
    public Node getTail(Node root) {

        Node curr = root;
        while(curr.childs.size() != 0)
            curr = curr.childs.get(0);
        
       return curr;

    }

    public void flatten(Node root) {
        
        for(int i = root.childs.size() - 1; i >= 0; i--){
            Node child = root.childs.get(i);
            flatten(child);
            Node tail = getTail(child);
            if(tail.childs.size() == 0){
                if(i != root.childs.size() - 1){
                    tail.childs = new ArrayList<>();
                    tail.childs.add(root.childs.get(i + 1));
                    root.childs.remove(i + 1);
                }
            }
        }

       
    }
    //429
    public List<List<Integer>> levelOrder(Node root) {
        
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        
        LinkedList<Node> q = new LinkedList<>();
        q.addLast(root);

        while(q.size() > 0){
            int sz = q.size();
            List<Integer> temp = new ArrayList<>();
            while(sz-- > 0){
                Node node = q.removeFirst();
                temp.add(node.val);

                for(Node child: node.childs){
                    q.addLast(child);
                }
            }
            ans.add(temp);
        }
        return ans;
    }

    //serialized and deSerialized
    public static String serialize(Node root) {  
        StringBuilder result = new StringBuilder();  
        if (null != root) {  
             result.append(".");  
             result.append(root.key);  
             result.append(".");  
             for (Node child : root.childs) {  
                  result.append(Node.serialize(child));  
             }  
             result.append(")");  
        }  
        return result.toString();  
   }  
   public static Node deserialize(String node)  {  
        Node result = null;  
        Stack<Node> stack = new Stack<Node>();  
        boolean isData = false;  
        StringBuilder data = null;  
        for (int i = 0; i < node.length(); i++) {  
             if (node.charAt(i) == '.') {  
                  isData = !isData;  
                  if (isData) {  
                       data = new StringBuilder();  
                  } else {  
                       Node child = new Node(data.toString());  
                       if (!stack.isEmpty()) {  
                            Node parent = stack.peek();  
                            parent.addChild(child);  
                       } else {  
                            result = child;  
                       }  
                       stack.push(child);  
                  }  
             } else {  
                  if (isData) {  
                       data.append(node.charAt(i));  
                  } else if (node.charAt(i) == ')') {  
                       stack.pop();  
                  } else {  
                  }  
             }  
        }  
        return result;  
   }  

                                //****************Amazon asked************************* */

//    Given an n-ary tree of resources arranged hierarchically. A process needs to lock a resource node in order to use it. But a node cannot be 
// locked if any of its descendant or ancestor is locked. You are supposed to:
// -> write the structure of node
// -> write codes for

// Islock()- returns true if a given node is locked and false if it is not
// Lock()- locks the given node if possible and updates lock information
// Unlock()- unlocks the node and updates information.
// Codes should be :

// Islock –O(1)
// Lock()- O(log n)
// unLock()- O(log n)


    // class Tree {
    //     private: 
        
    //         // Tree Structure related.
    //         List <TreeNode *> _children;
    //         TreeNode * _parent;
            
    //         // Locking related.
    //         bool _locked = false;
    //         uint _numDescendantsLocked = 0;
    // };
    // bool Tree::IsLocked() {
    //     return _locked;
    // }

    // bool Tree::Lock() {
    //     // Any descendants locked?
    //     if (_numDescendantsLocked > 0) { return false;}
    
    //     // Check if any ancestor is locked.  
    //     Tree *parent = _parent;
    //     while (parent) {
    //         if (parent->IsLocked()) {
    //             return false;
    //         }
    //         parent = parent->_parent;
    //     }
    //     // Can lock now.
    //     parent = _parent;
    //     while(parent) {
    //         parent->_numDescendantsLocked++;
    //         parent = parent->_parent;
    //     }
    //     _locked = true;
    //     return true;
    // }





    // diameter of GT

    static int depthOfTree(Node ptr)
    {
        // Base case
        if (ptr == null)
            return 0;
    
        int maxdepth = 0;
    
        // Check for all children and find
        // the maximum depth
        for (Node it : ptr.child)
    
            maxdepth = Math.max(maxdepth,
                                depthOfTree(it));
    
        return maxdepth + 1;
    }
    
    // Function to calculate the diameter
    // of the tree
    static int diameter(Node ptr)
    {
        // Base case
        if (ptr == null)
            return 0;
    
        // Find top two highest children
        int max1 = 0, max2 = 0;
        for (Node it : ptr.child)
        {
            int h = depthOfTree(it);
            if (h > max1)
            {
                max2 = max1;
                max1 = h;
            }
            else if (h > max2)
            max2 = h;
        }
    
        // Iterate over each child for diameter
        int maxChildDia = 0;
        for (Node it : ptr.child)
            maxChildDia = Math.max(maxChildDia,
                                diameter(it));
    
        return Math.max(maxChildDia, max1 + max2 + 1);
    }
    
    


      
    public static void main(String[] args) {
        
    }
}