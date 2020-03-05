package com.fly.fankun.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Title:
 * @author: fan.kun
 * @date: 2020/3/5
 */
public class FileUtils {
    /**
     * 文件上传
     * @param file
     * @param filePath
     * @param fileName
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try(FileOutputStream out = new FileOutputStream(filePath+ File.separator+fileName)){
            out.write(file);
            out.flush();
        }
    }
}
