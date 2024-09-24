package heap_priorityqueue;

class Solution {
    private void sort(int[] nums){
        quickSort(nums,0,nums.length-1);
    }
    private void quickSort(int[] nums, int start, int end){
        if (start >= end) return;
        int pivotPos = partition(nums,start,end);
        quickSort(nums,start,pivotPos-1);
        quickSort(nums,pivotPos+1,end);
    }
    private int partition(int[] nums, int start, int end){
        int pivotPos = start;
        int pivot = nums[pivotPos];
        for (int i = start+1; i <= end; i++) {
            if (nums[i]<pivot){
                pivotPos++;
                if (i!=pivotPos) swap(nums,i,pivotPos);

            }
        }
        nums[start] = nums[pivotPos];
        nums[pivotPos] = pivot;
        return pivotPos;
    }
    private void swap(int[] nums, int idx1, int idx2){
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
    //全部快排，超时
//    public int findKthLargest(int[] nums, int k) {
//        sort(nums);
//        return nums[nums.length-k];
//    }

    // 快速选择算法
    public int findKthLargest1(int[] nums, int k){
        return selectKth(nums,0,nums.length-1, nums.length-k);
    }
    private int selectKth(int[] nums, int low, int high, int k){
        if (low >= high) return nums[k];
        int pivot = nums[low], i = low-1, j = high+1;
        while (i < j){
            do i++; while (nums[i] < pivot);
            do j--; while (nums[j] > pivot);
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        if (k <= j) return selectKth(nums,low,j,k);
        else return selectKth(nums,j+1,high,k);
    }

    //堆排序算法
    public int findKthLargest(int[] nums, int k){
        buildHeap(nums);
        int heapSize = nums.length;
        for (int i = 0; i < k-1; i++) {
            //将第一个元素值置为最后一个值
            nums[0] = nums[heapSize-1];
            heapSize --;
            maxHeapify(nums,0,heapSize);
        }
        return nums[0];
    }
    private void maxHeapify(int[] nums, int i, int heapSize){
        int left = 2*i+1, right = 2*i+2, largest = i;
        if (left < heapSize && nums[left] > nums[largest]) largest = left;
        if (right < heapSize && nums[right] > nums[largest]) largest = right;
        if (i!=largest){
            swap(nums,i,largest);
            maxHeapify(nums,largest,heapSize);
        }
    }
    private void buildHeap(int[] nums){
        int heapSize = nums.length;
        // 建最大堆必须从根部将较大的元素依次上移
        for (int i = heapSize/2; i>=0; i--){
            maxHeapify(nums,i,heapSize);
        }
    }
}
