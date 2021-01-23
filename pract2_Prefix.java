import java.util.HashSet;
import java.util.Scanner;

public class pract2_Prefix {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of Character");
        int n = scan.nextInt();
        String str[] = new String[n];
        HashSet<String> set = new HashSet<>();
        boolean ans = false;
        for(int i = 0; i < n; i++){
            System.out.println("Enter Character:");
            str[i] = scan.next();
            System.out.println("Enter code:");
            String s = scan.next();
            for(int j = 0; j < s.length() - 1; j++){
                String s1 = s.substring(0, j + 1);
                if(set.contains(s1)){
                    System.out.println("Code is not Prefix");
                    ans = true;
                }
                if(ans)
                    break;
            }
            if(ans)
                break;
            set.add(s);

        }
        if(!ans){
            System.out.println("Code is Prefix");
        }

    }
    
}
