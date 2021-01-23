import java.util.HashSet;
import java.util.Scanner;

public class leetCode1593 {
    public  void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        System.out.println(maxUniqueSplit(str));
       
    }
    private int maxSplit = 0;

    public int maxUniqueSplit(String s) {
        maxSplit = 0;
        helper(s, new HashSet<>());
        return maxSplit;
    }

    private void helper(String s, HashSet<String> hash) {
        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i);
            if (!hash.contains(sub)) {
                hash.add(sub);
                helper(s.substring(i), hash);
                maxSplit = Integer.max(maxSplit, hash.size());
                hash.remove(sub);
            }
        }
    }
}