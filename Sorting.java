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


    // Merge algorithm to merge two sorted arrays into one
    // a = arr, aux = arr of length a, lo = first idx, mid = mid idx, hi = last idx
    static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++]; // If ith array is exhausted (past mid point)
            else if (j > hi) a[k] = aux[i++];  // If jth array is exhausted (past last point)
            else if (a[i] > a[j]) a[k] = aux[j++]; // Set aux[k] to the smaller element
            else a[k] = aux[i++];
        }

    }


    static void mergeSort(int[] arr, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(arr, aux, lo, mid);  // Sort left half
        mergeSort(arr, aux, mid + 1, hi); // Sort right half
        merge(arr, aux, lo, mid, hi); // Merge both halves into new arr
    }

    static void mergeSort(int[] arr) {
        int[] aux = new int[arr.length];
        mergeSort(arr, aux, 0, arr.length - 1);
    }

    // Partition is O(N) Runtime
    static int partition(int[] arr, int left_pointer, int right_pointer) {
        int pivot = right_pointer; // Starting right pointer will be our pivot index
        int i = left_pointer; // Start at the left most index
        int j = right_pointer - 1;  // Start at the right most index excluding the pivot

        while (true) {
            while (arr[i] < arr[pivot]) {
                if (i == right_pointer) {
                    break;
                }
                i++;
            }

            while (arr[j] > arr[pivot]) {
                if (j == left_pointer) {
                    break;
                }
                j--;
            }

            // If the pointers have crossed, break out, otherwise swap the values
            if (i >= j) {
                break;
            }

            // Swap values and increase left pointer
            int tmp = arr[j];
            arr[j] = arr[i];
            arr[i] = tmp;
            i++;

        }

        // Swap the left pointer with the pivot
        int tmp = arr[i];
        arr[i] = arr[pivot];
        arr[pivot] = tmp;

        // Return index of pivot;
        return i;

    }

    // 1. Shuffle Array (Didn't implement shuffling here)
    // 2. Partition Array (Get pivot)
    // 3. Recursively sort each sub array (Arrays to left and right of pivot);
    //      - Base case: when our subarray has 0 or 1 elements, we do nothing.
    // Time Complexity: O(N log N)
    static void quickSort(int[] arr, int left_pointer, int right_pointer) {
        if (right_pointer - left_pointer <= 0) {
            return;
        }

        int pivot = partition(arr, left_pointer, right_pointer);
        quickSort(arr, left_pointer, pivot - 1);
        quickSort(arr, pivot + 1, right_pointer);
    }

    static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
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

        //int[] arr4 = { 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5 };

        int[] arr5 = { 4, 1, 8, 1, 2, 3, 10, 6, 5 };
        mergeSort(arr5);
        System.out.println(Arrays.toString(arr5));

        int[] pArr = { 0, 5, 2, 1, 6, 3 };
        quickSort(pArr);
        System.out.println(Arrays.toString(pArr));

        int[] x = { 5, 4, 2, 1, 5, 10, 0, 4, 5, 2, 3, 1, 10, 100, 2, 45 };
        quickSort(x);
        System.out.println(Arrays.toString(x));

        String[] sa = { "Hey", "mike", "Sup", "bro", "EZ" };
        Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(sa));

    }
}
