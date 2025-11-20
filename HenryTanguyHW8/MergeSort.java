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

        for (int i = 0; i < 5; i++) 
        {
            int[] fiveK = new Random().ints(5000, 0, 100000).toArray();
            startTime = nanoTime();
            mergeSort(fiveK); 
            endTime = nanoTime();
            runTimes[i] = endTime - startTime;
        }

        System.out.println("average run time for 5,000 array: " + averageTime(runTimes) + " seconds");

        for(int i = 0; i < 5; i++) 
        {
            int[] tenK = new Random().ints(10000, 0, 100000).toArray();
            startTime = nanoTime();
            mergeSort(tenK); 
            endTime = nanoTime();
            //System.out.println("Execution time for 10,000 array: " + (endTime - startTime) + " nanoseconds");
            runTimes[i] = endTime-startTime;
        }

        System.out.println("average run time for 10,000 array: " + averageTime(runTimes) + " seconds");

        for(int i = 0; i < 5; i++) 
        {
            int[] fiftyK = new Random().ints(50000, 0, 100000).toArray();
            startTime = nanoTime();
            mergeSort(fiftyK); 
            endTime = nanoTime();
            runTimes[i] = endTime-startTime;
        }

        System.out.println("average run time for 50,000 array: " + averageTime(runTimes) + " seconds");


        for(int i = 0; i < 5; i++) 
        {
            int[] hundredK = new Random().ints(100000, 0, 100000).toArray();
            startTime = nanoTime();
            mergeSort(hundredK); 
            endTime = nanoTime();
            runTimes[i]=endTime-startTime;
        }

        System.out.println("average run time for 100,000 array: " + averageTime(runTimes) + " seconds");

    }
}
