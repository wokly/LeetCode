import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayProblem arrayProblem = new ArrayProblem();
        //int result = arrayProblem.trap2(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
        List<String> result = arrayProblem.generateParenthesis(3);
        for (String s : result) {
            System.out.println("s = " + s);
        }
        //System.out.println("result = " + result);
    }
}
