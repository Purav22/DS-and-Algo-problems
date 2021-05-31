package General;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;



public class codechef {
    public static void print1D(long[] arr) {
        for (long ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }
    public static void print2D(long[][] arr) {
        for (long[] ar : arr) {
            print1D(ar);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main (String[] args) throws java.lang.Exception {
		// your code goes here
		
		
		try {
		    int T = Integer.parseInt(br.readLine());

            while(T-- > 0){
                String input[] = br.readLine().split(" ");
                int n = Integer.parseInt(input[0]);
                int m = Integer.parseInt(input[1]);
                solveModularEquation(n, m);
            }
		} catch(Exception e) {
		}
	}

    public static void solveModularEquation(int n, int m) {


        for(int a = 1; a <= n; a++){
            for(int b = a + 1; b <= n; b++){
                int num1 = ((m % a) % b);
                int num2 = ((m % b) % a);
                // System.out.println((m % a) + "  " + (m % b));
                
                if(num1 != num2){
                    System.out.println(a + " " + b + " " + num1 + " " + num2 + " --->");
                }
                    // System.out.println((num1 == num2));
            }
        }
        System.out.println(n);

    }
    public static long solveXorEquality(int n){
        int x = 2;
        int mod = (int)1e9 + 7;
        long res = 1;
        x = x % mod;

        if (x == 0)
            return 0;
        
        while (n > 0)
        {
        
            if ((n & 1) != 0)
            res = (res * x) % mod;
        
            n = n >> 1; 
            x = (x * x) % mod;
        }
        return res;
    } 

    static int power(int x, int y, int p){
    int res = 1; // Initialize result
 
    x = x % p; // Update x if it is more than or
    // equal to p
 
     // In case x is divisible by p;
 
    while (y > 0)
    {
 
      // If y is odd, multiply x with result
      if ((y & 1) != 0)
        res = (res * x) % p;
 
      // y must be even now
      y = y >> 1; // y = y/2
      x = (x * x) % p;
    }
    return res;
  }
    // public static void main(String[] args) {
    //     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //     //Scanner scan = new Scanner(System.in);
    //     try {
    //         StringBuilder sb = new StringBuilder();
    //         int T = Integer.parseInt(br.readLine());

    //         while(T-- > 0){
    //             String s1[] = br.readLine().split(" ");
    //             long n = Long.parseLong(s1[0]);
    //             long m = Long.parseLong(s1[1]);
    //             long k = Long.parseLong(s1[2]);
                
    //             double[][] arr = new double[(int)n + 1][(int)m + 1];
                
                
    //             for(int i = 1; i <= n; i++){
    //                 String s[] = br.readLine().split(" ");
    //                 for(int j = 1; j <= m; j++){
    //                         arr[i][j] = Long.parseLong(s[j - 1]);
    //                 }
    //             }
                
    //             for(int i = 0; i <= n; i++){
    //                 double prev = 0;
    //                 for(int j = 0; j <= m; j++){
    //                     arr[i][j] += prev;
    //                     prev = arr[i][j];
    //                 }
    //             }
    //             for(int j = 0; j <= m; j++){
    //                 double prev = 0;
    //                 for(int i = 0; j <= n; i++){
    //                     arr[i][j] += prev;
    //                     prev = arr[i][j];
    //                 }
    //             }

    //             long zz = Math.min(m, n);
    //             long ans = 0;
                
    //             for(int len = 1; len <= zz; len++){
    //                 for(int i = len; i <= n; i++){
    //                     for(int j = len; j <= m; j++){
                            
    //                         if((arr[i][j] + arr[i - len][j - len] - arr[i][j - len] - arr[i - len][j]) / (len * len) >= k)
    //                             ans++;

    //                     }
    //                 }
                    
    //                }
    //            sb.append(ans + "\n");
               
    //         }
    //         System.out.print(sb);
    //     } catch (Exception e) {

    //     }
	// }
	
	//   public static BigInteger worthyMetrix(int n, int m, int k, int[][] arr){
    //     //int ans = 0;
    //     BigInteger total = new BigInteger("0");
    //     int[][] dp = new int[n][m];
    //     for(int i = n - 1; i >= 0; i--){
    //         for(int j = m - 1; j >= 0; j--){
    //             if(j == m - 1 || i == n - 1){
    //                 dp[i][j] = arr[i][j] >= k ? 1 : 0;
    //             }
    //             else{
    //                 if(arr[i][j] >= k){
    //                     dp[i][j] = dp[i + 1][j + 1] + 1;
    //                 }else{
    //                     if(dp[i + 1][j + 1] == 0){
    //                         dp[i][j] = 0;
    //                     }else
    //                     dp[i][j] = eveluate(i, j, n, m,  arr, dp, k);
    //                 }
    //             }

    //             total = total.add(BigInteger.valueOf(dp[i][j]));
               
    //         }
    //     }
    //     print2D(dp);
    //     //System.out.println(total);
    //     return total;

    // }

    private static long eveluate(int i, int j,int ei, int ej, long[][] arr, long k, long[][] dp) {
        long res = arr[ei][ej];
        if(i > 0)
            res = res - arr[i - 1][ej];
        if(j  > 0)
            res  = res - arr[ei][j - 1];
        
        if(i > 0 && j > 0){
            res = res + arr[i - 1][j - 1];
        }

        long ele = ei - i + 1;
        ele = ele * ele;
       
        if(res / ele >= k) {
            //System.out.println(1);
            return dp[ei][ej];

        }
        //System.out.println(2);
        return -1;
    }

    public static boolean calculateAVG(int si, int sj, int ei, int ej, int[][] arr, int k){
        int num = 0;
        int sum = 0;
        for(int i = si; i <= ei; i++){
            for(int j = sj; j <= ej; j++){
                num++;
                sum += arr[i][j];
            }
        }
        //System.out.println(si + " " + sj + " " + ei + " " + ej);

        float ans = sum / num;
        return ans >= k;
    }

    public void temp(){
         // Scanner scan = new Scanner(System.in);
        // // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // try {
        //     int T = scan.nextInt();
        //     while(T-- > 0){
        //         int n = scan.nextInt();
        //         int m = scan.nextInt();

        //         int[] foot = new int[n];
        //         int[] cric = new int[m];

        //         for(int i = 0; i < n; i++) foot[i] = scan.nextInt();
        //         for(int i = 0; i < m; i++) cric[i] = scan.nextInt();

        //         int count = 0;
        //         if(foot[0] > cric[0]){
        //             count++;
        //             int i = 1;
        //             int j = 0;
                    
        //             char ch = 'j';
        //             while(true){
        //                 if(foot[i] > cric[j]){
        //                     if(ch == 'i') count++;
        //                     ch = 'j';
        //                     j++;
        //                     if(j == m){
        //                         if( i < n) count++;
        //                         break;
        //                     }
        //                 }else{
        //                     if(ch == 'j') count++;
        //                     ch = 'i';
        //                     i++;
        //                     if(i == n){
        //                         if(j < m) count++;
        //                         break;
        //                     }
        //                 }
                        
                        
        //             }
        //         }else{
        //             int i = 1;
        //             int j = 0;
        //             char ch = 'i';
        //             while(true){
        //                 if(foot[i] > cric[j]){
        //                     if(ch == 'i') count++;
        //                     ch = 'j';
        //                     j++;
        //                     ch = 'j';
        //                     if(j == m){
        //                         if( i < n) count++;
        //                         break;
        //                     }
        //                 }else{
        //                     if(ch == 'j') count++;
        //                     ch = 'i';
        //                     i++;
        //                     if(i == n){
        //                         if(j < m) count++;
        //                         break;
        //                     }
        //                 }
                        
                        
        //             }

        //         }
        //             System.out.println(count);
        //     }
        // } catch (Exception e) {

        // }
    }
    // private static int giveSqr(int i) {
    //     int flag = 0;
    //     // int num = 1;
    //     // while(flag == 0){
    //     //     System.out.println(giveSqr(num++));
    //     //     System.out.flush();

    //     //     flag = scan.nextInt();
            
    //     // }
    //     // System.out.flush();
    //     return i * i;
    // }
       
    public static void clgLife4(String[] str){
        int n = Integer.parseInt(str[0]);
        int e = Integer.parseInt(str[1]);
        int h = Integer.parseInt(str[2]);
        int A = Integer.parseInt(str[3]);
        int B = Integer.parseInt(str[4]);
        int C = Integer.parseInt(str[5]);
        
        int ans = clgLife4_(n, e, h, A, B, C);
        if(ans == (int)1e9) System.out.println(-1);
        else System.out.println(ans);
        
    }
    public void chefdice(){
        //Scanner scan = new Scanner(System.in);
		// try {
		//     int T = scan.nextInt();
		//     int dp[] = new int[5];
		//     dp[1] = 20;
		//     dp[2] = 36;
		//     dp[3] = 51;
		//     dp[4] = 60;
		//     while(T-- > 0){
		//         int N = scan.nextInt();
		//         if(N <= 4){
		//             System.out.println(dp[N]);
		//         }else{
        //             BigInteger b1 = new BigInteger("44");
                    
        //             b1 = b1.multiply(BigInteger.valueOf(N / 4));
		//             //long twoSideVisible = 44 * (N / 4);
		//             int lastFloorDice = N % 4;
        //             b1 = b1.add(BigInteger.valueOf((4 - lastFloorDice) * 4));
        //             b1.add(BigInteger.valueOf(dp[lastFloorDice]));
                   
		//             System.out.println(b1);
		//         }
		//     }
		// } catch(Exception e) {
		// }
    }

    public static int clgLife4_(int n, int e, int h, int A, int B, int C){
        if(n <= 0){
            return 0;
        }


        int ans = (int)1e9;
        // omlet
        if(n * 2 <= e){
            ans = Math.min(ans, n * A);
        }
        //milkshake
        if(n * 3 <= h){
            ans = Math.min(ans, n * B);
        }
        //cake  
        if(e >= n && h >= n){
            ans = Math.min(ans, n * C);
        }
        //omlet && cake
        if(e - n >= 1 && e - n >= n - h){
            if(A - C < 0){
                int temp = Math.min(n - 1, e - n);
                ans = Math.min(ans, (A - C) * temp + (n * C));
            }
            else{
                int temp = Math.max(1, n - h);
                ans = Math.min(ans, (A - C) * temp +(n * C));
            }
        }

        //milkshake & cake
        if((h - n) / 2 >= 1 && ((h - n) / 2 >= n - e)){
            if(B - C < 0){
                int temp = Math.min(n -1, (h - n) / 2);
                ans = Math.min(ans, (B - C) * temp + (n * C));
            }else{
                int temp = Math.max(1, n - e);
                ans = Math.min(ans, (B - C) * temp + (n * C));
            }
        }
        // milkshake & omlet

        if((e / 2 >= 1) && (e / 2 >= (3 * n - h + 2) / 3)){
            if(A - B < 0){
                int temp = Math.min(e / 2, n - 1);
                ans = Math.min(ans, (A - B) * temp + (n * B));
            }
            else{
                int temp = Math.max(1, (3 * n - h + 2) / 3);
                ans = Math.min(ans, (A - B) * temp + (n * B));
            }
        }

        //all item

        if(e >= 3 && h >= 4 && n >= 3){
            ans = Math.min(ans, A + B + C + clgLife4_(n - 3, e - 3, h - 4, A, B, C));
        }

        return ans;
    }
    
    public static String spaceArray(int[] arr){

        Arrays.sort(arr);
        
        int num = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == i + 1){
                continue;
            }
            else if(i + 1 > arr[i]){
                num += (i + 1) - arr[i];
            }else if(arr[i] > i + 1){
                return "First";
            }
        }
        if(num % 2 == 0) return "Second";

        return "First";
    }
}
