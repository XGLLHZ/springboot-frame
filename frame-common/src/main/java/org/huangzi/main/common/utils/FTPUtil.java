package org.huangzi.main.common.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: XGLLHZ
 * @date: 2019/12/25 上午11:09
 * @description: ftp 工具类
 */
public class FTPUtil {

    private static final String HOST = "192.168.10.8";

    private static final Integer PORT = 8601;

    private static final String USER_NAME = "bsj";

    private static final String PASS_WORD = "bsj123456";

    private static final String REMOTE_PATH = "/";

    private static final String LOCAL_PATH = "/Users/xinou/Desktop/test";

    public static void getFileNames() {
        FTPClient ftpClient = new FTPClient();
        Integer reply;

        try {
            ftpClient.connect(HOST, PORT);
            ftpClient.login(USER_NAME, PASS_WORD);
            reply = ftpClient.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                System.out.println("FTP 连接失败");
            }

            System.out.println("FTP 连接成功");

            ftpClient.setControlEncoding("UTF-8");
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(REMOTE_PATH);
            String[] fileNames = ftpClient.listNames();
            System.out.println("获取到文件目录 开始下载");
            for (String fileName : fileNames) {
                File file = new File(LOCAL_PATH + "/" + fileName);
                OutputStream outputStream = new FileOutputStream(file);
                ftpClient.retrieveFile(REMOTE_PATH + fileName, outputStream);
            }
            System.out.println("下载结束");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FTPUtil.getFileNames();
    }

}


