package Algorithm;

public class Main {
    public static void main(String[] args) {
        ArrayProblem arrayProblem = new ArrayProblem();
       /* List<String> result = arrayProblem.generateParenthesis(3);
        for (String s : result) {
            System.out.println("s = " + s);
        }*/
        //int result = arrayProblem.trap2(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
        /*int result = arrayProblem.reversePairs(new int[]{});
        System.out.println("result = " + result);*/

        arrayProblem.sort(new int[]{2,54,74,123,546754,123,765,234,125,63,1,78,23,8,23});
        //arrayProblem.sort(new int[]{6,5,4,3,2,1});
    }
}
