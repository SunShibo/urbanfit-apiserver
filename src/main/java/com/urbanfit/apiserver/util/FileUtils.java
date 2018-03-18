package com.urbanfit.apiserver.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wangyubo on 2018/3/18.
 */
public class FileUtils {

    public static synchronized File getFileFromBytes(byte[] b, String outputFile) {
        BufferedOutputStream stream = null;
        File file = null;

        try {
            file = new File(outputFile);
            FileOutputStream e1 = new FileOutputStream(file);
            stream = new BufferedOutputStream(e1);
            stream.write(b);
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            if(stream != null) {
                try {
                    stream.close();
                } catch (IOException var12) {
                    var12.printStackTrace();
                }
            }
        }
        return file;
    }
}
