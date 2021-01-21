import java.util.Scanner;

public class redBlueCard {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        while(T-- > 0){
            int n = scan.nextInt();
            String red = scan.next();
            String blue = scan.next();

            int redCount = 0;
            int bleCount = 0;

            for(int i = 0; i < n; i++){
                char ch1 = red.charAt(i);
                char ch2 = blue.charAt(i);
                int r = Character.getNumericValue(ch1);
                int b = Character.getNumericValue(ch2);
                if(r == b){
                    continue;
                }
                else if(r > b){
                    redCount++;
                }else if(b > r){
                    bleCount++;
                }
            }

            if(redCount == bleCount){
                System.out.println("EQUAL");
            }else if(redCount > bleCount){
                System.out.println( "RED");
            }else{
                System.out.println("BLUE");
            }
        }
    }
    
}
