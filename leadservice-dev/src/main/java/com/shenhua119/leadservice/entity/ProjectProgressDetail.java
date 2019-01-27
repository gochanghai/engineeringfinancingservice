package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-11-30 0:07
 */

@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_project_progress_detail")
public class ProjectProgressDetail implements Serializable {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    // 项目ID
    @JsonSerialize(using= ToStringSerializer.class)
    private Long projectId;

    // 施工时间
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;

    // 施工进度
    private Double constructionProgress;

    // 进度说明
    private String progressDesc;

    // 产值
    private Double outputValue;

    // 现场文件
    private String progressFile;
}
