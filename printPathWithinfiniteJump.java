import java.util.Scanner;

public class printPathWithinfiniteJump {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        scan.close();
        boolean visited[][] = new boolean[n][m];
        //visited[0][0] = true;
        System.out.println(printPath(n, m, 0, 0, "", visited));

    }

    public static int printPath(int n, int m, int row, int col, String ans){
        if(row == n - 1 && col == m - 1){
            System.out.println(ans);
            return 1;
        }
        if(row >= n || col >= m){
            return 0;
        }
        
        int count = 0;

        for(int j = 1; j < m; j++){
            count += printPath(n, m, row, col + j, ans + "h" + j);
        }

        for(int j = 1; j < n; j++){
            count += printPath(n, m, row + j, col, ans + "v" + j);
        }

    for(int i = 1, j = 1; i < n  && j < m; i++, j++){
        count += printPath(n, m, row + i, col + j, ans + "d" + i);
    }

        return count;

    }
    static int[] diri = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dirj = {0, 1, 1, 1, 0, -1, -1, -1};
    static String[] ch = {"U", "E", "R","S", "D", "W", "L", "N"};

    public static int printPath(int n, int m, int row, int col, String ans, boolean[][] visited){
        if(row == n - 1 && col == m - 1){
            System.out.println(ans);
            return 1;
        }

        visited[row][col] = true;
        int count  = 0;
        for(int i = 0; i < diri.length; i++){
            if(row + diri[i] >= 0 && col + dirj[i] >= 0 &&  row + diri[i] < n && col + dirj[i] < m && visited[row + diri[i]][col + dirj[i]] == false   ){
                //visited[row + diri[i]][col + dirj[i]] = true;
                count += printPath(n, m, row + diri[i], col + dirj[i], ans + ch[i], visited);
                //visited[row + diri[i]][col + dirj[i]] = false;
            }
        }
        visited[row][col] = false;
        return count;

    }
    
}
