// Hash Table SC = 'Separate Chaining'
// Separate chaining uses linked list at each ith index to store values we identical hashes
class HashTableSC<Key, Value> {

    private int M = 100; // Fixed size array, this could be changed to a resizing array.
    private Node[] symbolTable = new Node[M];

    private static class Node {
        private Object key; // key/value are type objects since we cant have an array of generics
        private Object value;
        private Node next;

        public Node(Object key, Object value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        int i = hash(key);

        for (Node x = symbolTable[i]; x != null; x = x.next) {
            if (key.equals(x.key)) return (Value) x.value;
        }

        return null;
    }

    public void put(Key key, Value value) {
        int i = hash(key);

        for (Node x = symbolTable[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }

        // Insert new node that links to old node position
        symbolTable[i] = new Node(key, value, symbolTable[i]);
    }

    public static void main(String[] args) {
        HashTableSC hashTable = new HashTableSC();

    }
}