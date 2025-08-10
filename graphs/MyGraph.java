import java.util.*;
class MyGraph {
    private class Node {
        private int val;
        private List<Node> adjacents;
        private Node(int val) {
            this.val = val;
            adjacents = new ArrayList<>();
        }
    }
    
    private Map<Integer, Node> children;
    public MyGraph() {
        children = new HashMap<>();
    }
    
    public void addEdge(int source, int dest) {
        Node s = getNode(source);
        Node d = getNode(dest);
        s.adjacents.add(d);
        // indirected
        // d.adjacents.add(s);
    }
    
    public boolean hasPathBfs(int source, int dest) {
        Node s = getNode(source);
        Node d = getNode(dest);
        Set<Integer> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(s);
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current == d) {
                return true;
            }
            if (visited.contains(current.val)) {
                continue;
            }
            visited.add(current.val);
            
            for (Node adjacent: current.adjacents) {
                queue.offer(adjacent);
            }
        }
        
        return false;
    }
    
    public boolean hasPathDfs(int source, int dest) {
        return hasPathDfs(getNode(source), getNode(dest), new HashSet<>());
    }
    
    private boolean hasPathDfs(Node source, Node dest, Set<Integer> visited) {
        if (visited.contains(source.val)) {
            return false;
        }
        
        if (source == dest) {
            return true;
        }
        
        visited.add(source.val);
        
        for(Node adjacent: source.adjacents) {
            if (hasPathDfs(adjacent, dest, visited)) {
                return true;
            }
        }
        
        return false;
    }
    
    private Node getNode(int val) {
        Node node = children.get(val);
        if (node == null) {
            node = new Node(val);
            children.put(val, node);
        }
        return node;
    }
}

class Main {
    public static void main(String[] args) {
        MyGraph graph = new MyGraph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        
        System.out.println(graph.hasPathBfs(0, 1));
        System.out.println(graph.hasPathBfs(0, 4));
        System.out.println(graph.hasPathBfs(4, 0));
        System.out.println(graph.hasPathBfs(3, 4));
    }
}

