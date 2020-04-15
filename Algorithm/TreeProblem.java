package Algorithm;

import java.util.LinkedList;
import java.util.List;

public class TreeProblem {
    public boolean isValidBST(TreeNode root) {
        List<Integer> nums = new LinkedList<>();
        leftFirst(root, nums);
        for (int i = 0; i < nums.size()-1; i++) {
            if (nums.get(i) >= nums.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private void leftFirst(TreeNode root, List<Integer> nums) {
        if(root != null){
            leftFirst(root.left,nums);
            nums.add(root.val);
            leftFirst(root.right,nums);
        }
    }
}
