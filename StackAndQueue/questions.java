package StackAndQueue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class questions {
    //N = next, G = greater, S = small, R = Right, L = Left
    public static void NGOR(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, n);

        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < n; i++){

            while(st.size() != 0 && arr[st.peek()] < arr[i]){
                
                ans[st.pop()] = i;
            }
            
            st.push(i);
        }

        
    }
    public static void NGOL(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, -1);

        Stack<Integer> st = new Stack<>();

        for(int i = n - 1; i >= 0; i--){

            while(st.size() != 0 && arr[st.peek()] < arr[i]){
                
                ans[st.pop()] = i;
            }
            
            st.push(i);
        }

        
    }

    public static void NSOR(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, n);

        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < n; i++){

            while(st.size() != 0 && arr[st.peek()] > arr[i]){
                
                ans[st.pop()] = i;
            }
            
            st.push(i);
        }

        
    }
    public static void NSOL(int[] arr, int[] ans){
        int n = arr.length;
        Arrays.fill(ans, -1);

        Stack<Integer> st = new Stack<>();

        for(int i = n - 1; i >= 0; i--){

            while(st.size() != 0 && arr[st.peek()] > arr[i]){
                
                ans[st.pop()] = i;
            }
            
            st.push(i);
        }

        
    }

    //503
    public int[] nextGreaterElements(int[] arr) {
        int n = arr.length;
        int ans[] = new int[n];
        Arrays.fill(ans, -1);
        
        
         Stack<Integer> st = new Stack<>();

        for(int i = 0; i < 2 * n; i++){

            while(st.size() != 0 && arr[st.peek()] < arr[i % n]){
                
                ans[st.pop()] = arr[i % n];
            }
            if(i < n)
                st.push(i);
        }
        return ans;

    }

    //https://practice.geeksforgeeks.org/problems/stock-span-problem-1587115621/1
    public static int[] calculateSpan(int arr[], int n){
        // Your code here
        int[] ans = new int[n];
        //Arrays.fill(ans, -1);
        NGOL(arr, ans);
        for(int i = 0; i < n; i++){
            ans[i] = i - ans[i];
        }
        return ans;
    }

    //20
    public boolean isValid(String s) {
        boolean ans=false;
        
        Stack<Character> stack=new Stack<>();
        
        char ch[]=s.toCharArray();
        
        for(int i=0;i<ch.length;i++){
            if(ch[i]=='(' || ch[i]=='{'|| ch[i]=='['){
                stack.push(ch[i]);
            }
            else if(ch[i]==')' && !stack.isEmpty()){
                char temp=stack.pop();
                if(temp!='(')
                    return ans;
                    
            }
            else if(ch[i]=='}' && !stack.isEmpty()){
                char temp=stack.pop();
                if(temp!='{')
                    return ans;
                    
            }
            else if(ch[i]==']' && !stack.isEmpty()){
                char temp=stack.pop();
                if(temp!='[')
                    return ans;
                    
            }
            else{
                return false;
            }
        }
        if(stack.isEmpty())
            ans =true;
        
        return ans;
        
    }

    //946
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int N = pushed.length;
        Stack<Integer> stack = new Stack<>();

        int j = 0;
        for (int x: pushed) {
            stack.push(x);
            while (!stack.isEmpty() && j < N && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return j == N;
    }

    //1249
    public String minRemoveToMakeValid(String s) {
        ArrayList<Integer> st = new ArrayList<>();

        int n = s.length();
        for(int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if(ch == '('){
                st.add(i);
            }
            else if(ch == ')'){
                if(st.size() != 0 && s.charAt(st.get(st.size() - 1)) == '(')
                    st.remove(st.size() - 1);
                else
                    st.add(i);
            }
        }

        StringBuilder ans = new StringBuilder();
        int idx = 0;
        for(int i = 0; i < n; i++){
            if(idx < st.size() && st.get(idx) == i) {
                idx++;
                continue;
            }
            ans.append(s.charAt(i));
        }
        return ans.toString();
    }

    //32

    public int longestValidParentheses(String s) {
        int n = s.length();
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int len = 0;
        for (int i = 0; i < n; i++) {
            if (st.peek() != -1 && s.charAt(st.peek()) == '(' && s.charAt(i) == ')') {
                st.pop();
                len = Math.max(len, i - st.peek());
            } else {
                st.push(i);
            }
        }

        return len;
    }

    //735
    public int[] asteroidCollision(int[] arr) {
        Stack<Integer> st = new Stack<>();

        int n = arr.length;
        for (int ele : arr)
        {
            if (ele > 0)
            {
                st.push(ele);
                continue;
            }

            while (st.size() != 0 && st.peek() > 0 && st.peek() < -ele)
                st.pop();

            if (st.size() != 0 && st.peek() == -ele)
                st.pop();
            else if (st.size() == 0 || st.peek() < 0)
                st.push(ele);
            else
            {
            }
        }

        int[] ans = new int[(st.size())];
        int idx = st.size() - 1;
        while (st.size() != 0)
        {
            ans[idx--] = st.pop();
        }

        return ans;
    }

    //84
    public int largestRectangleArea_(int[] heights) {
        int n = heights.length;
        int[] nsol = new int[n];
        int[] nsor = new int[n];
        NSOL(heights, nsol);
        NSOR(heights, nsor);

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int h = heights[i];
            int w = nsor[i] - nsol[i] - 1;

            maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
    }

    //84
    public int largestRectangleArea_02(int[] heights)
    {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int maxArea = 0;
    
        int i = 0;
        while (i < n)
        {
            while (st.peek() != -1 && heights[st.peek()] >= heights[i])
            {
                int idx = st.peek();
                st.pop();
    
                int h = heights[idx];
                int w = i - st.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
    
            st.push(i++);
        }
    
        while (st.peek() != -1)
        {
            int idx = st.peek();
            st.pop();
    
            int h = heights[idx];
            int w = n - st.peek() - 1;
            maxArea = Math.max(maxArea, h * w);
        }
    
        return maxArea;
    }
    
    

    // public static void NSOR(int[] arr, int[] ans) {
    //     int n = arr.length;
    //     Arrays.fill(ans, n); // Java : Arrays.fill(ans,n);

    //     Stack<Integer> st = new Stack<>();
    //     for (int i = 0; i < n; i++) {
    //         while (st.size() != 0 && arr[st.peek()] > arr[i]) {
    //             ans[st.pop()] = i;
    //         }
    //         st.push(i);
    //     }
    // }

    // public static void NSOL(int[] arr, int[] ans) {
    //     int n = arr.length;
    //     Arrays.fill(ans, -1); // Java : Arrays.fill(ans,-1);

    //     Stack<Integer> st = new Stack<>();
    //     for (int i = n - 1; i >= 0; i--) {
    //         while (st.size() != 0 && arr[st.peek()] > arr[i]) {
    //             ans[st.pop()] = i;
    //         }
    //         st.push(i);
    //     }
    // }

    // 85
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] nsol = new int[n];
        int[] nsor = new int[n];
        NSOL(heights, nsol);
        NSOR(heights, nsor);

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int h = heights[i];
            int w = nsor[i] - nsol[i] - 1;

            maxArea = Math.max(maxArea, (h < w) ? h * h : w * w);
        }

        return maxArea;
    }

   

    // 221
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int maxRec = 0;
        int[] heights = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = matrix[i][j];
                heights[j] = ch == '1' ? heights[j] + 1 : 0;
            }

            maxRec = Math.max(maxRec, largestRectangleArea(heights));
        }

        return maxRec;
    }

    //402
    public String removeKdigits(String num, int k) {
        ArrayList<Character> st = new ArrayList<>();

        for (int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);
            while (st.size() != 0 && st.get(st.size() - 1) > ch && k > 0) {
                st.remove(st.size() - 1);
                k--;
            }
            st.add(ch);
        }

        while (k-- > 0) {
            st.remove(st.size() - 1);
        }

        StringBuilder ans = new StringBuilder();
        boolean flag = false;
        for (Character ch : st) {
            if (ch == '0' && !flag) {
                continue;
            }

            flag = true;
            ans.append(ch);
        }

        String res = ans.toString();
        return res.length() == 0 ? "0" : res;
    }

    //316
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        StringBuilder st = new StringBuilder();
        boolean[] vis = new boolean[26];
        int[] freq = new int[26];

        for (int i = 0; i < n; i++)
            freq[s.charAt(i) - 'a']++;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            freq[ch - 'a']--;
            if (vis[ch - 'a'])
                continue;

            while (st.length() != 0 && st.charAt(st.length() - 1) > ch && freq[st.charAt(st.length() - 1) - 'a'] > 0) {
                char rch = st.charAt(st.length() - 1); // st.peek();
                vis[rch - 'a'] = false;
                st.deleteCharAt(st.length() - 1); // st.pop();
            }

            vis[ch - 'a'] = true;
            st.append(ch);
        }

        return st.toString();
    }

    int trap(int[] height){
        int n = height.length;
        int[] lHeight = new int[n];
        int[] rHeight = new int[n];

        int prev = 0;
        for (int i = 0; i < n; i++){
            lHeight[i] = Math.max(height[i], prev);
            prev = lHeight[i];
        }

        prev = 0;
        for (int i = n - 1; i >= 0; i--){
            rHeight[i] = Math.max(height[i], prev);
            prev = rHeight[i];
        }

        int totalWater = 0;
        for (int i = 0; i < n; i++){
            totalWater += Math.min(lHeight[i], rHeight[i]) - height[i];
        }

        return totalWater;
    }

    int trap_01(int[] height){
        int n = height.length;
        Stack<Integer> st = new Stack<>();

        int totalWater = 0;
        for (int i = 0; i < n; i++){
            while (st.size() != 0 && height[st.peek()] <= height[i]){
                int idx = st.peek();
                st.pop();
                if (st.size() == 0)
                    break;

                int w = i - st.peek() - 1;
                int h = height[idx];

                totalWater += w * (Math.min(height[st.peek()], height[i]) - h);
            }

            st.push(i);
        }

        return totalWater;
    }

    int trap_03(int[] height){
        int n = height.length;
        int lmax = 0, rmax = 0;
        int l = 0, r = n - 1, totalWater = 0;

        while (l < r)
        {
            lmax = Math.max(lmax, height[l]);
            rmax = Math.max(rmax, height[r]);

            totalWater += lmax < rmax ? lmax - height[l++] : rmax - height[r--];
        }

        return totalWater;
    }

    //1209
    class Pair{
        char ch;
        int freq;
        
        Pair(char ch, int freq){
            this.ch = ch;
            this.freq = freq;
        }
    }
    public String removeDuplicates(String s, int k) {
        Stack<Pair> st = new Stack<>();
        
        
        for(int i = 0; i < s.length(); i++){
                char ch =  s.charAt(i);
            if(st.size() > 0 && st.peek().ch == ch){
                st.push(new Pair(ch, st.peek().freq + 1));
            }else{
                st.push(new Pair(ch, 1));
            }
            
            if(st.size() > 0 && st.peek().freq == k){
                
                int n = k;
                while(n > 0){
                    st.pop();
                    n--;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        
        while(st.size() > 0)
            sb.append(st.pop().ch);
        
        return sb.reverse().toString();
    }
    

    //1003
    public boolean isValid(String s) {
        Stack<Integer> st = new Stack<>();
        
        for(int i = 0; i < s.length(); i++){
            char ch =  s.charAt(i);

            if(ch == 'c'){
                if(st.size() > 0 && s.charAt(st.peek()) == 'b'){ 
                    st.pop();
                    if(st.size() > 0 && s.charAt(st.peek()) == 'a') st.pop();
                    else return false;
                }else return false;
            }
            else
                st.push(i);
        }

        return st.size() == 0;
    }

    //394
    public String decodeString(String s) {
        Stack<Character> st = new Stack<>();
        
        for(int i = 0; i < s.length(); i++){

            char ch = s.charAt(i);

            if(ch == ']'){
                StringBuilder sb = new StringBuilder();

                while(st.size() > 0 && st.peek() != '['){
                    sb.append(st.pop());
                }

                st.pop();
                sb.reverse();
                StringBuilder n = new StringBuilder();
                while(st.size() > 0 && st.peek() >= '0' && st.peek() <= '9'){
                    n.append(st.pop());
                }
                n.reverse();
                int num = Integer.parseInt(n.toString());
                n = new StringBuilder();
                while(num-- > 0){
                    n.append(sb);
                }

                for(int j = 0; j < n.length(); j++){
                    st.push(n.charAt(j));
                }
                
            }else{
                st.push(ch);
            }
        }
        StringBuilder ans = new StringBuilder();
        while(st.size() > 0){
            ans.append(st.pop());
        }

        return ans.reverse().toString();
    }

    //1541
    public int minInsertions(String s) {
        Stack<Integer> st = new Stack<>();
        int count = 0;
        for(int i = 0; i < s.length(); i++){

            char ch = s.charAt(i);

            if(ch == '('){
                st.push(i);
            }else{

                if(i + 1 < s.length() && s.charAt(i + 1) == ')'){
                    i++;
                    if(st.size() > 0 && s.charAt(st.peek()) == '('){
                        st.pop();
                    }else{
                        count++;
                    }
                }else{
                    count++;
                    if(st.size() > 0 && s.charAt(st.peek()) == '('){
                        st.pop();
                    }else{
                        count++;
                    }
                }
            }
        }

        while(st.size() > 0){
            count += 2;
            st.pop();
        }
        return count;

    }


    //227
    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;
          int len = s.length();
          Stack<Integer> stack = new Stack<Integer>();
          int currentNumber = 0;
          char operation = '+';
          for (int i = 0; i < len; i++) {
              char currentChar = s.charAt(i);
              if (Character.isDigit(currentChar)) {
                  currentNumber = (currentNumber * 10) + (currentChar - '0');
              }
              if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                  if (operation == '-') {
                      stack.push(-currentNumber);
                  }
                  else if (operation == '+') {
                      stack.push(currentNumber);
                  }
                  else if (operation == '*') {
                      stack.push(stack.pop() * currentNumber);
                  }
                  else if (operation == '/') {
                      stack.push(stack.pop() / currentNumber);
                  }
                  operation = currentChar;
                  currentNumber = 0;
              }
          }
          int result = 0;
          while (!stack.isEmpty()) {
              result += stack.pop();
          }
          return result;
      }






    public static void main(String[] args) {
        int arr[] = {1,7,8,2,6,2,8,9,10};
        int ans[] = new int[arr.length];
        NGOR(arr, ans);
        for(int num : ans) System.out.print(num+ " ");

    }
}
