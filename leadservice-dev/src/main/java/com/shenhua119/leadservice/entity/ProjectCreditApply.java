package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-12-16 15:06
 */

@Data
@Accessors(chain = true)
@TableName("project_credit_apply")
public class ProjectCreditApply implements Serializable {

    /** 主键ID */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /** 申请人ID */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /** 项目ID */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long projectId;

    /** 申请编号 */
    private String applyNo;

    /** 申请日期 */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date applyDate;

    /** 申请人姓名 */
    private String name;

    /** 项目合同编号 */
    private String projectNo;

    /** 申请金额 */
    private Double applyAmount;

    /** 申请事由 */
    private String reason;

    /** 银行流水文件 */
    private String bankWater;

    /** 审批进度 */
    private Integer step;

    /** 状态 */
    private Integer status;

    /** 授信类型 1:正常批复 2: 特批 */
    private Integer creditType;

    /** 授信金额 */
    private Double creditAmount;

    /** 工程公司ID */
    private Long companyId;

    /** 资金方ID */
    private Long fcompanyId;

    /** 更新时间 */
    private Date updateTime;

    /** 修改时间 */
    private Date createTime;

}
