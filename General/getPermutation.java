import java.util.Scanner;

public class getPermutation {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        int fre[] = new int[26];
        
        solve(str, "");
        solveUnique(str, "");

    }
    
    public static int solve(String str, String ans){
        if(str.length() == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for(int i = 0; i < str.length(); i++){
            
                char ch = str.charAt(i);
                
                
                String res = str.substring(0, i) + str.substring(i+ 1);// res == rest of string (without current character)

                count += solve(res, ans + ch);
            
        }
        return count;
    }

    public static int solveUnique(String str, String ans){
        if(str.length() == 0){
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        boolean visited[] = new boolean[26];
        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if(!visited[ch - 'a']){
                
                visited[ch - 'a'] = true;
                String res = str.substring(0, i) + str.substring(i+ 1);// res == rest of string (without current character)

                count += solveUnique(res, ans + ch);
            
            }
        }   
        return count;
    }
}
