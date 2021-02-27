import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class allInOne {
    public static void main (String[] args) throws java.lang.Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            int T = Integer.parseInt(br.readLine());

            while(T-- > 0){
                String input[] = br.readLine().split(" ");

                int L = Integer.parseInt(input[0]);
                int R = Integer.parseInt(input[1]);
                
                int num = R -L + 1;
                
               while(true){
                if(isPrime(num))
                  break;
                  num++;
               }
               System.out.println(num);
            }
        } catch (Exception e) {
            
        }
    }
   
    private static boolean[] giveAllPrime(int l) {
        boolean ans[] = new boolean[l+ 1];
        Arrays.fill(ans,true);
        for(int i = 2 ; i * i < ans.length; i++){
            if(ans[i]){
                for(int p = i * 2; p <= l; p += i){
                    ans[p] = false;
                }
            }
        }
        return ans;
    }
    boolean isPrime(int num){
        boolean flag = false;
    for (int i = 2; i <= num / 2; ++i) {
      // condition for nonprime number
      if (num % i == 0) {
        flag = true;
        break;
      }
    }
        if (!flag)
        return true;
    return false;
    }

    static int dir[][] = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };
    static String ch[] = {"L", "U", "R", "D"};
    public static String[] givePath(int n, int m, boolean visited[][], String ans, int row, int col){
        if(row == n -1 && col == m - 1){
            //System.out.println(1);
            String[] a = new String[2];
            a[0] = ans;
            a[1] = ans;
            return a;
        }

    
        visited[row][col] = true;
        String Long = "";
        String sort = "";
        for(int i = 0; i < 4; i++){
            int ni = row + dir[i][0];
            int nj = col + dir[i][1];

            if(ni >= 0 && ni < n && nj >= 0 && nj < m && !visited[ni][nj]){
                String str[] = givePath(n, m, visited, ans + ch[i], ni, nj);

                if(Long.length() < str[0].length()){
                    Long = str[0];
                }
                if(sort.length() == 0){
                    sort = str[1];
                }else{
                    if(sort.length() > str[1].length()){
                        sort = str[1];
                    }
                }
            }
        }
        visited[row][col] = false;
        String aa[] =  new String[2];
        aa[0] = Long;
        aa[1] = sort; 
        return aa;

    }

    public static class Pair{
        int length = 0;
        String str = "";
        public Pair(int length, String str){
            this.length = length;
            this.str = str;
        }
    }
// ***********shortest and longest path code without using global and static variable ********
    public static Pair giveMaxMinPath(int sr, int sc, int dr, int dc, boolean visited[][], int dir[][], String ch[]){
        if(sr == dr && sc == dc){
            Pair base = new Pair(0, "");
            return base;
        }

        Pair myAns = new Pair(-1, "");
        visited[sr][sc] = true;

        for(int i = 0; i < dir.length; i++){
            int r = sr + dir[i][0];
            int c = sc + dir[i][1];

            if(r >= 0 && c >= 0 && r <= dr && c <= dc && !visited[r][c]){
                Pair recAns = giveMaxMinPath(r, c, dr, dc, visited, dir, ch);
                if(recAns.length + 1 > myAns.length){
                    myAns.length = recAns.length + 1;
                    myAns.str = ch[i] + recAns.str;
                }

            }
        }
        return myAns;
    }
   

    public static int CPLcodechef(Integer arr[], int k, int sum){

        int n = arr.length;
        int sum1 = 0;
        int sum2 = 0;
        boolean visited[] = new boolean[n];

        Arrays.sort(arr,Collections.reverseOrder());

        
        sum1 += arr[0];
        sum2 += arr[1];
        visited[0] = true;
        visited[1] = true;
        int count = 2;
        int i = 2;
        while(sum1 < k && i < n){
            
            if(arr[i] > k - sum1){
                i++;
               // System.out.println(1);
            }else{
               // System.out.println(2);
                sum1 += arr[i];
                //System.out.println(arr[i]);
                visited[i] = true;
                count++;
                i = 2;
            }

        }
        //System.out.println(sum1);
        i = 2;
        while(sum2 < k && i < n){
            //System.out.println(2);
            if(!visited[i]){
               // System.out.println(21);
                sum2 += arr[i];
                //System.out.println(arr[i]);
                i++;
                count++;
            }else{
                i++;
            }
        }
        //System.out.println(sum2);

        if(sum1 >= k && sum2 >= k){
            return count;
        }

        return -1;

    }
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); 
    
        for (int j = 0, i = 0; j < n; j++) {
            System.out.println("************" + j +"***********");
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
                System.out.println(i);
            }
            ans = Math.max(ans, j - i + 1);
            System.out.print(ans + " ");
            System.out.println(s.charAt(j) + " " + (j + 1));
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

public static ArrayList<Integer> findPrime(int n){
    int flag;
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
        
        if (i == 1 || i == 0)
            continue;

        
        flag = 1;

        for (int j = 2; j <= i / 2; ++j) {
            if (i % j == 0) {
                flag = 0;
                break;
            }
        }

        
        if (flag == 1)
            list.add(i);

        
    }
    return list;
}
public static boolean isPrime(int n){
    int i,m=0;      
   
    m=n/2;      
    if(n==0||n==1){  
     return false;    
    }else{  
     for(i=2;i<=m;i++){      
      if(n%i==0){      
        return false;        
      }      
     }      
     return true;  
    }
}
public void chefAndMeeting(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
        
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            StringBuilder ans = new StringBuilder();
            String p[] = br.readLine().split(" ");
            //System.out.println(p);
            String meet = "";
            //String timing = p[1];
           
            //System.out.println(meetting);
            if(p[1].equals("PM")){
                if(p[0].charAt(0) =='1' && p[0].charAt(1) =='2'){
                    meet =p[0];
                    // System.out.println(meet);
                }else{

                    int temp = (int)(p[0].charAt(0) - 48);
                    temp *= 10;
                    temp += (int)(p[0].charAt(1) - 48);
                    temp += 12;
                    int rem = temp % 10;
                    char ch1 = (char)(rem + 48);
                    char ch2 = (char)((int)(temp / 10) + 48);
                    
                     meet = ch2 + ""  + ch1 + "" + p[0].substring(2);
                    //  System.out.println(meet);
                }
            }else{
                if(p[0].charAt(0) =='1' && p[0].charAt(1) =='2'){
                    int temp = (int)(p[0].charAt(0) - 48);
                    temp *= 10;
                    temp += (int)(p[0].charAt(1) - 48);
                    temp -= 12;
                    int rem = temp % 10;
                    char ch1 = (char)(rem + 48);
                    System.out.println(ch1);
                    char ch2 = (char)((int)(temp / 10) + 48);
                    System.out.println(ch2);
                     meet = ch2 + ""  + ch1 + "" + p[0].substring(2);
                      System.out.println(meet);
                }
                else{
                    meet = p[0];
                }
            }
            int n = Integer.parseInt(br.readLine());

            for(int i = 0; i < n; i++){
                String str[] = br.readLine().split(" ");
                //String temp1 = str.charAt(0) + "" + str.charAt(1) + "" + '.' + "" + str.charAt(3) + "" + str.charAt(4) + "" + "";
                //float start = Float.parseFloat(temp);
                String temp1 = "";
                //String ampm = str[1];
                if(str[1].equals("PM")){
                    if(str[0].charAt(0) =='1' && str[0].charAt(1) =='2'){
                        temp1 = str[0];
                        // System.out.println(temp1);
                    }else{  
    
                        int temp = (int)(str[0].charAt(0) - 48);
                        temp *= 10;
                        temp += (int)(str[0].charAt(1) - 48);
                        temp += 12;
                        int rem = temp % 10;
                        char ch1 = (char)(rem + 48);
                        char ch2 = (char)((int)(temp / 10) + 48);
                        
                         temp1 = ch2 + ""  + ch1 + "" + str[0].substring(2);
                        //  System.out.println(temp1);
                    }
                }else{
                    if(str[0].charAt(0) =='1' && str[0].charAt(1) =='2'){
                        int temp = (int)(str[0].charAt(0) - 48);
                        temp *= 10;
                        temp += (int)(str[0].charAt(1) - 48);
                        temp -= 12;
                        int rem = temp % 10;
                        char ch1 = (char)(rem + 48);
                        char ch2 = (char)((int)(temp / 10) + 48);
                         temp1 = ch2 + ""  + ch1 + "" + str[0].substring(2);
                        //  System.out.println(temp1);
                    }else{
                        temp1 = str[0];
                    }
                }
                String temp2 = "";
                //String ampm2 = str[3];
                if(str[3].equals("PM")){
                    if(str[2].charAt(0) =='1' && str[2].charAt(1) =='2'){
                        temp2 = str[2];
                        //  System.out.println(temp2);
                    }else{
    
                        int temp = (int)(str[2].charAt(0) - 48);
                        temp *= 10;
                        temp += (int)(str[2].charAt(1) - 48);
                        temp += 12;
                        int rem = temp % 10;
                        char ch1 = (char)(rem + 48);
                        char ch2 = (char)((int)(temp / 10) + 48);
                        
                         temp2 = ch2 + ""  + ch1 + "" + str[2].substring(2);
                        //  System.out.println(temp2);
                    }
                }else{
                    if(str[2].charAt(0) =='1' && str[2].charAt(1) =='2'){
                        int temp = (int)(str[2].charAt(0) - 48);
                        temp *= 10;
                        temp += (int)(str[2].charAt(1) - 48);
                        temp -= 12;
                        int rem = temp % 10;
                        char ch1 = (char)(rem + 48);
                        char ch2 = (char)((int)(temp / 10) + 48);
                         temp2 = ch2 + ""  + ch1 + "" + str[2].substring(2);
                        //  System.out.println(temp2);
                    }
                    else{
                        temp2 = str[2];
                    }
                }

                // int num = 0;
                // for(int k = 0; k < meet.length(); k++) num += (int)meet.charAt(k);
                // int num1 = 0;
                // for(int k = 0; k < temp1.length(); k++) num1 += (int)temp1.charAt(k);
                // int num2 = 0;   
                // for(int k = 0; k < temp2.length(); k++) num2 += (int)temp2.charAt(k);
                //System.out.println(temp1);
                String s1 = meet.replace(":",".");
                String s2 = temp1.replace(":",".");
                String s3 = temp2.replace(":",".");
                //System.out.println(s1 + " " + s2 + "- " + s3);
                float num = Float.parseFloat(s1);
                float num1 = Float.parseFloat(s2);
                float num2 = Float.parseFloat(s3);
                //System.out.println(num + " " + num1 + " " + num2);
              // System.out.println(s1 + s2 + s3);
                if(num >= num1 && num <= num2){
                    ans.append(1);
                }else
                    ans.append(0);

            }
            System.out.println(ans);
        }
        
        
        
    } catch(Exception e) {
    }
}
static ArrayList<String> list;

public static int teamName(String[] name , int n){
   
    for(int i = 0; i < n; i++){
        for(int j = i + 1; j < n; j++){
            String s1 = name[i];
            String s2 = name[j];
            if(s1.length() == s2.length()){
            if(s1.charAt(0) != s2.charAt(0) && isDiff(s1, s2)){
                String t1 = s2.charAt(0) + "" + s1.substring(1);
                String t2 = s1.charAt(0) + "" + s2.substring(1);
                if(list.contains(t1) == false && list.contains(t2) == false){
                    list.add(s1);
                    list.add(s2);
                }
            }
        }else{
            String t1 = s2.charAt(0) + "" + s1.substring(1);
            String t2 = s1.charAt(0) + "" + s2.substring(1);
            if(list.contains(t1) == false && list.contains(t2) == false){
                list.add(s1);
                list.add(s2);
            }
        }
        }
    }
    return list.size() - n;
}

public static boolean isDiff(String s1, String s2) {

    for(int i = 1; i < s1.length(); i++){
        if(s1.charAt(i) != s2.charAt(i)){
            return true;
        }
    }
    return false;
}

public static int jumpFrog(int N, int[] w, int[] l, int[] idx) {
    int count  = 0;

    for(int i = 2; i <= N; i++){
        if(idx[i] > idx[i - 1]){
            continue;
        }else{
            int num = idx[i];
            while(num <= idx[i - 1]){
                num += l[idx[i]];
                count++;
            }
            idx[i] = num;
        }
    }
    return count;
}
    
}
