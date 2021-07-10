import edu.princeton.cs.algs4.In;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node head, tail = null;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }


    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node oldHead = head;
        head = new Node();
        head.item = item;
        head.next = oldHead;

        // If the list was empty, set the tail to equal the head
        if (oldHead == null) {
            tail = head;
        }
        // If theres only 1 node in the list
        else if (oldHead.next == null) {
            tail = oldHead;
            oldHead.prev = head;
        }
        else {
            oldHead.prev = head;
        }

        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node oldTail = tail;


        // We need to check if tail is not null
        if (oldTail != null) {
            tail = new Node();
            tail.item = item;
            oldTail.next = tail;
            tail.prev = oldTail;
        }
        // If the linked list is empty
        else {
            head = new Node();
            head.item = item;
            head = tail;
        }

        size++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        Item item = head.item;
        head = head.next;

        size--;

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        Item item = tail.item;

        if (tail == head) {
            tail = null;
            head = null;
        }
        else {
            tail.prev.next = null;
            tail = tail.prev;

        }

        size--;


        return item;
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(0);
        deque.addLast(10);
        deque.addLast(20);


        for (int i : deque) {
            System.out.println(i);
        }

        System.out.println(deque.size());
        System.out.println(deque.isEmpty());

        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());

        System.out.println(deque.isEmpty());

    }

}