package ArraysAndString;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class questions{

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            swap(arr, i++, j--);
        }
    }

    public static void rotateByK(int[] arr, int r) {
        int n = arr.length;
        // r %= n;
        // if (r < 0)
        // r += n;

        r = (r % n + n) % n;

        reverse(arr, 0, n - 1);
        reverse(arr, n - r, n - 1);
        reverse(arr, 0, n - r - 1);

    }

    public static void segregatePositiveAndNegative(int[] arr) {
        int n = arr.length, pt = -1, itr = 0;
        while (itr < n) {
            if (arr[itr] < 0)
                swap(arr, ++pt, itr);
            itr++;
        }
    }

    public static int max_sum(int[] arr, int n) {
        int sum = 0, sumWithIndex = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            sumWithIndex += arr[i] * i;
        }

        int maxAns = sumWithIndex;
        for (int i = 1; i < n; i++) {
            sumWithIndex = sumWithIndex - sum + arr[i - 1] * n;
            maxAns = Math.max(maxAns, sumWithIndex);
        }

        return maxAns;
    }

    // 11
    public int maxArea(int[] arr) {
        int n = arr.length, maxArea = 0, i = 0, j = n - 1;
        while (i < j) {
            int w = j - i;
            maxArea = arr[i] < arr[j] ? Math.max(maxArea, arr[i++] * w) : Math.max(maxArea, arr[j--] * w);
        }

        return maxArea;
    }


    // 003
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1)
            return s.length();

        int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
        int[] freq = new int[128]; // vector<int> freq(128,0);

        int gsi = 0, gei = 0;
        while (ei < n) {
            if (freq[s.charAt(ei++)]++ > 0)
                count++;

            while (count > 0)
                if (freq[s.charAt(si++)]-- > 1)
                    count--;

            if (ei - si > len) {
                len = ei - si;
                gsi = si;
                gei = ei;
            }
        }

        // System.out.println(s.substring(gsi,gei));

        return len;
    }

    // 003
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1)
            return s.length();

        int n = s.length(), si = 0, ei = 0, count = 0, len = 0;
        int[] freq = new int[128]; // vector<int> freq(128,0);

        while (ei < n) {
            if (freq[s.charAt(ei)] == 1)
                count++;
            freq[s.charAt(ei)]++;
            ei++;

            while (count > 0) {
                if (freq[s.charAt(si)] == 2)
                    count--;
                freq[s.charAt(si)]--;
                si++;
            }

            len = Math.max(len, ei - si);
        }

        return len;
    }

    // 159
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() <= 2)
            return s.length();

        int n = s.length(), si = 0, ei = 0, len = 0, count = 0;
        int[] freq = new int[128];

        while (ei < n) {
            if (freq[s.charAt(ei++)]++ == 0)
                count++;

            while (count > 2) {
                if (freq[s.charAt(si++)]-- == 1)
                    count--;
            }

            len = Math.max(len, ei - si);
        }

        return len;
    }

    // 76
    public String minWindow(String s, String t) {

        int ns = s.length(), nt = t.length();
        if (ns < nt)
            return "";
        int[] freq = new int[128];
        for (int i = 0; i < nt; i++)
            freq[t.charAt(i)]++;

        int si = 0, ei = 0, count = nt, len = (int) 1e9, gsi = 0;

        while (ei < ns) {
            if (freq[s.charAt(ei++)]-- > 0)
                count--;

            while (count == 0) {
                if (ei - si < len) {
                    len = ei - si;
                    gsi = si;
                }

                if (freq[s.charAt(si++)]++ == 0)
                    count++;
            }
        }

        return len == (int) 1e9 ? "" : s.substring(gsi, gsi + len);
    }

    // 76
    public String minWindow2(String s, String t) {

        int ns = s.length(), nt = t.length();
        if (ns < nt)
            return "";
        int[] freq = new int[128];
        for (int i = 0; i < nt; i++)
            freq[t.charAt(i)]++;

        int si = 0, ei = 0, count = nt, len = (int) 1e9, gsi = 0;
        while (ei < ns) {
            if (freq[s.charAt(ei)] > 0)
                count--;
            freq[s.charAt(ei)]--;
            ei++;

            while (count == 0) {
                if (ei - si < len) {
                    len = ei - si;
                    gsi = si;
                }

                if (freq[s.charAt(si)] == 0)
                    count++;
                freq[s.charAt(si)]++;
                si++;
            }
        }

        return len == (int) 1e9 ? "" : s.substring(gsi, gsi + len);
    }

    // https://practice.geeksforgeeks.org/problems/smallest-distant-window3132/1
    public String findSubString(String s) {
        // Your code goes here
        if(s.length() <= 1) return s;
        int ns = s.length();
        int si = 0, ei = 0, count = 0, len = (int) 1e9, gi = 0;
        int[] freq = new int[128];
        for (int i = 0; i < ns; i++){
           
            if(freq[s.charAt(i)] == 0){
                count++;
                freq[s.charAt(i)] = 1;
            }
        }
            

        
        while (ei < ns) {
            if (freq[s.charAt(ei++)]-- > 0)
                count--;
            

            while (count == 0) {
                if (ei - si < len) {
                    len = ei - si;
                    gi = si;
                }

                if (freq[s.charAt(si)] == 0)
                    count++;
                freq[s.charAt(si)]++;
                si++;
            }
        }
        return s.substring(gi, gi + len);
    }

    //340
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s.length() <= k)
            return s.length();

        int n = s.length(), si = 0, ei = 0, len = 0, count = 0;
        int[] freq = new int[128];

        while (ei < n) {
            if (freq[s.charAt(ei++)]++ == 0)
                count++;

            while (count > k) {
                if (freq[s.charAt(si++)]-- == 1)
                    count--;
            }

            len = Math.max(len, ei - si);
        }

        return len;
    }


    //1456
    public int maxVowels(String s, int k) {
        int n = s.length();
        int VowelCount = 0, maxVowelCount = 0, si = 0, ei = 0;
        
        while(ei < n){
            if(isVowel(s.charAt(ei++))) VowelCount++;

            if(ei - si == k){
                maxVowelCount = Math.max(maxVowelCount, VowelCount);
                if(isVowel(s.charAt(si++)))
                    VowelCount--;
            }
        }

        return maxVowelCount;
    }

    public boolean isVowel(Character ch){
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

     // Count all subarrays with atMost k Different Integers.
     public int atMostKDistinct(int[] arr, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        int n = arr.length, si = 0, ei = 0, ans = 0;

        while (ei < n) {
            freq.put(arr[ei], freq.getOrDefault(arr[ei], 0) + 1);
            ei++;
            while (freq.size() > k) {

                freq.put(arr[si], freq.get(arr[si]) - 1);
                if (freq.get(arr[si]) == 0) {
                    freq.remove(arr[si]);
                }
                si++;
            }

            ans += ei - si;
        }

        return ans;
    }

    public int atMostKDistinct_02(int[] arr, int k) {
        int[] freq = new int[20000 + 1];
        int n = arr.length, si = 0, ei = 0, count = 0, ans = 0;

        while (ei < n) {
            if (freq[arr[ei++]]++ == 0)
                count++;

            while (count > k) {
                if (freq[arr[si++]]-- == 1)
                    count--;
            }
            ans += ei - si;
        }

        return ans;
    }

    public int subarraysWithKDistinct(int[] arr, int k) {
        return atMostKDistinct(arr, k) - atMostKDistinct(arr, k - 1);
    }

    //1248
    public int numberOfSubarrays_(int[] arr, int k) {
        
        int n = arr.length, si = 0, ei = 0, count = 0, ans = 0;

        while (ei < n) {
            if (arr[ei++] % 2 == 1)
                count++;

            while (count > k) {
                
                if (arr[si++] % 2 == 1)
                    count--;
            }
            ans += ei - si;
        }

        return ans;
    }

    public int numberOfSubarrays(int[] arr, int k) {
        return numberOfSubarrays_(arr, k) - numberOfSubarrays_(arr, k - 1);
    }


    //https://practice.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1
    public int longestkSubstr(String s, int k) {
        // code here
        if (s.length() < k)
            return -1;
        

        int n = s.length(), si = 0, ei = 0, len = -1, count = 0;
        int[] freq = new int[128];

        while (ei < n) {
            if (freq[s.charAt(ei++)]++ == 0)
                count++;

            while (count > k) {
                if (freq[s.charAt(si++)]-- == 1)
                    count--;
            }
            if(count == k)
                len = Math.max(len, ei - si);
        }

        return len;
    }

     // 904
     public int totalFruit(int[] arr) {
        int n = arr.length, si = 0, ei = 0, len = 0, count = 0;
        int[] freq = new int[40000 + 1];

        while (ei < n) {
            if (freq[arr[ei++]]++ == 0)
                count++;

            while (count > 2)
                if (freq[arr[si++]]-- == 1)
                    count--;

            len = Math.max(len, ei - si);
        }

        return len;
    }

    // 930
    public int atMostSum(int[] arr, int tar) {
        int n = arr.length, si = 0, ei = 0, sum = 0, count = 0;
        while (ei < n) {
            sum += arr[ei++];

            while (sum > tar)
                sum -= arr[si++];

            count += ei - si;
        }

        return count;
    }

    public int numSubarraysWithSum(int[] arr, int tar) {
        return atMostSum(arr, tar) - (tar != 0 ? atMostSum(arr, tar - 1) : 0);
    }

    // 485
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length, si = 0, ei = 0, len = 0, count = 0;
        while (ei < n) {
            if (nums[ei++] == 0)
                count++;

            while (count == 1) {
                if (nums[si++] == 0)
                    count--;
            }

            len = Math.max(len, ei - si);
        }
        return len;
    }

    public int findMaxConsecutiveOnes_01(int[] nums) {
        int n = nums.length, si = 0, ei = 0, len = 0;
        while (ei < n) {
            if (nums[ei] == 0) {
                ei++;
                si = ei;
            } else {
                ei++;
            }

            len = Math.max(len, ei - si);
        }
        return len;
    }

    // 487
    public int findMaxConsecutiveOnes(int[] arr) {
        int n = arr.length, si = 0, ei = 0, count = 0, len = 0;

        while (ei < n) {
            if (arr[ei++] == 0)
                count++;

            while (count == 2)
                if (arr[si++] == 0)
                    count--;

            len = Math.max(len, ei - si);
        }

        return len;
    }

    public int findMaxConsecutiveOnes_01(int[] arr) {
        int n = arr.length, si = 0, ei = 0, firstZeroIndex = -1, len = 0;

        while (ei < n) {
            if (arr[ei++] == 0) {
                si = firstZeroIndex + 1;
                firstZeroIndex = ei - 1;
            }

            len = Math.max(len, ei - si);
        }

        return len;
    }

    // 1004
    public int longestOnes(int[] arr, int k) {
        int n = arr.length, si = 0, ei = 0, count = 0, len = 0;

        while (ei < n) {
            if (arr[ei++] == 0)
                count++;

            while (count > k)
                if (arr[si++] == 0)
                    count--;

            len = Math.max(len, ei - si);
        }

        return len;
    }

     // 974
     public int subarraysDivByK(int[] arr, int k) {
        int[] rem = new int[k];
        int n = arr.length, ei = 0, sum = 0, ans = 0;
        rem[0] = 1;
        while (ei < n) {
            sum += arr[ei++];
            int r = (sum % k + k) % k;

            ans += rem[r];
            rem[r]++;
        }

        return ans;
    }

    public int longestSubarraysDivByK(int[] arr, int k) {
        int[] rem = new int[k];
        int n = arr.length, ei = 0, sum = 0, len = 0;
        Arrays.fill(rem, -2);
        rem[0] = -1;
        while (ei < n) {
            sum += arr[ei];
            int r = (sum % k + k) % k;

            if (rem[r] == -2)
                rem[r] = ei;
            else
                len = Math.max(len, ei - rem[r]);
            ei++;
        }

        return len;
    }

    public int subarraysDivByK_map(int[] arr, int k) {
        HashMap<Integer, Integer> rem = new HashMap<>();
        int n = arr.length, ei = 0, sum = 0, ans = 0;
        rem.put(0, 1);
        while (ei < n) {
            sum += arr[ei++];
            int r = (sum % k + k) % k;

            ans += rem.getOrDefault(r, 0);
            rem.put(r, rem.getOrDefault(r, 0) + 1);
        }

        return ans;
    }

    public int longestSubarraysDivByK_map(int[] arr, int k) {
        HashMap<Integer, Integer> rem = new HashMap<>();
        int n = arr.length, ei = 0, sum = 0, len = 0;
        rem.put(0, -1);
        while (ei < n) {
            sum += arr[ei];
            int r = (sum % k + k) % k;

            rem.putIfAbsent(r, ei);
            len = Math.max(len, ei - rem.get(r));
            ei++;
        }

        return len;
    }

    // https://practice.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s-1587115620/1
    static int countSubarrWithEqualZeroAndOne(int arr[], int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ei = 0, count = 0, sum = 0;

        while (ei < n) {
            int val = arr[ei++];
            sum += val;
            if (val == 0)
                sum += -1;

            count += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    // 525
    public int findMaxLength(int[] arr) {

        // rem, firstIndex
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int n = arr.length, ei = 0, sum = 0, len = 0;

        while (ei < n) {
            int val = arr[ei];
            sum += val;
            if (val == 0)
                sum += -1;

            map.putIfAbsent(sum, ei); // if(map.find(sum) == map.end()) map[sum] = ei;
            len = Math.max(len, ei - map.get(sum));
            ei++;
        }

        return len;
    }

     // 239
     public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return nums[b] - nums[a]; // other - this, reverse of default behaviour.
        });

        int n = nums.length, idx = 0;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < nums.length; i++) {
            while (pq.size() != 0 && pq.peek() <= i - k)
                pq.remove();

            pq.add(i);

            if (i >= k - 1)
                ans[idx++] = nums[pq.peek()];
        }
        return ans;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> deque = new LinkedList<>();
        int n = nums.length, idx = 0;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < nums.length; i++) {
            while (deque.size() != 0 && deque.getFirst() <= i - k)
                deque.removeFirst();

            while (deque.size() != 0 && nums[deque.getLast()] <= nums[i])
                deque.removeLast();

            deque.addLast(i);

            if (i >= k - 1)
                ans[idx++] = nums[deque.getFirst()];
        }
        return ans;
    }

     // KadanesAlgo
    // [-1,-7,-8,-9] -> max sum Subarray if 0(no subarray exist);
    public static int kadanesAlgo(int[] arr) {
        int gSum = 0, cSum = 0;
        for (int ele : arr) {
            cSum += ele;
            if (cSum > gSum)
                gSum = cSum;
            if (cSum <= 0)
                cSum = 0;
        }

        return gSum;
    }

    public static int kadanesAlgo_SubArray(int[] arr) {
        int gSum = 0, cSum = 0, gsi = 0, gei = 0, csi = 0;
        for (int i = 0; i < arr.length; i++) {
            int ele = arr[i];
            cSum += ele;
            if (cSum > gSum) {
                gSum = cSum;

                gsi = csi;
                gei = i;
            }
            if (cSum <= 0) {
                cSum = 0;
                csi = i + 1;
            }
        }

        return gSum;
    }

    // [-1,-7,-8,-9] -> max sum Subarray if -1 (0,0);
    public static int kadanesAlgoGeneric(int[] arr) {
        int gSum = -(int) 1e9, cSum = 0;
        for (int ele : arr) {
            cSum = Math.max(ele, cSum + ele);
            gSum = Math.max(gSum, cSum);
        }

        return gSum;
    }

    public static int[] kadanesAlgoGenericSubarray(int[] arr) {
        int gSum = -(int) 1e9, cSum = 0, gsi = 0, gei = 0, csi = 0;
        for (int i = 0; i < arr.length; i++) {
            int ele = arr[i];
            cSum += ele;
            if (ele >= cSum) {
                cSum = ele;
                csi = i;
            }

            if (cSum > gSum) {
                gSum = cSum;
                gsi = csi;
                gei = i;
            }
        }

        return new int[] { gSum, gsi, gei };
    }

    int mod = (int) 1e9 + 7;

    public int kadanesAlgo(int[] arr, int k) {
        int n = arr.length;
        long gsum = 0, csum = 0;

        for (int i = 0; i < k * n; i++) {
            int ele = arr[i % n];
            csum += ele;

            if (csum > gsum)
                gsum = csum;
            if (csum <= 0)
                csum = 0;
        }

        return (int) gsum % mod;
    }

    public int kConcatenationMaxSum(int[] arr, int k) {
        long prevSum = 0, sum = 0;
        for (int i = 1; i <= 3; i++) {
            prevSum = sum;
            sum = kadanesAlgo(arr, i);
            if (i == k)
                return (int) sum;
        }

        return (int) ((prevSum + (k - 2) * (sum - prevSum)) % mod);
    }

    public int kConcatenationMaxSum(int[] arr, int k) {
        int kadansSum = kadanesAlgo(arr, 1);

        if (k == 1)
            return kadansSum;

        long prefixSum = 0, suffixSum = 0, maxPrefixSum = 0, maxSuffixSum = 0, arraySum = 0;
        int n = arr.length;
        for (int i = 0, j = n - 1; i < n; i++, j--) {
            prefixSum += arr[i];
            suffixSum += arr[j];
            arraySum += arr[i];

            maxPrefixSum = Math.max(maxPrefixSum, prefixSum);
            maxSuffixSum = Math.max(maxSuffixSum, suffixSum);
        }

        // if (arraySum > 0)
        // return (int) ((maxPrefixSum + maxSuffixSum + (k - 2) * arraySum) % mod);
        // else
        // return (int) (Math.max(maxPrefixSum + maxSuffixSum, kadansSum) % mod);

        arraySum = arraySum < 0 ? 0 : arraySum % mod;
        return (int) Math.max(kadansSum, maxPrefixSum + maxSuffixSum + ((k - 2) * arraySum) % mod) % mod;
    }


     // https://practice.geeksforgeeks.org/problems/maximum-sum-rectangle2948/1

     public static int kadanesAlgoForNegative(int[] arr) {
        int gSum = -(int) 1e9, cSum = 0;
        for (int ele : arr) {
            cSum = Math.max(ele, cSum + ele);
            gSum = Math.max(gSum, cSum);
        }

        return gSum;
    }

    int maximumSumRectangle(int R, int C, int arr[][]) {
        int n = R, m = C, maxSum = -(int) 1e9;
        int[] colPrefixSum = new int[m];

        for (int fixRow = 0; fixRow < n; fixRow++) {

            Arrays.fill(colPrefixSum, 0);

            for (int row = fixRow; row < n; row++) {
                for (int col = 0; col < m; col++)
                    colPrefixSum[col] += arr[row][col];

                int sum = kadanesAlgoForNegative(colPrefixSum);
                maxSum = Math.max(maxSum, sum);

            }
        }

        return maxSum;
    }

     // if we want to print matrix
     int maximumSumRectangle_02(int R, int C, int arr[][]) {
        int n = R, m = C, maxSum = -(int) 1e9;
        int[] colPrefixSum = new int[m];

        int r1 = 0, c1 = 0, r2 = 0, c2 = 0;

        for (int fixRow = 0; fixRow < n; fixRow++) {

            Arrays.fill(colPrefixSum, 0);

            for (int row = fixRow; row < n; row++) {
                for (int col = 0; col < m; col++)
                    colPrefixSum[col] += arr[row][col];

                int[] res = kadanesAlgoGenericSubarray(colPrefixSum);
                if (res[0] >= maxSum) {
                    maxSum = res[0];
                    r1 = fixRow;
                    c1 = res[1];
                    r2 = row;
                    c2 = res[2];
                }
            }
        }

        for (int i = r1; i <= r2; i++) {
            for (int j = c1; j <= c2; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        return maxSum;
    }

    // 781
    public int numRabbits(int[] arr) {
        int[] map = new int[999 - 0 + 1];

        int ans = 0;
        for (int ele : arr) {
            if (map[ele] == 0)
                ans += (ele + 1);
            map[ele]++;

            if (map[ele] == ele + 1)
                map[ele] = 0;
        }

        return ans;
    }

    // 1074

    public int countSubarraysGivenTarget(int[] arr, int tar) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0, sum = 0;
        for (int ele : arr) {
            sum += ele;
            count += map.getOrDefault(sum - tar, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    public int numSubmatrixSumTarget(int[][] arr, int tar) {
        int n = arr.length, m = arr[0].length;
        int count = 0;

        for (int fixedRow = 0; fixedRow < n; fixedRow++) {

            int[] prefixColArray = new int[m];
            for (int row = fixedRow; row < n; row++) {
                for (int col = 0; col < m; col++)
                    prefixColArray[col] += arr[row][col];

                count += countSubarraysGivenTarget(prefixColArray, tar);
            }
        }

        return count;
    }

    // 363

    public int kadanesAlgoWithSumUnderK(int[] arr, int k) {
        int gsum = -(int) 1e9, csum = 0;
        for (int ele : arr) {
            csum += ele;
            csum = Math.max(csum, ele);
            gsum = Math.max(gsum, csum);

            if (gsum >= k)
                return gsum;
        }

        return gsum;
    }

    public int maxSumSubmatrix(int[][] arr, int k) {
        int n = arr.length, m = arr[0].length;
        int maxRes = 0;

        for (int fixedRow = 0; fixedRow < n; fixedRow++) {

            int[] prefixColArray = new int[m];
            for (int row = fixedRow; row < n; row++) {
                for (int col = 0; col < m; col++)
                    prefixColArray[col] += arr[row][col];

                int sum = kadanesAlgoWithSumUnderK(prefixColArray, k);

                if (sum == k)
                    return sum;
                else if (sum < k) {
                    maxRes = Math.max(maxRes, sum);
                    continue;
                }

                // ????
            }
        }

        return maxRes;

    }

    //556
    public int nextGreaterElement(int num) {
        char[] arr = (num + "").toCharArray();
        int n = arr.length;
        if(n == 1) return -1;

        int i = n - 1;

        for(i = n - 1; i > 0; i--){
            if(arr[i - 1] < arr[i]){
                break;
            }
        }

        if(i == 0) return -1;
        int replace = arr[i - 1];
        int smallest = i;
        for(int j = i + 1; j < n; j++){
            if(arr[smallest] >= arr[j] && arr[j] > replace) {
                smallest = j;
            }
        }

        char temp = arr[i - 1];
        arr[i - 1] = arr[smallest];
        arr[smallest] = temp;


        Arrays.sort(arr, i, n);
        Long val = Long.parseLong(new String(arr));
        return (int) (val > Integer.MAX_VALUE ? -1 : val);
 
    }
}