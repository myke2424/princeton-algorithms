import java.util.Iterator;

// We want to support iteration in our client code, so we need to implement the Iterable interface
// An Iterable, is a class that has a method that returns an iterator.
// An iterator has the following methods: hasNext() and next()
// When implemented, we can call the elegant foreach statement on our stack
// E.g. -> for(String s: stack)

// Generic syntax = Class<GenericName>
public class Stack<Item> implements Iterable<Item> {
    private Node first = null;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    void push(Item item) {
        Node oldFirst = first;
        first = new Node();
        size++;

        first.item = item;
        first.next = oldFirst;
    }

    Item pop() {
        Item item = first.item;
        first = first.next;
        size--;
        return item;
    }

    boolean isEmpty() {
        return first == null;
    }

    int size() {
        return size;
    }

    public String toString() {
        String str = "";

        for (Item item : this) {
            str += item + " -> ";
        }

        str += " null ";

        return str;
    }

    public static void main(String[] args) {
        Stack<Integer> intStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();

        intStack.push(1);
        intStack.push(2);
        intStack.push(3);

        System.out.println(intStack);

        for (int item : intStack) {
            System.out.println("ITEM: " + item);
        }

        intStack.pop();

        for (int item : intStack) {
            System.out.println("ITEM: " + item);
        }
        // System.out.println(intStack.size());
        //
        // System.out.println(intStack.toString());
        // for (int i = 0; i <= 3; i++) {
        //     intStack.pop();
        //     System.out.println(intStack.toString());
        // }

        // System.out.println(intStack.size());

    }
}
