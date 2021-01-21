
import java.util.Scanner;

public class moveAndTurn {
    /* package codechef; // don't place package name! */


	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner scan = new Scanner(System.in);
		try {
		    
		    int T = scan.nextInt();
		    while(T-- > 0){
		        int x = scan.nextInt();
		        int y = scan.nextInt();
		        int k = scan.nextInt();
		        int n = scan.nextInt();
		        
		        int startx = x;
		        int starty = y;
		        if(x > y){
		            while(true){
		                if(x - k >= 0){
		                    x = x - k;
		                }else if(x + k <= n){
		                    x = x + k;
		                }
		                if(y - k >=  0){
		                    y = y - k;
		                }else if(y + k <= n){
		                    y = y + k;
		                }
		                
		                if(x == y){
		                    System.out.println("YES");
		                    break;
		                }
		                
		                if(starty == y || startx == x){
		                    System.out.println("NO");
		                    break;
		                }
		            }
		        }
		        else{
    		        while(true){
    		            if(x + k <= n){
		                    x = x + k;
		                }else if(x - k >= 0){
		                    x = x - k;
		                }
		                if(y + k <=  n){
		                    y = y + k;
		                }else if(y - k >= 0){
		                    y = y - k;
		                }
		                
		                if(x == y){
		                    System.out.println("YES");
		                    break;
		                }
		                
		                if(starty == y || startx == x){
		                    System.out.println("NO");
		                    break;
		                }
    		        }
		        }
		    }
		} catch(Exception e) {
		}
	}
	


}
