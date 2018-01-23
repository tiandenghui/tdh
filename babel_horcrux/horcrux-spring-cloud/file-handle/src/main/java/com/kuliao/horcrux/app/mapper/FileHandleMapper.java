package com.kuliao.horcrux.app.mapper;

import com.kuliao.horcrux.app.model.FileInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileHandleMapper {

    @Insert("insert into fileinfo_tab values(#{fileId},#{fileName},#{fileType},#{filePath},now())")
    void uploadFile(FileInfo fi);

    @Select("select * from fileinfo_tab where file_id=#{fileId}")
    @Results({
            @Result(column = "file_id", property = "fileId"),
            @Result(column = "file_name", property = "fileName"),
            @Result(column = "file_type", property = "fileType"),
            @Result(column = "file_path", property = "filePath"),
            @Result(column = "upload_time", property = "uploadTime")
    })
    FileInfo findFileById(String fileId);

}
