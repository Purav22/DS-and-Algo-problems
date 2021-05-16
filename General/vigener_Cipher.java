import java.util.Scanner;

public class vigener_Cipher {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Plain Text");
        String plainText = scan.nextLine();
        System.out.println("Enter Key");
        String key = scan.nextLine();
        
        System.out.println(plainText + " " + key);
        vigener(plainText, key);
        
    }

    private static void vigener(String str, String key) {
        
        String k = "",encryptMessage= "",decryptMessage = "";
        for(int i = 0, j = 0; i < str.length(); i++, j++)
        {
                if(j==key.length())
                {
                    j=0;
                }
                k += key.charAt(j);
        }
        for(int i = 0; i < str.length(); i++)
        {
                if(str.charAt(i)!=' ')
                    encryptMessage += (char)(((str.charAt(i)+k.charAt(i))%26)+'A');
                else
                    encryptMessage += str.charAt(i);
        }

        for(int i = 0; i < encryptMessage.length(); i++)
        {
            if(encryptMessage.charAt(i) != ' ')
                decryptMessage += (char)(((encryptMessage.charAt(i)-k.charAt(i))%26)+'A');
            else
                decryptMessage += encryptMessage.charAt(i);
        }
        System.out.println("Encrypted Message-->" + encryptMessage);
        System.out.println("Decrypted Message-->" + decryptMessage);
    }
}
