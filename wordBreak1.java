import java.util.HashSet;
import java.util.Scanner;

public class wordBreak1 {
    
    
    static HashSet<String> map = new HashSet<>();
    
    public static int wordBreak1Solution(String str, String ans, int len){

        if(str.length() == 0){
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for(int i = 1; i <= str.length(); i++){
            
                
                String left = str.substring(0, i);
                
                if(map.contains(left)){
                    count += wordBreak1Solution(str.substring(i), ans + left + " ", len);
                }
            
        }
        return count;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        
        for(int i = 0; i < n; i++){
            map.add(scan.next());
        }
        int len = 0;
        for(String st :map){
            len = Math.max(len, st.length()); 
        }
        String s = scan.next();
        wordBreak1Solution(s,"",len);
    }

}
