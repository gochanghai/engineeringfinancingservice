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

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2019-01-06 16:54
 * <p>消防工程</p>
 */

@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_fire_project")
public class FireProjectEntity {

    /**
     * 主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 工程类型
     */
    private String projectType;
    /**
     * 工程名称
     */
    private String projectName;
    /**
     * 工程造价
     */
    private Double projectCost;
    /**
     *工程面积
     */
    private Double floorArea;
    /**
     * 工程地点
     */
    private String projectLocation;
    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;

}
