package com.kuliao.horcrux.app.util;

import com.kuliao.horcrux.app.model.FileExt;

public class FileUtil {

    public static String getFileType(String fileName){

        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
        System.out.println(ext);
        try {
            FileExt fileExt = FileExt.valueOf(ext.toUpperCase());
            return fileExt.getFileType();
        }catch (Exception e){
            return "document";
        }

    }


}
