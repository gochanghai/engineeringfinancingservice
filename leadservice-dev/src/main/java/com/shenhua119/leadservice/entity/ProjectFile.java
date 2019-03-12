package com.shenhua119.leadservice.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

public class ProjectFile {
    /**
     *主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 文件分类
     */
    private String classify;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件类型
     */
    private String type;
    /**
     * 上传者
     */
    private String author;
    /**
     * 文件url
     */
    private String fileurl;
    /**
     * 文件状态
     */
    private Integer status;
    /**
     * 上传时间
     */
    private Date uploadDate;
    /**
     * 文件备注
     */
    private String remarks;
}
