public class InPlaceMergeSort {
    public static void mergeSort(int[] arr, int left, int right) 
    {
        if (left < right) 
        {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) 
    {
        int i = left, j = mid + 1;
        while (i <= mid && j <= right) 
        {
            if (arr[i] <= arr[j]) 
            {
                i++;
            } else 
            {
                int temp = arr[j];
                System.arraycopy(arr, i, arr, i + 1, j - i);
                arr[i] = temp;
                i++;
                mid++;
                j++;
            }
        }
    }
}
