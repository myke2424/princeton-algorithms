import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Node head = null;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private class RandomListIterator implements Iterator<Item> {
        private Node current = head;

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
        System.out.println(randomQueue.dequeue());
        System.out.println(randomQueue.dequeue());
        System.out.println(randomQueue.size());
        System.out.println(randomQueue.isEmpty());
        System.out.println(randomQueue.sample());
        System.out.println();
    }

}