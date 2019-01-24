package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-12-18 1:07
 * <p>文件系统</p>
 */

@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_file_system")
public class FileSystemEntity {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    // 文件ID
    private String fileId;

    // 文件存储路径
    private String filePath;

    // 文件大小
    private Long fileSize;

    // 文件名称
    private String fileName;

    // 文件类型
    private String fileType;

    // 上传时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;

}
