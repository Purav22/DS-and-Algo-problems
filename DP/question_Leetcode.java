package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class question_Leetcode {
    
    //646
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        int dp[] = new int[n + 1];
        Arrays.fill(dp, -1);
        
        Arrays.sort(pairs, (a,b)->{
            return a[0] - b[0];
        });
       // for(int a[] : pairs) System.out.println(a[0] + " " + a[1]);
        return findLongestChain_memo(pairs, 0, n, dp) + 1;

    }
    public int findLongestChain_memo(int[][] pairs, int idx, int n, int[] dp) {
        if(idx == n) return dp[idx] = 0;
    
        if(dp[idx] != -1) return dp[idx];
        int count = 0;
        int first = 0;
        int second = 0;
        
        for(int i = idx + 1; i < n; i++){
            if(pairs[i][0] > pairs[idx][1]){
                first = Math.max(first, 1 + findLongestChain_memo(pairs, i, n, dp));
            }
            second = Math.max(second, findLongestChain_memo(pairs, i, n, dp));
        }

        if(first > second)
            count = first;
        else count = second;

        return dp[idx] = count;

    }

    //813
    public double largestSumOfAverages(int[] A, int K) {
        int n = A.length;
        double[][] dp = new double[n + 1][K + 1];
        for(double d[] : dp) Arrays.fill(d, -1);

        return largestSumOfAverages_memo(A, K, 0, n, dp);
        
    }
    public double largestSumOfAverages_memo(int[] arr, int k, int si, int n, double[][] dp) {
        if(k == 0 && si == n){
            return dp[si][k] = 0;
        }
        if(si == n || k == 0){
            return dp[si][k] = -(int)1e9;
        }
        if(dp[si][k] != -1) return dp[si][k];

        double myAns = 0;
        for(int i = si; i < n; i++){
            double count = calculate(arr, si, i);
            //System.out.println(si + " " + i + " " + count);
            double ans = largestSumOfAverages_memo(arr, k - 1, i + 1, n, dp);
            //System.out.println(si + " " + i + " " + count + " " +ans);
            if(ans != -(int)1e9)
                myAns = Math.max(myAns, ans + count); 
            
            
        }
        return dp[si][k] = myAns;
    }
    private double calculate(int[] arr, int si, int n) {
        int ele = 0;
        double count = 0;
        for(int i = si; i <= n; i++){
            count += arr[i];
            ele++;
        }
        return count / ele;
    }
    //688
    public double knightProbability(int N, int K, int r, int c) {
        double dp[][][] = new double[N][N][K + 1];
        for(int i = 0; i < N; i++){
            for(double d[] : dp[i]) Arrays.fill(d, -1);
        }
        int[][] dir = {{-2, 1},{-1, 2},{1, 2},{2, 1},{2, -1},{1, -2},{-1, -2},{-2, -1}};

        return knightProbability_memo(N, K, r, c, dp, dir);
    }
    
    
    public double knightProbability_memo(int N, int K, int r, int c, double[][][] dp, int[][] dir) {
        if(K == 0) return dp[r][c][K] = 1;
        double ans = 0;

        if(dp[r][c][K] != -1) return dp[r][c][K];
        for(int i = 0; i < dir.length; i++){
            int nr =  r + dir[i][0];
            int nc =  c + dir[i][1];

            if(nr >= 0 && nc >= 0 && nr < N && nc < N){
                ans += (knightProbability_memo(N, K - 1,  nr, nc, dp, dir))/8;
            }
        }
        return dp[r][c][K] = ans;
    }
    
    //740

    public int deleteAndEarn(int[] arr) {
        int n = arr.length;
       Arrays.sort(arr);
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return deleteAndEarn_memo(arr, 0, n, dp);
        
    }
    public int deleteAndEarn_memo(int[] arr, int idx, int n, int[] dp) {
     
        if(idx == n) return dp[idx] = 0;

       // if(dp[idx] != -1) return dp[idx];

        int i = idx;
        int sum = 0;
        while(i < n && arr[i] == arr[idx]) {
            i++;
            sum += arr[idx];
        }
        while(i < n && arr[i] == (arr[idx] + 1) ) {
            i++;
            
        }

        return dp[idx] = Math.max(sum + deleteAndEarn_memo(arr, i, n, dp), deleteAndEarn_memo(arr, idx + 1, n, dp));
    }


    //838

    public String pushDominoes(String str) {
        int n = str.length();
        int[] arr = new int[n];

        int temp = 0;

        for(int i = 0; i < n; i++){
            char ch = str.charAt(i);

            if(ch == 'R'){
                temp = n;
            }else if(ch == 'L'){
                temp = 0;
            }else{
                temp = Math.max(temp - 1, 0);
            }

            arr[i] = temp;
        }
        temp = 0;
        for(int i = n - 1; i >= 0; i--){
            char ch = str.charAt(i);

            if(ch == 'R'){
                temp = 0;
            }else if(ch == 'L'){
                temp = n;
            }else{
                temp = Math.max(temp - 1, 0);
            }

            arr[i] -= temp;
        }
        StringBuilder sb = new StringBuilder();
        for(int num : arr){

            if(num > 0) sb.append("R");
            else if(num < 0) sb.append("L");
            else sb.append(".");
        }
        return sb.toString();
        
    }

    //1262
    public int maxSumDivThree(int[] arr) {
        
        int n = arr.length;
        int[][] dp = new int[n + 1][3];
        for(int d[] : dp)
        Arrays.fill(d, -1);

        return maxSumDivThree_memo(arr, 0, n, dp,0);
         
    }
    int max = 0;
    public int maxSumDivThree_memo(int[] arr, int idx, int n, int[][] dp, int r) {
        if(idx == n) return r % 3 == 0 ? 0 : -(int)1e8;

        //if(dp[idx][r] != -1) return dp[idx][r];
        int count1 = arr[idx] + maxSumDivThree_memo(arr, idx + 1, n, dp, (r + arr[idx]) % 3);

        int count2 = maxSumDivThree_memo(arr, idx + 1, n, dp, r);

        
        

        return dp[idx][r] = Math.max(count1, count2);
    }

    //1027
    public int longestArithSeqLength(int[] A) {
        int n = A.length;
        int dp[][] = new int[n + 1][(int)1e7];
        for(int[] d : dp) Arrays.fill(d, -1);

        return longestArithSeqLength_memo(A, 0, n, -1, dp);
    }

    public int longestArithSeqLength_memo(int[] arr, int idx, int n, int prev, int[][] dp) {
        if(idx == n) return 0;
        int count= 0;
        if(dp[idx][prev] != -1) return dp[idx][prev];
        if(prev == -1){
                count = Math.max(count, longestArithSeqLength_memo(arr, idx + 1, n, prev, dp));
                if(idx + 1 < n ){
                    int num = Math.abs(arr[idx + 1] - arr[idx]);
                    count = Math.max(count, longestArithSeqLength_memo(arr, idx + 1, n, num, dp) + 1);
                }

        }else{
            if(idx + 1 < n ){
                int num = Math.abs(arr[idx + 1] - arr[idx]);
                if(prev == num){
                    count = Math.max(count, longestArithSeqLength_memo(arr, idx + 1, n, prev, dp) + 1);
                }
                count = Math.max(count, longestArithSeqLength_memo(arr, idx + 1, n, prev, dp));
            }   
        }

        return dp[idx][prev] = count;
    }

    //152
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int ans = nums[0];
        int max = 1, min = 1;
        for(int i = 0; i < n; i++){
            if(nums[i] == 0){
                //ans = Math.max(ans, Math.max(nums[i], max));
                max = 1;
                min = 1;
                
            }

            int temp1 = nums[i] * max;
            int temp2 = nums[i] * min;

            max = Math.max(temp1, Math.max(temp2, nums[i]));
            min = Math.min(temp1, Math.min(temp2, nums[i]));
            ans = Math.max(ans, max);
            //System.out.println(max + " " + min);
        }

        
        return ans;
    }

    //264

    public int nthUglyNumber(int n) {
        if (n <= 0)
		    return 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        
        int two = 0;
        int three = 0;
        int five = 0;
        
        
        while(list.size() < n){
            int next = Math.min(list.get(two) * 2, Math.min(list.get(three) * 3, list.get(five) * 5));
            list.add(next);
            
            if(list.get(two) * 2 == next) two++;
            if(list.get(three) * 3 == next) three++;
            if(list.get(five) * 5 == next) five++;
            
            if(list.size() == n) return next;
        }
        return 1;
        
        
    }

    //368

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int dp[] = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            dp[i] = 1;
            for(int j=0;j<i;j++){
                if(nums[i]%nums[j]==0) dp[i] = Math.max(dp[i],dp[j]+1);
            }
        }
        int mx = 0;
        for(int i : dp) mx = Math.max(i,mx);
        List<Integer> lis = new ArrayList<>();
        int prev = -1;
        for(int i=nums.length-1;i>=0;i--){
            if(dp[i]==mx&&(prev==-1||prev%nums[i]==0)){
                lis.add(nums[i]); mx--; prev = nums[i];
            }
        }
        return lis;
    }


    //464

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int m=maxChoosableInteger;
        int sum=desiredTotal;
        int maxSum= (m*(m+1))/2;
        if(sum>maxSum){
            return false;
        }
        int[] arr=new int[m+1];
        Map<Integer,Boolean> map=new HashMap<>();
        boolean ans=util(m,sum,arr,map);
        
        return ans;
    }
    boolean util(int m,int s,int[] arr,Map<Integer,Boolean> map){
        for(int i=1;i<=m;i++){
            if(arr[i]==0){
                if(i>=s){
                    return true;
                }
            }
        }        
        int state=getState(arr);
        if(map.get(state) != null){
            return map.get(state);
        }
        boolean res=false;
        for(int i=m;i>=1;i--){
            if(arr[i] ==0){
                arr[i]=1;
               boolean temp=util(m,s-i,arr,map);
                arr[i]=0;
                if(temp == false){
                    res=true;
                    break;
                }
            }
        }
        map.put(state,res);
        return res;
    }
    int getState(int[] arr){
        int temp=1;
        int sum=0;
        for(int i=1;i<arr.length;i++){
            sum+=arr[i]*temp;
            temp=temp*2;
        }
        return sum;
    }
}
