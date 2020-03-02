import java.util.ArrayList;
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
        return null;
    }
}
