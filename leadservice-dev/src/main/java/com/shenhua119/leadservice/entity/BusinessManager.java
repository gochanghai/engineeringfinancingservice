package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 商务经理实体
 * @author liuchanghai
 * @create 2018-11-28 22:02
 */

@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("businessmanager")
public class BusinessManager implements Serializable {
    /**
     * 主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 工程公司ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long companyId;

    // 公司名称
    @TableField(exist=false)
    private String companyName;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 出生年月
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthdate;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 从业日期
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date jobDate;

    /**
     * 从业年限
     */
    private Double jobYearNumber;

    /**
     * 与公司合作日期
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date coopDate;

    /**
     * 与公司合作年限
     */
    private Double coopYearNumber;

    /**
     * 分工司ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long branchId;
    // 分公司名称
    @TableField(exist=false)
    private String childCompanyName;

    /**
     * 评级
     */
    private String rate;

    /**
     * 评级说明
     */
    private String rateDesc;

    /**
     * 状态
     */
    private Integer status;
}
