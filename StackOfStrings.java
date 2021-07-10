
// First implementation of a stack will use a LinkedList

import edu.princeton.cs.algs4.StdIn;

public class StackOfStrings {
    private Node head = null;

    // Inner class data type
    private class Node {
        String item;
        Node next;
    }

    StackOfStrings() {
    }

    void push(String item) {
        Node currentHead = head;
        head = new Node();
        head.item = item;
        head.next = currentHead;
    }

    String pop() {
        Node currentHead = head;
        head = currentHead.next;

        return currentHead.item;

    }

    boolean isEmpty() {
        return head == null;
    }

    int size() {
        int counter = 0;
        while (head.next != null) {
            counter++;
        }

        return counter;
    }

    // TestClient
    public static void main(String[] args) {
        StackOfStrings s = new StackOfStrings();

        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            if (str.equals("-")) {
                System.out.println(s.pop());
            }
            else {
                s.push(str);
            }
        }
    }
}
