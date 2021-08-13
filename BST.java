// Symbol table implementation using a Binary Search Tree

import edu.princeton.cs.algs4.Queue;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int count; // total number of nodes in subtree

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }

    }

    // add the key/val pair
    // invoke recursive method starting at the root
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    // recursive private method for inserting into the bst
    // reset links on the way up
    // Time complexity: O(logn)
    private Node put(Node node, Key key, Value val) {
        // if node is null we want to return the link to the new node
        if (node == null) return new Node(key, val);

        int cmp = key.compareTo(node.key);

        if (cmp < 0) node.left = put(node.left, key, val);

        else if (cmp > 0) node.right = put(node.right, key, val);

            // If the key is already in the tree, we want to reset the value.
        else node.val = val;

        node.count = 1 + size(node.left) + size(node.right);

        return node;
    }

    // Time complexity O(logn)
    public Value get(Key key) {
        Node node = root;

        while (node != null) {
            int cmp = key.compareTo(node.key);

            if (cmp < 0) node = node.left;

            else if (cmp > 0) node = node.right;

            else return node.val;


        }

        return null;

    }


    public void delete(Key key) {
        root = delete(root, key);
    }

    // 3 cases for delete (Hibbard deletion)
    // Case 1. Node to delete has no children: return null for that node and update counts
    // Case 2. Node to delete has one children: return the link to the child and update the counts
    // Case 3. Node to delete has two children:
    //         - Find the next smallest node in the right subtree:  min(node.right) and store in variable
    //         - Delete the minimum node in the right tree
    //         - Replace the node that has to be deleted with the minimum node from the right subtree.
    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);

        if (cmp < 0) node.left = delete(node.left, key);  // search for key
        else if (cmp > 0) node.right = delete(node.right, key);  // search for key
        else {
            if (node.right == null) return node.left; // no right child
            if (node.left == null) return node.right; // no left child

            // replace deleted node with successor (most min child)
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }

        node.count = size(node.left) + size(node.right) + 1; // update subtree counts
        return node;

    }


    public void deleteMin() {
        root = deleteMin(root);
    }

    // Go left until finding node with a null left link
    // Replace that node by its right link
    // Update subtree counts
    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    // Go right until null
    public Key max() {
        Node node = root;

        if (node == null) return null;

        while (node.right != null) {
            node = node.right;
        }

        return node.key;
    }

    // Go left until null
    public Node min(Node node) {
        if (node == null) return null;

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    // How many keys are less than the given key?
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node node) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);

        if (cmp < 0) return rank(key, node.left);

            // Add one for the root + size left subtree and rank of the right
        else if (cmp > 0) return 1 + size(node.left) + rank(key, node.right);

            // If the key is equal to the current node, return the size of the nodes subtree
        else return size(node.left);
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.count;
    }


    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        inorder(root, q); // add all the keys in the tree to the queue.
        return q; // return the queue, the client can iterate the queue for iteration

    }

    // Inorder Traversal (yields keys in ascending order)
    // - Traverse left sub tree
    // - Enqueue key
    // - Traverse right subtree
    // Left sub tree -> Root -> Right subtree
    private void inorder(Node node, Queue<Key> q) {
        if (node == null) return;
        inorder(node.left, q);
        q.enqueue(node.key);
        inorder(node.right, q);
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.put(10, "mike");
        bst.put(8, "charlie");
        bst.put(6, "goat");
        bst.put(12, "myke");
        bst.put(55, "yo");
        bst.put(1, "test");

        bst.delete(10);
        System.out.println(bst.get(10));

    }
}
