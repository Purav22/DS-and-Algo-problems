package StackAndQueue;

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


    
    public static void main(String[] args) {
        int arr[] = {1,7,8,2,6,2,8,9,10};
        int ans[] = new int[arr.length];
        NGOR(arr, ans);
        for(int num : ans) System.out.print(num+ " ");
    }
}
