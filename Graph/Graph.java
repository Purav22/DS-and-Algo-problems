package Graph;
import java.util.ArrayList;
import java.util.HashSet;

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

    public static int hamiltonialPathAndCycle(int src){
        
        

    }
    public static int hamiltonialAnswer(int src, HashSet<Integer> set, String ans){
        if(set.size() - 1 == graph.length){
            set.add(src);

        }
        int count = 0;
        set.add(src);
        for(Edge edge : graph[src]){
            if(set.contains(edge.v) == false){
                count += hamiltonialAnswer(edge.v, set, ans + src);
            }
        }
        set.remove(src);
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

    }

    public boolean solve130(char[][] board, int i, int j, boolean visited[]){

       
        for(int d = 0; d < 4; d++){
            int nr = i + dirX[d];
            int nc = j + dirY[d];
            if(nr < 0 || nc < 0 || nr >= board.length || nc >= board[0].length)
                return true;
            if(board[nr][nc] == 'O'){
                board[nr][nc] = 'X';
                if(solve130(board, nr, nc, visited)){
                    board[nr][nc] = 'O';
                    return true;
                }
            }
        }
        return false;
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

