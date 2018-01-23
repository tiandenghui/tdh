package com.kuliao.horcrux.app.dao;

import com.kuliao.horcrux.app.mapper.FileHandleMapper;
import com.kuliao.horcrux.app.model.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileHandleDao {

    @Autowired
    private FileHandleMapper mapper;

    public void uploadFile(FileInfo fi){
        mapper.uploadFile(fi);
    }

    public FileInfo findFileById(String fileId){
        return mapper.findFileById(fileId);
    }
}
