// Three LAWS of Recursion:
// 1. A recursive algorithm must have a base case
// 2. A recursive algorithm must move towards the base case
// 3. A recursive algorithm must call itself (RECURSE!)

import java.util.Arrays;

public class Recursion {

    public static int factorial(int n) {
        if (n == 1) return 1;

        return n * factorial(n - 1);
    }

    public static int[] doubleArray(int[] arr) {
        return doubleArray(arr, 0);
    }

    private static int[] doubleArray(int[] arr, int idx) {
        if (idx == arr.length) return arr;

        arr[idx] = arr[idx] * 2;
        idx++;

        return doubleArray(arr, idx);
    }

    public static String reverseString(String str) {
        if (str.length() == 1) return str;
        return reverseString(str.substring(1)) + str.charAt(0);
    }

    public static int countChar(String str, char c) {
        // base case
        if (str.length() == 0) {
            return 0;
        }

        // recurse
        if (str.charAt(0) == c) return 1 + countChar(str.substring(1), c);

        return countChar(str.substring(1), c);
    }

    // Let’s say we have a staircase of N steps, and a person has the ability to climb
    // one, two, or three steps at a time. How many different possible “paths” can
    // someone take to reach the top?
    public static int stairCaseProblem(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1; // 1
        if (N == 2) return 2; // 1,1 && 2
        if (N == 3) return 4; // 1,1,1 && 3 && 2,1 && 1,2

        return stairCaseProblem(N - 1) + stairCaseProblem(N - 2) + stairCaseProblem(N - 3);
    }
    

    public static void main(String[] args) {
        System.out.println(factorial(5));

        int[] a = { 10, 20, 30, 40 };

        String name = "mike";
        System.out.println(reverseString(name));

        String s = "xxxcmxxxcxxxmsdfsf";
        System.out.println(countChar(s, 'x'));

        System.out.println(stairCaseProblem(4));
    }
}
