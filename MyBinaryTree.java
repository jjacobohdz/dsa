import java.util.*;
class MyBinaryTree {
    private class Node {
        private Node left, right;
        private int val;
        private Node(int val) {
            this.val = val;
        }
    }
    private Node root;
    public MyBinaryTree(int root) {
        this.root = new Node(root);
    }
    public void insert(int val) {
        insert(root, val);
    }
    public void inorder() {
        inorder(root);
    }
    public void preorder() {
        preorder(root);
    }
    public void postorder() {
        postorder(root);
    }
    public boolean containsDfs(int val) {
        return containsDfs(root, val);
    }
    public boolean containsBfs(int val) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        Set<Node> visited = new HashSet<>();
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.val == val) {
                return true;
            }
            if (visited.contains(current)) {
                continue;
            }
            
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return false;
    }
    private boolean containsDfs(Node root, int val) {
        if (root == null) {
            return false;
        }
        if (root.val == val) {
            return true;
        }
        return containsDfs(root.left, val) || containsDfs(root.right, val);
    }
    private Node insert(Node root, int val) {
        if (root == null) {
            return new Node(val);
        } else if (val < root.val) {
            // insert left
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }
    private void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }
    private void preorder(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preorder(root.left);
        preorder(root.right);
    }
    private void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.val);
    }
}

class Main {
    public static void main(String[] args) {
        MyBinaryTree tree = new MyBinaryTree(10);
        tree.insert(8);
        tree.insert(11);
        tree.insert(2);
        tree.insert(14);
        // tree.inorder();
        // tree.preorder();
        // tree.postorder();
        System.out.println(tree.containsBfs(2));
        System.out.println(tree.containsBfs(19));
    }
}