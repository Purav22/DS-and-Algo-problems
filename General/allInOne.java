import java.util.*;

public class allInOne {
    static int dir[][] = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
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


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        lengthOfLongestSubstring("abcdabcdasdsac");
        // int T = scan.nextInt();
		//     while(T-- > 0){
		//         int n = scan.nextInt();
		//         int k = scan.nextInt();
		//         Integer arr[] = new Integer[n];
		//         int sum = 0;
		        
		//         for(int i = 0; i < n; i++){
		//             arr[i] = scan.nextInt();
		//             sum += arr[i];
		//         }
		        
		//         if(sum < 2 * k){
		//             System.out.println(-1);
		//         }else if(sum == 2 * k){
		//             System.out.println(n);
		//         }
		//         else{
        //             System.out.println(CPLcodechef(arr, k ,sum));
        //            //CPLcodechef(arr, k ,sum);
		//         }
		//     }
        
        // String text = scan.next();
        //dcPract1(text);

        // String ans[] = givePath(5, 5, new boolean[5][5], "", 0, 0);
        // System.out.println(ans[0] + " " + ans[1]);
    }
    
}
