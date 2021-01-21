import java.util.Arrays;
import java.util.Scanner;


public class leetcode42 {
    public static void main(String[] args) {
        Scanner scan  = new Scanner(System.in);
        int n = scan.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = scan.nextInt();
        }
        Arrays.sort(arr);
        System.out.println(trap(arr));
        
    }
    public static int trap(int[] arr) {
        
        int n = arr.length;
        if(n == 0)
            return 0;
        
        int left[] = new int[n];
        int right[] = new int[n];
        
        
        left[0] = -1;
        
        int max = arr[0];
        int idx = 0;
        for(int i = 1; i < n; i++){
            if(arr[i] > max){
                max = arr[i];
                idx = i;
                left[i] = -1;
            }else{
                left[i] = idx;
            }
        }
        
        right[n - 1] = -1;
        
        max = arr[n - 1];
        idx = n - 1;
        for(int i = n - 2; i >= 0; i--){
            if(arr[i] > max){
                max = arr[i];
                idx = i;
                right[i] = -1;
            }else{
                right[i] = idx;
            }
        }
        for(int num : left)
            System.out.println(num);
        for(int num : right)
            System.out.println(num);
        int ans = 0;
        for(int i = 0; i < n; i++){
            if(left[i] == -1 || right[i] == -1){
                continue;
            }
            ans += Math.min(arr[left[i]], arr[right[i]]) - arr[i];
        }
        return ans;
    }
}