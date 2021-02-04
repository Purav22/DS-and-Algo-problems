import java.util.Scanner;

public class affine_Cipher {
    static int a=17,b=20;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Plain Text");
        String plainText = scan.nextLine();

        String encryptedMessage = encrypt(plainText);
        String decryptedMessage = decrypt(encryptedMessage);

        System.out.println("Encrypted Message is-->" + encryptedMessage);
        System.out.println("Decrypted Message is-->" + decryptedMessage);
    }

    private static String decrypt(String str) {
        String ans = "";
        for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) != ' ')
            ans = ans + (char)((((a * (str.charAt(i)-'A') ) + b) % 26) + 'A');
            else
            ans += str.charAt(i);
        }
        return ans;
    }

    private static String encrypt(String str) {
        String ans = "";

        int temp=0,flag=0;
        for (int i = 0; i < 26; i++)
        {
            flag = (a * i) % 26;
            if (flag == 1)
            {
                temp = i;
            }
        }
        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)!=' ')
            ans = ans + (char)((temp * ((str.charAt(i)+'A')  - b) % 26) + 'A');
            else
            ans += str.charAt(i);
        }
        return ans;
    }
}
