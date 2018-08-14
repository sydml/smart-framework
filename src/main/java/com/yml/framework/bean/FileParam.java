package com.yml.framework.bean;

import java.io.InputStream;

/**
 * 封装上传文件参数
 *
 * Created by Yuming-Liu
 * 日期： 2018-08-14
 * 时间： 19:46
 */
public class FileParam {

    /**
     * 表单字段名
     */
    private String filedName;

    /**
     * 上产文件的文件名
     */
    private String FileName;

    /**
     * 上传文件的大小
     */
    private long fileSize;

    /**
     * 上传文件的Content-Type,可以判断文件的类型
     */
    private String contentType;

    /**
     * 上传文件的字节输入流
     */
    private InputStream inputStream;

    public FileParam(String filedName, String fileName, long fileSize, String contentType, InputStream inputStream) {
        this.filedName = filedName;
        FileName = fileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.inputStream = inputStream;
    }

    public String getFiledName() {
        return filedName;
    }

    public String getFileName() {
        return FileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
