public class N_Ary_Tree {
     //https://practice.geeksforgeeks.org/problems/check-mirror-in-n-ary-tree1528/1
     static int checkMirrorTree(int n, int e, int[] A, int[] B) {
        HashMap<Integer, ArrayList<Integer>> l1 = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> l2 = new HashMap<>();

        for(int i = 0; i < A.length; i += 2){
            int x = A[i];
            int y = A[i + 1];

            if(!l1.containsKey(x)) l1.put(x, new ArrayList<Integer>());
            l1.get(x).add(y);
        }

        for(int i = 0; i < B.length; i += 2){
            int x = B[i];
            int y = B[i + 1];

            if(!l2.containsKey(x)) l2.put(x, new ArrayList<Integer>());
            l2.get(x).add(y);
        }

        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();

        st1.push(A[0]);
        st2.push(B[0]);

        while(st1.size() > 0 && st2.size() > 0){
            int x = st1.pop();
            int y = st2.pop();
            if(x != y) return 0;

            if(l1.containsKey(x))
                for(int i = 0; i < l1.get(x).size(); i++){
                    st1.push(l1.get(x).get(i));
                }
            if(l2.containsKey(y))
                for(int i = l2.get(y).size() - 1; i >= 0; i--){
                    st2.push(l2.get(y).get(i));
                }



        }

        if(st1.size() != st2.size()) return 0;

        return 1;
    }
}
