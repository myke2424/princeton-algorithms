/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

// Use the adjacency list to represent the graph
// Where each index will be a Bag Data structure that will
// contain all vertices adjacent (connected by edge) to the given vertex (index)
// Time/Space proportion to number of entries in the graph
// A bag is just a stack with no pop, and the underlying data structure is a linked list or resizing array.
public class Graph {
    private final int V; // Number of vertices
    private int E;
    private Bag<Integer>[] adj; // Adjacency lists using bag data type

    // Create empty graph with V Vertices
    public Graph(int V) {
        this.V = V;
        this.E = E;

        adj = (Bag<Integer>[]) new Bag[V]; // array of bags of integers
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>(); // every entry in the array will be its own bag.
        }
    }

    // Add an edge v-w (Parallel edges allowed) (V connects to W, W connects to V)
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    // Vertices adjacent to v (Return bag of adjacent vertices that can be iterated on)
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // Number of vertices
    public int V() {
        return V;
    }

    // Number of edges
    public int E() {
        return E;
    }

    // String representation of graph
    public String toString() {
        return null;
    }

    // Degree of a vertice is the number of edges connecting it
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int _ : G.adj(v)) degree++;
        return degree;
    }

    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++) {
            if (degree(G, v) > max) {
                max = degree(G, v);
            }
        }
        return max;
    }

    public static double averageDegree(Graph G) {
        return 2.0 * G.E() / G.V();
    }

    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w) count++;
            }
        }
        return count / 2; // each edge counted twice.
    }


    public static void main(String[] args) {

    }
}

// BFS - Put unvisited vertices on a queue
class BreadthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;

    // Put s onto a FIFO queue, mark s as visited.
    // Repeat until the queue is empty:
    // - remove the least recently added vertex v
    // - add each of v's unvisited adjacent neighbours to the queue and mark them as visited
    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] = true;

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int adjacent : G.adj(v)) {
                if (!marked[adjacent]) {
                    queue.enqueue(adjacent);
                    marked[adjacent] = true;
                    edgeTo[adjacent] = v;
                }
            }
        }
    }
}

// DFS - Put unvisited vertices on a stack (implicitly using the call-stack via recursion)
class DepthFirstSearch {
    private boolean[] marked; // Marked vertexes we've already visited

    // Previous vertex connected by an edge to current vertex (Used to keep track of the dfs path)
    private int[] edgeTo; // Parent-link representation of a tree rooted at s
    private int s; // Source vertex

    public DepthFirstSearch(Graph G, int s) {
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
                edgeTo[w] = v;
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    // path from source vertex to V
    // Use a stack to keep track of a path since we get it in reverse order (S -> V) but we're starting with V so its reversed.
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
