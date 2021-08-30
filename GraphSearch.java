/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GraphSearch {
    public static void main(String[] args) {

    }

    // DFS Implementation using a stack instead of implicitly using the call-stack via recursion
    public static void dfsPrint(Node start) {
        Stack<Node> stack = new Stack<>();
        Set<Node> seen = new HashSet<>();

        stack.push(start); // Push starting node onto the stack

        while (!stack.isEmpty()) {
            Node current = stack.pop(); // Pull a node off the stack

            // Process the node if it hasn't been seen (We're printing it out but we could do any processing here)
            if (!seen.contains(current)) {
                seen.add(current);
                System.out.println(current.val);
            }

            // Add the unseen children of the node onto the stack
            for (Node adjacent : current.adjacents) {
                if (!seen.contains(adjacent)) {
                    stack.push(adjacent);
                }
            }
        }
    }

    // The only difference between DFS and BFS, is that BFS uses a queue instead of a stack for its underlying data structure
    // for the order in which the nodes are processed.
    // Time Complexity O(V + E) (Vertices + Edges were traversing)
    // Space Complexity O(V) (Number of vertices we store in our AST (stack/queue)
    public static void bfsPrint(Node start) {
        LinkedList<Node> queue = new LinkedList<>(); // Linked list implements the queue interface
        Set<Node> seen = new HashSet<>();

        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll(); // Retrieves / removes head of the queue (linked list)

            if (!seen.contains(current)) {
                seen.add(current);
                System.out.println(current);
            }

            for (Node adjacent : current.adjacents) {
                if (!seen.contains(adjacent)) {
                    queue.add(adjacent);
                }
            }
        }
    }
}


class Node {
    int val;
    List<Node> adjacents;
}
