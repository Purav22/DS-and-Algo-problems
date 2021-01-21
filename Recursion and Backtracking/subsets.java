import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class subsets {
    public static ArrayList <ArrayList <Integer>> AllSubsets(int nums[], int n){
        // your code here
        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
    
        if (nums == null || nums.length == 0) {
            return results;
        }
        
        Arrays.sort(nums);
        
        List<Integer> subset = new ArrayList<>();
        toFindAllSubsets(nums, results, subset, 0);
        results.remove(0);
        
        return results;
    }
    
        private static void toFindAllSubsets(int[] nums, ArrayList<ArrayList<Integer>> results, List<Integer> subset, int startIndex) {
        results.add(new ArrayList<>(subset));
        
        for (int i = startIndex; i < nums.length; i++) {
            if (i != startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            
            subset.add(nums[i]);
            toFindAllSubsets(nums, results, subset, i + 1);
            subset.remove(subset.size() - 1);            
        }
    }
}
