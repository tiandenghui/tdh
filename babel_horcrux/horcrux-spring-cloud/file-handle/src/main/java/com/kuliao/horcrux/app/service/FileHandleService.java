package com.kuliao.horcrux.app.service;

import com.kuliao.horcrux.app.dao.FileHandleDao;
import com.kuliao.horcrux.app.model.FileInfo;
import com.kuliao.horcrux.app.util.FileUtil;
import com.kuliao.horcrux.app.util.WaterMarkerImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileHandleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileHandleService.class);
    @Autowired
    private FileHandleDao dao;

    //批量文件上传服务
    public List<String> uploadFilesService(List<MultipartFile> files) {
        List<String> fileIds = new ArrayList<String>();
        if (files != null && files.size() > 0) {
            for (MultipartFile file : files) {
                if (file != null && file.getSize() > 0) {
                    fileIds.add(uploadService(file));
                }
            }
        } else {
            LOGGER.info("There are empty files in the files ready to be uploaded");
            return null;
        }
        return fileIds;


    }

    public String uploadService(MultipartFile file) {
        try {

            FileInfo fi = saveFile(file);
            dao.uploadFile(fi);

            return fi.getFileId();
        } catch (IOException e) {
            LOGGER.error("file upload fail,the reason is {}", e.getMessage());
            return null;
        }
    }

    public void downloadFile(HttpServletResponse response, String fileId) throws IOException {

        FileInfo fi = dao.findFileById(fileId);
        String fileName = fi.getFileName();

        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        String fileFullPath = fi.getFilePath() + "\\" + fi.getFileId() + "." + ext;
        File file = new File(fileFullPath);
        if (file.exists()) {
            //重置response
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");

            //设置http头信息的内容
            //解决中文文件名显示问题
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fi.getFileName().getBytes("UTF-8"), "ISO8859-1"));
            //设置文件长度

            int fileLength = (int) file.length();
            response.setContentLength(fileLength);
            InputStream inStream = null;
            ServletOutputStream servletOS = null;
            if (fileLength != 0) {
                try {
                    inStream = new FileInputStream(file);
                    byte[] buf = new byte[4096];

                    //创建输出流
                    servletOS = response.getOutputStream();
                    int readLength;

                    //读取文件内容并写入到response的输出流当中
                    while ((readLength = inStream.read(buf)) != -1) {
                        servletOS.write(buf, 0, readLength);
                    }
                } catch (IOException e) {
                    LOGGER.error("downloadfile fail,the reason is {}", e.getMessage());
                } finally {
                    //关闭输入流
                    inStream.close();

                    //刷新输出流缓冲
                    servletOS.flush();

                    //关闭输出流
                    servletOS.close();
                }
            }
        } else {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("文件不存在");
        }
    }

    private FileInfo saveFile(MultipartFile file) throws IOException {
        //文件是否为空文件
        byte[] bytes = file.getBytes();
        if (bytes.length == 0) {
            LOGGER.info("file is empty");
            return null;
        }
        String path = "file-handle/src/main/resources/";
        //通过文件名，获取类型。并将文件名改为Id.扩展名保存
        String fileId = getUUID();
        String fileName = file.getOriginalFilename();
        String fileType = FileUtil.getFileType(fileName);
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        String toSaveFileName = fileId + "." + ext;
        //判断根据文件类型判断保存路径
        File filesDir = null;
        if ("media".equals(fileType)) {
            filesDir = new File(path + "media");
        } else if ("image".equals(fileType)) {
            filesDir = new File(path + "image");
        } else if ("document".equals(fileType)) {
            filesDir = new File(path + "document");
        }


        if (!filesDir.exists()) {
            filesDir.mkdirs();
        }

        FileInfo fi = new FileInfo(fileId, fileName, fileType, filesDir.getAbsolutePath());
        //保存文件，上传完毕
        File fileToSave = new File(filesDir, toSaveFileName);
        FileCopyUtils.copy(bytes, fileToSave);
        return fi;
    }

    private String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void downloadThumbnail(HttpServletResponse response, String fileId, String size) {

        FileInfo fi = dao.findFileById(fileId);
        String fileName = fi.getFileName();

        try {
            //将文件名和Id加载图片上
            BufferedImage bufferedImage = WaterMarkerImageUtil.waterMarkImage(fileName, fileId, size);

            //设置response类型为图片
            response.setContentType("image/jpeg");
            OutputStream os = response.getOutputStream();

            //将图片流写出
            ImageIO.write(bufferedImage, "jpg", os);

        } catch (IOException e) {
            LOGGER.error("downloadthumb fail,the reason is {}", e.getMessage());
        }

    }


}
