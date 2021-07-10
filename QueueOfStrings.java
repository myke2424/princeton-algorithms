/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

public class QueueOfStrings {
    private Node first, last;

    private class Node {
        String item;
        Node next;
    }

    QueueOfStrings() {
    }

    // Insert new item into queue
    void enqueue(String item) {
        Node oldlast = last;
        Node last = new Node();
        last.item = item;
        last.next = null;

        // Edge case when linked list is empty
        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
        }

    }

    // Remove/return string least recently added (FIFO)
    // Identical to pop() in stack
    String dequeue() {
        String item = first.item;
        first = first.next;

        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    boolean isEmpty() {
        return first == null;
    }

    int size() {
        return 1;
    }

    public static void main(String[] args) {

    }
}
