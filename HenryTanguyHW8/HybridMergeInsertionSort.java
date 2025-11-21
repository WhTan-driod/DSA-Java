public class HybridMergeInsertionSort 
{

    //Initial function to take array
    public static void hybridSort(int[] arr) 
    {
        hybridSort(arr, 0, arr.length - 1);
    }

    //helper function to with logic to decide between merge/insertion sort
    private static void hybridSort(int[] arr, int left, int right) 
    {
        if (right - left + 1 <= 10) 
        {
            insertionSort(arr, left, right);
            return;
        }

        int mid = left + (right - left) / 2;

        hybridSort(arr, left, mid);
        hybridSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    private static void insertionSort(int[] arr, int left, int right) 
    {
        for (int i = left + 1; i <= right; i++) 
        {
            int key = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > key) 
            {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) 
    {

        int[] temp = new int[right - left + 1];

        int i = left;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= right) 
        {
            if (arr[i] <= arr[j]) temp[t++] = arr[i++];
            else temp[t++] = arr[j++];
        }

        while (i <= mid) temp[t++] = arr[i++];
        while (j <= right) temp[t++] = arr[j++];

        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    /**
     * Returns maximum triangle perimeter from sorted array.
     * 
     * After sorting ascending:
     *    Check triplets from end (largest values first).
     * 
     * Triangle rule for a ≤ b ≤ c:
     *      a + b > c
     * 
     * First valid found = maximum perimeter.
     */
    public static int maxTrianglePerimeter(int[] arr) 
    {

        hybridSort(arr);  //Sort using hybrid algorithm

        //Check triplets from largest to smallest
        for (int i = arr.length - 1; i >= 2; i--) 
        {
            int c = arr[i];
            int b = arr[i - 1];
            int a = arr[i - 2];

            if (a + b > c) 
            {
                return a + b + c;
            }
        }

        return -1; // No valid triangle
    }

    public static void main(String[] args) 
    {

        int[] ex1 = {4, 9, 7, 8, 3, 2};
        int[] ex2 = {1, 2, 3, 12, 15};
        int[] ex3 = {5, 6, 10, 12, 15};

        System.out.println(maxTrianglePerimeter(ex1)); // 24
        System.out.println(maxTrianglePerimeter(ex2)); // -1
        System.out.println(maxTrianglePerimeter(ex3)); // 37
    }
}

