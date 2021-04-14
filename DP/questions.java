package DP;

import java.rmi.dgc.Lease;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
    public long numDecodings2_twoPointer(String s){
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
    //120

    public int minimumTotal(List<List<Integer>> triangle) {
        int li = triangle.size();
        int[][] dp = new int[li + 1][li + 1];

        for(int[] d : dp) Arrays.fill(d, -1);

        return minimumTotal_memo(triangle, 0, 0, dp);
    }
    public int minimumTotal_memo(List<List<Integer>> triangle, int idx, int li, int[][] dp) {
        if(li == triangle.size()) {
            return dp[idx][li] = 0;
        }
        if(dp[idx][li] != -1) return dp[idx][li];

        int lans = minimumTotal_memo(triangle, idx, li + 1, dp);
        int rans = minimumTotal_memo(triangle, idx + 1, li + 1, dp);

        return dp[idx][li] = Math.min(lans, rans) + triangle.get(li).get(idx);   
        
    }

   //97
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length();
        int m = s2.length();
        if(n + m != s3.length()) return false;
        int[][] dp = new int[n + 1][m + 1];
        for(int[] d : dp) Arrays.fill(d, -1);
        return isInterleave_memo(s1, s2, s3, 0, 0,0, "", dp);
    }

// 1 == true, 0 == false -1 == default
    public boolean isInterleave_memo(String s1, String s2, String s3, int i, int j, int k, String res, int[][] dp) {
    
        if(i == s1.length() && j == s2.length() && res.equals(s3)){
            //System.out.println(res);
            dp[i][j] = 1;
            return true;
        }if(i == s1.length()){
            if(s2.substring(j).equals(s3.substring(k))){
                dp[i][j] = 1;
                return true;
            }
            return false;
        }
        if(j == s2.length()){
            if(s1.substring(i).equals(s3.substring(k))){
                dp[i][j] = 1;
                return true;
            }
            return false;
        }
    
        if(dp[i][j] != -1) {
            return dp[i][j] == 1;
        }
        boolean ans = false;
        if(s3.charAt(k) == s1.charAt(i))
            ans = ans || isInterleave_memo(s1,s2,s3,i + 1, j, k + 1, res + s1.charAt(i), dp);
        if(s3.charAt(k) == s2.charAt(j))
            ans = ans || isInterleave_memo(s1,s2,s3,i, j + 1, k + 1, res + s2.charAt(j), dp);
        

        dp[i][j] = ans ? 1: 0;
        return ans;
    }
    public boolean isInterleave_DP(String s1, String s2, String s3, int i, int j, int k, String res, int[][] dp) {
    
        if(i == s1.length() && j == s2.length() && res.equals(s3)){
            //System.out.println(res);
            dp[i][j] = 1;
            return true;
        }if(i == s1.length()){
            if(s2.substring(j).equals(s3.substring(k))){
                dp[i][j] = 1;
                return true;
            }
            return false;
        }
        if(j == s2.length()){
            if(s1.substring(i).equals(s3.substring(k))){
                dp[i][j] = 1;
                return true;
            }
            return false;
        }
    
        if(dp[i][j] != -1) {
            return dp[i][j] == 1;
        }
        boolean ans = false;
        if(s3.charAt(k) == s1.charAt(i))
            ans = ans || isInterleave_memo(s1,s2,s3,i + 1, j, k + 1, res + s1.charAt(i), dp);
        if(s3.charAt(k) == s2.charAt(j))
            ans = ans || isInterleave_memo(s1,s2,s3,i, j + 1, k + 1, res + s2.charAt(j), dp);
        

        dp[i][j] = ans ? 1: 0;
        return ans;
    }


    //139
    public static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        
        boolean ans = wordBreak_memo(s, wordDict, 0, n, dp);
        print1D(dp);
        return ans;
    }

    public static boolean wordBreak_memo(String s, List<String> wordDict, int si, int n, int[] dp) {
        if(si == n) {
            dp[si] = 1;
            System.out.println(si);
            return true;
        }

        if(dp[si] != -1) return dp[si] == 1;

        boolean res = false;
        for(int cut = si; cut < n; cut++){
            if(wordDict.contains(s.substring(si, cut + 1))){
                //System.out.println(s.substring(si,cut + 1));
                res = res || wordBreak_memo(s, wordDict, cut + 1, n, dp);
            }
           // System.out.println(1);
        }

        dp[si]= res ? 1 : 0;
        System.out.println(si);
        return res;

    }
    public static boolean wordBreak_DP(String s, List<String> wordDict, int SI, int n, boolean[] dp) {
        for(int si = n; si >= 0 ; si--){
            if(si == n) {
                dp[si] = true;
                continue;
            }
    
           
            for(int cut = si; cut < n; cut++){
                if(wordDict.contains(s.substring(si, cut + 1))){
                    //System.out.println(s.substring(si,cut + 1));
                    dp[si] = dp[si] || dp[cut + 1]; //wordBreak_memo(s, wordDict, cut + 1, n, dp);
                }
              
            }
    
        }
        return dp[SI];

    }

    //140
    public List<String> wordBreak_(String s, List<String> wordDict) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        List<String> list = new ArrayList<>();
        wordBreak_memo(s, wordDict, 0, n, dp, list, "");
        return list;
     }
    
    public static boolean wordBreak_memo(String s, List<String> wordDict, int si, int n, int[] dp, List<String> list, String ans) {
        if(si == n) {
            dp[si] = 1;
            list.add(ans.substring(0, ans.length() - 1));
            return true;
        }

        //if(dp[si] != -1) return dp[si] == 1;

        boolean res = false;
        for(int cut = si; cut < n; cut++){
            if(wordDict.contains(s.substring(si, cut + 1))){
                //System.out.println(s.substring(si,cut + 1));
                wordBreak_memo(s, wordDict, cut + 1, n, dp, list, ans + s.substring(si, cut + 1) + " ");
            }
           // System.out.println(1);
        }

        dp[si]= res ? 1 : 0;
        
        return res;

    }

    //174
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for(int[] d : dp) Arrays.fill(d, -(int)1e9);
        return -calculateMinimumHP_memo(dungeon, 0, 0, n, m, dp) + 1;
        
    }
    public int calculateMinimumHP_memo(int[][] arr, int i, int j,int n, int m, int[][] dp) {
        if(i == n || j == m){
            
            return dp[i][j] = -(int)1e9;
        }
        if(i == n - 1 && j == m - 1)
                return dp[i][j] = arr[i][j] >= 0 ? 0 : arr[i][j];

        if(dp[i][j] != -(int)1e9) return dp[i][j];
        int lans = calculateMinimumHP_memo(arr, i + 1, j, n, m, dp);
        int rans = calculateMinimumHP_memo(arr, i, j + 1, n, m, dp);

        if(lans >= 0){
            dp[i][j] = arr[i][j] >= 0 ? 0 : arr[i][j];
            //System.out.println("1 " + i + " " + j + " " + dp[i][j]);
            return dp[i][j];
        }
        if(rans >= 0){
            dp[i][j] = arr[i][j] >= 0 ? 0 : arr[i][j];
            //System.out.println("2 " + i + " " + j + " " + dp[i][j]);
            return dp[i][j];
        }

        dp[i][j] = Math.max(lans, rans);
        dp[i][j] = arr[i][j] + dp[i][j] >= 0 ? 0 : dp[i][j] + arr[i][j];
        //System.out.println("3 " + i + " " + j + " " + dp[i][j]);
        return dp[i][j];
    }

    

    //279
    public int numSquares(int n) {
        int dp[] = new int[n + 1];
        Arrays.fill(dp, -1);
        return numSquares(n, dp);
    }
    public int numSquares(int n, int dp[]) {
        if(n == 0)
        return dp[n] = 0;

        if(dp[n] != -1) return dp[n];

        int count = (int)1e9;
        for(int i = 1 ; i * i <= n; i++){
            count = Math.min(count, numSquares(n - i * i, dp));
        }
        return dp[n] = count + 1;
    }

    // https://www.geeksforgeeks.org/applications-of-catalan-numbers/

    // leetcode : 1216
    // length of string - longest palindromic subsequnece

    // Leetcode 95

    // leetcode 940
    public int distinctSubseqII(String S) {
        S = '$' + S;
        int n = S.length();
        int[] dp = new int[n];
        int[] lastOcc = new int[26];
        Arrays.fill(lastOcc, -1);

        int mod = (int) 1e9 + 7;

        dp[0] = 1; // for empty String.
        for (int i = 1; i < n; i++) {
            char ch = S.charAt(i);
            int idx = ch - 'a';
            dp[i] = (2 * dp[i - 1]) % mod;
            if (lastOcc[idx] != -1) {
                dp[i] = (dp[i] - dp[lastOcc[idx] - 1] + mod) % mod;
            }

            lastOcc[idx] = i;
        }

        return dp[n - 1] - 1;
    }

    // leetcode 1278
    public int[][] minChanges(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        for (int gap = 1; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 1)
                    dp[i][j] = str.charAt(i) == str.charAt(j) ? 0 : 1;
                else
                    dp[i][j] = str.charAt(i) != str.charAt(j) ? dp[i + 1][j - 1] + 1 : dp[i + 1][j - 1];
            }
        }
        return dp;
    }

    public int palindromePartition_(String str, int k, int si, int[][] dp, int[][] minChange) {
        if (str.length() - si <= k) {
            return dp[si][k] = (str.length() - si == k) ? 0 : (int) 1e9;
        }

        if (k == 1)
            return dp[si][k] = minChange[si][str.length() - 1];
        if (dp[si][k] != -1)
            return dp[si][k];

        int minAns = (int) 1e9;
        for (int i = si; i < str.length() - 1; i++) {
            int minChangesInMySet = minChange[si][i];
            int minChangesInRecSet = palindromePartition_(str, k - 1, i + 1, dp, minChange);
            if (minChangesInRecSet != (int) 1e9)
                minAns = Math.min(minAns, minChangesInRecSet + minChangesInMySet);
        }

        return dp[si][k] = minAns;
    }

    public int palindromePartition(String str, int k) {
        int[][] minChangeDP = minChanges(str);
        int[][] dp = new int[str.length()][k + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);

        return palindromePartition_(str, k, 0, dp, minChangeDP);
    }

    // leetcode 10

    //343
    public int integerBreak(int n) {
        if(n == 2 || n ==3) return n - 1;
        int[] dp = new int[n + 1];
        return integerBreak_memo(n, dp);
    }
    public int integerBreak_memo(int n, int[] dp) {
        if(n == 0) return dp[n] = 1;

        if(dp[n] != 0) return dp[n];
        int mult = 0;
        for(int i = 1; i <= n; i++){
            int ans = integerBreak_memo(n - i, dp);

            mult = Math.max(mult, ans * i);
        }

        return dp[n] = mult;
    }

    //375
    public int calculate(int l, int h,  int[][] dp) {
        if (l >= h)
            return 0;
         
         if(dp[l][h] != -1) return dp[l][h];
        int minres = Integer.MAX_VALUE;
        for (int i = l; i <= h; i++) {
            int res = i + Math.max(calculate(i + 1, h, dp), calculate(l, i - 1, dp));
            minres = Math.min(res, minres);
        }

        return dp[l][h] = minres;
    }
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for(int[] d : dp) Arrays.fill(d, -1);
        return calculate(1, n, dp);
    }

    //376
    // 1 == big, 0 == small
    public int calculate(int[] nums, int index, int takenAs, int[][] dp) {
        if(dp[index][takenAs] != -1) return dp[index][takenAs];
         int maxcount = 0;
        for (int i = index + 1; i < nums.length; i++) {
            if ((takenAs == 1 && nums[i] > nums[index]) || (takenAs == 0 && nums[i] < nums[index])){
                if(takenAs == 1)
                 maxcount = Math.max(maxcount, 1 + calculate(nums, i, 0, dp));
                else
                    maxcount = Math.max(maxcount, 1 + calculate(nums, i, 1, dp));
            }
        }
        return dp[index][takenAs] = maxcount;
    }

    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int n = nums.length;
        int[][] dp = new int[n + 1][2];
        for(int[] d : dp) Arrays.fill(d, -1);
        return 1 + Math.max(calculate(nums, 0, 1, dp), calculate(nums, 0, 0, dp));
    }

    //403
    public boolean canCross(int[] stones) {
        int n = stones.length;
        if(stones[1] != 1)  return false;
        HashMap<Integer, ArrayList<Long>> dp = new HashMap<>();
        for(int num : stones) 
            dp.put(num, new ArrayList<>());
        dp.get(0).add((long) 1);
        return canCross_memo(stones, 1, 1, n, dp) == 1;
       
    }

    //1 == true , 0 == false
    public long canCross_memo(int[] arr, int idx, int k, int n, HashMap<Integer, ArrayList<Long>> dp) {
        if(idx == n - 1){
            dp.get(idx).add(k, (long) 1);
            return 1;
        }
        if(dp.get(idx).contains((long)k)) return dp.get(idx).get(k);
        //System.out.println(idx + " " + k);
        boolean ans = false;
        for(int i = idx + 1; i < n; i++){
            int num = arr[i] - arr[idx];
            
            if(num == k - 1){
                ans = ans || canCross_memo(arr, i, k - 1, n, dp) == 1;
            }
            if(num == k){
                ans = ans || canCross_memo(arr, i, k, n, dp) == 1;
            }
            if(num == k + 1){
                ans =  ans || canCross_memo(arr, i, k + 1, n, dp) == 1;
            }
            if(num > k + 1){
                break;
            }
        }
        dp.get(idx).add(k,ans ? (long)1 :(long) 0);
        return ans ? 1 : 0;
    }

    public boolean canCross(int[] stones) {
        Set<Integer> indexes = new HashSet();
        for(int stone :stones){
            indexes.add(stone);
        }
        
        return solve(indexes, 1, 1, stones[stones.length - 1], new HashMap());
    }
    
    private boolean solve(Set<Integer> indexes, int i, int k, int lastIndex, Map<Integer, Boolean> cache){
        if(i == lastIndex){
            return true;
        }
        if(i >= lastIndex || !indexes.contains(i)){
            return false;
        }
        
        int key = i * lastIndex + k;
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        
        cache.put(key, false);
        for(int steps = Math.max(k - 1, 1); steps <= k + 1; steps++){
            if(solve(indexes, i + steps, steps, lastIndex, cache)){
                cache.put(key, true);
                break;
            }
        }
        return cache.get(key);
    }


    //410

    public int splitArray(int[] nums, int m) {
        int[][] memo = new int[nums.length][m+1];
        
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        return walk(nums, memo, 0, m);
    }
    
    private int walk(int[] nums, int[][] memo, int start, int rem) { 
	   
        if (rem == 0 && start == nums.length) {
            return 0;
        }
        if (rem == 0 || start == nums.length) {
		    
            return Integer.MAX_VALUE;
        }
        
        int n = nums.length;
        int ret = Integer.MAX_VALUE;
        int curSum = 0;
        
        if (memo[start][rem] != -1) {
            return memo[start][rem];
        }
        
		
        for (int i = start; i < n; i++) {
            curSum += nums[i];
            
			
            int futureSum = walk(nums, memo, i + 1, rem - 1);

            ret = Math.min(ret, Math.max(curSum, futureSum));
        }
        
        memo[start][rem] = ret;
        return ret;
    }

    
    //931
    public int minFallingPathSum(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] dp = new int[n][m];
        for(int[] d : dp) Arrays.fill(d, -1000);

        int ans = (int)1e9;
        for(int i = 0; i < m; i++){
            ans = Math.min(ans, minFallingPathSum_memo(arr, dp, 0, i, n, m));
        }
        return ans;
    }

    public int minFallingPathSum_memo(int[][] arr, int[][] dp, int r, int c, int n, int m) {
        if(r == n - 1) return dp[r][c] = arr[r][c];

        int count = (int)1e9;

        if(dp[r][c] != -1000) return dp[r][c];
        if(r + 1 < n && c - 1 >= 0){
            count = Math.min(count, minFallingPathSum_memo(arr, dp, r + 1, c - 1, n, m) + arr[r][c]);
        }if(r + 1 < n && c < m){
            count = Math.min(count, minFallingPathSum_memo(arr, dp, r + 1, c, n, m) + arr[r][c]);
        }
        if(r + 1 < n && c + 1 < m){
            count = Math.min(count, minFallingPathSum_memo(arr, dp, r + 1, c + 1, n, m) + arr[r][c]);
        }

        return dp[r][c] = count;
    }

    //983
    public int mincostTickets(int[] days, int[] costs) {
        int[][] dp = new int[366][366];
        for(int[] d : dp) Arrays.fill(d, -1);
        return mincostTickets_memo(days, costs, 0, 0, 1, dp);
    }
    public int mincostTickets_memo(int[] days, int[] costs, int idx, int sum, int day, int[][] dp) {
        if(idx == days.length || day > days[days.length - 1]){
            return dp[idx][day] = sum;
        }
        if(dp[idx][day] != -1) return dp[idx][day];

        while(day > days[idx]) idx++;

        while(day < days[idx]) day++;

        int first = mincostTickets_memo(days, costs, idx + 1, sum + costs[0], day + 1, dp);
        int second = mincostTickets_memo(days, costs, idx + 1, sum + costs[1], day + 7, dp);
        int last = mincostTickets_memo(days, costs, idx + 1, sum + costs[2], day + 30, dp);

        return dp[idx][day] = Math.min(first, Math.min(second, last));
    }

    //647
    public int countSubstrings(String s) {
        return minChanges(s);
    }
    public int minChanges(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        int count = 0;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                
                if(gap == 0) {
                    count++;
                    dp[i][j] = 1;
                }else if(gap == 1){
                    if((str.charAt(i) == str.charAt(j))){
                        dp[i][j] = 1;
                        count++;
                    }
                }
                else{
                    if(str.charAt(i) == str.charAt(j) && dp[i + 1][j - 1] == 1){
                        dp[i][j] = 1;
                        count++;
                    }
                }
                    
            }
        }
        return count;
    }
    //712
    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for(int[] d : dp) Arrays.fill(d, -1);
        return minimumDeleteSum_memo(s1, s2, 0, 0, dp);
    }
    int calculate(String str,int K){
           int res=0;
            for(int k=K;k<str.length();k++) res+=str.charAt(k);
            return res;
    }
    public int minimumDeleteSum_memo(String s1, String s2, int i, int j, int[][] dp) {
        if(i == s1.length() || j == s2.length()){
            if(i == s1.length()) return dp[i][j] = calculate(s2, j);
            
            return dp[i][j] = calculate(s1, i);
            
        }
        if(dp[i][j] != -1)return dp[i][j];
        
        if(s1.charAt(i) == s2.charAt(j)){
            return dp[i][j] = minimumDeleteSum_memo(s1, s2, i + 1, j + 1, dp);
            
        }else{
           
            int count1 = minimumDeleteSum_memo(s1, s2, i + 1, j, dp) + (s1.charAt(i));
            int count2 = minimumDeleteSum_memo(s1, s2, i, j + 1, dp) + (s2.charAt(j));

            return dp[i][j] = Math.min(count1, count2);
        }
    }

    public int minimumDeleteSum_DP(String s1, String s2, int I, int J, int[][] dp) {
        for(int i = s1.length(); i >= 0; i--){
            for(int j = s2.length(); j >= 0; j--){
                if(i == s1.length() || j == s2.length()){
                    if(i == s1.length()){
                        dp[i][j] = calculate(s2, j);
                        continue;
                    }
                    else{

                        dp[i][j] = calculate(s1, i);
                        continue;
                    }
                    
                }

                if(s1.charAt(i) == s2.charAt(j)){
                    dp[i][j] = dp[i + 1][j + 1]; //minimumDeleteSum_memo(s1, s2, i + 1, j + 1, dp);
                    
                }else{
                   
                    int count1 = dp[i + 1][j] + s1.charAt(i); //(s1, s2, i + 1, j, dp) + (s1.charAt(i));
                    int count2 = dp[i][j + 1] + s2.charAt(j); //minimumDeleteSum_memo(s1, s2, i, j + 1, dp) + (s2.charAt(j));
        
                    dp[i][j] = Math.min(count1, count2);
                }

            }
        }
        
        
        return dp[I][J];
    }

    //1048
    public int longestStrChain(String[] words) {
        int n = words.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
         int ans = 0;
        for(int i = 0; i < words.length; i++) ans = Math.max(ans, longestStrChain_memo(words, i, dp) + 1);
         
         return ans;
    }
    public int longestStrChain_memo(String[] words, int idx, int[] dp) {
        //if(idx == words.length) return dp[idx] = 1;
        if(dp[idx] != -1) return dp[idx];
        int len = 0;
        for(int i = 0; i < words.length; i++){
            if(isPossible(words[idx], words[i])){
                //System.out.println(i);
                len = Math.max(len, longestStrChain_memo(words, i, dp) + 1);
            }
            
        }
        return dp[idx] = len;
    }

    private boolean isPossible(String s1, String s2) {
        if(s1.length() + 1 != s2.length()) return false;
        
        int i = 0, j = 0;
        for(i = 0; i < s1.length() && j < s2.length(); j++){
            if(s1.charAt(i) == s2.charAt(j)) i++;
        }
        return i == s1.length();
     }

    public static void main(String[] args) {
//         int n = 10;
//         int dp[] = new int[n + 1];
// //        System.out.println(mazePath_dp(dp,0,0,n - 1 ,n - 1));
//         // System.out.println(dice_memo(dp, 0, 10));
//         // print1D(dp);
//         // System.out.println(dice_DP(dp,0,10));
//         // print1D(dp);
//         System.out.println(climbStairs_DP(n,  dp));

            List<String> list = new ArrayList<>();
            list.add("cat");
            list.add("cats");
            list.add("and");
            list.add("sand");
            list.add("dog");
            wordBreak("catsanddog", list);
    }
}
