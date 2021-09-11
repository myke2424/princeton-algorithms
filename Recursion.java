import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static int sumArr(int[] arr) {
        if (arr.length == 1) return arr[0];
        return arr[0] + sumArr(Arrays.copyOfRange(arr, 1, arr.length));
    }

    // Given three poles, and a stack of disks on the first pole (largest to smallest)
    // Only move one disk at a time, never place a larger disk on top of a smaller one
    public static void hanoi(int N, String fromPole, String toPole, String intermediatePole) {
        if (N >= 1) {
            hanoi(N - 1, fromPole, intermediatePole, toPole);
            System.out.println("Moving disk from: " + fromPole + "to: " + toPole);
            hanoi(N - 1, intermediatePole, toPole, fromPole);
        }
    }

    public static ArrayList<Integer> reverselist(ArrayList<Integer> arr) {
        if (arr.size() > 1) {
            Integer val = arr.remove(0);
            reverselist(arr);
            arr.add(val);
        }
        return arr;
    }

    // return total number of chars across all strings
    public static int totalChars(String[] strArr) {
        if (strArr.length == 1) return strArr[0].length();
        return strArr[0].length() + totalChars(Arrays.copyOfRange(strArr, 1, strArr.length));
    }

    // Return array with even numbers...
    // Break it into sub problems
    // We can say -> The even numbers are the first item (if even) + remaining array
    public static List<Integer> evenNums(List<Integer> arr) {
        if (arr.size() > 0) {
            if (!(arr.get(0) % 2 == 0)) {
                arr.remove(0);
                evenNums(arr.subList(0, arr.size()));
            }
            else {
                evenNums(arr.subList(1, arr.size()));
            }

        }

        return arr;
    }

    // 1 -> 3 -> 6 -> 10 -> 15 -> 21 -> 27
    // N plus the previous number
    // The 7th number in the sequence is 28, since it’s 7 (which is N) plus 21 (the previous number in the sequence)
    public static int triangularNum(int N) {
        if (N == 1) return 1;

        return N + triangularNum(N - 1);
    }

    // return first index that is equal to the char (Assume at least one is present)
    public static int getFirstCharIndex(String str, char c, int index) {
        if (str.charAt(0) == c) {
            return index;
        }
        return getFirstCharIndex(str.substring(1), c, ++index);
    }

    // Given a grid of rows and columns
    // calculates the number of possible “shortest” paths
    // from the upper-leftmost square to the lower-rightmost square.
    // By "shortest" path, i mean, at every step, you're either:
    // moving one step to the right, or one step downward.
    public static int uniquePaths(int rows, int cols) {
        if (rows == 1 || cols == 1) return 1;
        return uniquePaths(rows - 1, cols) + uniquePaths(rows, cols
                - 1);// unique paths of 1x1 grid + n-1 grid
    }

    public static int sumOfDigits(int N) {
        if (N == 1) return 1;
        return N + sumOfDigits(N - 1);
    }

    public static boolean isPalindrome(String str) {
        if (str.length() == 1) return true;

        if (str.length() >= 2) {
            if (str.charAt(0) == str.charAt(str.length() - 1)) {
                return true;
            }
            else {
                return false;
            }
        }


        return isPalindrome(str.substring(1, str.length() - 1));
    }

    // return true if first str is subsequence of second string
    public static boolean isSubsequence(String s1, String s2) {
        return isSubsequence(s1, s2, s1.length() - 1, s2.length() - 1);
    }

    private static boolean isSubsequence(String s1, String s2, int m, int n) {
        if (m == 0) return true;

        if (n == 0) return false;

        // Check if last two chars are equal, if they are, move the pointer one char to the left for both strings
        if (s1.charAt(m) == s2.charAt(n)) {
            return isSubsequence(s1, s2, m - 1, n - 1);
        }

        // If last chars are not matching, only move the pointer for the bigger string (s2)
        return isSubsequence(s1, s2, m, n - 1);
    }

    public static int countSubstring(String mainStr, String subStr) {
        int N = subStr.length();

        System.out.println(mainStr);

        if (mainStr.length() < N) return 0;

        if (mainStr.substring(0, N).equals(subStr)) {
            return 1 + countSubstring(mainStr.substring(N), subStr);
        }

        return countSubstring(mainStr.substring(1), subStr);

    }

    public static int countSubstringIterative(String mainStr, String subStr) {
        int N = subStr.length();
        int counter = 0;

        for (int i = 0; i < mainStr.length(); i++) {
            if (i + N > mainStr.length()) break;

            if (mainStr.substring(i, i + N).equals(subStr)) counter++;
        }

        return counter;
    }

    public static int runningSum(int N) {
        if (N <= 0) return 0;
        return N + runningSum(N - 1);
    }




    public static void main(String[] args) {

    }
}
