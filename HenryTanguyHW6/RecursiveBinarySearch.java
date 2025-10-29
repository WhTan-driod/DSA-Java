public class RecursiveBinarySearch{
    public static int binarySearch(int[] arr, int left, int right, int target) {
        if (right >= left) {
            int mid = left + (right - left) / 2;

            // Check if the target is present at mid
            if (arr[mid] == target) {
                return mid;
            }

            // If target is smaller than mid, it can only be present in left subarray
            if (arr[mid] > target) {
                return binarySearch(arr, left, mid - 1, target);
            }

            // Else the target can only be present in right subarray
            return binarySearch(arr, mid + 1, right, target);
        }

        // Target is not present in array
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40};
        int target = 10;
        int result = binarySearch(arr, 0, arr.length - 1, target);
        if (result == -1) {
            System.out.println("Element not present in array");
        } else {
            System.out.println("Element found at index: " + result);
        }
    }
}