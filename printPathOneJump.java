import java.util.Scanner;

public class printPathOneJump {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int m = scan.nextInt();
        System.out.println(printPath(n , m, 0, 0, ""));

    }

    public static int printPath(int n, int m, int row, int col, String ans){

        if(row == n - 1 && col == m - 1){
            System.out.println(ans);
            return 1;
            
            
        }

        if(row == n || col == m){
            return 0;
        }

        int count = 0;
        count += printPath(n, m, row, col + 1, ans + "h");
        count += printPath(n, m, row + 1, col, ans + "v");
        count += printPath(n, m, row + 1, col + 1, ans + "d");

        return count;

    }
    
}
