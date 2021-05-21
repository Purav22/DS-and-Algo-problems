import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class questions {

    public static class Edge {
        int v = 0;
        int w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

     //207 
    boolean canFinish(int N, int[][] arr){
        ArrayList<Edge>[] graph = new ArrayList[N];
         for (int[] ar : arr){
             graph[ar[0]].add(new Edge(ar[1], 0));
         }
         int[] vis = new int[N];
         Arrays.fill(vis,-1);
     
         boolean res = false;
         for (int i = 0; i < N; i++){
             if (vis[i] == -1){
                 if (isCyclePresent_DFSTopo(i, vis, new ArrayList<>(), graph))
                     return false;
             }
         }
     
         return true;
     }
     
        public boolean isCyclePresent_DFSTopo(int src, int[] vis, ArrayList<Integer> ans,  ArrayList<Edge>[] graph){
            vis[src] = 0;
    
            boolean res = false;
    
            for(Edge e : graph[src]){
                if(vis[e.v] == -1){
                    res = res || isCyclePresent_DFSTopo(e.v, vis, ans, graph);
                } else if(vis[e.v] == 0){
                    return true; // isCycle
                }
            }
    
            vis[src] = 1;
            ans.add(src);
            return res;
        }
    
    
    public ArrayList<Integer> isCyclePresent_DFS(){
        int N = 0;
        ArrayList<Edge>[] graph = new ArrayList[N];
        //  for (int[] ar : arr){
        //      graph[ar[0]].add(new Edge(ar[1], 0));
        //  }
        int[] vis = new int[N];
        Arrays.fill(vis, -1);
        
        ArrayList<Integer> ans = new ArrayList<>();
        boolean res = false;
        for(int i = 0; i < N; i++){
            if(vis[i] == -1){
                res = res || isCyclePresent_DFSTopo(i, vis, ans, graph);
            }
        }
        if(res) ans.clear();

        return ans;
    }
     
    ArrayList<Integer> findOrder(int N, int[][] arr){
        ArrayList<Edge>[] graph = new ArrayList[N];
         for (int[] ar : arr){
            graph[ar[0]].add(new Edge(ar[1], 0));
         }
         int[] vis = new int[N];
         Arrays.fill(vis,-1);
         ArrayList<Integer> ans = new ArrayList<>();
     
         boolean res = false;
         for (int i = 0; i < N; i++)
             if (vis[i] == -1)
                 res = res || isCyclePresent_DFSTopo(i, vis, ans, graph);
     
         if (res)
             ans.clear();
         return ans;
     }
     
     
    //329
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] indegree = new int[n][m];
    
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int d = 0; d < 4; d++)
                {
                    int x = i + dir[d][0];
                    int y = j + dir[d][1];
    
                    if (x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] > matrix[i][j])
                        indegree[x][y]++;
                }
    
        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (indegree[i][j] == 0)
                    que.addLast(i * m + j);
    
        int level = 0;
        while (que.size() != 0)
        {
            int size = que.size();
            while (size-- > 0)
            {
                int idx = que.removeFirst();
                int r = idx / m;
                int c = idx % m;
    
                for (int d = 0; d < 4; d++)
                {
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];
    
                    if (x >= 0 && y >= 0 && x < n && y < m && matrix[x][y] > matrix[r][c] && --indegree[x][y] == 0)
                        que.addLast(x * m + y);
                }
            }
    
            level++;
        }
    
        return level;
    }
    
    
    //1061
    public String smallestEquivalentString(String A, String B, String S)
    {
        par = new int[26];
        for (int i = 0; i < 26; i++){
            par[i] = i;
        }
           
    
        for (int i = 0; i < A.length(); i++)
        {
            int p1 = findPar(A.charAt(i) - 'a');
            int p2 = findPar(B.charAt(i) - 'a');
    
            par[p1] = Math.min(p1, p2);
            par[p2] = Math.min(p1, p2);
        }
    
        String ans = "";
        for (int i = 0; i < S.length(); i++)
        {
            ans += (char)(findPar(S.charAt(i) - 'a') + 'a');
        }
    
        return ans;
    }
    
   
    int[] par;
    int[] size;
    
    int findPar(int u) {
        return par[u] == -1 ? u : (par[u] = findPar(par[u]));
    }

    public int[] findRedundantConnection(int[][] edges) {
        int N = edges.length + 1;
        par = new int[N];
        Arrays.fill(par, -1);

        for (int[] edge : edges) {
            int p1 = findPar(edge[0]);
            int p2 = findPar(edge[1]);

            if (p1 != p2)
                par[p1] = p2;
            else
                return edge;

        }

        return new int[0];

    }
     //839
    public boolean isSimilar(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i) && ++count > 2)
                return false;
        }

        return true;

    }

    public int numSimilarGroups(String[] strs) {
        int count = strs.length;
        int n = strs.length;

        par = new int[n];
        for (int i = 0; i < n; i++)
            par[i] = i;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    int p1 = findPar(i);
                    int p2 = findPar(j);

                    if (p1 != p2) {
                        par[p1] = p2;
                        count--;
                    }
                }

            }

        }

        return count;
    }
    
    //305
    ArrayList<Integer> numIslands2(int m, int n, int[][] positions)
    {
        par = new int[n * m];
        Arrays.fill(par, -1);
    
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
        int[][] grid = new int[m][n]; 
        int count = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int[] pos : positions)
        {
            int i = pos[0];
            int j = pos[1];
    
            if (grid[i][j] != 1)
            {
    
                grid[i][j] = 1;
                count++;
    
                for (int d = 0; d < 4; d++)
                {
                    int x = i + dir[d][0];
                    int y = j + dir[d][1];
    
                    if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == 1)
                    {
                        int p1 = findPar(i * n + j);
                        int p2 = findPar(x * n + y);
    
                        if (p1 != p2)
                        {
                            count--;
                            par[p1] = p2;
                        }
                    }
                }
            }
    
            ans.add(count);
        }
    
        return ans;
    }
    
    //305
    ArrayList<Integer> numIslands2(int m, int n, int[][] positions)
    {
        par = new int[n * m];
        Arrays.fill(par, -1);
    
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
        int count = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int[] pos : positions)
        {
            int i = pos[0];
            int j = pos[1];
    
            if (par[i * n + j] == -1)
            {
    
                par[i * n + j] = (i * n + j);
                count++;
    
                for (int d = 0; d < 4; d++)
                {
                    int x = i + dir[d][0];
                    int y = j + dir[d][1];
    
                    if (x >= 0 && y >= 0 && x < m && y < n && par[x * n + y] != -1)
                    {
                        int p1 = findPar(i * n + j);
                        int p2 = findPar(x * n + y);
    
                        if (p1 != p2)
                        {
                            count--;
                            par[p1] = p2;
                        }
                    }
                }
            }
    
            ans.add(count);
        }
    
        return ans;
    }
    
    public int minCostToSupplyWater(int N, ArrayList<int[]> Edges) {
        par = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            par[i] = i;
        }
    
        int cost = 0;
        for (int[] edge : Edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            int p1 = findPar(u);
            int p2 = findPar(v);
    
            if (p1 != p2) {
                par[p1] = p2;
                cost += w;
            }
        }
        return cost;
    }
    
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        ArrayList<int[]> PIPES = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            PIPES.add(new int[] { 0, i + 1, wells[i] });
        }
    
        for (int[] p : pipes)
            PIPES.add(p);
    
        Collections.sort(PIPES, (a, b) -> {
            return a[2] - b[2];
        });
    
        return minCostToSupplyWater(n, PIPES);
    }
    //200
    public int numIslands(char[][] grid)
    {
        int n = grid.length;
        int m = grid[0].length;
    
        for (int i = 0; i < n * m; i++)
            par[i] = i;;
    
        int oncesCount = 0;
       int[][] dir = {{1, 0}, {0, 1}};
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (grid[i][j] == '1')
                {
                    oncesCount++;
                    int p1 = findPar(i * m + j);
                    for (int d = 0; d < 2; d++)
                    {
                        int x = i + dir[d][0];
                        int y = j + dir[d][1];
    
                        if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == '1')
                        {
                            int p2 = findPar(x * m + y);
                            if (p1 != p2)
                            {
                                par[p2] = p1;
                                oncesCount--;
                            }
                        }
                    }
                }
            }
        }
    
        return oncesCount;
    }
    
    int maxAreaOfIsland(char[][] grid)
    {
    
        int n = grid.length;
        int m = grid[0].length;

        par = new int[n * m];
        for (int i = 0; i < n * m; i++)
            par[i] = i;
    
        int[] componentSize = new int[(n * m)];

        Arrays.fill(componentSize, 1);
        int maxArea = 0;
    
        int[][] dir = {{1, 0}, {0, 1}};
    
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (grid[i][j] == 1)
                {
                    int p1 = findPar(i * m + j);
                    for (int d = 0; d < 2; d++)
                    {
                        int x = i + dir[d][0];
                        int y = j + dir[d][1];
    
                        if (x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1)
                        {
                            int p2 = findPar(x * m + y);
                            if (p1 != p2)
                            {
                                par[p2] = p1;
                                componentSize[p1] += componentSize[p2];
                            }
                        }
                        maxArea = Math.max(maxArea, componentSize[p1]);
                    }
                }
                else
                    componentSize[i * m + j] = 0;
            }
        }
    
        return maxArea;
    }
    
    public long journeyToMoon(int n, List<List<Integer>> astronaut) {
        // Write your code here
            par = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++){
                par[i] = i;
                size[i] = 1;
            }
        
            for (List<Integer> ast : astronaut){
                int p1 = findPar(ast.get(0));
                int p2 = findPar(ast.get(1));
        
                if (p1 != p2)
                {
                    par[p1] = p2;
                    size[p2] += size[p1];
                }
            }
        
            long sum = 0, totalPairs = 0;
            
            for (int i = 0; i < n; i++){
                if (par[i] == i){
                    totalPairs += sum * size[i];
                    sum += size[i];
                }
            }
        
            return totalPairs;
    
        }
    
    // https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/practice-problems/algorithm/mr-president/
     int mrPresident(int n,int[][] edges, long k)
    {
        for (int i = 0; i <= n; i++)
            par[i] = i;;
    
        ArrayList<Integer> mstEdgeWeight = new ArrayList<>();
        long overallWeightSum = 0;
        for (int[] e : edges)
        {
            int p1 = findPar(e[0]);
            int p2 = findPar(e[1]);
    
            if (p1 != p2)
            {
                par[p1] = p2;
                mstEdgeWeight.add(e[2]);
                overallWeightSum += e[2];
                n--;
            }
        }
    
        if (n > 1)
            return -1;
        if (overallWeightSum <= k)
            return 0;
    
        int transform = 0;
        for (int i = mstEdgeWeight.size() - 1; i >= 0; i--)
        {
            overallWeightSum = overallWeightSum - mstEdgeWeight.get(i) + 1;
            transform++;
    
            if (overallWeightSum <= k)
                break;
        }
    
        return overallWeightSum <= k ? transform : -1;
    }
    
    
    
    //815
    public int numBusesToDestination(int[][] routes, int src, int dest)
    {
    
        if (src == dest)
            return 0;
        int n = routes.length;
        HashMap<Integer, ArrayList<Integer>> busStandMapping = new HashMap<>();
        int busNumber = 0;
        for (int[] busStandList : routes)
        {
            for (int busStand : busStandList)
            {
                if(!busStandMapping.containsKey(busStand))
                    busStandMapping.put(busStand, new ArrayList<>());

                busStandMapping.get(busStand).add(busNumber);
                
            }
            busNumber++;
        }
    
        HashSet<Integer> isBusStandSeen = new HashSet<>();
        boolean[] isBusSeen = new boolean[n];
    
        Queue<Integer> que = new ArrayDeque<>();
        que.add(src);
        isBusStandSeen.add(src);
    
        int level = 0;
        while (que.size() != 0)
        {
            int size = que.size();
            while (size-- > 0)
            {
                int busStand = que.remove();
    
                ArrayList<Integer> allBuses = busStandMapping.get(busStand);
                for (int busNo : allBuses)
                {
                    if (isBusSeen[busNo])
                        continue;
    
                    for (int bs : routes[busNo]) // bs is bus stand
                    {
                        if (!isBusStandSeen.contains(bs))
                        {
                            que.add(bs);
                            isBusStandSeen.add(bs);
    
                            if (bs == dest)
                                return level + 1;
                        }
                    }
    
                    isBusSeen[busNo] = true;
                }
            }
            level++;
        }
    
        return -1;
    }
    
    //743
    public int networkDelayTime(int[][] times, int n, int k) {

        ArrayList<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++)
            graph[i] = new ArrayList<>();
    
        // {u -> {v,w} }
        for (int[] ar : times) {
            graph[ar[0]].add(new int[] { ar[1], ar[2] });
        }
    
        int[] dis = new int[n + 1];
        Arrays.fill(dis, (int) 1e9);
        boolean[] vis = new boolean[n + 1];
    
        // {vtx,wsf}
        PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
    
        que.add(new int[] { k, 0 });
        dis[k] = 0;
        int NoOfEdges = 0;
        int maxvalue = 0;
        while (que.size() != 0) {
            int[] p = que.remove();
            int vtx = p[0], wsf = p[1];
    
            if (vis[vtx])
                continue;
            if (vtx != k)
                NoOfEdges++;
    
            maxvalue = Math.max(maxvalue, wsf);
    
            vis[vtx] = true;
            for (int[] e : graph[vtx]) {
                if (!vis[e[0]] && e[1] + wsf < dis[e[0]]) {
                    dis[e[0]] = e[1] + wsf;
                    que.add(new int[] { e[0], e[1] + wsf });
                }
            }
        }
    
        if (NoOfEdges != n - 1)
            return -1;
    
        return maxvalue;
    
    }
    
    //1192
    public List<List<Integer>> criticalConnections(int N, List<List<Integer>> connections) {
        low = new int[N];
        dis = new int[N];
        vis = new boolean[N];
        List<List<Integer>> list = new ArrayList<>();
        
    
        ArrayList<Integer>[] graph = new ArrayList[N];
        
        for(int i = 0; i < N; i++) graph[i] = new ArrayList<>();
        
        for(List<Integer> con : connections){
            int u = con.get(0);
            int v = con.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }
        
        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                dfs(i, -1, N, graph, list);
                
            }
        }
        return list;
        
    }
     int[] dis, low;
     boolean[] vis;
     int time = 0;
    public void dfs(int src, int par, int N, ArrayList<Integer>[] graph, List<List<Integer>> list) {
        dis[src] = low[src] = time++;
        vis[src] = true;

        for (Integer nbr : graph[src]) {
            if (!vis[nbr]) {

                

                dfs(nbr, src, N, graph,list);

                if (dis[src] < low[nbr]) { // why not this? : low[src] <= low[nbr]
                    
                   List<Integer> l = new ArrayList<>();
                    l.add(src);
                    l.add(nbr);
                    list.add(l);
                }

                low[src] = Math.min(low[src], low[nbr]);

            } else if (nbr != par) {
                low[src] = Math.min(low[src], dis[nbr]); // why not this? : low[src] = Math.min(low[src], low[nbr]);
            }
        }
    }


    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] dis = new int[n];
        Arrays.fill(dis, (int) 1e9);
        dis[src] = 0;
    
        for (int EdgeCount = 1; EdgeCount <= K + 1; EdgeCount++) {
            int[] ndis = new int[n];
            for (int i = 0; i < n; i++)
                ndis[i] = dis[i];
    
            for (int[] e : flights) {
                int u = e[0], v = e[1], w = e[2];
                if (dis[u] != (int) 1e9 && dis[u] + w < ndis[v])
                    ndis[v] = dis[u] + w;
            }
    
            dis = ndis;
        }
    
        return dis[dst] != (int) 1e9 ? dis[dst] : -1;
    }
    // 924
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        size = new int[n];
        par = new int[n];
    
        for (int i = 0; i < n; i++) {
            par[i] = i;
            size[i] = 1;
        }
    
        Arrays.sort(initial);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && graph[i][j] == 1) {
                    int p1 = findPar(i);
                    int p2 = findPar(j);
    
                    if (p1 != p2) {
                        par[p1] = p2;
                        size[p2] += size[p1];
                    }
                }
            }
        }
    
        int[] InfectedNodesInCity = new int[n];
        for (int i : initial) {
            int leader = findPar(i);
            InfectedNodesInCity[leader]++;
        }
    
        int ans = initial[0];
        int maxPopulatedCity = 0;
        for (int i : initial) {
            int NoOfNodesInfected = InfectedNodesInCity[findPar(i)];
            if (NoOfNodesInfected == 1 && size[findPar(i)] > maxPopulatedCity) {
                maxPopulatedCity = size[findPar(i)];
                ans = i;
            }
        }
    
        return ans;
    }

    //959
    public int regionsBySlashes(String[] grid) {
        int n = grid.length + 1;
        
        par = new int[n * n];
        

        for(int i = 0; i < par.length; i++){
            par[i] = i;
            int r = i / n;
            int c = i % n;
            if(r == 0 || r == n - 1 || c == 0 || c == n - 1)
                par[i] = 0;
        }


        // for(int i = 0; i < par.length; i++){
        //   System.out.println(par[i]);
        // }
        int ans = 1;
        for(int i = 0; i < grid.length;i++){
            String str = grid[i];

            for(int j = 0; j < str.length(); j++){
                char ch = str.charAt(j);
                if(ch == '\\'){
                    int p1 = findPar(i * n + j);
                    int p2 = findPar((i + 1) * n + (j + 1));

                    if(p1 == p2) ans++;
                    par[p1] = p2;
                    
                }else if(ch == '/'){
                    int p2 = findPar(i * n + (j + 1));
                    int p1 = findPar((i + 1) * n + (j));

                    if(p1 == p2) ans++;
                    par[p1] = p2;
                }
            }
        }
        return ans;
    }

    //947
    public int removeStones(int[][] stones) {
        int n = stones.length;
       par = new int[n];
       int count = 0;
       for(int i = 0; i < n; i++){
           par[i] = i;
       }

       for(int i = 0; i < n; i++){
           for(int j = i + 1; j < n; j++){
               if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]){
                   int p1 = findPar(i);
                   int p2 = findPar(j);
                   if(p1 != p2){
                       par[p2] = p1;
                       count++;
                   }  
               }
           }
       }
       return count;
   }
}
