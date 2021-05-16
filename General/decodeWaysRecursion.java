import java.util.Scanner;

public class decodeWaysRecursion {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        printEncodings(s, 0, "");

    }

    public static int printEncodings(String str, int idx, String ans) {
        if(idx == str.length()){
            System.out.println(ans);
            return 1;
        }
        
        if(str.charAt(idx) == '0'){
            return 0;
        }
        
        int count = 0;
        
        count += printEncodings(str, idx + 1, ans + (char)(str.charAt(idx) - '1' + 'a'));
        
        if(idx + 1 < str.length()){
            
            int num = (str.charAt(idx) -'0') * 10  + (str.charAt(idx + 1) -'0');
            
            if(num < 27)
                count += printEncodings(str, idx + 2, ans + (char)(num - 1 + 'a'));
        }
        return count;

    }
}
