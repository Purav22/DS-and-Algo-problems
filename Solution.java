import java.util.*;
import java.util.Scanner;
import java.io.*;

public class Solution {
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			
			int T = Integer.parseInt(br.readLine());
			while(T-- > 0){
				String s1[] = br.readLine().split(" ");
				String s2[] = br.readLine().split(" ");
				int n, m, k;
				n = Integer.parseInt(s1[0]);
				m = Integer.parseInt(s1[1]);
				k = Integer.parseInt(s1[2]);

				int[] arr = new int[n];
				int[][] temp = new int[m][3];

				for(int i = 0; i < ; i++){
					arr[i] = Integer.parseInt(s2[i]);
				}
				for(int i = 0; i < m; i++){
					String s[] = br.readLine().split(" ");
					temp[i][0] = Integer.parseInt(s[0]);
					temp[i][1] = Integer.parseInt(s[1]);
					temp[i][2] = Integer.parseInt(s[2]);
				}
				booleanGame(n, m, k, arr, temp);
			
			}
		} catch (Exception e) {
			
		}
	}


	public static void booleanGame(int n, int m, int k, int[] arr, int[][] temp){

		int num = (int) Math.pow(2, n);
		ArrayList<Integer> ans = new ArrayList<>();
		ans.add(0);
		for(int i = 1; i <= num; i++){
			int cal = calculateXI(i, arr, n);

			for(int j = 0; j < m; j++){
				if(temp[j][2] != 0 && isValid(i, j, temp)){
					cal += temp[j][2];
				}
			}
			ans.add(cal);
		}
		Collections.sort(ans, Collections.reverseOrder());
		for(int i = 0; i < k; i++) System.out.print(ans.get(i) +" ");
		System.out.println();
	}

	private static boolean isValid(int i, int j, int[][] temp) {
		String num = Integer.toBinaryString(i);
		
		int si = temp[j][0];
		int ei = temp[j][1];
		if(ei >= num.length()) return false;
		for(int itr = si; itr <= ei; itr++){	
			if(num.charAt(itr) == '0') return false;
		}
		return true;
	}


	private static int calculateXI(int i, int[] arr, int n) {
		String num = Integer.toBinaryString(i);
		String padding = String.format("%" + n + "s", num).replace(' ', '0');
		int ans = 0;
		for(int j = padding.length() - 1; j >= 0; j--){
			ans += arr[j] * (num.charAt(j) - '0');
		} 
		return ans;
	}


	private static boolean isPossible(String str, String s) {
		int i = 0;
		int j = 0;
		while(i < str.length() && j < str.length()){
			if(str.charAt(i) == s.charAt(j)){
				i++;
				
			}
			j++;
		}
		return i == str.length();

		
	}
		

}

