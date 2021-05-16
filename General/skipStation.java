import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class skipStation {
    public static void main(String[] args) throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String day[] = br.readLine().split(" ");
        int days[] = new int[5];
        for(int i = 0; i < 5; i++){
            days[i] = Integer.parseInt(day[i]);
        }
        int n = Integer.parseInt(br.readLine());

        int arr[][] = new int[n][5];
        int min[] = new int[5];
        for(int i = 0; i < n; i++){
            String com[] = br.readLine().split(" ");
            for(int j = 0; j < 5; j++){
                arr[i][j] = Integer.parseInt(com[j]);
            }
        }
        for(int i = 0; i < 5; i++){
           int num = Integer.MAX_VALUE;
            for(int j = 0; j < n; j++){
                if(num > arr[j][i]){
                    num = arr[j][i];
                }
            }
            min[i] = num;
        }
        int m = Integer.parseInt(br.readLine());
        String travel[] = br.readLine().split(" ");
        int noOfDays[] = new int[m];
        ArrayList<Integer> count = new ArrayList<>();
        int c = 1;
        for(int i = 0; i < m; i++){
            noOfDays[i] = Integer.parseInt(travel[i]);
            if(i != 0){
            
                if(noOfDays[i] == noOfDays[i - 1] + 1){
                    c++;
                }else{
                    count.add(c);
                    c = 1;
                }
            }
        }
        if(c == 1)
        count.add(1);
        else
        count.add(c);

        int ans = 0;
        for(int itr = 0; itr < count.size(); itr++){
            int len = count.get(itr) + 1;
            int dp[][] = new int[5][len];

            for(int i = 0; i < 5; i++){
                for(int j = 0; j < len; j++){
                    if(j == 0){
                        dp[i][j] = 0;
                    }else if(i == 0){
                        dp[i][j] = j / days[i] * min[i];
                    }
                    else{
                        if(j >= days[i]){
                            dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - days[i]] + min[i]);
                        }else{
                            dp[i][j] = dp[i - 1][j];
                        }
                    }
                }
            }
            ans += dp[4][len - 1];
        }
        System.out.println(ans);
    }
}
