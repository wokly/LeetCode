package Algorithm;

public class StringProblem {
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int maxLen = 1;
        int maxIndex = 0;
        int start = 0;
        int end = 0;
        for(int i=0;i + maxLen <chars.length;i++){
            for(int j=0;i-j>=0 && i+j<chars.length;j++){
                if(chars[i-j] != chars[i+j] ){
                    break;
                }else if(j*2+1>maxLen){
                    maxLen = j*2+1;
                    start = i-j;
                    end = i+j;
                }
            }
            for(int j=1;i-j-1>=0 && i+j<chars.length;j++){
                if(chars[i-j-1] != chars[i+j] ){
                    break;
                }else if(j*2>maxLen){
                    maxLen = j*2;
                    start = i-j-1;
                    end = i+j;
                }
            }
        }
        return s.substring(start,end+1);
    }

    public static void main(String[] args) {
        String s = "cbbd";
        StringProblem stringProblem = new StringProblem();
        String tt = stringProblem.longestPalindrome(s);
        System.out.println(tt);

    }
}
