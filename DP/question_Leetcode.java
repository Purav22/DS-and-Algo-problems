package DP;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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

    //1728
    int mouceCount = 0;
    int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public boolean canMouseWin(String[] grid, int c, int m) {
        char[][] mat = new char[grid.length][grid[0].length()];
        int mr=0,mc=0,cr=0,cc=0;

        for(int i = 0; i < grid.length; i++){
            mat[i] = grid[i].toCharArray();

            for(int j = 0; j < grid[i].length(); j++) {
                if(mat[i][j] == 'C'){
                    cr = i;
                    cc = j;
                }
                if(mat[i][j] == 'M'){
                    mr = i;
                    mc = j;
                }
            }
        }
        
        HashMap<String, Boolean> dp = new HashMap<>();

        boolean res = false;
        for(int j = 1; j < m; j++){
            for(int di = 0; di < 4; di++){
                int nmr = mr + j * dir[di][0];
                int nmc = mc + j * dir[di][1];

                if(nmr < mat.length && nmc < mat[0].length && nmr >= 0 && nmc >= 0 && mat[nmr][nmc] != '#' && mat[nmr][nmc] != 'C'){
                    mouceCount++;
                    res = res || canMouseWin_memo(mat, c, m, nmr, nmc, cr, cc, dp);
                    if(res) {
                        return true;
                    }
                    mouceCount--;
                }
            }
        }
        return res;


    }
    
    public boolean canMouseWin_memo(char[][] mat, int c, int m, int mr, int mc, int cr, int cc, HashMap<String, Boolean> map) {
        if(mat[mr][mc] == 'F') 
        {
            map.put("" + mr + mc + cr + cc , true);
            return true;
        }
        if(mat[mr][mc] == 'C' || mat[mr][mc] == '#') 
        {
            map.put("" + mr + mc + cr + cc , false);
            return false;
        }
        if(mouceCount == 1000) return false;
        if(map.containsKey("" + mr + mc + cr + cc)) return map.get("" + mr + mc + cr + cc);
        boolean ans = false;
        for(int i = 1; i < c; i++){
            for(int d = 0; d < 4; d++){
                int nr = cr + i * dir[d][0];
                int nc = cc + i * dir[d][1];

                if(nr < mat.length && nc < mat[0].length && nr >= 0 && nc >= 0 && mat[nr][nc] != '#'){

                    for(int j = 1; j < m; j++){
                        for(int di = 0; di < 4; di++){
                            int nmr = mr + j * dir[d][0];
                            int nmc = mc + j * dir[d][1];

                            if(nmr < mat.length && nmc < mat[0].length && nmr >= 0 && nmc >= 0 && mat[nmr][nmc] != '#' && mat[nmr][nmc] != 'C'){
                                mouceCount++;
                                ans = ans || canMouseWin_memo(mat, c, m, nmr, nmc, nr, nc, map);
                                if(ans) {
                                    map.put("" + mr + mc + cr + cc , true);
                                    return true;
                                }
                                mouceCount--;
                            }
                        }
                    }
                }

            }   
        }
        if(ans) map.put("" + mr + mc + cr + cc , true);
        else map.put("" + mr + mc + cr + cc , false);

        return ans;
    }

    //877
    public boolean stoneGame(int[] piles) {
        Deque<Integer> deque = new ArrayDeque<Integer>();
        for(int i : piles)
            deque.addLast(i);
        int alex = 0, lee = 0;
        int i = 0;
        while(!deque.isEmpty()){
            int first = deque.getFirst();
            int last = deque.getLast();
            int val = 0;
            if(first>last)
                val+=deque.removeFirst();
            else
                val+=deque.removeLast();
            if(i%2==0)
                alex+=val;
            else
                lee+=val;
        }
        return alex>lee;
    }

    //474   
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[101][101][601];

        for(int[][] d1 : dp)
            for(int[] d2 : d1)
                Arrays.fill(d2, -1);
        
        return findMaxForm_memo(strs, m, n, 0, dp);
    }

    public int findMaxForm_memo(String[] strs, int z, int o, int idx, int[][][] dp) {
        if(idx == strs.length || z < 0 || o < 0) 
            return dp[z][o][idx] = 0;
        
        if( z < 0 && o < 0) 
            return dp[z][o][idx] = 0;


        if(dp[z][o][idx] != -1) 
            return dp[z][o][idx];

        int zeros = giveZero(strs[idx]);
        int ones = strs[idx].length() - zeros;

        int ans = 0;
        if(zeros <= z && ones <= o){
            ans = Math.max(ans, Math.max(1 + findMaxForm_memo(strs, z - zeros, o - ones, idx + 1, dp), findMaxForm_memo(strs, z, o, idx + 1, dp)));
        }
        else{
            ans = findMaxForm_memo(strs, z, o, idx + 1, dp);
        }

        return dp[z][o][idx] = ans;
    }

    public int giveZero(String str){
        int ans = 0;
        for(int i = 0; i < str.length(); i++)
            if(str.charAt(i) == '0')
                ans++;
        return ans;
    }

    //1186
    public int maximumSum(int[] arr) {
        int n = arr.length;
        int count = 0;
        int max = -(int)1e7;
        for(int i = 0; i < n; i++){
            if(arr[i] >= 0) count++;

            max = Math.max(max, arr[i]);
        }

        if(count == 0) return max;
        int[][] dp = new int[n + 1][2];

        for(int[] d : dp) Arrays.fill(d, -1);
        int ans = -(int)1e9;
        for(int i = 0; i < n; i++){
            ans = Math.max(ans, maximumSum_memo(arr, n, i, 1, dp));
        }
        return ans;
        
    }

    public int maximumSum_memo(int[] arr, int n, int i, int delete, int[][] dp) {
        if(i == n) return 0;

        if(delete < 0) return 0;
        if(dp[i][delete] != -1) return dp[i][delete];
        int ans = -(int)1e8;

        ans = Math.max(ans, Math.max(arr[i] + maximumSum_memo(arr, n, i + 1, delete, dp), maximumSum_memo(arr, n, i + 1, delete - 1, dp)));

        return dp[i][delete] = ans;
    }

    //1405

    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((v1,v2)->(v2[0]-v1[0]));
        if (a > 0) pq.offer(new int[] {a, 'a'});
        if (b > 0) pq.offer(new int[] {b, 'b'});
        if (c > 0) pq.offer(new int[] {c, 'c'});

        StringBuilder buf = new StringBuilder();

        int lastChar = ' ';
        while(!pq.isEmpty()) {
            if (buf.length() > 0) lastChar = buf.charAt(buf.length() - 1);
            int[] first = pq.poll();

            // check if last char same as top item
            if (lastChar != first[1]) {
                for (int i = 0; i < 2 && first[0] > 0; ++i,--first[0]) 
                    buf.append((char)first[1]);
                if (first[0] > 0) pq.offer(first);
                continue;
            }

            // break is just one item
            if (pq.isEmpty()) break;

            // add second item one time
            int[] second = pq.poll();
            buf.append((char)second[1]);
            --second[0];
            if (second[0] > 0) pq.offer(second);

            // add top item back to q
            pq.offer(first);
        }

        return buf.toString();
    }

    //1824
    public int minSideJumps(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][4];
        for(int[] d : dp){
            Arrays.fill(d, (int)1e9);
        }
        return minSideJumps_memo(arr, 0, 2, dp);
    }

    public int minSideJumps_memo(int[] arr, int idx, int len, int[][] dp) {
        
        if(idx == arr.length - 1){
            return dp[idx][len] = 0;
        }
        if(arr[idx] == len){
            return (int)1e8;
        }

        if(dp[idx][len] != (int)1e9) return dp[idx][len];
        int ans = (int)1e8;
        if(arr[idx + 1] != len){
            ans = Math.min(ans, minSideJumps_memo(arr, idx + 1, len, dp));
        }else{
            if(len == 1){
                ans = Math.min(ans, Math.min(1 + minSideJumps_memo(arr, idx, len + 1, dp), 
                                1 + minSideJumps_memo(arr, idx, len + 2, dp)));
            }else if(len == 2){
                ans = Math.min(ans, Math.min(1 + minSideJumps_memo(arr, idx, len - 1, dp), 
                                1 + minSideJumps_memo(arr, idx, len + 1, dp)));
            }else{
                ans = Math.min(ans, Math.min(1 + minSideJumps_memo(arr, idx, len - 1, dp), 
                                1 + minSideJumps_memo(arr, idx, len - 2, dp)));
            }

        }
        return dp[idx][len] = ans;
    }
    

    //523
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        //map.put(0, 1);
        
         int result = 0;
        int sum = 0;
        map.put(0,-1);
        for(int i=0;i<nums.length;i++)
        {
            sum = sum+nums[i];                
            int mod = sum%k;
            if(map.containsKey(mod))
            {
                if(i-map.get(mod)>=2)
                    return true;
            }
            else
                map.put(mod,i);
        }
        return false;
    }
    //1191
    public long kadane (int arr[]) {
        long curr = arr[0];
        long max = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if (curr >= 0) curr+=arr[i];
            else curr = arr[i];
            if (curr > max) max = curr;
        }
        return max;
    }
    public long kadaneTwo(int arr[]) {
        int narr[] = new int[arr.length * 2];
        for (int i = 0; i < arr.length; i++) 
            narr[i] = arr[i];
        for (int i = 0; i < arr.length; i++)
            narr[i + arr.length] = arr[i];
        return kadane(narr);
    }
    public long sum(int arr[]) {
        long s = 0;
        for (int i = 0; i < arr.length; i++)
            s += arr[i];
        return s;
    }
    public int kConcatenationMaxSum(int[] arr, int k) {
        long sum = sum(arr);
        long res = 0;
        long x = 1000000007;
        if (k == 1) res = kadane(arr);
        else if (sum < 0) res = kadaneTwo(arr);
        else res = kadaneTwo(arr) + (k - 2)*sum;
        if (res < 0) return 0;
        return (int)(res % x);
    }

    //1654
    public int minimumJumps(int[] arr, int a, int b, int x) {
        
        int[][] dp = new int[2001][2];
        int[] valid = new int[2001];
        for(int num : arr){
            valid[num] = 1;
        }
        for(int[] d : dp){
            Arrays.fill(d, -1);
        }
        int ans = minimumJumps_memo(valid, a, b, x, true, 0, dp);
        if(ans == (int)1e9) return -1;

        return ans;
    }
    public int minimumJumps_memo(int[] arr, int a, int b, int tar, boolean back, int curr, int[][] dp) {
        if(curr < 0) return (int)1e9;

        
        int idx = back ? 1 : 0;
        if(arr[curr] == 1){
            return dp[curr][idx] = (int)1e9;
        }
        if(curr == tar){
            return dp[curr][idx] = 0;

        }
        
        if(curr > tar) return dp[curr][idx] = (int)1e9;

        if(dp[curr][idx] != -1) return dp[curr][idx];
        

        int myAns = (int)1e9;
        if(back){

            myAns = Math.min(myAns, 1 + minimumJumps_memo(arr, a, b, tar, false, curr + a, dp));
        }else{
            myAns = Math.min(myAns, 1 + minimumJumps_memo(arr, a, b, tar, false, curr + a, dp));
            myAns = Math.min(myAns, 1 + minimumJumps_memo(arr, a, b, tar, true, curr - b, dp));
        }

        return dp[curr][idx] = myAns; 
    }

    //1770

    public int maximumScore(int[] nums, int[] multipliers) {
        
        int n = nums.length, m = multipliers.length;
        
        Integer mem[][] = new Integer[m][m]; // start = 0 -> m && curr = 0 -> m
        
        return helper(nums, multipliers, 0, 0, n-1, mem);
    }
    
    private int helper(int[] nums, int[] M, int curr, int start, int end, Integer[][] mem){
        
        if(curr == M.length) return 0;
        
        if(mem[start][curr] != null) return mem[start][curr];
        
        int one = nums[start]*M[curr] + helper(nums, M, curr+1, start+1, end,mem);
        int two = nums[end]*M[curr] + helper(nums, M, curr+1, start, end-1, mem);
        
        mem[start][curr] = Math.max(one, two);
        
        return mem[start][curr];
    }

    //1043
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;

        int[] dp = new int[n + k + 1];

        Arrays.fill(dp ,-1);

        return maxSumAfterPartitioning_memo(arr, k, 0, n-1, dp);
    }

    public int maxSumAfterPartitioning_memo(int[] arr, int k, int si, int ei, int[] dp) {
        if(si > ei){
            return dp[si] = 0;
        }
        if(si == ei){
            return dp[si] = arr[si];
        }

        if(dp[si] != -1) return dp[si];
        int ans = 0;
        for(int i = 1; i <= k; i++){
            int sum = si + i - 1 < arr.length ? giveSum(arr, si, si + i -1) : 0;
            //System.out.println(sum + " " + si + "-" + i);
            ans = Math.max(ans , sum + maxSumAfterPartitioning_memo(arr, k, si + i, ei, dp));
        }
        
        return dp[si] = ans;
    }
    private int giveSum(int[] arr, int si, int i) {
       int ele = i - si + 1;
        int max = 0;
       for(int j = si; j <= i; j++){
            max = Math.max(max,arr[j]);
       }

       return ele * max;
    }

    public int maxSumAfterPartitioning_02(int[] arr, int k) {
        int n=arr.length,i,j;
        
        int dp[] = new int[n+1];
        
        for(i=1;i<=n;++i){
            int maxInWindow = 0, bestInWindow = 0;
            for(j=1;j<=k && i-j>=0;j++){
                maxInWindow = Math.max(maxInWindow, arr[i-j]);
                bestInWindow = Math.max(bestInWindow, dp[i-j]+maxInWindow*j);
            }
            dp[i] = bestInWindow;
        }
        
        return dp[n];
    }
    public int maxSumAfterPartitioning_DP(int[] arr, int k, int SI, int EI, int[] dp) {
        
        for(int si = arr.length - 1; si >= 0; si--){

            
            if(si == EI){
                dp[si] = arr[si];
                continue;
            }
    
           
            int ans = 0;
            for(int i = 1; i <= k; i++){
                if(si + i > arr.length) break;
                int sum = giveSum(arr, si, si + i -1);
                //System.out.println(sum + " " + si + "-" + i);
                ans = Math.max(ans , sum + dp[si + i]); //maxSumAfterPartitioning_memo(arr, k, si + i, ei, dp));
            }
            
            dp[si] = ans;
        }
        
        return dp[SI];
    }

    //873
    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        return lenLongestFibSubseq_memo(arr, 0, -1, -1);       
    }
    public int lenLongestFibSubseq_memo(int[] arr, int idx, int prev1, int prev2) {
        
        int ans = 0;
        if(prev1 == -1 || prev2 == -1){
            
            ans = Math.max(ans, 1 + lenLongestFibSubseq_memo(arr, idx + 1, arr[idx], prev1));
            ans = Math.max(ans, lenLongestFibSubseq_memo(arr, idx + 1, prev1, prev2));
        }else
            if(prev1 + prev2 == arr[idx])
                ans = Math.max(ans, 1 + lenLongestFibSubseq_memo(arr, idx + 1, arr[idx], prev1));

        ans = Math.max(ans, lenLongestFibSubseq_memo(arr, idx + 1, prev1, prev2));

        return ans;
    }
    public int lenLongestFibSubseq_02(int[] A) {
        int ans = 0, n = A.length;
        int [][] dp = new int [n][n];

        for (int idx = 2; idx < n; idx ++) {
            int a = A [idx], s = 0, e = idx - 1;
            while (s < e) {
                int v = A [s] + A [e];
                if (v > a) e --;
                else if (v < a) s ++;
                else { 
                    ans = Math.max (ans, dp [e][idx] = (dp [s][e] == 0 ? 2 : dp [s][e]) + 1);
                    s++;
                }
            }
        }    
        return ans;
    }

    //1155

    public int numRollsToTarget(int d, int f, int target) {
        int[][] dp = new int[d + 1][target + 1];
        
        for(int[] DP : dp) Arrays.fill(DP, -1);
        return numRollsToTarget_memo(d, f, target, dp) ;
    }
    int mod = (int)1e9 + 7;
    public int numRollsToTarget_memo(int d, int f, int target, int[][] dp) {
        if(d == 0 && target == 0){
            return dp[d][target] = 1;
        }

        if(d == 0){
            return dp[d][target] = 0;
        }

        if(dp[d][target] != -1) return dp[d][target];
        int count = 0;
        for(int i = 1; i <= f; i++){
            count = (count + (target - i >= 0 ? numRollsToTarget_memo(d - 1, f, target - i, dp) % mod : 0)) % mod;
        }

        return dp[d][target] = count;
    }

    public int numRollsToTarget_DP(int D, int f, int Target, int[][] dp) {
        for(int d = 0; d <= D; d++){
            for(int target = 0; target <= Target; target++){
                if(d == 0 && target == 0){
                     dp[d][target] = 1;
                     continue;
                }
        
                if(d == 0){
                     dp[d][target] = 0;
                     continue;
                }
        
                //if(dp[d][target] != -1) return dp[d][target];
                int count = 0;
                for(int i = 1; i <= f; i++){
                    count = (count + (target - i >= 0 ? dp[d -1][target - i] % mod : 0)) % mod;
                }
        
                dp[d][target] = count;
            }
        }

        return dp[D][Target];
        
       
    }

    //1423
    public int maxScore(int[] cardPoints, int k) {
        if (cardPoints == null || cardPoints.length == 0 || k == 0) return 0;
        
        int n = cardPoints.length;
        int[] dp = new int[n + 1];
        
        for(int i = 1; i <= n; i++){
            dp[i] = dp[i - 1] + cardPoints[i - 1];
        }
        
        
        int ans = cardPoints[0];
        
        
        for(int i = 0; i <= k; i++){
            
            int first = dp[i];
            int last = dp[n] - dp[n - (k - i)];
            ans = Math.max(ans, first + last);

        }
        return ans;
    }
    
   
}
