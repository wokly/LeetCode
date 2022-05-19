package Algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if(root == null){
                return result;
            }
            List<List<TreeNode>> temp = new ArrayList<>();
            List<TreeNode> i = new ArrayList<>();
            i.add(root);
            while(i.size()>0){
                temp.add(i);
                List<TreeNode> j = new ArrayList<>();
                for(TreeNode node  : i){
                    if(node.left != null){
                        j.add(node.left);
                    }
                    if(node.right != null){
                        j.add(node.right);
                    }
                }
                i = j;
            }
            for(int level = 0;level< temp.size();level++){
                List<TreeNode> nodeList = temp.get(level);
                List<Integer> numberList =  new ArrayList<>();
                if(level%2 ==0){
                    for (TreeNode treeNode : nodeList) {
                        numberList.add(treeNode.val);
                    }
                }else{
                    for(int k=nodeList.size()-1;k>=0;k--){
                        numberList.add(nodeList.get(k).val);
                    }
                }
                result.add(numberList);
            }
            return result;

        }

    public static void main(String[] args) {
        TreeProblem treeProblem = new TreeProblem();
        String[] t = {"3","9","20","null","null","15","7"};
        TreeNode root = treeProblem.buildTree(t);
        List<List<Integer>> r = treeProblem.zigzagLevelOrder(root);
        System.out.println("root = " + 123123);
    }

    private TreeNode buildTree(String[] parts) {
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }
}
