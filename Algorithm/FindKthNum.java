package Algorithm;

import java.util.Arrays;

public class FindKthNum {
    public int findKthNum(int[] nums, int k) {
        return findKthNum(nums, 0, nums.length - 1, k - 1);
    }

    private int findKthNum(int[] nums, int start, int end, int k) {
        if (start < end) {
            int pi = partition(nums, start, end);
            if (pi == k) {
                return nums[k];
            } else if (pi < k) {
                return findKthNum(nums, pi + 1, end, k);
            }else {
                return findKthNum(nums, start, pi - 1, k);
            }
        }
        return 0;
    }


    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length-1);

    }

    private void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int pi = partition(nums, start, end);
            quickSort(nums, start, pi-1);
            quickSort(nums,pi+1,end);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int i = start - 1;
        for (int j = start; j < end ; j++) {
            if (nums[j] < pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, end);
        return i + 1;
    }

    private void swap(int[] nums, int l, int r) {
        int t = nums[l];
        nums[l] = nums[r];
        nums[r] = t;
    }

    //[3,2,1,5,6,4]
    //2
    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        FindKthNum findKthNum = new FindKthNum();
        int result = findKthNum.findKthNum2(nums,2);

        System.out.println("nums = " + result);
    }



    public int findKthNum2(int[] nums, int k) {
        int heapSize = nums.length;
       buildMaxHeap(nums,heapSize);
        for (int i = 1; i < k; i++) {
            swap(nums,0,heapSize-1);
            heapSize--;
            maxHeap(nums, 0, heapSize);
        }
        return nums[0];
    }
    public void buildMaxHeap(int[] nums, int heapSize) {
        for (int i = (heapSize -2) / 2; i >=0; --i) {
            maxHeap(nums, i, heapSize);
        }
    }
    private void maxHeap(int[] nums, int root, int heapSize) {
        int l = root*2+1,r=root*2+2,largest = root;
        if (l < heapSize && nums[l] > nums[largest]) {
            largest = l;
        }
        if (r < heapSize && nums[r] > nums[largest]) {
            largest = r;
        }
        if (largest != root) {
            swap(nums, root, largest);
            maxHeap(nums, largest, heapSize);
        }
    }
}
