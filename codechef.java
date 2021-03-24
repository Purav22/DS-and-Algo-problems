import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


public class codechef {
    public static void main(String[] args) {
        // Scanner scan = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            
            int T = Integer.parseInt(br.readLine());
            while(T-- > 0){
                String s[] = br.readLine().split(" ");
                int n = Integer.parseInt(s[0]);
                int m = Integer.parseInt(s[1]);
                int k = Integer.parseInt(s[2]);

                long num = 0;

                for(int i = 1; i <= n; i++){
                    int freq = Math.min(i, m);
                    if(freq % 2 == 1){
                        num = num ^ k + i + 1;
                    }
                }

                for(int j = 2; j <= m; j++){
                    int freq = Math.min(n, m - j + 1);
                    if(freq % 2 == 1){
                        num = num ^ k + j + n;
                    }
                }

                
                System.out.println(num);
            }
        } catch (Exception e) {

        }
    }
    private static int giveSqr(int i) {int flag = 0;
        // int num = 1;
        // while(flag == 0){
        //     System.out.println(giveSqr(num++));
        //     System.out.flush();

        //     flag = scan.nextInt();
            
        // }
        // System.out.flush();
        return i * i;
    }
    public static void clgLife4(String[] str){
        int n = Integer.parseInt(str[0]);
        int e = Integer.parseInt(str[1]);
        int h = Integer.parseInt(str[2]);
        int A = Integer.parseInt(str[3]);
        int B = Integer.parseInt(str[4]);
        int C = Integer.parseInt(str[5]);
        
        int ans = clgLife4_(n, e, h, A, B, C);
        if(ans == (int)1e9) System.out.println(-1);
        else System.out.println(ans);
        
    }

    public static int clgLife4_(int n, int e, int h, int A, int B, int C){
        if(n <= 0){
            return 0;
        }


        int ans = (int)1e9;
        // omlet
        if(n * 2 <= e){
            ans = Math.min(ans, n * A);
        }
        //milkshake
        if(n * 3 <= h){
            ans = Math.min(ans, n * B);
        }
        //cake  
        if(e >= n && h >= n){
            ans = Math.min(ans, n * C);
        }
        //omlet && cake
        if(e - n >= 1 && e - n >= n - h){
            if(A - C < 0){
                int temp = Math.min(n - 1, e - n);
                ans = Math.min(ans, (A - C) * temp + (n * C));
            }
            else{
                int temp = Math.max(1, n - h);
                ans = Math.min(ans, (A - C) * temp +(n * C));
            }
        }

        //milkshake & cake
        if((h - n) / 2 >= 1 && ((h - n) / 2 >= n - e)){
            if(B - C < 0){
                int temp = Math.min(n -1, (h - n) / 2);
                ans = Math.min(ans, (B - C) * temp + (n * C));
            }else{
                int temp = Math.max(1, n - e);
                ans = Math.min(ans, (B - C) * temp + (n * C));
            }
        }
        // milkshake & omlet

        if((e / 2 >= 1) && (e / 2 >= (3 * n - h + 2) / 3)){
            if(A - B < 0){
                int temp = Math.min(e / 2, n - 1);
                ans = Math.min(ans, (A - B) * temp + (n * B));
            }
            else{
                int temp = Math.max(1, (3 * n - h + 2) / 3);
                ans = Math.min(ans, (A - B) * temp + (n * B));
            }
        }

        //all item

        if(e >= 3 && h >= 4 && n >= 3){
            ans = Math.min(ans, A + B + C + clgLife4_(n - 3, e - 3, h - 4, A, B, C));
        }

        return ans;
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
