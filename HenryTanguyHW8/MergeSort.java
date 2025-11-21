import static java.lang.System.nanoTime;
import java.util.Arrays;
import java.util.Random;

/**
This program demonstrates the difference in execution time between
an in-place vs non-in-place Merge-sort algorithm.
*/

class InPlaceMergeSort {

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
}

public class MergeSort 
{

    public static void NotInPlaceMergeSort(int[] arr) 
    {
        if (arr.length < 2) 
        {
            return;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        NotInPlaceMergeSort(left);
        NotInPlaceMergeSort(right);
        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) 
    {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) 
        {
            if (left[i] <= right[j]) 
            {
                arr[k++] = left[i++];
            } 
            else 
            {
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

    public static long averageTime(long[] arr)
    {
        long average=0;
        for(long num : arr){
            average += num;
        }
        return (average/arr.length);
    }

    public static void main(String[] args) 
    {
        long startTime;
        long endTime;
        long[] runTimes = new long[5];

        //In-Place vs. Not-in-place Merge-sort for 5k array size
        //create an array of randomly generated integers and call sort method
        //store each run time in separate array
        for (int i = 0; i < 5; i++) 
        {
            int[] fiveK = new Random().ints(5000, 0, 100000).toArray();
            startTime = nanoTime();
            NotInPlaceMergeSort(fiveK); 
            endTime = nanoTime();
            runTimes[i] = endTime - startTime;
        }

        //calculate average of 5 run times and display
        System.out.println("average run time for not-in-place merge-sort 5,000 array: " + averageTime(runTimes) + " nanoseconds");

        for (int i = 0; i < 5; i++) 
        {
            int[] fiveK = new Random().ints(5000, 0, 100000).toArray();
            startTime = nanoTime();
            InPlaceMergeSort.mergeSort(fiveK);
            endTime = nanoTime();
            runTimes[i] = endTime - startTime;
        }

        System.out.println("average run time for in place merge-sort 5,000 array: " + averageTime(runTimes) + " nanoseconds");
        //---------------------------------------------------------

        //In-Place vs. Not-in-place Merge-sort for 10k array size
        for(int i = 0; i < 5; i++) 
        {
            int[] tenK = new Random().ints(10000, 0, 100000).toArray();
            startTime = nanoTime();
            NotInPlaceMergeSort(tenK); 
            endTime = nanoTime();
            runTimes[i] = endTime-startTime;
        }

        System.out.println("average run time for not-in-place merge-sort 10,000 array: " + averageTime(runTimes) + " nanoseconds");
        
        for(int i = 0; i < 5; i++) 
        {
            int[] tenK = new Random().ints(10000, 0, 100000).toArray();
            startTime = nanoTime();
            InPlaceMergeSort.mergeSort(tenK); 
            endTime = nanoTime();
            runTimes[i] = endTime-startTime;
        }

        System.out.println("average run time for in-place merge-sort 10,000 array: " + averageTime(runTimes) + " nanoseconds");
        //---------------------------------------------------------

        //In-Place vs. Not-in-place Merge-sort for 50k array size
        for(int i = 0; i < 5; i++) 
        {
            int[] fiftyK = new Random().ints(50000, 0, 100000).toArray();
            startTime = nanoTime();
            NotInPlaceMergeSort(fiftyK); 
            endTime = nanoTime();
            runTimes[i] = endTime-startTime;
        }

        System.out.println("average run time for not-in-place merge-sort 50,000 array: " + averageTime(runTimes) + " nanoseconds");

        for(int i = 0; i < 5; i++) 
        {
            int[] fiftyK = new Random().ints(50000, 0, 100000).toArray();
            startTime = nanoTime();
            InPlaceMergeSort.mergeSort(fiftyK); 
            endTime = nanoTime();
            runTimes[i] = endTime-startTime;
        }

        System.out.println("average run time for in-place merge-sort 50,000 array: " + averageTime(runTimes) + " nanoseconds");
        //---------------------------------------------------------

        //In-Place vs. Not-in-place Merge-sort for 50k array size
        for(int i = 0; i < 5; i++) 
        {
            int[] hundredK = new Random().ints(100000, 0, 100000).toArray();
            startTime = nanoTime();
            NotInPlaceMergeSort(hundredK); 
            endTime = nanoTime();
            runTimes[i]=endTime-startTime;
        }

        System.out.println("average run time for not-in-place 100,000 array: " + averageTime(runTimes) + " nanoseconds");

        for(int i = 0; i < 5; i++) 
        {
            int[] hundredK = new Random().ints(100000, 0, 100000).toArray();
            startTime = nanoTime();
            InPlaceMergeSort.mergeSort(hundredK); 
            endTime = nanoTime();
            runTimes[i]=endTime-startTime;
        }

        System.out.println("average run time for in-place 100,000 array: " + averageTime(runTimes) + " nanoseconds");

    }
}
