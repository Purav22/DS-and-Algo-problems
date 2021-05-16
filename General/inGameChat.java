import java.util.Scanner;

public class inGameChat {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int T = scan.nextInt();
        while(T-- > 0){
            int n = scan.nextInt();
            String s = scan.next();

            int idx = n - 1;
            int count = 0;
            while( idx >= 0 && s.charAt(idx) == ')'){
                count++;
                idx--;
            }
            int rem = n - count;
            if(count > rem)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
