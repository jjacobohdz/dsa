/*
Given a string, find the length of the longest substring in it with no more than K distinct characters.

Example 1:
Input: String="araaci", K=2
Output: 4
Explanation: The longest substring with no more than '2' distinct characters is "araa".

Example 2:
Input: String="araaci", K=1
Output: 2
Explanation: The longest substring with no more than '1' distinct characters is "aa".

Example 3:
Input: String="cbbebi", K=3
Output: 5
Explanation: The longest substrings with no more than '3' distinct characters are "cbbeb" & "bbebi".
*/
import java.util.*;
class LongestSubstringWithKDistinctCharacters {
    public static int longestSubstringWithKDistinctCharacters(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int max = 0;
        while (end < s.length()) {
            char c = s.charAt(end++);
            map.put(c, map.getOrDefault(c, 0) + 1);
            
            while (map.size() > k) {
                c = s.charAt(start++);
                
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) - 1);
                    
                    if (map.get(c) == 0) {
                        map.remove(c);
                    }
                }
            }
            max = Math.max(max, end - start);
        }
        return max;
    }
    
    public static void main(String[] args) {
        String s = "araaci";
        int k = 2;
        int res = longestSubstringWithKDistinctCharacters(s, k);
        System.out.println(res);
        
        s = "araaci";
        k = 1;
        res = longestSubstringWithKDistinctCharacters(s, k);
        System.out.println(res);

        s = "cbbebi";
        k = 3;
        res = longestSubstringWithKDistinctCharacters(s, k);
        System.out.println(res);
    }
}