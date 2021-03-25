package DP;

import java.util.Arrays;

public class StringSet {

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int dp[][] = new int[n][n];

        for(int[] d : dp){
            Arrays.fill(d, -1);
        }

        return longestPalindromeSubseq(s, 0, n - 1, dp);
        
    }

    public int longestPalindromeSubseq(String s,int i, int j, int[][] dp) {
        if(i >= j){
            return dp[i][j] = (i == j) ? 1 : 0;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        if(s.charAt(i) == s.charAt(j)){
            return dp[i][j] = longestPalindromeSubseq(s, i + 1, j - 1, dp);
        }

        return dp[i][j] = Math.max(longestPalindromeSubseq(s, i + 1, j, dp), longestPalindromeSubseq(s, i, j - 1, dp));
    
    }

    public int longestPalindromeSubseq_dp(String s,int I, int J, int[][] dp) {
        int n = s.length();
        for(int gap = 0; gap < n; gap++){
            for(int i = 0, j = gap; j < n ; j++, i++){

                if(i >= j){
                     
                    dp[i][j] = (i == j) ? 1 : 0;
                    continue;
                }
        
                
        
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i + 1][j - 1]; //longestPalindromeSubseq(s, i + 1, j - 1, dp);
                }
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]); //longestPalindromeSubseq(s, i + 1, j, dp), longestPalindromeSubseq(s, i, j - 1, dp));
            }
        }
        return dp[I][J];
    
    }




    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for(int[] d : dp)
            Arrays.fill(d, -1);
        return numDistinct_memo(s, t, n, m, dp);
        
    }

    public int numDistinct_memo(String s, String t, int n, int m, int[][] dp){
        if(m == 0){
            return dp[n][m] = 1;
        }

        if(n < m){
            return dp[n][m] = 0;
        }

        if(dp[n][m] != -1) return dp[n][m];

        if(s.charAt(n) == t.charAt(m)){
            dp[n][m] = numDistinct_memo(s, t, n - 1, m - 1, dp) + numDistinct_memo(s, t, n - 1, m, dp);
        }else{
            dp[n][m] = numDistinct_memo(s, t, n - 1, m, dp);
        }

        return dp[n][m];
    }
    public int numDistinct_DP(String s, String t, int N, int M, int[][] dp){
        
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(m == 0){
                    dp[n][m] = 1;
                    continue;
                }
        
                if(n < m){
                    dp[n][m] = 0;
                    continue;
                }
        
                //if(dp[n][m] != -1) return dp[n][m];
        
                if(s.charAt(n - 1) == t.charAt(m -1)){
                    dp[n][m] = dp[n - 1][m - 1] + dp[n - 1][m]; //numDistinct_memo(s, t, n - 1, m - 1, dp) + numDistinct_memo(s, t, n - 1, m, dp);
                }else{
                    dp[n][m] = dp[n - 1][m]; //numDistinct_memo(s, t, n - 1, m, dp);
                }
        
            }
        }
        return dp[N][M];
    }

    //1143
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n + 1][m + 1];
        for(int[] d : dp){
            Arrays.fill(d,-1);
        }

        return longestCommonSubsequence_memo(text1, text2, n, m, dp);
    }

    public int longestCommonSubsequence_memo(String text1, String text2, int n, int m, int[][] dp) {
        
        if(n == 0 || m == 0){
            return dp[n][m] = 0;
        }

        if(dp[n][m] != -1){
            return dp[n][m];
        }
        if(text1.charAt(n) == text2.charAt(m)){
            return dp[n][m] = longestCommonSubsequence_memo(text1, text2, n - 1, m - 1, dp) + 1;
        }

        return dp[n][m] = Math.max(longestCommonSubsequence_memo(text1, text2, n - 1, m ,dp), longestCommonSubsequence_memo(text1, text2, n, m - 1, dp));
    }
    


     // 1035
    public int maxUncrossedLines(int[] A, int[] B) {
        int N = A.length;
        int M = B.length;
        
        int[][] dp = new int[N + 1][M + 1];

        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(n == 0 || m == 0){
                    dp[n][m] = 0;
                    continue;
                }

                if(A[n - 1] == B[m - 1]) dp[n][m] = dp[n - 1][m - 1] + 1;

                else dp[n][m] = Math.max(dp[n - 1][m], dp[ n][m - 1]);
            }
        }
        return dp[N][M];
    }

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int[][] dp = new int[n + 1][m + 1];
        for(int[] d : dp){
            Arrays.fill(d, -(int)1e8);
        }

        return maxDotProduct_memo(nums1, nums2, n, m, dp);
    }
    public int maxDotProduct_memo(int[] nums1, int[] nums2, int n, int m, int[][] dp) {
        if(m == 0 || n == 0) return dp[n][m] = -(int)1e7;

        if(dp[n][m] != -(int)1e8) return dp[n][m];

        int val = nums1[n - 1] * nums2[m - 1];

        int bothIncluded = maxDotProduct_memo(nums1, nums2, n - 1, m - 1, dp) + val;

        int a = maxDotProduct_memo(nums1, nums2, n - 1, m, dp);
        int b = maxDotProduct_memo(nums1, nums2, n, m - 1, dp);

        return dp[n][m] = Math.max(Math.max(val, bothIncluded), Math.max(a, b));
    }
    public int maxDotProduct_DP(int[] nums1, int[] nums2, int N, int M, int[][] dp) {

        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){
                if(m == 0 || n == 0){ 
                    dp[n][m] = -(int)1e7;
                    continue;
                }
                // if(dp[n][m] != -(int)1e8) return dp[n][m];

                int val = nums1[n - 1] * nums2[m - 1];

                int bothIncluded = dp[n - 1][m - 1] + val; //maxDotProduct_memo(nums1, nums2, n - 1, m - 1, dp) + val;

                int a = dp[n - 1][m]; //maxDotProduct_memo(nums1, nums2, n - 1, m, dp);
                int b = dp[n][m - 1]; //maxDotProduct_memo(nums1, nums2, n, m - 1, dp);

                dp[n][m] = Math.max(Math.max(val, bothIncluded), Math.max(a, b));
            }
        }
        return dp[N][M];
        
    }

    //72
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int dp[][] = new int[n + 1][m + 1];
        for(int[] d: dp) Arrays.fill(d, -1);

        return minDistance_memo(word1, word2, n, m, dp);
    }

    public int minDistance_memo(String word1, String word2, int n, int m, int[][] dp) {
        if(n == 0 || m == 0) {
            return dp[n][m] = (n != 0) ? n : m;

        }


        if(dp[n][m] != -1) return dp[n][m];

        

        int insert = minDistance_memo(word1, word2, n, m - 1, dp);
        int delete = minDistance_memo(word1, word2, n - 1, m, dp);
        int replace = minDistance_memo(word1, word2, n - 1, m - 1, dp);

        if(word1.charAt(n - 1) == word2.charAt(m - 1)){
            return dp[n][m] = replace;
        }
        
        return dp[n][m] = Math.min(insert, Math.min(delete, replace)) + 1;
    }
    public int minDistance_DP(String word1, String word2, int N, int M, int[][] dp) {
        
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){

                if(n == 0 || m == 0) {
                    dp[n][m] = (n != 0) ? n : m;
                    continue;
                }
        
        
               // if(dp[n][m] != -1) return dp[n][m];
        
                
        
                int insert = dp[n][m - 1]; //minDistance_memo(word1, word2, n, m - 1, dp);
                int delete = dp[n - 1][m]; //minDistance_memo(word1, word2, n - 1, m, dp);
                int replace = dp[n - 1][m - 1]; //minDistance_memo(word1, word2, n - 1, m - 1, dp);
        
                if(word1.charAt(n - 1) == word2.charAt(m - 1)){
                    dp[n][m] = replace;
                }
                else
                    dp[n][m] = Math.min(insert, Math.min(delete, replace)) + 1;
            }
        }
        return dp[N][M];
    }


    //44
    public boolean isMatch(String s, String p) {
        int n = s.length();
        p = removeStart(p);
        int m = p.length();

        int dp[][] = new int[n + 1][m + 1];
        for (int[] d : dp) Arrays.fill(d, -1);
        

        return isMatch_memo(s, p, n, m, dp) == 1;
        
    }
    // -1 default, 0 -> false , 1 -> true
    public int isMatch_memo(String s, String p, int n, int m, int[][] dp) {
       if(n == 0 || m == 0){
           if(n == 0 && m == 0) return dp[n][m] = 1;

           else if(m == 1 && p.charAt(m - 1) == '*') return dp[n][m] = 1;

           else return dp[n][m] = 0;
       }
        if(dp[n][m] != -1) return dp[n][m];

       char ch1 = s.charAt(n - 1);
       char ch2 = p.charAt(m - 1);

       if(ch1 == ch2 || ch2 == '?'){
           return dp[n][m] = isMatch_memo(s, p, n - 1, m - 1, dp);

       }
       else if(ch2 == '*'){
           boolean res = false;

           res = res || (isMatch_memo(s, p, n - 1, m, dp) == 1);
           res = res || (isMatch_memo(s, p, n, m - 1, dp) == 1);

            return dp[n][m] = res ? 1 : 0;

       }
       else return dp[n][m] = 0;
    }

    public int isMatch_DP(String s, String p, int N, int M, int[][] dp) {

        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++){

                if(n == 0 || m == 0){
                    if(n == 0 && m == 0) { 
                        dp[n][m] = 1;
                        continue;
                    }
         
                    else if(m == 1 && p.charAt(m - 1) == '*')  {
                        dp[n][m] = 1;
                        continue;
                    }
         
                    else {
                        dp[n][m] = 0;
                        continue;
                    }
                }
                 //if(dp[n][m] != -1) return dp[n][m];
         
                char ch1 = s.charAt(n - 1);
                char ch2 = p.charAt(m - 1);
         
                if(ch1 == ch2 || ch2 == '?'){
                    dp[n][m] = dp[n - 1][m - 1]; //isMatch_memo(s, p, n - 1, m - 1, dp);
         
                }
                else if(ch2 == '*'){
                    boolean res = false;
         
                    res = res || (dp[n - 1][m] == 1); //(isMatch_memo(s, p, n - 1, m, dp) == 1);
                    res = res || (dp[n][m - 1] == 1); //(isMatch_memo(s, p, n, m - 1, dp) == 1);
         
                    dp[n][m] = res ? 1 : 0;
         
                }
                else dp[n][m] = 0;
            }
        }
        return dp[N][M];
     }

    public String removeStart(String str){
        if(str.length() == 0) return "";

        StringBuilder ans = new StringBuilder();

        ans.append(str.charAt(0));
        int i = 1;

        while(i < str.length()){
            while(i < str.length() && ans.charAt(ans.length() - 1) == '*' && str.charAt(i) == '*') 
                i++;
            if(i < str.length()) ans.append(str.charAt(i));

            i++;
        }

        return ans.toString();
    }
//https://practice.geeksforgeeks.org/problems/count-palindromic-subsequences/1
    long countPS(String str)
    {
        // Your code here
        int n = str.length();
        long[][] dp = new long[n][n];
        for(long[] d : dp) Arrays.fill(d, -1);

        return countPS(str, 0, n - 1, dp);
     }
    int mod = (int)1e9 + 7;
    long countPS(String str, int i, int j, long[][] dp){
        if(i >= j){
            return dp[i][j] = (i == j) ? 1 : 0;
        }

        if(dp[i][j] != -1) return dp[i][j];

        long a = countPS(str, i + 1, j, dp);
        long b = countPS(str, i, j - 1, dp);
        long c = countPS(str, i + 1, j - 1, dp);

        return dp[i][j] = ((str.charAt(i) == str.charAt(j)) ? (a + b + 1) : (a + b - c + mod)) % mod; 
        //why a + b - c + mod???? bcz it may possible a + b - c gives negative value 
        //(if test case is "aaaaa" the it will give negative value) that's why. 
    }

    //730
   
}
