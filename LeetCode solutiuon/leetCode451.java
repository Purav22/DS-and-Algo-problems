import java.util.*;

public class leetCode451 {
    public static void main(String[] args) {
        Scanner scan  = new Scanner(System.in);
        String s = scan.next();

        System.out.println(frequencySort(s));
    }
    public static String frequencySort(String s) {
        Map<Character,Integer> map = new HashMap<>();
        
        for(int i = 0; i < s.length(); i++){    
            if(!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i), 1);
            }else{
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            }
        }
        Map<Character,Integer> result = sortByValue(map);
        StringBuilder sb = new StringBuilder();
        System.out.println(result);
        for(Character ch : map.keySet()){
            int count  = map.get(ch);
            while(count-- > 0)
                sb.append(ch);
        }
        System.out.println(sb);
        
        return sb.reverse().toString();
        
    }
    public static Map<Character, Integer> sortByValue(Map<Character, Integer> unsortMap) {

        List<Map.Entry<Character, Integer>> list =
                new LinkedList<Map.Entry<Character, Integer>>(unsortMap.entrySet());


        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

       
        Map<Character, Integer> sortedMap = new LinkedHashMap<Character, Integer>();
        for (Map.Entry<Character, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }


        return sortedMap;
    }
}