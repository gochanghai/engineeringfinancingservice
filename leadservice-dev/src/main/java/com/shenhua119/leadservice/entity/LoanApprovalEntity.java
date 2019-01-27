package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-12-09 1:43
 * 贷款审批信息
 */
@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_loan_approval")
public class LoanApprovalEntity {
    /**
     * 贷款ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 申请ID
     */
    private Long applyId;

    // 工程公司审批结果
    private Integer ecResult;
    // 采购金额
    private Double ecPurchaseAmount;
    // 工程公司拒绝原因说明
    private String ecDesc;
    // 审批人
    private String ecPerson;
    // 审批时间
    private Date ecDate;

    // 平台审批结果
    private Integer pResult;
    // 工程公司有效采购金额
    private Double pPurchaseAmount;
    // 现场文件
    private String onSiteFile;
    // 资信评估表
    private Integer creditRatingFile;
    // 拒绝原因
    private String pDesc;
    // 审批人
    private String pPerson;
    // 审批时间
    private Date pDate;


    // 资金方批复结果
    private Integer fResult;
    // 资金方拒绝原因
    private String fDesc;
    // 批复人
    private String fPerson;
    // 批复时间
    private Date fDate;

}
