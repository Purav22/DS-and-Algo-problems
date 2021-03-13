import java.util.Arrays;
import java.util.Scanner;

public class codechef {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try {
            
            int T = scan.nextInt();
            while(T-- > 0){
                int N = scan.nextInt();

                int arr[] = new int[N];
                for(int i = 0; i < N; i++){
                    arr[i] = scan.nextInt();
                }
                System.out.println(spaceArray(arr));
            }
        } catch (Exception e) {
            
        }
    }

    public static String spaceArray(int[] arr){

        Arrays.sort(arr);
        
        int num = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == i + 1){
                continue;
            }
            else if(i + 1 > arr[i]){
                num += (i + 1) - arr[i];
            }else if(arr[i] > i + 1){
                return "First";
            }
        }
        if(num % 2 == 0) return "Second";

        return "First";
    }
}
