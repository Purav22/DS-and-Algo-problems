import java.util.Scanner;
import java.util.Stack;

class dungeon {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        
        int n = scan.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = scan.nextInt();
        }
        System.out.println(largestRectangleArea(arr)); 
        
    }
    public static int largestRectangleArea(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        
        int left[] = new int[n];
        left[0] = -1;
        st.push(0);
        for(int i = 1; i < n; i++){
            
            while(st.size() > 0 &&arr[i] <= arr[st.peek()]){
                st.pop();
            }
            if(st.size() == 0){
                left[i] = -1;
            }
            else{
                left[i] = st.peek();
            }
            st.push(i);
        }
        for(int num : left){
            System.out.println(num);
        }
        st = new Stack<>();
        int right[] = new int[n];
        right[n - 1] = n;
        st.push(n - 1);
        for(int i = n - 2; i >= 0; i--){
            while(st.size() > 0 && arr[i] <= arr[st.peek()]){
                st.pop();
            }
            if(st.size() == 0){
                right[i] = n;
            }
            else{
                right[i] = st.peek();
            }
            st.push(i);
        }
        for(int num : right){
            System.out.println(num);
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            int count = (right[i] - 1) - (left[i] + 1) + 1;
            if(max < arr[i] * count){
                max = arr[i] * count;
            }
        }
        return max;
    }
}