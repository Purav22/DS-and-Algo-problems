package DP;

import java.util.Arrays;
import java.util.LinkedList;

public class questions {
    public static void print1D(int arr[]){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    public static void print2D(int arr[][]){
        for(int i = 0; i < arr.length; i++){
            print1D(arr[i]);
            System.out.println();
        }
    }

    public static int fibo_memo(int n, int[] dp){
        if(n <= 1){
            return dp[n] = n;
        }
        if(dp[n] != -1) return dp[n];

        int ans = fibo_memo(n - 1, dp) + fibo_memo(n - 2, dp);
        dp[n] = ans;
        return ans;

    }

    public static int fibo_DP(int N, int[] dp){
        
        for(int n = 0; n <= N; n++){
            if(n <= 1){
                dp[n] = n;
                continue;
            }
            
            int ans = dp[n - 1] + dp[n - 2];
            dp[n] = ans;
        }

        return dp[N];

    }

    public static int fibo_twoPointer(int N){
        int a = 0, b = 1;
        for(int i = 0; i < N; i++){
            System.out.print(a + " ");
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;

    }
    

    public static int mazePath_memo(int[][] dp, int sr, int sc, int er, int ec){
        
        if(sr == er && sc == ec){
            return dp[sr][sc] = 1;
        } 
        if(dp[sr][sc] != 0) return dp[sr][sc];
       int ans = 0;
       if(sr + 1 <= er){
           ans += mazePath_memo(dp, sr + 1, sc, er, ec);
       }
       if(sc + 1 <= ec){
            ans += mazePath_memo(dp, sr, sc + 1, er, ec);
       }
       if(sc + 1 <= ec && sr + 1 <= er){
            ans += mazePath_memo(dp, sr + 1, sc + 1, er, ec);
       }
        return dp[sr][sc] = ans;
    }
    public static int mazePath_dp(int[][] dp, int sr, int sc, int er, int ec){
        
        for(sr = er; sr >= 0; sr--){
            for(sc = ec; sc >= 0; sc--){
                if(sr == er && sc == ec){
                    dp[sr][sc] = 1;
                    continue;
                }
                int ans = 0;
                if(sr + 1 <= er){
                    ans += dp[sr + 1][sc]; //mazePath_memo(dp, sr + 1, sc, er, ec);
                }
                if(sc + 1 <= ec){
                        ans += dp[sr][sc + 1]; //mazePath_memo(dp, sr, sc + 1, er, ec);
                }
                if(sc + 1 <= ec && sr + 1 <= er){
                        ans += dp[sr + 1][sc + 1]; //mazePath_memo(dp, sr + 1, sc + 1, er, ec);
                } 
                dp[sr][sc] = ans;
            }

        }
        
        // if(dp[sr][sc] != 0) return dp[sr][sc];
       
        return dp[0][0];
    }
    public static int mazePathInfi_memo(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        for (int jump = 1; sc + jump <= ec; jump++)
            count += mazePathInfi_memo(sr, sc + jump, er, ec, dp);
        for (int jump = 1; sr + jump <= er; jump++)
            count += mazePathInfi_memo(sr + jump, sc, er, ec, dp);
        for (int jump = 1; sc + jump <= ec && sr + jump <= er; jump++)
            count += mazePathInfi_memo(sr + jump, sc + jump, er, ec, dp);

        return dp[sr][sc] = count;
    }

    public static int mazePathInfi_DP(int SR, int SC, int er, int ec, int[][] dp) {

        for (int sr = er; sr >= SR; sr--) {
            for (int sc = ec; sc >= SC; sc--) {

                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int jump = 1; sc + jump <= ec; jump++)
                    count += dp[sr][sc + jump]; // mazePathInfi_memo(sr, sc + jump, er, ec, dp);
                for (int jump = 1; sr + jump <= er; jump++)
                    count += dp[sr + jump][sc]; // mazePathInfi_memo(sr + jump, sc, er, ec, dp);
                for (int jump = 1; sc + jump <= ec && sr + jump <= er; jump++)
                    count += dp[sr + jump][sc + jump]; // mazePathInfi_memo(sr + jump, sc + jump, er, ec, dp);

                dp[sr][sc] = count;
            }
        }

        return dp[SR][SC];
    }

    public static int dice_memo(int dp[], int si, int target){


        if(si == target) {
            return dp[si] = 1;
        }
        if(dp[si] != 0) return dp[si];
        int count = 0;
        for(int i = 1; i <= 6 && si + i <= target; i++){
            count += dice_memo(dp, si + i, target);
        }
        return dp[si] = count;
    }
    public static int dice_DP(int dp[], int si, int target){

        for(si = target; si >= 0; si--){
            if(si == target) {
                dp[si] = 1;
                continue;
            }
            //if(dp[si] != 0) return dp[si];
            int count = 0;
            for(int i = 1; i <= 6 && si + i <= target; i++){
                
                count += dp[si + i]; //dice_memo(dp, si + i, target);
            }
            dp[si] = count;
        }
        return dp[0];
    }

    public static int boardPath_opti(int sp, int ep) {
        LinkedList<Integer> list = new LinkedList<>();

        for (sp = ep; sp >= 0; sp--) {
            if (sp >= ep - 1)
                list.addFirst(1);
            else {
                if (list.size() <= 6)
                    list.addFirst(list.getFirst() * 2);
                else
                    list.addFirst(list.getFirst() * 2 - list.removeLast());
            }
        }

        return list.getFirst();

    }
   
    public static void main(String[] args) {
        int n = 10;
        int dp[] = new int[n + 1];
        Arrays.fill(dp, -1);
//        System.out.println(mazePath_dp(dp,0,0,n - 1 ,n - 1));
        System.out.println(dice_memo(dp, 0, 10));
        print1D(dp);
        System.out.println(dice_DP(dp,0,10));
        print1D(dp);
    }
}
