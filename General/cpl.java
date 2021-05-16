import java.util.*;
import java.lang.*;
import java.io.*;

public class cpl {
    
    public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner scan = new Scanner(System.in);
		try {
		    int T = scan.nextInt();
		    
		    while(T-- > 0){
		        
		        int n = scan.nextInt();
		        int k = scan.nextInt();
		        
		        int arr[] = new int[n];
		        
		        for(int i = 0; i < n; i++){
		            arr[i] = scan.nextInt();
		        }
		        boolean visited[] = new boolean[n];
                
                
            //     for(int i = 0; i < arr.length; i++){
                    ans = Integer.MAX_VALUE;
                    c1 = Integer.MAX_VALUE;
                    c2 = Integer.MAX_VALUE;
                    solve1(arr, k, visited, 0, 0);
            //         solve2(arr, k, visited, 0, 0);

                    // if(ans > c1 + c2){
                    //     ans = c1 + c2;
                    // }
            //     }
                if(ans == Integer.MAX_VALUE){
                    System.out.println(-1);
                }else{
                    System.out.println(ans);
                }
                
		    }
		} catch(Exception e) {
		}
    }

    static int ans = Integer.MAX_VALUE;
    static int c1 = Integer.MAX_VALUE;
    static int c2 = Integer.MAX_VALUE;

    public static void solve1(int arr[], int k, boolean visited[], int sum, int count) {

        if (sum >= k) {
            
           c1 = count;
            solve2(arr, k, visited, 0, 0);
            return;
        }

        for(int i = 0; i < arr.length; i++){
            if(!visited[i]){
                visited[i] = true;
                count += 1;
                solve1(arr, k, visited, sum + arr[i], count);
                count -= 1;
                visited[i] = false;
            }
        }

    }

    public static void solve2(int arr[], int k, boolean visited[], int sum, int count){

        if(sum >= k){
            c2 = count;
            if(ans > c1 + c2){
                ans = c1 + c2;
                //System.out.println(ans);
            }
            return;
        }

        for(int i = 0; i < arr.length; i++){
            if(!visited[i]){
                visited[i] = true;
                count += 1;
                solve2(arr, k, visited, sum + arr[i],count);
                count -= 1;
                visited[i] = false;
            }
        }

    }
}
