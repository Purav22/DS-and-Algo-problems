import java.util.*;
import java.util.Scanner;
import java.io.*;

public class Solution {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try {
		int N=Integer.parseInt(br.readLine());
		int no[]=new int[N];
		String type[]=new String[N];
		String com[]=new String[N];
		int pri[]=new int[N];
		int q[]=new int[N];
		
		ArrayList<String> ans1=new ArrayList<>();
		ArrayList<Integer> ans2=new ArrayList<>();
		
		for(int i=0;i<N;i++) {
		    String s[]=br.readLine().split(" ");
			no[i]=Integer.parseInt(s[0]);
			type[i]=s[1];
			com[i]=s[2];
			pri[i]=Integer.parseInt(s[3]);
			q[i]=Integer.parseInt(s[4]);
		//	System.out.println(type[i]+" "+com[i]);
		}
		
		for(int i=0;i<N;i++) {
			if(type[i].equals("Sell")) {
			   
				for(int j=0;j<N;j++) {
					if(q[i]!=0) {
					if(!type[j].equals(type[i]) && com[j].equals(com[i]) && q[j]!=0 ) {
						if(pri[j]>=pri[i]) {
							if(q[i]<=q[j]) {
								q[j]=q[j]-q[i];
								q[i]=0;
								ans1.add(com[i]);
								ans2.add(pri[i]);
							//	System.out.println(ans1.get(i)+":"+ans2.get(i));
							}
							else {
								q[i]=q[i]-q[j];
								q[j]=0;
								ans1.add(com[i]);
								ans2.add(pri[i]);
							
							//	System.out.println(ans1.get(i)+":"+ans2.get(i));
							}
						}
					}
				}
					else {
					    //System.out.println("1");
						break;
					}
				}
				
			}
		}
		ArrayList<Integer> arr=new ArrayList<>(ans2);
		StringBuffer s1=new StringBuffer();
		Collections.sort(arr);
		for(int i=0;i<arr.size();i++){
		    int j=0;
		    while(ans2.get(j)!=arr.get(i)){j++;}
		    s1.append(ans1.get(j)+":"+arr.get(i));
		    System.out.println(s1);
		}
		
		
// 		for(int i=0;i<ans1.size();i++) {
// 			System.out.println(ans1.get(i)+":"+ans2.get(i));
// 		}

	
		

		}catch(Exception e) {
			
		}
		}
		
		

}

