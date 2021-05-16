import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class queHM {
    

    //1207
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int num : arr){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        HashSet<Integer> set = new HashSet<>();

        for(Integer key : map.keySet()){
            if(set.contains(map.get(key))) return false;

            set.add(map.get(key));
        }

        return true;
    }

    //811
    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> counts = new HashMap();
        for (String domain: cpdomains) {
            String[] str = domain.split(" ");
            
            //System.out.println(Arrays.toString(str));
            String[] rest = str[1].split("\\.");
            int count = Integer.valueOf(str[0]);
            String cur = "";
            for (int i = rest.length - 1; i >= 0; --i) {
                cur = rest[i] + (i < rest.length - 1 ? "." : "") + cur;
                counts.put(cur, counts.getOrDefault(cur, 0) + count);
            }
        }

        List<String> ans = new ArrayList();
        for (String dom: counts.keySet())
            ans.add("" + counts.get(dom) + " " + dom);
        return ans;
    }

    //1002
    public List<String> commonChars(String[] A) {
        int [] res = new int [26];
        
        for(int i = 0; i < A[0].length(); i++){
            res[(int)A[0].charAt(i) - 97] += 1;

        }
        
        for(int i = 0; i < A.length; i++){
            int [] temp = new int [26];
            for(int j = 0; j < A[i].length(); j++){
                temp[(int)A[i].charAt(j) - 97] += 1;
            }
            
            for(int j = 0; j < 26; j++){
                res[j] = Math.min(temp[j], res[j]);
            }
        }
        List<String> list = new ArrayList<>();
        for(int i = 0; i < res.length; i++){
            for(int j = 0; j < res[i]; j++){
                Character temp = (char)(i+97);
                list.add(temp.toString());
            }
        }
        return list;
    }

    //692
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
         
         
         for(int i = 0; i < words.length; i++){
             
             map.put(words[i], map.getOrDefault(words[i], 0) + 1);
         }
         
         
         
         PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
              int x = map.get(a);
              int y = map.get(b);
             
             if(x != y) return x - y;
             
             return b.compareTo(a);
         });
         
         for(String key : map.keySet()){
             
             pq.add(key);    
             if(pq.size() > k) pq.remove();
         }
         
         String[] ans = new String[pq.size()];
         int i = pq.size() - 1;
         while(pq.size() != 0){
             ans[i] = pq.remove();
             i--;
         }
 
         return Arrays.asList(ans);
     }


     

}
