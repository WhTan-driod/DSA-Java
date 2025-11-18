import static java.lang.System.nanoTime;
import java.util.Arrays;
import java.util.Random;

public class MergeSort 
{

    public static void mergeSort(int[] arr) 
    {
        if (arr.length < 2) 
        {
            return;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) 
    {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) 
        {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) 
        {
            arr[k++] = left[i++];
        }
        while (j < right.length) 
        {
            arr[k++] = right[j++];
        }
    }
    public static void main(String[] args) 
    {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        long startTime = nanoTime();
        mergeSort(arr);
        long endTime = nanoTime();
        System.out.println("Sorted array: " + Arrays.toString(arr));
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");

        // Test with a larger random array
        int[] largeArr = new Random().ints(100000, 0, 100000).toArray();
        startTime = nanoTime();
        mergeSort(largeArr); 
        endTime = nanoTime();
        System.out.println("Execution time for large array: " + (endTime - startTime) + " nanoseconds");
    }
}
