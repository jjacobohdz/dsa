/*
Topological Sort of a directed graph (a graph with unidirectional edges) is a linear ordering of its vertices such that for every directed edge (U, V) from vertex U to vertex V, U comes before V in the ordering.

Given a directed graph, find the topological ordering of its vertices.

Example 1:
Input: Vertices=4, Edges=[3, 2], [3, 0], [2, 0], [2, 1]
Output: Following are the two valid topological sorts for the given graph:
1) 3, 2, 0, 1
2) 3, 2, 1, 0

Example 2:
Input: Vertices=5, Edges=[4, 2], [4, 3], [2, 0], [2, 1], [3, 1]
Output: Following are all valid topological sorts for the given graph:
1) 4, 2, 3, 0, 1
2) 4, 3, 2, 0, 1
3) 4, 3, 2, 1, 0
4) 4, 2, 3, 1, 0
5) 4, 2, 0, 3, 1

Example 3:
Input: Vertices=7, Edges=[6, 4], [6, 2], [5, 3], [5, 4], [3, 0], [3, 1], [3, 2], [4, 1]
Output: Following are all valid topological sorts for the given graph:
1) 5, 6, 3, 4, 0, 1, 2
2) 6, 5, 3, 4, 0, 1, 2
3) 5, 6, 4, 3, 0, 2, 1
4) 6, 5, 4, 3, 0, 1, 2
5) 5, 6, 3, 4, 0, 2, 1
6) 5, 6, 3, 4, 1, 2, 0
There are other valid topological ordering of the graph too.
*/
import java.util.*;
class TopologicalSort {
    
    public static List<Integer> topologicalSort(int v, int[][] edges) {
        // init the graph and indegree
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int i = 0; i < v; i++) {
            graph.put(i, new ArrayList<>());
            indegree.put(i, 0);
        }
        
        // populate the edges and indegree
        for (int[] edge: edges) {
            int parent = edge[0];
            int child = edge[1];
            graph.get(parent).add(child);
            indegree.put(child, indegree.get(child) + 1);
        }
        // find the source nodes (nodes with 0 indegree)
        // put the source nodes in a queue
        Queue<Integer> sources = new LinkedList<>();
        for (int key: indegree.keySet()) {
            if (indegree.get(key) == 0) {
                sources.add(key);
            }
        }
        
        Set<Integer> visited = new HashSet<>();
        
        // iterate the nodes in queue until empty
        List<Integer> topologicalSort = new ArrayList<>();
        while (!sources.isEmpty()) {
            // add node to result
            int current = sources.poll();
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);
            topologicalSort.add(current);
            // visit all the adjacents of current node
            for (int adjacent: graph.get(current)) {
                // decrease their indegree
                indegree.put(adjacent, indegree.get(adjacent) - 1);
                // if adjacent's indegree is 0 add to queue
                if (indegree.get(adjacent) == 0) {
                    sources.add(adjacent);
                }
            }
        }
        // if result size equals v, then topological sort was successful
        if (topologicalSort.size() != v) {
            return new ArrayList<>();
        }
        return topologicalSort;
    }
    
    public static void main(String[] args) {
        int[][] edges = {{3, 2}, {3, 0}, {2, 0}, {2, 1}};
        int v = 4;
        List<Integer> result = topologicalSort(v, edges);
        System.out.println(result);
        
        edges = new int[][]{{4, 2}, {4, 3}, {2, 0}, {2, 1}, {3, 1}};
        v = 5;
        result = topologicalSort(v, edges);
        System.out.println(result);
        
        edges = new int[][]{{6, 4}, {6, 2}, {5, 3}, {5, 4}, {3, 0}, {3, 1}, {3, 2}, {4, 1}};
        v = 7;
        result = topologicalSort(v, edges);
        System.out.println(result);
    }
}