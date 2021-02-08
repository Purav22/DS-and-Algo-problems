
import java.util.Scanner;

public class funds {
    public static void main(String[] args) {
        
    
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        if(n == 1){
           scan.nextInt();
           System.out.println(1);
            return;
        }
        int arr[] = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = scan.nextInt();
        }
        int visited[] = new int[n + 1];
        long sum[][] = new long[n + 1][2];
        int m = scan.nextInt();
        int idx = 0;
        for(int i = 0; i < m; i++){
            int a = scan.nextInt();
            int b = scan.nextInt();
            if(visited[a] == 0 && visited[b] == 0){
                idx++;
                visited[a] = idx;
                visited[b] = idx;
                sum[idx][0] = a == b ? arr[a -1] :arr[a - 1] + arr[b -1];
                sum[idx][1] = a != b ? 2 : 1;
                
            }else if(visited[a] != 0 && visited[b] == 0){
                visited[b] = visited[a];
                sum[visited[a]][0] += arr[b- 1];
                sum[visited[a]][1]++;
            }else if(visited[b] != 0 && visited[a] == 0){
                visited[a] = visited[b];
                sum[visited[b]][0] += arr[a - 1];
                sum[visited[b]][1]++;
            }
        }
        // for(int i = 1; i <= n; i++){
        //     if(visited[i] == 0){
        //        // System.out.println(visited[i] + " " + arr[i - 1]);
        //         sum[i][0] = arr[i - 1];
        //         sum[i][1]++;
        //         visited[i] = i;
        //     }
        // }
        // for(int i = 0; i < visited.length; i++){
        //     System.out.println(visited[i]);
        // }
        long max = Long.MIN_VALUE;
        int count = 0 ;
        int id = 0;
        for(int i = 0; i < sum.length; i++){
            if(max < sum[i][0]){
                max = sum[i][0];
                count = (int)sum[i][1];
                //System.out.println("01");
                id = i;
            }else if(max == sum[i][0]){
               if(count > sum[i][1]){
                   max = sum[i][0];
                   count = (int)sum[i][0];
                   //System.out.println(i);
                   //System.out.println("10");
                   id = i;
               }
            }
        }
        //System.out.println(id);
        for(int i = 0 ; i< arr.length; i++){
            if(max < arr[i]){
                System.out.println(i + 1);
                return;
            }
        }
        for(int i = 0 ; i < visited.length; i++){
            if(visited[i] == id){
                System.out.print(i + " ");
            }
        }
        
    }
}
