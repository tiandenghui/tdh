package com.kuliao.horcrux.app.model;


public enum FileExt {
    MP3("media"),MP4("media"),MKV("media"),RMVB("media"),WMA("media"),AAC("media"),AVI("media"),TS("media"),FLV("media"),
    WMV("media"),DAT("media"),MOV("media"),VOB("media"),
    JPG("image"),BMP("image"),PNG("image"),GIF("image"),ICO("image"),AI("image"),PSD("image");

    private String fileType;

    FileExt(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {

        return fileType;
    }
}
