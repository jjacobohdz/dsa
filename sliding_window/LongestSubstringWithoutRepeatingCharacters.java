/*
Given a string, find the length of the longest substring which has no repeating characters.

Example 1:
Input: String="aabccbb"
Output: 3
Explanation: The longest substring without any repeating characters is "abc".

Example 2:
Input: String="abbbb"
Output: 2
Explanation: The longest substring without any repeating characters is "ab".

Example 3:
Input: String="abccde"
Output: 3
Explanation: Longest substrings without any repeating characters are "abc" & "cde".

Leetcode problem:
https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
*/
import java.util.*;
class LongestSubstringWithoutRepeatingCharacters {
    public static int noRepeatSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        int max = 0;
        while (end < s.length()) {
            char c = s.charAt(end++);
            map.put(c, map.getOrDefault(c, 0) + 1);
            
            boolean repeats = map.get(c) > 1;
            while (repeats) {
                c = s.charAt(start++);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) - 1);
                    repeats = map.get(c) != 1;
                }
            }
            max = Math.max(max, end - start);
        }
        return max;
    }
    
    public static void main(String[] args) {
        String s = "aabccbb";
        int res = noRepeatSubstring(s);
        System.out.println(res);
        
        s = "abbbb";
        res = noRepeatSubstring(s);
        System.out.println(res);

        s = "abccde";
        res = noRepeatSubstring(s);
        System.out.println(res);
    }
}