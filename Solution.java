import java.util.*;
import java.util.Scanner;
import java.io.*;

public class Solution {
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			
			int T = Integer.parseInt(br.readLine());
			while(T-- > 0){
				String s = br.readLine();
				long num = 0;
				while(true){
					if(!isPossible(Long.toBinaryString(num), s)){
						System.out.println(num);
						break;
					}
					num++;
				}
			}
		} catch (Exception e) {
			
		}
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

