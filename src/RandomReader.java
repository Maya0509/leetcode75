import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomReader {
    private static final int DOWNLOAD_THREAD_NUM = 1;
    private static final String FILE_TEMP_SUFFIX = "";

    public boolean merge(String filename) throws IOException {
        byte[] buffer = new byte[10*1024];
        int len = -1;
        try(RandomAccessFile oSavedFile = new RandomAccessFile(filename,"rw")){
            for (int i = 0; i < DOWNLOAD_THREAD_NUM; i++) {
                try(BufferedInputStream bis = new BufferedInputStream(
                        new FileInputStream(filename+FILE_TEMP_SUFFIX+i)
                )) {
                    while((len = bis.read(buffer)) != -1){
                        oSavedFile.write(buffer,0,len);
                    }
                }
            }
//            LogUtils.info("文件合并完毕 {}",filename);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
