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
 * @create 2019-01-06 17:14
 * <p>维保与检测项目</p>
 */

@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("fire_maintenance_project")
public class FireMaintenanceProjectEntity {

    /**
     * 主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 建筑类型
     */
    private String projectType;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 建筑面积
     */
    private Double floorArea;
    /**
     * 项目地点
     */
    private String projectLocation;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;


}
