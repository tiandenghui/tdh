package com.kuliao.horcrux.app.controller;


import com.kuliao.horcrux.app.service.FileHandleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/filehandle")
public class FileHandleController {

    @Autowired
    private FileHandleService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileHandleService.class);

    /**
     * 上传文件
     *
     * 界面的测试：http://localhost:5050/index.html
     * 使用命令：curl -F "file=@文件全名" localhost:zuul_port/zuul/upload
     *
     * @param file 待上传的文件
     * @return 文件的唯一ID
     */
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam(value = "file", required = true) MultipartFile file ) {
        LOGGER.info("fileupload start....");
        return service.uploadService(file);
    }

    /**d
     * 批量上传文件，接收格式为文件集合
     *
     * @param files 批量上传的文件集合
     * @return  文件的ID集合
     */
    @RequestMapping(value = "/uploadfiles", method = RequestMethod.POST)
    public List<String> filesUpload(@RequestParam(value = "files", required = true) List<MultipartFile> files){
        LOGGER.info("batch file upload start....");
        return service.uploadFilesService(files);
    }



    /**
     * 下载文件
     *
     * @param fileId 文件的唯一ID
     *
     */
    @RequestMapping(value = "/downloadfile")
    public void downloadFile(HttpServletResponse response, String fileId) throws IOException {
        LOGGER.info("download file start...");

        service.downloadFile(response,fileId);


    }

    /**
     *
     * 下载缩略图
     * @param fileId 上传文件的唯一id
     * @param size 返回的缩略图大小，默认为320x240
     */
    @RequestMapping(value = "/downloadthumbnail",method = RequestMethod.POST)
    public void downloadThumbnail(HttpServletResponse response,String fileId,@RequestParam(defaultValue = "320x240") String size){
        LOGGER.info("download thumbnail start....");

        service.downloadThumbnail(response,fileId,size);

    }



}
