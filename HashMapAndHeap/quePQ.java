package HashMapAndHeap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class quePQ {
    //871

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            return b - a;
        });
        
        int count = 0;
        int curr = startFuel;
        int idx = 0;
        int n = stations.length;
        while(idx < n && stations[idx][0] <= curr){
                pq.add(stations[idx++][1]);
            }
        while(target > curr && pq.size() != 0){
            int next = pq.remove();
            curr += next;
            // System.out.println(curr);
            count++;
            
            while(idx < n && stations[idx][0] <= curr){
                pq.add(stations[idx++][1]);
                
            }
            
          
            
        }
        
        if(curr >= target) return count;
        
        return -1;
        
    }

    //373
    //{i == nums1, j == nums2}
    class Pair{
        int i;
        int j;
        int sum;

        Pair(int i, int j, int sum){
            this.i = i;
            this.j = j;
            this.sum = sum;
        }
    }
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
           List<List<Integer>> ans = new ArrayList<>();

           PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->{
               return a.sum - b.sum;
           });

           for(int i = 0; i < nums2.length; i++){
                int sum = nums2[i] + nums1[0];
                Pair p = new Pair(0, i, sum); 
                pq.add(p);
           }

        
           while(k-- > 0 && pq.size() > 0){
                Pair p = pq.remove();
                List<Integer> temp = new ArrayList<>();
                temp.add(nums1[p.i]);
                temp.add(nums2[p.j]);
                ans.add(temp);


                if(p.i < nums1.length - 1){
                    pq.add(new Pair(p.i + 1, p.j, nums1[p.i + 1] + nums2[p.j]));
                }
           }

           return ans;
    }

    //355

    int time = 1;
    class pair{
        int id;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(); // listOfUserAndPost
        HashSet<Integer> follower = new HashSet<>();
        
        pair(int id, ArrayList<Integer> l){
            this.id = id;
            list.add(l);
        }
    }
    
    
    HashMap<Integer, pair> map = new HashMap<>();
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        
        if(!map.containsKey(userId)){
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(tweetId);
            temp.add(time++);
            map.put(userId, new pair(userId, temp));
        }else{
            pair p = map.get(userId);
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(tweetId);
            temp.add(time++);
            p.list.add(temp);
        }
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        if(!map.containsKey(userId)) return new ArrayList<>();
        PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>((a,b)->{
            return b.get(1) - a.get(1);
        });
        pair p = map.get(userId);
        
        for(ArrayList<Integer> temp : p.list){
            if(temp.size() != 0)
                pq.add(temp);
        }
        
        for(Integer id : p.follower){
            if(!map.containsKey(id)) continue;
            pair temp = map.get(id);
            
            for(ArrayList<Integer> t : temp.list){
                if(t.size() != 0)
                    pq.add(t);
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        while(pq.size() != 0 && ans.size() < 10){
            ans.add(pq.remove().get(0));
        }
        return ans;
        
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(map.containsKey(followerId)){
            pair p = map.get(followerId);
            p.follower.add(followeeId);
        }else{
            map.put(followerId, new pair(followerId, new ArrayList<>()));
            pair p = map.get(followerId);
            p.follower.add(followeeId);
        }
        
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        pair p = map.get(followerId);
        p.follower.remove(followeeId);
    }
}
