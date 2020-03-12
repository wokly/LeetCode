package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArrayProblem {
    /**
     *https://leetcode-cn.com/problems/container-with-most-water/
     * 11. 盛最多水的容器
     */
    public int maxArea(int[] height) {
        int l = 0;
        int r = height.length-1;
        int max = 0;
        int area = 0;
        while (l <= r) {
            area = Math.min(height[r], height[l]) * (r - l);
            max = Math.max(max, area);
            if(height[l]>height[r])
                r--;
            else l++;
        }
        return max;
    }

    /**
     * https://leetcode-cn.com/problems/trapping-rain-water/
     * 42. 接雨水
     */
    public int trap(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int water = 0;
        int last = 0;
        int small = 0;
        while (l < r) {
            small = Math.min(height[r], height[l]);
            if(small >last ) {
                water += (small - last) * (r - l - 1) - last;
                last = small;
            }else {
                water -= small;
            }
            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return water;
    }

    public int trap2(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int water = 0;
        int left_max = 0;
        int right_max = 0;
        while (l < r) {
            if (height[l] > height[r]) {
                if (height[r] > right_max) right_max = height[r];
                else water += right_max - height[r];
                r--;
            } else {
                if (height[l] > right_max) left_max = height[l];
                else water += left_max - height[l];
                l++;
            }
        }
        return water;
    }

    /**
     * https://leetcode-cn.com/problems/generate-parentheses/
     * 22. 括号生成
     */
    public List<String> generateParenthesis(int n) {
        return generateParenthesis(n, n);
    }

    private List<String> generateParenthesis(int l, int r) {
        List<String> result = new ArrayList<>();
        if (l == 0) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < r; i++) {
                s.append(")");
            }
            result.add(s.toString());
            return result;
        } else {
            List<String> result1 = generateParenthesis(l - 1, r);
            for (String s : result1) {
                result.add("(" + s);
            }
            if (l < r) {
                List<String> result2 = generateParenthesis(l, r - 1);
                for (String s : result2) {
                    result.add(")" + s);
                }
            }
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        int l = 0;
        int r = nums.length - 1;
        int pos = (l + r) / 2;
        if (nums[pos] > target) {
            result[0] = findFirst(nums, l, pos, target);
            result[1] = findLast(nums, l, pos, target);
        }else if (nums[pos] < target) {
            result[0] = findFirst(nums, pos, r, target);
            result[1] = findLast(nums, pos, r, target);
        }else {
            result[0] = findFirst(nums, l, pos, target);
            result[1] = findLast(nums, pos, r, target);
        }
        return result;
    }

    private int findLast(int[] nums, int l, int r, int target) {
        if(l == r){
            if (nums[r] == target) {
                return r;
            }else if (r > 0) {
                if (nums[r - 1] == target) {
                    return r - 1;
                }else {
                    return -1;
                }
            } else return -1;
        } else if (l + 1 == r) {
            return -1;
        }else {
            int pos = (l + r) / 2;
            if (nums[pos] > target) {
                return findLast(nums, l, pos, target);
            }else {
                return findLast(nums, pos, r, target);
            }
        }
    }

    private int findFirst(int[] nums, int l, int r, int target) {
        if (l == r) {
            if (nums[r] == target) {
                return r;
            } else if (nums[l + 1] == target) {
                return l + 1;
            } else {
                return -1;
            }

        } else if (l + 1 == r) {
            return -1;
        } else {
            int pos = (l + r) / 2;
            if (nums[pos] < target) {
                return findFirst(nums, l, pos, target);
            } else {
                return findFirst(nums, pos, r, target);
            }
        }
    }

    /**
     * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
     * 51. 数组中的逆序对
     */
    public int reversePairs(int[] nums) {
        int[] tmp = new int[nums.length];
        return reversePairs(nums, 0, nums.length - 1,tmp);

    }
    public int reversePairs(int[] nums, int left, int right, int[] tmp){
        int result = 0;
        if(left>=right){
            return result;
        }
        int half = (left + right) / 2;
        result += reversePairs(nums, left, half, tmp);
        result += reversePairs(nums, half + 1, right, tmp);
        if(nums[half]<nums[half+1])
            return result;
        return result + merge(nums, left, half,right, tmp);

}

    private int merge(int[] nums, int left, int half,int right, int[] tmp) {
        for (int i = left; i <=right; i++) {
            tmp[i] = nums[i];
        }
        int result = 0;
        int l = left;
        int r = half + 1;
        int i = left;
        while (l <= half && r <= right) {
            if (tmp[l] <= tmp[r]) {
                nums[i++] = tmp[l++];
            } else {
                nums[i++] = tmp[r++];
                result += half + 1 - l;

            }
        }
        while (l <= half) {
            nums[i++] = tmp[l++];

        }
        while (r <= right) {
            nums[i++] = tmp[r++];
        }

        return result;
    }

    public void sort(int[] nums){
        quickSort(nums, 0, nums.length-1);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int tmp = nums[start];
            int l = start;
            int r = end;
            while (l < r) {
                while (l < r && nums[r] >= tmp) {
                    r--;
                }
                nums[l] = nums[r];
                while ((l < r && nums[l] <= tmp)) {
                    l++;
                }
                nums[r] = nums[l];
            }
            nums[l] = tmp;
            quickSort(nums, start, l - 1);
            quickSort(nums, l + 1, end);
        }
    }

    private void quickSort1(int[] nums, int start, int end) {
        if (start >= end)
            return;
        int mid = (nums[start] + nums[end]) / 2;
        int l = start;
        int r = end;
        while (l <r) {
            while (l <r && nums[l] <= mid ) {
                l++;
            }
            while ((l <r &&nums[r] > mid) ) {
                r--;
            }
            if (nums[l] > nums[r]) {
                swap(nums, l, r);
                l++;
                r--;
            }
        }
        quickSort1(nums, start, l - 1);
        quickSort1(nums, l, end);
    }

    private void swap(int[] nums, int l, int r) {
        int t = nums[l];
        nums[l] = nums[r];
        nums[r] = t;
    }

    /**
     * https://leetcode-cn.com/problems/3sum/
     * 15. 三数之和
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums.length<3){
            return result;
        }
        Arrays.sort(nums);
        int i = 0,r, l,sum;
        while (i<nums.length-2){
            if(nums[i]>0)
                break;
            r = i + 1;
            l = nums.length - 1;
            while (r<l) {
                sum = nums[i] + nums[r] + nums[l];
                if (sum < 0) {
                   r++;
                   while (r<l && nums[r-1] == nums[r]) r++;
                }else if(sum>0){
                    l--;
                    while (r<l && nums[l+1] == nums[l]) l--;
                }else {
                    LinkedList<Integer> t = new LinkedList<>();
                    t.add(nums[i]);
                    t.add(nums[r]);
                    t.add(nums[l]);
                    result.add(t);
                    l--;
                    r++;
                    while (r<l && nums[r-1] == nums[r]) r++;
                    while (r<l && nums[l+1] == nums[l]) l--;
                }
            }
            i++;
            while (i<nums.length-1 && nums[i-1] == nums[i]){
                i++;
            }
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/longest-palindromic-substring/
     * 5. 最长回文子串
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1)
            return "";
        int l1 = 0;
        int l2 = 0;
        int len = 0;
        int start = 0;
        int end = 0;
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            l1 = expandAroundCenter(c, i, i);
            l2 = expandAroundCenter(c, i, i + 1);
            len = Math.max(l1, l2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + (len) / 2;
            }
        }
        return s.substring(start, end+1);
    }

    private int expandAroundCenter(char[] c, int r, int l) {
        while (r>=0 && l<c.length && c[r] == c[l])
        {
            r--;
            l++;
        }
        return l - r-1 ;
    }
}
