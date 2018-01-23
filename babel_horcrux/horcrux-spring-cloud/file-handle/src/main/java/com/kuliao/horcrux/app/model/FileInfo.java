package com.kuliao.horcrux.app.model;

import java.sql.Timestamp;

public class FileInfo {
    private String fileId;
    private String fileName;
    private String fileType;
    private String filePath;
    private Timestamp uploadTime;

    public FileInfo() {
    }

    public String getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public FileInfo(String fileId, String fileName, String fileType, String filePath) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", filePath='" + filePath + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                '}';
    }
}
