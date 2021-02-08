import java.util.Scanner;

public class golomb_Code {
    public static void main(String[] args) {
        
    
    Scanner scan = new Scanner(System.in);
    System.out.println("********Enter n and m*********");
    int n = scan.nextInt();
    int m = scan.nextInt();

    int q = n / m;
    int r = n % m;

    String str = "1";
    for(int i = 0; i < q; i++){
        str += "1";
    }
    String gc1 = str + '0';
    int b = (int)Math.floor((int)(Math.log(m) / Math.log(2)));

    int k = (int)Math.pow(2, b + 1) - m;
    String gc2 = "";
    if(r < k){
         gc2 = Integer.toBinaryString(r);
        int len = gc2.length();
        if(len < b){
            String temp = '0' + "";
            for(int j = 0; j < b - len; j++){
                temp += '0';
            }
            gc2 = temp + gc2;
        }
    }
    else{
         gc2 = Integer.toBinaryString(r + k);
        int len = gc2.length();
        if(len < b + 1){
            String temp = '0' + "";
            for(int j = 0; j < b + 1 - len; j++){
                temp += '0';
            }
            gc2 = temp + gc2;
        }
    }
    String gc = gc1 + gc2;
    
    System.out.println("Golomb Code for n = " + n + "and m = " + m  + "is--->" + gc);
    }
}
