package string_operation;

/*
greatest common divisor of strings
 */
public class Solution1701GcdOf2Strings {
    public String gcdOfStrings(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int minLen = Math.min(len1,len2);
        for (int i = minLen; i >= 0; i--) {
            if (len1%i==0 && len2%i==0){
                String substr = str1.substring(0,i);
                if (str1.equals(multiplySubstr(substr, len1 / i)) &&
                        str2.equals(multiplySubstr(substr, len2/i))) return substr;
            }
        }
        return "";
    }
    private String multiplySubstr(String substr,int num){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(substr);
        }
        return stringBuilder.toString();
    }

    /*
    compute gcd of two integers
     */
    private int gcd(int num1,int num2){
        if (num2==0){
            return num1;
        }else{
            return gcd(num2,num1%num2);
        }
    }
    private String gcdOfStrings2(String str1, String str2){
        if (!(str1 + str2).equals(str2 + str1)) return "";
        return str1.substring(0,gcd(str1.length(),str2.length()));
    }
}
