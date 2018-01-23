package com.kuliao.horcrux.app.app.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将horcrux-spring-cloud下的所有子微服务生成的war包
 * copy到deploy-deploy-support/target/classes/tarsdir中，并重新命名
 */
public class DeployUtil {

    private static final String PROJECT_BASE_PATH = System.getProperty("user.dir");
    private static final String WAR_INDEX = ".war";
    private static final String JAR_INDEX = ".jar";
    private static final String TARGET_DIR = "target";
    private static final File DEST_DIR = new File(PROJECT_BASE_PATH, "deploy-support/target");
    private static final File DEST_JAR_DIR = new File(DEST_DIR, "dir-jar");
    private static final File DEST_WAR_DIR = new File(DEST_DIR, "dir-war");
    private static final Logger LOGGER = LoggerFactory.getLogger(DeployUtil.class);

    public static void copyWarToOneDir(String fromDir) {
        LOGGER.info("\n---------------------------------copyWarToOneDir begin---------------------------------");
        String warOrJarBaseDir = StringUtils.isEmpty(fromDir) ? PROJECT_BASE_PATH : fromDir;
        LOGGER.info("copy jar or war files in {} to path {}", warOrJarBaseDir, DEST_DIR.getAbsolutePath());

        List<File> needCopyWarPathLst = new ArrayList<>();
        fillNeededFileLst(needCopyWarPathLst, new File(warOrJarBaseDir));
        copyAndRenameWarFiles(needCopyWarPathLst);
        LOGGER.info("\n---------------------------------copyWarToOneDir end----------------------");
    }

    /**
     * 将needCopyFileLst中的所有war包都拷贝到DEST_WARS_DIR目录，并将war包名中的版本号删除
     * 如 deploy-support-0.0.1-SNAPSHOT.war 更名为 deploy-support.war
     *
     * @param needCopyFileLst：需要拷贝的war包列表
     */
    public static void copyAndRenameWarFiles(List<File> needCopyFileLst) {
        deleteDirectory(DEST_JAR_DIR);
        deleteDirectory(DEST_WAR_DIR);

        boolean copyResult = executeCopyAndRename(needCopyFileLst);
        if(copyResult){
            afterCopy();
        };
    }

    private static void afterCopy() {
        LOGGER.info("----------jar or war files dir : {}----------", DEST_DIR.getAbsolutePath());
        try {
            java.awt.Desktop.getDesktop().open(DEST_DIR);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static boolean executeCopyAndRename(List<File> needCopyFileLst) {
        if(CollectionUtils.isEmpty(needCopyFileLst)){
            LOGGER.info("----------------v_v------------v_v---------v_v----------------\n{}",
                    "please check your horcrux-spring-cloud project root dir whether correct");
            return false;
        }

        needCopyFileLst.stream().forEach(copyFile -> {
            try {
                String rmVersName = copyFile.getName().replaceAll("-(\\d.){2}\\d-SNAPSHOT", "");
                File destFile = new File(rmVersName.endsWith(JAR_INDEX) ? DEST_JAR_DIR : DEST_WAR_DIR, rmVersName);
                LOGGER.info("============copy file : {}", destFile.getAbsolutePath());
                FileCopyUtils.copy(copyFile, destFile);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        });
        return true;
    }

    /**
     * 将filePath路径下所有的war包文件路径填充到needCopyFileLst中
     *
     * @param needCopyFileLst
     * @param filePath
     */
    public static void fillNeededFileLst(List<File> needCopyFileLst, File filePath) {
        if (!filePath.exists()) {
            LOGGER.info("{}file not exist!", filePath.getAbsolutePath());
            return;
        }

        if (filePath.listFiles().length == 0) {
            LOGGER.info("{}dir is empty!", filePath.getAbsolutePath());
            return;
        }

        //pro子微服务项目根路径,inpro子微服务项目里的文件
        Arrays.stream(filePath.listFiles()).filter(dir -> dir.isDirectory()).forEach(pro -> {
            //过滤出target目录再遍历
            Arrays.stream(pro.listFiles()).filter(inpro -> inpro.getName().endsWith(TARGET_DIR)).forEach(targetfile -> {
                List<File> fileLst = Arrays.stream(targetfile.listFiles()).filter(intar ->
                        isWarOrJarFileAndNotContain(intar, needCopyFileLst)).collect(Collectors.toList());
                needCopyFileLst.addAll(fileLst);
            });
        });
    }

    /**
     * @param file
     * @param needCopyFileLst
     * @return needCopyFileLst中不包含的war文件返回true
     */
    public static boolean isWarOrJarFileAndNotContain(File file, List<File> needCopyFileLst) {
        return file.isFile() && (file.getName().endsWith(WAR_INDEX) || file.getName().endsWith(JAR_INDEX)) && !needCopyFileLst.contains(file);
    }

    /**
     * 删除文件夹path下的所有文件
     */
    public static void deleteDirectory(File path) {
        if (!path.exists()) {
            path.mkdirs();
            return;
        }
        Arrays.stream(path.listFiles()).forEach(childFilePath -> childFilePath.deleteOnExit());
    }
}
