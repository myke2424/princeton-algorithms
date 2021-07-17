/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */


import java.util.Arrays;

public class Sorting {

    // In iteration i, find index 'min' of smallest remaining entry, swap a[i] with a[min]
    // Scans from left to right
    // Entries to the left of the pointer are fixed in ascending order
    // No entry to right of pointer is smaller than any entry to the left of pointer
    // Time Complexity: O(N^2) | Space Complexity O(1)
    static int[] selectionSort(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (arr[j] < arr[i]) {
                    int min = arr[j];
                    arr[j] = arr[i];
                    arr[i] = min;
                }
            }
        }

        return arr;
    }

    // In iteration i, swap a[i] with each larger entry to its left
    // Scans from left to right
    // Entries to the left of the pointer are in ascending order
    // Entries to the right of the pointer have not been seen
    // Twice as fast as selectionSort
    // Time Complexity: O(N^2) | Space Complexity O(1)
    static int[] insertionSort(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
                else {
                    break;
                }
            }
        }

        return arr;

    }


    // Uses insertion sort with stride h length (Scans left to right)
    // Instead of going back 1 position we go 'h' back
    // E.g. instead of j-1, we go j-h
    // When it gets to the last increment '1', the array will be partially sorted
    // and it will use the 'original' insertion sort with j-1 stride
    // Time Complexity O(nlogn)^2 | Space Complexity O(1)
    static int[] shellSort(int[] arr) {
        int N = arr.length;
        int h = 1;

        // 3x + 1 increment sequence (1, 4, 7, 10, 13...) You always start at the highest increment
        while (h < N / 3) {
            h = (3 * h) + 1;
        }

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && arr[j] < arr[j - h]; j -= h) {
                    int tmp = arr[j];
                    arr[j] = arr[j - h];
                    arr[j - h] = tmp;
                }
            }
            h = h / 3; // Move to next increment, e.g. move increment 7 to increment 4
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] arr = { 8, 3, 2, 5, 1, 10, };

        selectionSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = { 8, 3, 2, 5, 1, 10, };

        insertionSort(arr2);
        System.out.println(Arrays.toString(arr2));


        int[] arr3 = { 8, 3, 2, 5, 1, 10, 17, 22, 4, 9, 50, 44 };

        shellSort(arr3);
        System.out.println(Arrays.toString(arr3));
    }
}
