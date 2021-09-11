import java.util.Iterator;

public class StackGenericArray<Item> implements Iterable<Item> {
    private Item[] s;
    private int N;

    // Java doesn't allow generic array creation, this is a work around
    // We create an object array and cast it with an item[]
    StackGenericArray(int capacity) {
        s = (Item[]) new Object[capacity];
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // The iterator will be in reverse since we're traversing the stack! (Top to bottom)
    private class ReverseArrayIterator implements Iterator<Item> {
        private int index = N;

        public boolean hasNext() {
            return index > 0;
        }

        public Item next() {
            return s[--index];
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        s[N++] = item;
    }

    public Item pop() {
        Item item = s[N--];
        s[N] = null;
        return item;
    }


    public static void main(String[] args) {
        StackGenericArray<Integer> stackArr = new StackGenericArray<>(5);
        stackArr.push(1);
        stackArr.push(2);
        stackArr.push(3);

        for(Integer i : stackArr) {
            System.out.println(i);
        }
    }
}
