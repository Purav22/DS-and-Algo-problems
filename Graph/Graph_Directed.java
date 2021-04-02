package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class Graph_Directed {

    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static int N = 7;

    @SuppressWarnings("unchecked")
    // public static ArrayList<Edge>[] graph = new ArrayList[N];
    static ArrayList<Edge>[] graph = new ArrayList[N];

    public static void addEdge(int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
    }

    public static void constructGraph() {
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(0, 1, 10);
        addEdge(0, 3, 10);
        addEdge(1, 2, 10);
        addEdge(2, 3, 40);
        addEdge(3, 4, 2);
        addEdge(4, 5, 2);
        addEdge(4, 6, 8);
        addEdge(5, 6, 3);
    }

    public static void display(){

        for(int i = 0; i < N; i++){
            System.out.print(i + " -> ");
            for(int j = 0; j < graph[i].size(); j++){
                System.out.print("( " + graph[i].get(j).v + ", " + graph[i].get(j).w + " ), ");
            }
            System.out.println();
        }

    }
    
    public void dfs_topo(int src, boolean[] vis, ArrayList<Integer> ans)
    {
        vis[src] = true;
        for (Edge e : graph[src])
        {
            if (!vis[e.v])
                dfs_topo(e.v, vis, ans);
        }
    
        ans.add(src);
    }
    
    public void topologicalOrder_DFS()
    {
        boolean[] vis = new boolean[N];
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < N; i++)
        {
            if (!vis[i])
                dfs_topo(i, vis, ans);
        }
    
        for (int ele : ans)
            System.out.println(ele + " ");
    }
    
    public void kahnsAlgo()
    {
        int[] indegree = new int[N];
        for (int i = 0; i < N; i++)
            for (Edge e : graph[i])
                indegree[e.v]++;
    
        ArrayList<Integer> ans = new ArrayList<>();
        LinkedList<Integer> que = new LinkedList<>();
    
        for (int i = 0; i < N; i++)
            if (indegree[i] == 0)
                que.addLast(i);
    
        while (que.size() != 0)
        {
            int size = que.size();
            while (size-- > 0)
            {
                int rvtx = que.removeFirst();
    
                ans.add(rvtx);
    
                for (Edge e : graph[rvtx])
                {
                    if (--indegree[e.v] == 0)
                        que.addLast(e.v);
                }
            }
    
        }
    
        for (int ele : ans)
            System.out.println(ele + " ");
    }
    
    public boolean isCyclePresent_DFSTopo(int src, int[] vis, ArrayList<Integer> ans){
        vis[src] = 0;
        boolean res = false;
        for (Edge e : graph[src]){
            if (vis[e.v] == -1){ // unvisited
                res = res || isCyclePresent_DFSTopo(e.v, vis, ans);
            }
            else if (vis[e.v] == 0){
                return true; // there is cycle.
            }
        }
    
        vis[src] = 1;
        ans.add(src);
        return res;
    }
    
    public ArrayList<Integer> isCyclePresent_DFS(){
        int[] vis =  new int[N];
        Arrays.fill(vis, -1);
        ArrayList<Integer> ans = new ArrayList<>();
    
        boolean res = false;
        for (int i = 0; i < N; i++){
            if (vis[i] == -1){
                res = res || isCyclePresent_DFSTopo(i, vis, ans);
            }
        }
    
        if (res)
            ans.clear();
        return ans;
    }
    
    void dfs_SCC(ArrayList<Edge>[] ngraph, int src, boolean[] vis,  ArrayList<Integer> ans)
    {
        vis[src] = true;
        for (Edge e : ngraph[src])
        {
            if (!vis[e.v])
                dfs_SCC(ngraph, e.v, vis, ans);
        }
    
        ans.add(src);
    }
    
    //Kosaraju
    public void kosaraju(){
        boolean[] vis = new boolean[N];
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < N; i++){
            if (!vis[i])
                dfs_topo(i, vis, ans);
        }
    
        // Graph inverse.
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] ngraph = new ArrayList[N];

        for (int i = 0; i < N; i++){
            for (Edge e : graph[i]){
                ngraph[e.v].add(new Edge(i, 1));
            }
        }
    
        vis = new boolean[N];
        for (int i = ans.size() - 1; i >= 0; i--){
            int ele = ans.get(i);
            if (!vis[ele])
            {
                ArrayList<Integer> scc = new ArrayList<>();
                dfs_SCC(ngraph, ele, vis, scc);
    
                for (int e : scc)
                    System.out.println(e + " ");

                System.out.println();
            }
        }
    }
    
}
