package Graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
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
    public static void removeEdge(int u,int v){
        for(int i = 0; i < graph[u].size(); i++){
            if(graph[u].get(i).v == v){
                graph[u].remove(i);
                break;
            }
        }
        for(int i = 0; i < graph[v].size(); i++){
            if(graph[v].get(i).v == u){
                graph[v].remove(i);
                break;
            }
        }
    }
    
    public static void removeVtx(int u){
       for(int i = graph[u].size() - 1; i >= 0; i--){
           graph[u].remove(i);
       }
    }
    static boolean visited[] = new boolean[N];
    public static boolean hasPath(int src, int dest){

        if(src == dest)
            return true;
        if(visited[src]) return false;

        boolean ans = false;
        visited[src] = true;
        for(Edge edge: graph[src]){
            ans = ans || hasPath(edge.v, dest);
        }
        

        return ans;

    }

    public static int printAllPath(int src, int dest,String str){
        if(src == dest){
            System.out.println(str);
            return 1;
        }

        int count = 0;
        visited[src] = true;
        for(Edge edge : graph[src]){
            if(!visited[edge.v]){

                count += printAllPath(edge.v, dest, str + edge.v); 
            }
        }
        visited[src] = false;
        return count;
    }

    public static void printHeavyPath(int src, int dest){
       String ans[]= printHeavy(src, dest, 0, src + "");
       System.out.println(ans[1] + " @ " + ans[0]);
       
    }

    public static String[] printHeavy(int src, int dest, int sum, String str){
        if(src == dest){
            String base[] = new String[2];
            
            base[0] = sum + "";
            base[1] = str;
            return base;
        }
        visited[src] = true;
        String ans[] = new String[2];
        
      
        for(Edge edge : graph[src]){
            if(!visited[edge.v]){
                String call[] = printHeavy(edge.v, dest, sum + edge.w, str + edge.v);
                if(ans[0] != null){

                    if(Integer.parseInt(call[0]) > Integer.parseInt(ans[0])){
                        ans[0] = call[0];
                        ans[1] = call[1];
                    }
                }
                else{
                        ans[0] = call[0];
                        ans[1] = call[1];
                }
            }
        }
        visited[src] = false;

        return ans;
    }


    public static void printLowPath(int src, int dest){
        String ans[]= printLow(src, dest, 0, src + "");
       System.out.println(ans[1] + " @ " + ans[0]);
    }

    public static String[] printLow(int src, int dest, int sum, String str){
        if(src == dest){
            String base[] = new String[2];
            
            base[0] = sum + "";
            base[1] = str;
            return base;
        }
        visited[src] = true;
        String ans[] = new String[2];
        
      
        for(Edge edge : graph[src]){
            if(!visited[edge.v]){
                String call[] = printLow(edge.v, dest, sum + edge.w, str + edge.v);
                if(ans[0] != null){

                    if(Integer.parseInt(call[0]) < Integer.parseInt(ans[0])){
                        ans[0] = call[0];
                        ans[1] = call[1];
                    }
                }
                else{
                        ans[0] = call[0];
                        ans[1] = call[1];
                }
            }
        }
        visited[src] = false;

        return ans;
    }
    int findEdge(int u, int v){
        int idx = -1;
        for (int i = 0; i < graph[u].size(); i++)
        {
            if (graph[u].get(i).v == v)
            {
                idx = i;
                break;
            }
        }

        return idx;
    }
    public  void hamiltonialPathAndCycle(int src){
        boolean[] vis = new boolean[N];
        System.out.println(hamintonialPathAndCycle(src, src, 0, vis, ""));
        

    }
    public  int hamintonialPathAndCycle(int src, int osrc, int totalNoEdges, boolean[] vis, String psf)
    {
        if (totalNoEdges == N - 1)
        {
            int idx = findEdge(osrc, src);
            if (idx != -1)
                System.out.println("Cycle : " + (psf + src));
            else
            System.out.println("path : " + (psf + src));
            return 1;   
        }
    
        vis[src] = true;
        int count = 0;
        for (Edge e : graph[src])
        {
            if (!vis[e.v])
                count += hamintonialPathAndCycle(e.v, osrc, totalNoEdges + 1, vis, psf + src + " ");
        }
    
        vis[src] = false;
        return count;
    }


    //200
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == '1'){
                    bfs(i, j,grid);
                    ans++;
                }
            }
        }
        return ans;
    }
    int dirX[] ={-1, 0, 1, 0}; 
    int dirY[] ={0, 1, 0, -1};
    public void bfs(int row, int col,char[][] grid){
        if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == '0'){
            return;
        }
        grid[row][col] = '2';
        for(int i = 0; i < 4; i++){
            int nr = row + dirX[i];
            int nc = col + dirY[i];
            if(nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length){
                continue;
            }
            if(grid[row][col] == '1'){
                bfs(nr, nc, grid);
            }
        }
       
    }

    //695
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    int num = bfs(i, j, grid);
                    if(num > ans)
                        ans = num;
                }
            }
        }
        return ans;
    }
   
    public int bfs(int row, int col, int[][] grid){
        if(row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] != 1){
            return 0;
        }
        grid[row][col] = 2;
        int count = 1;
        for(int i = 0; i < 4; i++){
            int nr = row + dirX[i];
            int nc = col + dirY[i];
            if(nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length){
                continue;
            }
            if(grid[nr][nc] == 1){
                count += bfs(nr, nc, grid);
            }
        }
       return count;
    }



    void dfs(int src, boolean[] vis, ArrayList<Integer> ans){
        vis[src] = true;
        for (Edge e : graph[src]){
            if (!vis[e.v])
                dfs(e.v, vis, ans);
        }

        ans.add(src);
    }

    // get conected components
    public int gcc(){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        boolean[] vis = new boolean[N];
        int components = 0;
        for (int i = 0; i < N; i++){
            if (!vis[i]){
                ArrayList<Integer> ans = new ArrayList<>();
                dfs(i, vis, ans);
                res.add(ans);
                components++;
            }
        }

        return components;
    }

    public void BFS_Cycle(int src, boolean[] vis){
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);

        int dest = 6;
        int atLevel = -1;

        boolean isCycle = false;
        int level = 0;

        while (que.size() != 0){
            int size = que.size();
            System.out.println(level + " -> ");
            while (size-- > 0){
                int rvtx = que.removeFirst();

                System.out.println(rvtx + " ");
                if (vis[rvtx]){
                    isCycle = true;
                    continue;
                }

                if (rvtx == dest){
                    atLevel = level;
                }

                vis[rvtx] = true;
                for (Edge e : graph[rvtx]){
                    if (!vis[e.v])
                        que.addLast(e.v);

                }
            }
            level++;
            System.out.println();
        }

        System.out.println(dest + " present at : " + atLevel);
        System.out.println("isCycle : " + isCycle);
    }

    public void BFS_shortestPath(int src, boolean[] vis)
    {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);
        
        int dest = 6;
        int atLevel = -1;

        int level = 0;
        vis[src] = true;

        while (que.size() != 0){
            int size = que.size();
            while (size-- > 0){
                int rvtx = que.removeFirst();
                for (Edge e : graph[rvtx]){
                    if (!vis[e.v]){
                        vis[e.v] = true;
                        que.addLast(e.v);
                    }

                    if (e.v == dest)
                        atLevel = level + 1;
                }
            }
            level++;
        }

        System.out.println(dest + " present at : " + atLevel);
    }

    void BFS_printshortestPath(int src, boolean[] vis)
    {
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);

        int dest = 6;
        int atLevel = -1;

        int level = 0;
        vis[src] = true;

        int[] par = new int[N];
        Arrays.fill(par, -1);

        while (que.size() != 0)
        {
            int size = que.size();
            while (size-- > 0)
            {
                int rvtx = que.removeFirst();
                for (Edge e : graph[rvtx])
                {
                    if (!vis[e.v])
                    {
                        vis[e.v] = true;
                        que.addLast(e.v);
                        par[e.v] = rvtx;
                    }

                    if (atLevel == -1 && e.v == dest)
                        atLevel = level + 1;
                }
            }
            level++;
        }

        System.out.println(dest + " present at : " + atLevel);
        int idx = dest;
        while (idx != -1)
        {
            System.out.println(idx + " -> ");
            idx = par[idx];
        }
    }

    void BFS_GCC()
    {
        boolean[] vis = new boolean[N];
        int components = 0;
        for (int i = 0; i < N; i++)
        {
            if (!vis[i])
            {
                components++;
                //BFS_Cycle(i, vis);
                BFS_printshortestPath(i,vis);
            }
        }
    
    }

    //463
    public int islandPerimeter(int[][] grid) {
        
        int count = 0, nbr = 0;
        int n = grid.length;
        int m = grid[0].length;
        
   
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                count ++;
                
             for(int d = 0; d < 4; d++){
                 int nr = i + dirX[d];
                 int nc = j + dirY[d];
                 if(nr >= 0 && nc >= 0 && nr < n && nc < m && grid[nr][nc] == 1)
                    nbr++;
             }      
            }
            }
        }

        return count * 4 - nbr;
        
    }
    //130

    public void solve(char[][] board){
        if(board.length == 0 || board[0].length == 0) return;

        int n =board.length;
        int m = board[0].length;
        int dir[][] ={{-1, 0},{1, 0}, {0, 1}, {0, -1}}; 
        for(int i = 0; i < n; i++){
            if(board[i][0] == 'O'){
                surroundedRegionDFS(i, 0, n, m, board, dir);
            }
            if(board[i][m - 1] == 'O'){
                surroundedRegionDFS(i, m - 1, n, m, board, dir);
            }
        }
        for(int j = 0; j < m; j++){
            if(board[0][j] == 'O'){
                surroundedRegionDFS(0, j, n, m, board, dir);
            }
            if(board[n - 1][j] == 'O'){
                surroundedRegionDFS(n - 1, 0, n, m, board, dir);
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                if(board[i][j] == '$'){
                    board[i][j] = 'O';
                }
            }
        }
    }

                    

    public void surroundedRegionDFS(int i, int j, int n, int m, char[][] board, int[][] dir) {

        board[i][j] = '$';

        for(int d = 0; d < dir.length; d++){
            int ni = i + dir[d][0];
            int nj = j + dir[d][1];

            if(ni >= 0 && nj >= 0 && ni < n && nj < m && board[ni][nj] == 'O'){
                surroundedRegionDFS(ni, nj, n, m, board, dir);
            }

        }
    }

    //994
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        
        LinkedList<Integer> q = new LinkedList<>();
        int[][] dir = {{0,1}, {0,-1}, {-1,0}, {1,0}};
        int time = 0, freshOranges = 0;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    freshOranges++;
                }else if(grid[i][j] == 2){
                    q.addLast(i * m + j);
                }
            }
        }
        if(freshOranges == 0) return 0;

        while(q.size() > 0){
            int sz = q.size();

            while(sz-- > 0){
                int idx = q.removeFirst();
                int r = idx / m;
                int c = idx % m;
                

                for(int d = 0; d < 4; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if(x >= 0 && y >= 0 && x < n && y < m && grid[x][y] == 1){
                        freshOranges--;
                        grid[x][y] = 2;
                        q.addLast(x * m + y);
                        if(freshOranges == 0){
                            return time + 1;
                        }
                    }
                }
            }
            time++;
        }
        return -1;

    }

    //286

    public void wallsAndGates(int[][] rooms){
        int n = rooms.length;
        int m = rooms[0].length;
        int[][] dir = {{0,1}, {0,-1}, {-1,0}, {1,0}};
        LinkedList<Integer> q = new LinkedList<>();
        int noOfRooms = 0, distance = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(rooms[i][j] == 0){
                    q.addLast(i * m + j);
                }else if(rooms[i][j] == 2147483647)
                    noOfRooms++;
            }
        }

        while(q.size() > 0){
            int sz = q.size();

            while(sz-- > 0){

                int idx = q.removeFirst();
                int r = idx / m;
                int c = idx % m;

                for(int d = 0; d < 4; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if(x >= 0 && y >= 0 && x < n && y < m && rooms[x][y] == 2147483647){
                        noOfRooms--;
                        rooms[x][y] = distance + 1;
                        q.addLast(x * m + y);

                        if(noOfRooms == 0){
                            return;
                        }
                        
                    }
                }

            }
            distance++;
        }
    }

    //207
    public boolean canFinish(int n, int[][] arr) {
        ArrayList<Edge>[] graph = new ArrayList[n];
        
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int ar[] : arr){
            
            graph[ar[0]].add(new Edge(ar[1],0));
        }
        
        
        return kahnsAlgo(n, graph);
    }
    public boolean kahnsAlgo(int N, ArrayList<Edge>[] graph){

        int indegree[] = new int[N];
        for (int i = 0; i < N; i++)
            for (Edge e : graph[i])
                indegree[e.v]++;

        LinkedList<Integer> que = new LinkedList<>();
        int count = 0;

        for (int i = 0; i < N; i++)
            if (indegree[i] == 0)
                que.addLast(i);

        int level = 0;
        while (que.size() != 0){

            int size = que.size();
            while (size-- > 0)
            {
                int rvtx = que.removeFirst();

                count++;
                for (Edge e : graph[rvtx])
                {
                    if (--indegree[e.v] == 0)
                        que.addLast(e.v);
                }
            }

            level++;
        }

        return count == N;
}

    //1091
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
        
        LinkedList<Integer> que = new LinkedList<>();

        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}};

        grid[0][0] = 1;
        que.addLast(0 * n + 0);

        int level = 1;

        while(que.size() > 0){
            int sz = que.size();

            while(sz-- > 0){
                int idx = que.removeFirst();
                int r = idx / n;
                int c = idx % n;

                if(r == n - 1 && c == n - 1){
                    return level;
                }
                for(int d = 0; d < dir.length; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if(x >= 0 && y >= 0 && x < n && y < n && grid[x][y] == 0){

                        grid[x][y] = 1;
                        que.addLast(x * n + y);
                    }
                }
                

            }
            level++;
        }
        return -1;
    }

    public static void main(String[] args) {
        constructGraph();
       // removeEdge(3,4);
        //System.out.println(hasPath(0,6));
        //System.out.println(printAllPath(0, 6, 0 + ""));
        visited = new boolean[N];
        printHeavyPath(0,6);
        visited = new boolean[N];
        printLowPath(0,6);
    }
}   

