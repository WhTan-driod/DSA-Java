public class InPlaceMergeSort {

    public static void mergeSort(int[] arr) 
    {
        inPlaceMergeSort(arr, 0, arr.length);
    }

    private static void inPlaceMergeSort(int[] a, int left, int right) 
    {
        if (right - left <= 1) return; //base case

        //binary shift - same as - mid = (left + (right - left)) /2 without risk of overflow
        int mid = (left + right) >>> 1;

        inPlaceMergeSort(a, left, mid);
        inPlaceMergeSort(a, mid, right);

        inPlaceMerge(a, left, mid, right);
    }

    // In-place merge using rotations (O(1) space)
    private static void inPlaceMerge(int[] a, int left, int mid, int right) 
    {
        int i = left;
        int j = mid;

        // While both halves have elements
        while (i < j && j < right) 
            {

            // Find first element in left half greater than first in right half
            if (a[i] <= a[j]) 
                {
                i++;
            } 
            else 
            {
                // Rotate block: [i .. j-1], j → inserted at i
                rotateRight(a, i, j, j + 1);

                // Update indices after rotation
                i++;
                j++;
            }
        }
    }

    // Rotate right by 1 position: [left .. mid-1], [mid .. right-1]
    private static void rotateRight(int[] a, int left, int mid, int right) 
    {
        reverse(a, left, mid);
        reverse(a, mid, right);
        reverse(a, left, right);
    }

    private static void reverse(int[] a, int l, int r) 
    {
        r--;
        while (l < r) 
        {
            int t = a[l];
            a[l] = a[r];
            a[r] = t;
            l++;
            r--;
        }
    }

    // Test
    public static void main(String[] args) 
    {
        int[] arr = {5, 2, 9, 1, 3, 7, 8, 4};
        mergeSort(arr);

        for (int x : arr) System.out.print(x + " ");
    }
}

