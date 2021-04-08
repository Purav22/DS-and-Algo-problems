import java.io.*;
import java.util.*;



public class TestClass {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine().trim());
        for(int t_i = 0; t_i < T; t_i++)
        {
            int N = Integer.parseInt(br.readLine().trim());
            String[] arr_V = br.readLine().split(" ");
            int[] V = new int[N];
            for(int i_V = 0; i_V < arr_V.length; i_V++)
            {
            	V[i_V] = Integer.parseInt(arr_V[i_V]);
            }

            int out_ = minimumChanges(N, V);
            System.out.println(out_);
            
         }

         wr.close();
         br.close();
    }
    static int minimumChanges(int N, int[] arr){
       // Write your code here
       int ans = 0;
       int max = arr[0];
       for(int i = 1; i < N; i++){
           if(arr[i] <= max) {
               ans++;
           }
           else if(arr[i] > max) max = arr[i];
       }
       return ans;
    
    }






















    static long strange_sum(int L, int R){
        // Write your code here
         long result = 0;
         
         
         dp = new long[R + 1];
         for(int i = L; i <= R; i++){
             ArrayList<Integer> arr = giveAllDiv(i);
             for(int num : arr){
 
                 result = ((result % mod) + (function(num) % mod)) % mod;
             }
         }
         return result;
     
     }
     public static ArrayList<Integer> giveAllDiv(int n){
         ArrayList<Integer> ans = new ArrayList<>();
 
         for(int i = 1; i * i < n; i++){
             if(n % i == 0) ans.add(i);
         }
         for(int i = (int)Math.sqrt(n); i >= 1; i--){
             if(n % i == 0)
                 ans.add(n / i);
         }
         return ans;
     }
 
     public static long function(int n){
         if(dp[n] != 0) return dp[n];
 
         if(n == 1) return dp[n] = 0;
 
         if(pr[n]) return dp[n] = 1;
 
         else {
             
             return dp[n] = fun(n);
         } 
     }
     public static long fun(int n){
         for(int i = 2; i <= n; i++){
             if(n % i == 0){
                 int num = n / i;
                 long ans = (num * function(i)) % mod + (i * function(num)) % mod;
                 return ans;
             }
         }
         return -1;
     }
 
     public static void prime(){
         Arrays.fill(pr, true);
         for(int i = 2; i * i < pr.length; i++){
             if(pr[i])
                 for(int p = i * 2; p < pr.length; p+= i){
                     pr[p] = false;
                 }
         }
     }










     public static void prime(){
        Arrays.fill(pr, true);
        for(int i = 2; i * i < pr.length; i++){
            if(pr[i])
                for(int p = i * 2; p < pr.length; p+= i){
                    pr[p] = false;
                }
        }
    }

    public static void fillDP(){
        dp[1] = 0;
        for(int i = 2; i < dp.length; i++){
            if(pr[i]) 
                dp[i] = 1;
            else{
                for(int j = 1; j < i; j++){
                    if(i % j == 0) dp[i] = (dp[i] % mod) + (dp[j] % mod);

                    
                }
                int arr[] = giveDiv(i);
                    long num = ((arr[0] * dp[arr[1]] % mod) % mod) + ((arr[1] * dp[arr[0]] % mod) % mod);
                    dp[i] = ((dp[i] % mod) + (num % mod) % mod);
            }

        }
    }
    public static int[] giveDiv(int n){
        int ans[] = new int[2];
        for(int i = 2; i <= n; i++){
            if(n % i == 0){
                ans[0] = n / i;
                ans[1] = i;
                break;
            }
        }
        return ans;
    }
}
    
