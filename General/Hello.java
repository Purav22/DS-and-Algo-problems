import java.util.*;
import java.io.IOException;
import java.lang.*;

public class Hello{
    public static void main(final String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int arr[] = new int[n];
        for(int  i = 0; i < n; i++){
            arr[i] = scan.nextInt();
        }
        System.out.println(sumOddLengthSubarrays(arr));
    }

    public static int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        
        int count = 3;
        for(int i = 0; i < arr.length; i++){
            sum+= arr[i];
        }
        //Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < arr.length; i++){
            if(count > arr.length){
                break;
            }
            //System.out.println(sum +" "+count);
            sum += counter(arr, count);
            count = count + 2;
        }
        return sum;

    }

    public static int counter(int arr[], int count) {
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            int j = i;
            System.out.println(sum +" "+count);
            while(j - i < count &&  count + i <= arr.length){

                sum += arr[j];
                j++;
            }
            System.out.println(sum +" "+count);
        }
        return sum;
    }
}