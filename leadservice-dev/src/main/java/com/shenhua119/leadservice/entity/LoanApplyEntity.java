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
@TableName("tb_loan_apply")
public class LoanApplyEntity {

    /**
     * 主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private  Long id;
    /**
     * 项目ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long pId;
    /**
     * 项目名称
     */
    @TableField(exist=false)
    private String projectName;
    /**
     * 贷款编号
     */
    private String loanNo;

    // 申请贷款时间
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date loanDate;
    /**
     * 融资人姓名
     */
    @TableField(exist=false)
    private String name;
    /**
     * 申请放款金额
     */
    private Double loanAmount;
    /**
     * 申请放款周期
     */
    private Integer loanCycle;
    /**
     * 状态
     */
    private String status;
    /**
     * 融资人ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long fId;
    /**
     * 工程公司ID
     */
    private Long comId;
    /**
     * 资金方ID
     */
    private Long fComId;

    /**
     * 项目采购订单
     */
    @TableField(exist=false)
    private List<ProjectPurchaseOrderEntity> purchaseOrders;
}
