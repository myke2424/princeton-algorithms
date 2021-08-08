// Delete an item: Remove the largest or smallest item in the queue
// We can have a Max or Min Priority Queue, we'll use a max priority queue for example.

// --- Implementations ---
// Unordered Array or LinkedList: Deletion = O(N), Max = O(N), Insert = O(1)
// Ordered Array or LinkedList (Use insertion sort): Deletion = O(1), Max = O(1), Insert = O(N)
// Binary Heap (OPTIMAL): Deletion && Insertion && Max = O(logN)

// MaxPriorityQueue generic items will be comparable (Our generic type 'key' extends comparable of 'key')
// This just means our generic type must be comparable
// We will use a resizing array under the hood.

class PriorityQueue<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    // PQ first index is 1 not 0.
    public PriorityQueue(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    // Insert at the end of the array (Last node in the binary heap),
    // Swim the node up to maintain the heap order invariant
    public void insert(Key item) {
        pq[++N] = item;
        swim(N);
    }

    // Exchange root with the node at the end of the array, then sink it down (logn)
    public Key deleteMax() {
        Key max = pq[1];
        swap(1, N--);
        sink(1);
        pq[N + 1] = null;
        return max;
    }

    // Swim when the child's key becomes larger then parent key (Swim the child up to its correct order)
    // while not the root node and the parent is less than the current node (child)
    // Swap parent and child node
    // Reset k to the parent index to check the next parent is in order to maintain heap invariant
    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    // Sink when parents key becomes smaller than one or both of its children's
    // While we're in bounds (2*K <=N)
    // Exchange key in parent with key in LARGE child
    // Repeat until heap order is restored.
    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k; // child one, child two is j+1

            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;

            swap(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void swap(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    public static void main(String[] args) {
        PriorityQueue p = new PriorityQueue(10);
        p.insert(10);
        p.insert(2);
        p.insert(100);
        p.insert(3);
        p.insert(50);
        p.insert(1);
        p.insert(6);
        p.insert(25);
        p.insert(3);

        System.out.println(p.deleteMax());
    }

}