import java.util.Arrays;

public class StackArray {
    private String[] s;
    private int N;

    StackArray() {
        this.s = new String[1];
    }

    boolean isEmpty() {
        return N == 0;
    }

    void push(String item) {
        if (N == s.length) {
            resize(2 * s.length);
        }
        s[N++] = item;
    }

    String pop() {
        // If the array is 25% capacity, we want to shrink it to half its size, making it 50% capacity.
        String item = s[N--];
        s[N] = null;
        if (N > 0 && N == s.length / 4) {
            resize(s.length / 2);
        }
        return item;
    }

    String peek() {
        return s[N - 1];
    }

    int size() {
        return N;
    }

    String str() {
        return Arrays.toString(s);
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }


    public static void main(String[] args) {
        StackArray s = new StackArray();
        s.push("hey");
        s.push("man");
        s.push("mike");
        System.out.println(s.str());
        System.out.println(s.pop());
        System.out.println(s.str());


    }

}
