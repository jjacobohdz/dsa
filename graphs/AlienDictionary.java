/*
There is a dictionary containing words from an alien language for which we don’t know the ordering of the characters. Write a method to find the correct order of characters in the alien language.

Example 1:
Input: Words: ["ba", "bc", "ac", "cab"]
Output: bac
Explanation: Given that the words are sorted lexicographically by the rules of the alien language, so
from the given words we can conclude the following ordering among its characters:
 
1. From "ba" and "bc", we can conclude that 'a' comes before 'c'.
2. From "bc" and "ac", we can conclude that 'b' comes before 'a'
 
From the above two points, we can conclude that the correct character order is: "bac"

Example 2:
Input: Words: ["cab", "aaa", "aab"]
Output: cab
Explanation: From the given words we can conclude the following ordering among its characters:
 
1. From "cab" and "aaa", we can conclude that 'c' comes before 'a'.
2. From "aaa" and "aab", we can conclude that 'a' comes before 'b'
 
From the above two points, we can conclude that the correct character order is: "cab"
Example 3:

Input: Words: ["ywx", "wz", "xww", "xz", "zyy", "zwz"]
Output: ywxz
Explanation: From the given words we can conclude the following ordering among its characters:
 
1. From "ywx" and "wz", we can conclude that 'y' comes before 'w'.
2. From "wz" and "xww", we can conclude that 'w' comes before 'x'.
3. From "xww" and "xz", we can conclude that 'w' comes before 'z'
4. From "xz" and "zyy", we can conclude that 'x' comes before 'z'
5. From "zyy" and "zwz", we can conclude that 'y' comes before 'w'
 
From the above five points, we can conclude that the correct character order is: "ywxz"

Example 4:
Input:
["z","x","z"]

Output: "" 

Explanation: The order is invalid, so return "".
*/
import java.util.*;
class AlienDictionary {
    
    /*
    topological sort approach:
    intuition:
        build graph:
        compare every word with the next one
            compare every char in words
            take the minimum length
                if chars are different add edge and stop
                also keep track of indegrees
        
        execute topological sorting:
            add sources to queue
            iterate until queue is empty
                update indegrees and queue
            
            if there is a cycle (element seen before), then we are unable to decipher
    */
    public static String decipher(String[] words) {
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        
        buildGraph(graph, indegree, words);
        // System.out.println(graph);
        // System.out.println(indegree);
        return topologicalSort(graph, indegree);
    }
    
    private static String topologicalSort(Map<Character, List<Character>> graph, Map<Character, Integer> indegree) {
        Queue<Character> sources = new LinkedList<>();
        
        for (char key: indegree.keySet()) {
            if (indegree.get(key) == 0) {
                sources.add(key);
            }
        }
        
        Set<Character> visited = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        while (!sources.isEmpty()) {
            char current = sources.poll();
            if (visited.contains(current)) {
                return "";
            }
            visited.add(current);
            sb.append(current);
            
            for(char adjacent: graph.getOrDefault(current, new ArrayList<>())) {
                indegree.put(adjacent, indegree.get(adjacent) - 1);
                if (indegree.get(adjacent) == 0) {
                    sources.offer(adjacent);
                }
            }
        }
        return sb.toString();
    }
    
    private static void buildGraph(Map<Character, List<Character>> graph, Map<Character, Integer> indegree, String[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            edges:
            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if (c1 != c2) {
                    addEdge(graph, indegree, c1, c2);
                    break edges;
                }
            }
        }
    }
    
    private static void addEdge(Map<Character, List<Character>> graph, Map<Character, Integer> indegree, char parent, char child) {
        List<Character> adjacents = graph.get(parent);
        if (adjacents == null) {
            adjacents = new ArrayList<>();
            graph.put(parent, adjacents);
        }
        
        if (!adjacents.contains(child)) {
            adjacents.add(child);
            
            if (indegree.get(parent) == null) {
                indegree.put(parent, 0);
            }
            if (indegree.get(child) == null) {
                indegree.put(child, 0);
            }
            
            indegree.put(child, indegree.get(child) + 1);
        }
    }
    
    public static void main(String[] args) {
        String[] words = {"ba", "bc", "ac", "cab"};
        System.out.println(decipher(words));
        
        words = new String[]{"cab", "aaa", "aab"};
        System.out.println(decipher(words));
        
        words = new String[]{"ywx", "wz", "xww", "xz", "zyy", "zwz"};
        System.out.println(decipher(words));
        
        words = new String[]{"z","x","z"};
        System.out.println(decipher(words));
    }
}