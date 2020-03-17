package Algorithm;

import java.util.*;

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

    /**https://leetcode-cn.com/problems/coin-change/
     322. 零钱兑换
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if(coin<=i){
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    class Interval {
        int a;
        int b;
        Interval(int[] t){
            a = t[0];
            b = t[1];
        }

        int[] toArray(){
            int[] t = new int[2];
            t[0] = a;
            t[1] = b;
            return t;
        }

    }
    public int[][] merge(int[][] intervals) {
        if(intervals.length<1){
            return intervals;
        }
        Arrays.sort(intervals, (Comparator.comparingInt(o -> o[0])));
        LinkedList<int[]> tmp = new LinkedList<>();
        tmp.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] t = tmp.getLast();
            if (intervals[i ][0] <= t[1]) {
                t[1] = Math.max(t[1], intervals[i][1]);
            }else tmp.add(intervals[i]);
        }
        int[][] result = new int[tmp.size()][];
        for (int i = 0; i < tmp.size(); i++) {
            result[i] = tmp.get(i);
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
     * 215. 数组中的第K个最大元素
     */
    public int findKthLargest(int[] nums, int k) {
        return nums[quickSortPos(nums, 0, nums.length - 1, k-1)];
    }
    int quickSortPos(int[] nums,int start,int end,int k){
        int left = start;
        int right = end;
        int tmp = nums[left];
        while (left<right){
            while (left<right && nums[right]<=tmp){
                right--;
            }
            nums[left] = nums[right];
            while (left<right && nums[left]>=tmp){
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = tmp;
        if(left == k) return left;
        else if(left>k) return quickSortPos(nums, start, left - 1, k);
        else return quickSortPos(nums, left + 1, end, k);
    }

    /**
     * https://leetcode-cn.com/problems/candy/
     * 135. 分发糖果
     */
    public int candy(int[] ratings) {
        int[] tmp = new int[ratings.length + 2];
        tmp[0] = Integer.MAX_VALUE;
        tmp[tmp.length - 1] = Integer.MAX_VALUE;
        System.arraycopy(ratings, 0, tmp, 1, tmp.length - 1 - 1);
        Arrays.fill(ratings,0);
        for (int i = 1; i < tmp.length-1; i++) {
            if (tmp[i] <= tmp[i - 1] && tmp[i] <= tmp[i + 1]) {
                ratings[i-1] = 1;
            }
        }
        tmp[0] = Integer.MIN_VALUE;
        tmp[tmp.length - 1] = Integer.MIN_VALUE;
        for (int i = 1; i < tmp.length-1; i++) {
            if (ratings[i-1]!=1 && tmp[i] >= tmp[i - 1] && tmp[i] >= tmp[i + 1] ) {
                ratings[i-1] = -1;
            }
        }
        boolean start = false;
        for (int i = 0; i < ratings.length; i++) {
            if(ratings[i] == 1) {
                start = true;
            } else if(start){
                if(ratings[i] == -1){
                    start = false;
                }
                ratings[i] = ratings[i - 1] + 1;
            }
        }
        start = false;
        for (int i = ratings.length - 1; i >= 0; i--) {
            if(ratings[i] == 1) {
                start = true;
            } else if(start){
                if(ratings[i] == -1 || ratings[i]>1){
                    start = false;
                }
                ratings[i] = Math.max(ratings[i], ratings[i + 1] + 1);
            }
        }
        int sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }
        return sum;
    }

// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

    /**
     * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
     * 138. 复制带随机指针的链表
     */
    public Node copyRandomList(Node head) {
        Node t = head;
        if(head == null)
            return head;
        while (t!=null){
            Node copy = new Node(t.val);
            copy.next = t.next;
            t.next = copy;
            t = copy.next;
        }
        t = head;
        Node result = head.next;
        while (t!=null){
            Node t1 = t.next;
            if(t.random!=null) {
                t1.random = t.random.next;
            }
            t = t1.next;
        }
        t = head;
        while (t!=null){
            Node t1 = t.next;
            t.next = t1.next;
            t = t1.next;
            if(t!=null)
                t1.next = t.next;
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/spiral-matrix/
     * 54. 螺旋矩阵
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new LinkedList<>();
        int step = 0;
        int m = matrix.length;
        if (m == 0) {
            return result;
        }
        int n = matrix[0].length;
        while (m - 2 * step > 0 && n - 2 * step > 0) {
            for (int i = step; i < n - step; i++) {
                result.add(matrix[step][i]);
            }
            for (int i = step + 1; i < m - step; i++) {
                result.add(matrix[i][n - step - 1]);
            }
            if (m > 2 * step + 1) {
                for (int i = n - step - 2; i >= step; i--) {
                    result.add(matrix[m - step - 1][i]);
                }
            }
            if (n > 2 * step + 1) {
                for (int i = m - step - 2; i > step; i--) {
                    result.add(matrix[i][step]);
                }
            }
            step++;
        }
        return result;
    }
}
