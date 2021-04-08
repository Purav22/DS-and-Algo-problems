
public class recursionTest {
    public static void main(String[] args) {
        int[] arr = {2,3,5,7};
        int ans = giveTotalAns(arr, 10, 0, "");
        System.out.println(ans);
        
        
        
    }
    static int ans = 0;
    public static int giveSum1(int arr[], int idx){
        if(idx == arr.length) return 0;

        int sum = arr[idx] + giveSum1(arr, idx + 1);

        return sum;
    }
    public static void giveSum2(int arr[], int idx){
        if(idx == arr.length) return;
        
        giveSum2(arr, idx + 1);

        System.out.println(idx);
        ans = ans + arr[idx];
    }


    public static int fibo(int n){
        if(n == 0 || n == 1){
            System.out.println(n);
            return n;
        }
        int myAns = fibo(n - 1) + fibo(n - 2);
        System.out.println(myAns);
        return myAns;
    }

    public static boolean ans(int[] arr, int tar, int idx){
        if(tar == 0) return true;
        if(idx == arr.length) return false;
        
        boolean myAns = ans(arr, tar - arr[idx], idx + 1) || ans(arr, tar, idx + 1);
        return myAns;

    }

    public static int giveTotalAns(int arr[], int total, int idx, String ans){
        if(total == 0) {
            System.out.println(ans);
            return 1;
        }
        if(idx == arr.length) return 0;
        int first = giveTotalAns(arr, total - arr[idx], idx + 1, ans + arr[idx] + " ");
        int secod = giveTotalAns(arr, total, idx + 1, ans);
        System.out.println(idx);

        int myAns = first + secod;
        return myAns;
    }
    
}