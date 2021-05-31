import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        // Scanner scan = new Scanner(System.in);

        // int T = scan.nextInt();
        // for(int t = 1; t <= T; t++){
        //     long g = scan.nextLong();

        //     long k = 1;
        //     long ans = 0;

        //     ArrayList<Long> arr = new ArrayList<>();
        //     long sum = 0;
        //     int i = 1;
        //     while(sum <= g){
        //         sum = (i * (i - 1)) / 2;
        //         arr.add(sum);
        //         i++;
        //     }
            
        //     i = 1;

        //     while(k <= g){
        //         long temp = 0;
        //         i = 1;

        //         while(true){
                    
        //             if(i >= arr.size()) break;
        //             temp = i * k + (arr.get(i));
        //             if(temp == g) ans++;
        //             i++;
        //         }

        //         k++;
        //     }

        //     System.out.println("Case #" + t + ": " + ans);
        // }

        Scanner scan = new Scanner(System.in);
		
		try {
		    int T = scan.nextInt();
		    
		    while(T-- > 0){
		        int n = scan.nextInt();
		        int k = scan.nextInt();
		        HashMap<Long, Integer> map = new HashMap<>();
		        int arr[] = new int[n];
                ArrayList<Long> sum = new ArrayList<>();
		        long temp = 0;
		        for(int i = 0; i < n - k; i++){
		            arr[i] = scan.nextInt();
                    if(i - k - 1 >= 0){
                        if(i == 0){
                            for(int j = 0; j < k; j++){
                                temp += arr[j];
                            }
                            sum.add(temp);
                        }
                        else{
                            temp += arr[i];
                            sum.add(temp - sum.get(sum.size() - 1));
                        }
                        map.put(sum.get(sum.size() - 1), 1);
                    }
		        }

                int ans = map.size() - 1;
		        System.out.println(ans);
            
		        
		    }
		} catch(Exception e) {
		    
		}
    }

    // private static boolean solve(long k, long g) {

    //     long sum = 0;
    //     int i = 1;
    //     while(sum <= g){
    //         sum = (i * k) + ((i * (i - 1)) / 2);
    //         i++;
    //         if(sum == g) return true;
    //     }

    //     return false;
    // }
    
}
