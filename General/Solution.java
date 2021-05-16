import java.util.*;
import java.util.Scanner;
import java.io.*;

public class Solution {
	
	public static void main(String[] args) throws IOException{
		
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// try {
			
		// 	int T = Integer.parseInt(br.readLine());
		// 	while(T-- > 0){
		// 		String[] str1 = br.readLine().split(" ");
		// 		int n = Integer.parseInt(str1[0]);
		// 		int q = Integer.parseInt(str1[1]);

		// 		int arr[] = new int[n];
		// 		String str2[] = br.readLine().split(" ");

		// 		for(int i = 0; i < n; i++){
		// 			arr[i] = Integer.parseInt(str2[i]);
		// 		}

		// 		solve(arr);

		// 		for(int i = 0; i < q; i++){
		// 			String str3[] = br.readLine().split(" ");
		// 			int x = Integer.parseInt(str3[0]);
		// 			int v = Integer.parseInt(str3[1]);

		// 			arr[x - 1] = v;
		// 			solve(arr);
		// 		}
			
		// 	}
		// } catch (Exception e) {
			
		// }


	}


	private static void solve(int[] arr) {
		ArrayList<Integer> ans = new ArrayList<>();
		int count = 0;
		for(int i = 0; i < arr.length; i++){
			
			if(i == 0) {
				ans.add(arr[i]);
				count = count | arr[i];
				continue;
			}
			int num = ans.get(i - 1);
			num = num & arr[i];
			count = count | num;
			ans.add(num);
		}
		//System.out.println(ans.toString());
		for(int i = 1; i < arr.length; i++){
			
		}

		//System.out.println(ans.toString());
		System.out.println(count);
		
		// int num = 0;
		// for(int i = 0; i < arr.length; i++){
		// 	for(int j = i; j < arr.length; j++){
		// 		num = arr[j];
		// 		for(int k = i; k <= j; k++){
		// 			num = num & arr[k];
		// 		}
		// 		ans.add(num);
		// 	}
			
		// }
		// int a = 0;
		// 	for(int n : ans) {
		// 		a = a | n; 
		// 	}
		// System.out.println(a);
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

