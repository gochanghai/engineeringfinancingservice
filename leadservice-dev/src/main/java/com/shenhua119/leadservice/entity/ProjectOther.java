package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-11-30 0:05
 */

@Data
@Getter
@Setter
@TableName("project_other")
public class ProjectOther {

    /**
     * 主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 项目ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long projectId;

    /**
     * 是否购买保险
     */
    private Integer isInsur;
    /**
     * 保险到日期
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date insurEndDate;
    /**
     * 保险金额
     */
    private Double insurAmount;
    /**
     * 保险人数
     */
    private Integer insurPersonNumber;
    /**
     * 保险文件
     */
    private String insurFile;
    /**
     * 特殊说明
     */
    private String unusualDesc;

}
