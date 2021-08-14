// Binary search implementation
// We can implement binary search recursively or iteratively
// Must always operate on a sorted data structure.

public class BinarySearch {


    public static boolean binarySearchRecursive(int[] arr, int x) {
        return binarySearchRecursive(arr, x, 0, arr.length - 1);
    }


    // If out of bounds, return false;
    // If equal, return true
    // If < mid, search left side
    // If > mid, search right side
    private static boolean binarySearchRecursive(int[] arr, int x, int left, int right) {
        if (left > right) {
            return false;
        }

        // Left + Right can overflow with java integers, avoid it with left + ((right - left) / 2)
        int mid = left + ((right - left) / 2);

        if (arr[mid] == x) return true;

        else if (x < arr[mid]) {
            return binarySearchRecursive(arr, x, left, mid - 1);
        }
        else {
            return binarySearchRecursive(arr, x, mid + 1, right);
        }


    }

    public static boolean binarySearchIterative(int[] arr, int x) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) / 2);

            if (arr[mid] == x) return true;
            else if (x < arr[mid]) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }

        }

        return false;

    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 10, 20, 30, 50, 100, 120, 180, 1000, 2000, 35000 };

        System.out.println(binarySearchRecursive(arr, 200000));

        System.out.println(binarySearchIterative(arr, 120));
    }
}
