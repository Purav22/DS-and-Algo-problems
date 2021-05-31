package SearchingAndSorting;

public class quickSortAlgo{

    int segregateData(int[] arr, int si, int ei, int pivot){

        swap(arr, pivot, ei);
        int p = si - 1, itr = si;
        while (itr <= ei)
        {
            if (arr[itr] <= arr[ei])
                swap(arr, ++p, itr);
            itr++;
        }
    
        return p;
    }
    
    private void swap(int[] arr, int pivot, int ei) {

    }

    void quickSort(int[] arr, int si, int ei)
    {
        if (si > ei)
            return;
    
        // int len = (ei - si + 1);
        // int pivot = rand() % len + si;
    
        int pivot = ei;
        int pIdx = segregateData(arr, si, ei, pivot);
    
        //Sorted Array Check
        // if it is sorted then return
    
        quickSort(arr, si, pIdx - 1);
        quickSort(arr, pIdx + 1, ei);
    }


    

}
