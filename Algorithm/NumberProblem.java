package Algorithm;

public class NumberProblem {
    /**
     * https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
     * 440. 字典序的第K小数字
     * 十叉树 算每个Node下有几个节点
     */
    public int findKthNumber(int n, int k) {
        //十叉树
        int prefix = 1;
        int count = 1;
        int countP;
        while (count < k) {
            countP = getCount(prefix, n);
            if (count + countP <= k) {
                //不在这个数字下  查下一个节点数字（+1）
                count += countP;
                prefix++;
            } else {
                //在这个数字下 查下一层（*10）
                count += 1;
                prefix = prefix * 10;
            }
        }
        return prefix;
    }

    private int getCount(int s, int n) {
        //数字可能会超出int范围
        long start = s;
        long end = start+1;
        int count = 0;
        while (start<=n){
            count +=Math.min(end,n+1) -start;
            //满十叉树 第一层1 第二层 10 第三层100...
            start = start * 10;
            end = end * 10;
        }
        return count;
    }
}
