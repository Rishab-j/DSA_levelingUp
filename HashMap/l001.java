import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class l001 {

    public static void freqMap(String str) {
        HashMap<Character, Integer> map = new HashMap<>();

        // for(int i=0; i < str.length(); i++){
        // char ch = str.charAt(i);
        // if(map.containsKey(ch))
        // map.put(ch,map.get(ch) + 1);
        // else map.put(ch , 1);
        // }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        System.out.println(map.get('@'));
        for (Character ch : map.keySet()) {
            System.out.println(map.get(ch));
        }

        System.out.println(map);
    }

    public static void freqMap_pos(String str) {
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.putIfAbsent(ch, new ArrayList<>());
            map.get(ch).add(i);
        }

        for (Character ch : map.keySet()) {
            System.out.println(ch + " -> " + map.get(ch));
        }
    }

    // Leetcode 349
    public static int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> map = new HashSet<>();
        for (int ele : nums1)
            map.add(ele);

        System.out.println(map);

        ArrayList<Integer> ans = new ArrayList<>();
        for (int ele : nums2) {
            if (map.contains(ele)) {
                ans.add(ele);
                map.remove(ele);
            }
        }

        int[] arr = new int[ans.size()];
        int i = 0;
        for (int ele : ans)
            arr[i++] = ele;

        return arr;
    }

    // Leetcode 350
    public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int ele : nums1)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        ArrayList<Integer> ans = new ArrayList<>();
        for (int ele : nums2) {
            if (map.containsKey(ele)) {
                ans.add(ele);
                map.put(ele, map.get(ele) - 1);
                if (map.get(ele) == 0)
                    map.remove(ele);
            }
        }

        int[] arr = new int[ans.size()];
        int i = 0;
        for (int ele : ans)
            arr[i++] = ele;

        return arr;
    }

    // Leetcode 219
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int index = map.get(nums[i]);
                if (i - index <= k)
                    return true;

            }

            map.put(nums[i], i);
        }

        return false;
    }

    // Leetcode 451==================================================

    public String frequencySort(String str) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> {
            return map.get(b) - map.get(a);
        });

        for (Character ch : map.keySet()) {
            pq.add(ch);
        }

        StringBuilder sb = new StringBuilder();
        while (pq.size() != 0) {
            Character ch = pq.remove();
            int freq = map.get(ch);
            for (int i = 0; i < freq; i++)
                sb.append(ch);
        }

        return sb.toString();
    }

    // Leetcode 347
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : nums)
            map.put(ele, map.getOrDefault(ele, 0) + 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return map.get(a) - map.get(b);
        });

        for (Integer key : map.keySet()) {
            pq.add(key);
            if (pq.size() > k)
                pq.remove();
        }

        int[] ans = new int[k];
        int i = 0;
        while (pq.size() != 0) {
            ans[i++] = pq.remove();
        }

        return ans;
    }

    // using max heap
    public int[] topKFrequent_(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int c : nums) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));

        maxHeap.addAll(map.keySet());

        int[] ans = new int[k];
        int i = 0;

        while (k-- > 0) {
            int a = maxHeap.remove();
            ans[i] = a;
            i++;
        }
        return ans;
    }

    class RandomizedSet {

        public HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> arr = new ArrayList<>();

        /** Initialize your data structure here. */
        public RandomizedSet() {

        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain
         * the specified element.
         */
        public boolean insert(int val) {
            if (!map.containsKey(val))
                return false;

            map.put(val, arr.size());
            arr.add(val);

            return true;
        }

        public void swap(int a, int b) { // O(1)
            int val1 = arr.get(a);
            int val2 = arr.get(b);

            arr.set(a, val2);
            arr.set(b, val1);
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified
         * element.
         */
        public boolean remove(int val) {
            if (!map.containsKey(val))
                return false;

            int idx = map.get(val);
            swap(idx, arr.size() - 1);

            map.put(arr.get(idx), idx);
            arr.remove(arr.size() - 1);
            map.remove(val);

            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            // int r = (int)(Math.random()*arr.size());
            return arr.get(new Random().nextInt(arr.size()));
        }
    }


    class RandomizedSet_correct {

        /** Initialize your data structure here. */
        HashMap<Integer, Integer> map;
        ArrayList<Integer> list;
        Random random;

        public RandomizedSet_correct() {
            map = new HashMap<>();
            list = new ArrayList<>();
            random = new Random();
        }
        
        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(map.containsKey(val))
                return false;
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }
        
        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(!map.containsKey(val))
                return false;
            int index = map.get(val);
            int lastValue = list.get(list.size() - 1);
            list.set(index, lastValue);
            map.put(lastValue, index);
            list.remove(list.size() - 1);
            map.remove(val);
            return true;
        }
        
        /** Get a random element from the set. */
        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }
    
    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */

    // Leetcode 128
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> map = new HashSet<>();
        for (int ele : nums)
            map.add(ele);

        int len = 0;
        for (int ele : nums) {
            if (!map.contains(ele))
                continue;

            map.remove(ele);
            int prev = ele - 1;
            int next = ele + 1;

            while (map.contains(prev)) {
                map.remove(prev);
                prev--;
            }

            while (map.contains(next)) {
                map.remove(next);
                next++;
            }

            len = Math.max(len, next - prev - 1);
        }
        return len;
    }

    // Leetcode 49
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();

        int[] freq = new int[26];
        for (String s : strs) {

            Arrays.fill(freq, 0);
            for (int i = 0; i < s.length(); i++)
                freq[s.charAt(i) - 'a']++;

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (freq[i] > 0) {
                    sb.append((char) (i + 'a'));
                    sb.append(freq[i]);
                }
            }

            String RLES = sb.toString();
            map.putIfAbsent(RLES, new ArrayList<>());
            map.get(RLES).add(s);
        }

        List<List<String>> ans = new ArrayList<>();
        for (String key : map.keySet()) {
            ans.add(map.get(key));
        }

        return ans;
    }

    // Leetcode 295.=============================================

    class MedianFinder {

        PriorityQueue<Integer> minHeap;
        PriorityQueue<Integer> maxHeap;

        public MedianFinder() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>((a, b) -> {
                return b - a;
            });
        }

        public void addNum(int num) {
            if (maxHeap.size() == 0 || num <= maxHeap.peek())
                maxHeap.add(num);
            else
                minHeap.add(num);

            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.peek());
                maxHeap.remove();
            } else if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.peek());
                minHeap.remove();
            }
        }

        public double findMedian() {
            if (maxHeap.size() == minHeap.size() && maxHeap.size() != 0)
                return (maxHeap.peek() + minHeap.peek()) * 1.0 / 2.0;
            else if (maxHeap.size() != minHeap.size())
                return maxHeap.peek();
            return -1;
        }
    }

    public static void solve() {
        // freqMap("ajhvcjhjhagsx1321465kasjdbd8445632");
        // freqMap_pos("ajhvcjhjhagsx1321465kasjdbd8445632");
        int[] arr = intersection(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 });
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        solve();
    }

}