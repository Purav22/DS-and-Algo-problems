package DP;

import java.rmi.dgc.Lease;
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

    public static int climbStairs_memo(int n, int[] dp){
        if(n <= 1){ 
            return dp[n] = 1;
        
        }
        if(dp[n] != -1) return dp[n];
        int ans  = 0;
        ans =  climbStairs_memo(n - 1, dp) + climbStairs_memo(n - 2, dp);



        return dp[n] = ans;
    }

    public static int climbStairs_DP(int N, int[] dp){
        
        for(int n = 0; n <= N; n++){

            if(n <= 1){ 
                dp[n] = 1;
                continue;
            }
            
           dp[n] = dp[n - 1] + dp[n - 2];
        }
        return dp[N];
    }
    public static int minCostClimbingStairs_memo(int[] cost, int ei, int si, int dp[]) {
        if(si > ei) return 0;
         
        if(si == ei){
            return dp[si] = cost[si];
        }
         
        if(dp[si] != 0) return dp[si];

         
        return dp[si] = Math.min(minCostClimbingStairs_memo(cost,ei, si + 1, dp), minCostClimbingStairs_memo(cost,ei, si + 2, dp)) + cost[si];
        
    }

    public static int minCostClimbingStairs_DP(int[] cost, int ei, int si, int dp[]) {

        for(si = ei; si >= 0; si--){
            
            
            if(si == ei){
                dp[si] = cost[si];
                continue;
            }
            
            dp[si] = Math.min(dp[si + 1] , dp[si + 2]) + cost[si];
        }
        return Math.min(dp[0], dp[1]);
    }

    int mod = (int)1e9 + 7;
    public  long countFriendsPairings(int n) 
    { 
        long dp[] = new long[n + 1];
        return countFriendsPairings_memo(n, dp);

    }

    public  long countFriendsPairings_memo(int n, long[] dp){
        if(n <= 1) {
            return dp[n] = 1;
        }
        if(dp[n] != 0) return dp[n];
        long single = countFriendsPairings_memo(n - 1, dp);
        long pair = countFriendsPairings_memo(n - 2, dp) * (n - 1);

        return dp[n] = (single % mod + pair % mod) % mod;
    }
    public  long countFriendsPairings_DP(int N, long[] dp){
       for(int n = 0; n <= N; n++){

           if(n <= 1) {
                dp[n] = 1;
                continue;
           }
           
           long single = dp[n - 1]; //countFriendsPairings_memo(n - 1, dp);
           long pair = dp[n - 2] * (n - 1); //countFriendsPairings_memo(n - 2, dp) * (n - 1);
   
            dp[n] = (single % mod + pair % mod) % mod;
       }
       return dp[N];
    }
    static int maxGold(int n, int m, int M[][])
    {
        // code here
        int[][] dp = new int[n][m];
        for(int i = 0; i < n; i++)
        Arrays.fill(dp[i], -1);
        int[][] dir = {{-1,1}, {0,1}, {1,1}};
        
        return maxGold_DP(0, 0, n, m, M, dp, dir);
       
    }

    public static int maxGold_memo(int r, int c, int n, int m, int M[][], int dp[][], int[][] dir){
        if(c == m - 1){
            return dp[r][c] = M[r][c];
        }
        
        if(dp[r][c] != -1) return dp[r][c];
        int maxGold = 0;
        
        for(int d = 0; d < dir.length; d++){
            int x = r + dir[d][0];
            int y = c + dir[d][1];

            if(x >= 0 && x < n){
                maxGold = Math.max(maxGold, maxGold_memo(x, y, n, m, M, dp, dir));
            }
        }
        return dp[r][c] = maxGold  + M[r][c];
    }
    public static int maxGold_DP(int R, int C, int n, int m, int M[][], int dp[][], int[][] dir){
        
        for(int c = m -1; c >= 0; c--){
            for(int r = n -1; r >= 0; r--){
                if(c == m - 1){
                    dp[r][c] = M[r][c];
                    continue;
                }
                
                
                int maxGold = 0;
                
                for(int d = 0; d < dir.length; d++){
                    int x = r + dir[d][0];
                    int y = c + dir[d][1];

                    if(x >= 0 && x < n){
                        maxGold = Math.max(maxGold, dp[x][y]);
                    }
                }
                dp[r][c] = maxGold  + M[r][c];
            }
        }
        int maxGold = 0;
        for(int i = 0; i < n; i++){
            maxGold = Math.max(maxGold, dp[i][0]);
        }

        return maxGold;
        
    }
    public int numDecodings(String s) {
        int n = s.length();
        int dp[] = new int[n + 1];
        return printEncodings(s,0, dp);
     
    }
    public int printEncodings(String str, int idx, int[] dp) {
        if(idx == str.length()){
            return dp[idx] = 1;
        }
        if(dp[idx] != 0) return dp[idx];
        char ch1 = str.charAt(idx);
        if(ch1 == '0'){
            return dp[idx] = 0;
        }
        
        int count = 0;
        
        count += printEncodings(str, idx + 1, dp);
        
        if(idx + 1 < str.length()){
            char ch2 = str.charAt(idx + 1);
            int num = (ch1 -'0') * 10  + (ch2 -'0');
            
            if(num < 27)
                count += printEncodings(str, idx + 2, dp);
        }
        return dp[idx] = count;

    }
    int numDecodings_DP(String s, int IDX, int[] dp) {
        for (int idx = s.length(); idx >= 0; idx--) {
            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            char ch1 = s.charAt(idx);
            if (ch1 == '0') {
                dp[idx] = 0;
                continue;
            }

            int count = 0;
            count += dp[idx + 1];// numDecodings(s, idx + 1, dp);

            if (idx < s.length() - 1) {
                char ch2 = s.charAt(idx + 1);
                int num = (ch1 - '0') * 10 + (ch2 - '0');
                if (num <= 26)
                    count += dp[idx + 2];// numDecodings(s, idx + 2, dp);
            }
            dp[idx] = count;
        }

        return dp[IDX];
    }

    public int numDecodings_twoPointer(String s){
        int a = 1, b = 0;
        for (int idx = s.length() - 1; idx >= 0; idx--) {

            int count = 0;
            char ch1 = s.charAt(idx);
            if (ch1 != '0') {

                count += a;

                if (idx < s.length() - 1) {
                    char ch2 = s.charAt(idx + 1);
                    int num = (ch1 - '0') * 10 + (ch2 - '0');
                    if (num <= 26)
                        count += b;
                }
            }
            b = a;
            a = count;
        }

        return a;
    }
    public long numDecodings2_memo(String s, int idx, long[] dp) {
        if(idx == s.length()) {
            return dp[idx] = 1;
        }

        if(dp[idx] != -1) return dp[idx];
        if(s.charAt(idx) == '0') return 0;
        long count = 0;
        char ch1 = s.charAt(idx);

        if(s.charAt(idx) == '*'){
            count = (count % mod + 9 * numDecodings2_memo(s, idx + 1, dp) % mod) % mod;
            if(idx + 1< s.length()){

                char ch2 = s.charAt(idx + 1);
                if(ch2 == '*'){
                    count = (count % mod + 15 * numDecodings2_memo(s, idx + 2, dp) % mod) % mod;
                }
                if(ch2 >= '2' && ch2 <= '6'){
                    count = (count % mod + 2 * numDecodings2_memo(s, idx + 2, dp) % mod) % mod;
                }else if(ch2 > '6'){
                    count = (count % mod + numDecodings2_memo(s, idx + 2, dp) % mod) % mod;
                }
               
            }
        }else{
            
            count = (count % mod + numDecodings2_memo(s, idx + 1, dp) % mod) % mod;

            if(idx + 1 < s.length()){
                if(s.charAt(idx + 1) != '*'){

                    char ch2 = s.charAt(idx + 1);
                    int num = (ch1 -'0') * 10  + (ch2 -'0');
                    
                    if(num < 27)
                    count = (count % mod + numDecodings2_memo(s, idx + 2, dp) % mod) % mod;
                }else{
                    if(ch1 == '1')
                        count = (count % mod + 9 * numDecodings2_memo(s, idx + 2, dp) % mod) % mod;

                    if(ch1 == '2')
                    count = (count % mod + 6 * numDecodings2_memo(s, idx + 2, dp) % mod) % mod;
                }
            }
        }
        return dp[idx] = count;
    }
    long numDecodings2_dp(String s, int IDX, long[] dp) {
        for (int idx = s.length(); idx >= 0; idx--) {
            if (idx == s.length()) {
                dp[idx] = 1;
                continue;
            }

            if (s.charAt(idx) == '0') {
                dp[idx] = 0;
                continue;
            }

            long count = 0;
            char ch1 = s.charAt(idx);

            if (s.charAt(idx) == '*') {
                count = (count + 9 * dp[idx + 1]) % mod;
                if (idx < s.length() - 1) {
                    char ch2 = s.charAt(idx + 1);
                    if (ch2 == '*')
                        count = (count + 15 * dp[idx + 2]) % mod;
                    else if (ch2 >= '0' && ch2 <= '6')
                        count = (count + 2 * dp[idx + 2]) % mod;
                    else if (ch2 > '6')
                        count = (count + dp[idx + 2]) % mod;

                }
            } else {
                count = (count + dp[idx + 1]) % mod;
                if (idx < s.length() - 1) {
                    if (s.charAt(idx + 1) != '*') {
                        char ch2 = s.charAt(idx + 1);
                        int num = (ch1 - '0') * 10 + (ch2 - '0');
                        if (num <= 26)
                            count = (count + dp[idx + 2]) % mod;
                    } else {
                        if (s.charAt(idx) == '1')
                            count = (count + 9 * dp[idx + 2]) % mod;
                        else if (s.charAt(idx) == '2')
                            count = (count + 6 * dp[idx + 2]) % mod;
                    }
                }
            }

            dp[idx] = count;
        }

        return (int) dp[IDX];
    }
    public int numDecodings2_twoPointer(String s){
        long a = 1, b = 0;
        for (int idx = s.length() - 1; idx >= 0; idx--) {

            int count = 0;
            char ch1 = s.charAt(idx);
            if(ch1 == '0') count = 0;

            
            else if (ch1 != '0') {
                if(ch1 == '*'){
                        count += 9; 
                    if(idx + 1 < s.length()){
                        char ch2 = s.charAt(idx + 1);
                        if(ch2 == '*'){
                            count+= 15;
                        }else if(ch2 >= 0 && ch2 <= 6){
                            count+= b;
                        }
                    }
                }else{
                    
                }
                count += a;

                if (idx < s.length() - 1) {
                    char ch2 = s.charAt(idx + 1);
                    int num = (ch1 - '0') * 10 + (ch2 - '0');
                    if (num <= 26)
                        count += b;
                }
            }
            b = a;
            a = count;
        }

        return a;
    }

   
    public static void main(String[] args) {
        int n = 10;
        int dp[] = new int[n + 1];
//        System.out.println(mazePath_dp(dp,0,0,n - 1 ,n - 1));
        // System.out.println(dice_memo(dp, 0, 10));
        // print1D(dp);
        // System.out.println(dice_DP(dp,0,10));
        // print1D(dp);
        System.out.println(climbStairs_DP(n,  dp));
    }
}
