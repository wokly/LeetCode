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
}
