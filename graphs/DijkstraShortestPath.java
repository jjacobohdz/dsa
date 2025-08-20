/*
Implementation of Dijkstra's algorithm for shortest path using a minimum heap

You are given a graph of n nodes, labeled from 1 to n. 
You are also given a list of directed edges edge[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the weight it takes to travel from source to target.

We will travel from a given node k and return the minimum time it takes to arrive to a target node.

If it is impossible to arrive to the target destination, return -1

Example 1:
Input: edges = [[2,1,1],[2,3,1],[3,4,1]], n = 4, source = 2, target = 4
Output: 2

Example 2:
Input: edges = [[1,2,1]], n = 2, source = 1, target = 2
Output: 1

Example 3:
Input: times = [[1,2,1]], n = 3, source = 2, target = 3
Output: -1
*/
import java.util.*;
class DijkstraShortestPath {
    
    private static class Edge {
        int node, weight;
        private Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    
    public static int shortestPath(int[][] edges, int n, int source, int target) {
        // init the distances to infinite value and the source to 0
        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        
        // initialize the graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        
        // populate the graph with parents and edges
        for (int[] edge: edges) {
            int parent = edge[0];
            int child = edge[1];
            int weight = edge[2];

            // edge from parent to child
            graph.get(parent).add(new Edge(child, weight));
        }
        
        // keep track of the visited nodes
        Set<Integer> visited = new HashSet<>();
        visited.add(source);
        
        // min heap will keep the items with less weight on top
        PriorityQueue<Edge> minHeap = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
        minHeap.offer(new Edge(source, 0));
        
        while (!minHeap.isEmpty()) {
            Edge current = minHeap.poll();
            
            // get the adjacent nodes of the current one
            for (Edge adjacent: graph.get(current.node)) {
                if (!visited.contains(adjacent.node)) {
                    visited.add(adjacent.node);

                    // if the cost from the current node to the adjacent is minor than the current cost update the adjacent's cost
                    int newWeight = distances[current.node] + adjacent.weight;
                    if (newWeight < distances[adjacent.node]) {
                        distances[adjacent.node] = newWeight;
                        minHeap.offer(new Edge(adjacent.node, newWeight));
                    }
                }
            }
        }
        
        // infinite means we were unable to reach the target
        return distances[target] != Integer.MAX_VALUE ? distances[target] : -1;
    }
    
    public static void main(String[] args) {
        int[][] edges = {{2,1,1},{2,3,1},{3,4,1}};
        int res = shortestPath(edges, 4, 2, 4);
        System.out.println(res);
        
        edges = new int[][]{{1,2,1}};
        res = shortestPath(edges, 2, 1, 2);
        System.out.println(res);
        
        edges = new int[][]{{1,2,1}};
        res = shortestPath(edges, 3, 2, 3);
        System.out.println(res);
    }
}