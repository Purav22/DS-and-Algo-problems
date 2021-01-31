package Tree;

import java.util.ArrayList;
import java.util.List;

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
   
}
