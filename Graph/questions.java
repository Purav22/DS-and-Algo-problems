import java.util.ArrayList;

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
     
    int[] findOrder(int N, int[][] arr){
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
    
        LinkedList<Integer> que = new LikedList<>();
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
    
    
    
    public String smallestEquivalentString(String A, String B, String S)
    {
        for (int i = 0; i < 26; i++){

        }
            // par.push_back(i);
        // par.resize(26,-1);
    
        for (int i = 0; i < A.length(); i++)
        {
            int p1 = findPar(A.charAt(i) - 'a');
            int p2 = findPar(B.charAt(i) - 'a');
    
            par[p1] = min(p1, p2);
            par[p2] = min(p1, p2);
    
            //     if(p1 < p2)
            //         par[p2] = p1;
            //     else if(p2 < p1)par[p1] = p2;
            //
        }
    
        String ans = "";
        for (int i = 0; i < S.length(); i++)
        {
            ans += (char)(findPar(S.charAt(i) - 'a') + 'a');
        }
    
        return ans;
    }
    
    //839
    int[] par;
    
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
    vector<int> numIslands2(int m, int n, vector<vector<int>> &positions)
    {
        par.resize(m * n, -1);
    
        vector<vector<int>> dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
        vector<vector<int>> grid(m, vector<int>(n, 0));
        int count = 0;
        vector<int> ans;
        for (vector<int> &pos : positions)
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
    
            ans.push_back(count);
        }
    
        return ans;
    }
    
    //305
    vector<int> numIslands2(int m, int n, vector<vector<int>> &positions)
    {
        par.resize(m * n, -1);
    
        vector<vector<int>> dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
        int count = 0;
        vector<int> ans;
        for (vector<int> &pos : positions)
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
    
            ans.push_back(count);
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
    
    int numIslands(vector<vector<char>> &grid)
    {
        int n = grid.size();
        int m = grid[0].size();
    
        for (int i = 0; i < n * m; i++)
            par.push_back(i);
    
        int oncesCount = 0;
        vector<vector<int>> dir{{1, 0}, {0, 1}};
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
    
    int maxAreaOfIsland(vector<vector<int>> &grid)
    {
    
        int n = grid.size();
        int m = grid[0].size();
    
        for (int i = 0; i < n * m; i++)
            par.push_back(i);
    
        vector<int> componentSize(n * m, 1);
        int maxArea = 0;
    
        vector<vector<int>> dir{{1, 0}, {0, 1}};
    
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
                        maxArea = max(maxArea, componentSize[p1]);
                    }
                }
                else
                    componentSize[i * m + j] = 0;
            }
        }
    
        return maxArea;
    }
    
    long journeyToMoon(int n, vector<vector<int>> astronaut)
    {
        for (int i = 0; i < n; i++)
        {
            par.push_back(i);
            size.push_back(1);
        }
    
        for (vector<int> &ast : astronaut)
        {
            int p1 = findPar(ast[0]);
            int p2 = findPar(ast[1]);
    
            if (p1 != p2)
            {
                par[p1] = p2;
                size[p2] += size[p1];
            }
        }
    
        long sum = 0, totalPairs = 0;
        for (int i = 0; i < n; i++)
        {
            if (par[i] == i)
            {
                totalPairs += sum * size[i];
                sum += size[i];
            }
        }
    
        return totalPairs;
    }
    
    // https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/practice-problems/algorithm/mr-president/
    int mrPresident(int n, vector<vector<int>> &edges, long k)
    {
        for (int i = 0; i <= n; i++)
            par.push_back(i);
    
        vector<int> mstEdgeWeight;
        long overallWeightSum = 0;
        for (vector<int> &e : edges)
        {
            int p1 = findPar(e[0]);
            int p2 = findPar(e[1]);
    
            if (p1 != p2)
            {
                par[p1] = p2;
                mstEdgeWeight.push_back(e[2]);
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
            overallWeightSum = overallWeightSum - mstEdgeWeight[i] + 1;
            transform++;
    
            if (overallWeightSum <= k)
                break;
        }
    
        return overallWeightSum <= k ? transform : -1;
    }
    
    void mrPresident()
    {
        ios_base::sync_with_stdio(false);
        long n, m, k;
        cin >> n >> m >> k;
    
        vector<vector<int>> edges;
        for (int i = 0; i < m; i++)
        {
            int u, v, w;
            cin >> u >> v >> w;
            edges.push_back({u, v, w});
        }
    
        sort(edges.begin(), edges.end(), [](vector<int> &a, vector<int> &b) {
            return a[2] < b[2];
        });
    
        cout << mrPresident(n, edges, k) << endl;
    }
    
    //815
    int numBusesToDestination(vector<vector<int>> &routes, int src, int dest)
    {
    
        if (src == dest)
            return 0;
        int n = routes.size();
        unordered_map<int, vector<int>> busStandMapping;
        int busNumber = 0;
        for (vector<int> &busStandList : routes)
        {
            for (int busStand : busStandList)
            {
                busStandMapping[busStand].push_back(busNumber);
            }
            busNumber++;
        }
    
        unordered_set<int> isBusStandSeen;
        vector<bool> isBusSeen(n, false);
    
        queue<int> que;
        que.push(src);
        isBusStandSeen.insert(src);
    
        int level = 0;
        while (que.size() != 0)
        {
            int size = que.size();
            while (size-- > 0)
            {
                int busStand = que.front();
                que.pop();
    
                vector<int> allBuses = busStandMapping[busStand];
                for (int busNo : allBuses)
                {
                    if (isBusSeen[busNo])
                        continue;
    
                    for (int bs : routes[busNo]) // bs is bus stand
                    {
                        if (isBusStandSeen.find(bs) == isBusStandSeen.end())
                        {
                            que.push(bs);
                            isBusStandSeen.insert(bs);
    
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
    vector<int> dis, low;
    vector<bool> vis;
    int time = 0;
    vector<vector<int>> res;
    
    void dfs(int src, int par, int n, vector<vector<int>> &graph)
    {
        dis[src] = low[src] = time++;
        vis[src] = true;
    
        for (int nbr : graph[src])
        {
            if (!vis[nbr])
            {
                dfs(nbr, src, n, graph);
    
                if (dis[src] < low[nbr])
                    res.push_back({src, nbr});
    
                low[src] = min(low[src], low[nbr]);
            }
            else if (nbr != par)
                low[src] = min(dis[nbr], low[src]);
        }
    }
    
    vector<vector<int>> criticalConnections(int n, vector<vector<int>> &connections)
    {
        vector<vector<int>> graph(n);
        for (vector<int> &ar : connections)
        {
            graph[ar[0]].push_back(ar[1]);
            graph[ar[1]].push_back(ar[0]);
        }
    
        dis.resize(n, 0);
        low.resize(n, 0);
        vis.resize(n, false);
    
        dfs(0, -1, n, graph);
        return res;
    }
    class Edge
    {
    public:
        int v, w;
        Edge(int v, int w)
        {
            this->v = v;
            this->w = w;
        }
    };
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
}
