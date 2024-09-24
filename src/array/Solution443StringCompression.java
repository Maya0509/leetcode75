package array;

public class Solution443StringCompression {
    public int compress(char[] chars) {
        char c = chars[0];
        int readIdx = 0, writeIdx = 0;
        int charNum = chars.length, afterCompressionLen = 0;
        int repeatNum = 0;
        while (readIdx<charNum){
            if (chars[readIdx]==c){
                repeatNum++;
                if (readIdx==charNum-1 || (readIdx<charNum-1 && chars[readIdx+1]!=c)){
                    chars[writeIdx]=c;
                    afterCompressionLen++;
                    writeIdx++;
                    if (repeatNum != 1) {
                        String numstr = Integer.toString(repeatNum);
                        for (int i = 0; i < numstr.length(); i++) {
                            chars[writeIdx] = numstr.charAt(i);
                            afterCompressionLen++;
                            writeIdx++;
                        }
                    }
                    if(readIdx<charNum-1) c = chars[readIdx+1];
                    repeatNum = 0;
                }
                readIdx++;
            }
        }
        return afterCompressionLen;
    }
}
