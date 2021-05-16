import java.util.ArrayList;
import java.util.Scanner;

public class solution_codeforces {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int T = scan.nextInt();

        while(T-- > 0){
           
            int n = scan.nextInt();
            int k = scan.nextInt();

            int[] arr = new int[n];
            for(int i = 0 ; i < n; i++){
                arr[i] = scan.nextInt();
                
            }

            int i = 0;
            int j = n - 1;

            while(k > 0 && i < j){
                if(arr[i] == 0){
                    i++;
                }else{
                    arr[i]--;
                    arr[j]++;
                    k--;
                }
                
            }

            print1D(arr);
            System.out.println();
                
            
        }
        
    }
    public static void print1D(int arr[]){
        for(int i = 0; i < arr.length; i++){
            if(i == arr.length - 1)
                System.out.print(arr[i]);
            else
                System.out.print(arr[i] + " ");
        }
    }
    // public static boolean checkPerfectSquare(long number)    
    // {   
    
    //     double sqrt=Math.sqrt((double)number);   
       
    //     return ((sqrt - Math.floor(sqrt)) == 0);   
    // }   
   
}
