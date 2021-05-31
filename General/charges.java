package General;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class charges {
    
    
    public static void main (String[] args) throws java.lang.Exception {
		// your code goes here
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
		    int T = Integer.parseInt(br.readLine());

            while(T-- > 0){
                String str1[] = br.readLine().split(" ");
                int n = Integer.parseInt(str1[0]);
                int k = Integer.parseInt(str1[1]);

                String attoms = br.readLine();
                char[] arr = attoms.toCharArray();

                long sum = 0;
                for(int i = 0; i < n - 1; i++){
                    if(arr[i] == arr[i + 1]) sum += 2;
                    else sum += 1;
                }
                String[] str2 = br.readLine().split(" ");
                for(int i = 0; i < str2.length; i++){
                    int idx = Integer.parseInt(str2[i]) - 1;

                    if(arr[idx] == '0')
                        arr[idx] = '1';
                    else
                        arr[idx] = '0';

                    if(idx != 0){
                        if(arr[idx - 1] == arr[idx]) sum += 1;
                        else sum -= 1;
                    }

                    if(idx != n - 1){
                        if(arr[idx + 1] == arr[idx]) sum += 1;
                        else sum -= 1;
                    }

                    System.out.println(sum);
                }
                
            }
		} catch(Exception e) {
		}
	}
}
