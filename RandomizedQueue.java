import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int size;


    // construct an empty randomized queue
    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
    }

    private class RandomListIterator implements Iterator<Item> {
        private final Item[] tempArr;

        public RandomListIterator() {
            tempArr = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                tempArr[i] = arr[i];
            }
            StdRandom.shuffle(tempArr);
        }

        public boolean hasNext() {
            return size > 0;
        }

        public Item next() {
            if (size == 0) {
                throw new NoSuchElementException();
            }

            return tempArr[--size];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator() {
        return new RandomListIterator();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NoSuchElementException();
        }

        if (arr.length == size) {
            resize(2 * arr.length);
        }

        int randomIndex = StdRandom.uniform(size + 1);

        if (randomIndex != size) {
            arr[size] = arr[randomIndex];
        }

        arr[randomIndex] = item;
        size++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = arr[--size];
        arr[size] = null;

        // If arr is 25% full, resize to 50% full
        if (size > 0 && size == arr.length / 4) {
            resize(arr.length / 2);
        }

        return item;

    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int randomIndex = StdRandom.uniform(size);
        Item item = arr[randomIndex];
        return item;

    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = arr[i];
        }

        arr = copy;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomQueue = new RandomizedQueue<>();
        randomQueue.enqueue(1);
        randomQueue.enqueue(2);
        randomQueue.enqueue(3);

        System.out.println(randomQueue.size());
        System.out.println(randomQueue.isEmpty());
        System.out.println(randomQueue.sample());

        System.out.println(randomQueue.dequeue());

        for (int i : randomQueue) {
            System.out.println(i);
        }

    }

}

// LinkedList implementation
class RandomizedQueueLinkedList<Item> implements Iterable<Item> {
    private Node head = null;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueueLinkedList() {
    }

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private class RandomListIterator implements Iterator<Item> {
        private Node current = head;
        private int nodeCount = 0;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an independent iterator over items in random order
    // you may (and will need to) use a linear amount of extra memory per iterator.
    public Iterator<Item> iterator() {
        return new RandomListIterator();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return head == null;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldHead = head;
        head = new Node();
        head.item = item;
        head.next = oldHead;

        if (oldHead != null) {
            oldHead.prev = head;
        }


        size++;
    }

    // remove and return a random item
    // Worst case O(N), best case O(1) if removing the head
    // Maybe keep track of the tail and remove the tail if the random number is the size of the queue
    public Item dequeue() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        double randomNum = 0;

        // double randomNum = StdRandom.uniform(size);
        int nodeCount = 0;

        Node current = head;
        Item item = current.item;

        if (randomNum == 0) {
            head = current.next;
            if (head != null) {
                head.prev = null;
            }

        }

        else {
            while (current != null) {
                if (nodeCount == randomNum) {
                    item = current.item;
                    current.prev.next = current.next;
                    break;
                }
                current = current.next;
                nodeCount++;
            }
        }

        size--;


        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        double randomNum = StdRandom.uniform(size);
        int nodeCount = 0;

        Node current = head;
        Item item = current.item;

        if (randomNum == 0) {
            return item;
        }

        while (current != null) {
            if (randomNum == nodeCount) {
                item = current.item;
            }
            current = current.next;
            nodeCount++;
        }

        return item;
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomQueue = new RandomizedQueue<>();
        randomQueue.enqueue(1);
        randomQueue.enqueue(2);
        randomQueue.enqueue(3);

        for (Integer i : randomQueue) {
            System.out.println("Item: " + i);
        }
        // System.out.println(randomQueue.dequeue());
        // System.out.println(randomQueue.dequeue());
        // System.out.println(randomQueue.size());
        // System.out.println(randomQueue.isEmpty());
        // System.out.println(randomQueue.sample());
        // System.out.println();
    }

}