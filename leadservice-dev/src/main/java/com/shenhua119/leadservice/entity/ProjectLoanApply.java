package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-01 2:25
 */

@Data
@Getter
@Setter
@TableName("project_loan_apply")
public class ProjectLoanApply {

    /** 主键ID */
    @JsonSerialize(using= ToStringSerializer.class)
    private  Long id;
    /** 项目ID */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long projectId;
    /** 项目名称 */
    @TableField(exist=false)
    private String projectName;
    /** 贷款编号 */
    private String applyNo;

    /** 申请日期 */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date applyDate;
    /** 申请人姓名 */
    private String name;
    /** 申请放款金额 */
    private Double applyAmount;
    /** 申请放款周期 */
    private Integer loanCycle;
    /** 状态 */
    private String status;
    /** 融资人ID */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;
    /** 工程公司ID */
    private Long companyId;
    /** 资金方ID */
    private Long fcompanyId;

    /** 更新时间 */
    private Date updateTime;
    /** 修改时间 */
    private Date createTime;

    /** 项目采购订单 */
    @TableField(exist=false)
    private List<ProjectPurchaseOrder> purchaseOrders;
}
