package com.shenhua119.leadservice.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-12-09 0:48
 * 资金方审批信息
 */
public class FundCompanyExamineApprove {
    // 主键ID
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    // 贷款ID
    @JsonSerialize(using= ToStringSerializer.class)
    private Long loanId;
    // 评估结果
    private String isCredit;
    // 授信金额
    private Double creditMoney;
    // 授信开始时间
    private Date creditStartDate;
    // 授信结束时间
    private Date creditEndDate;
    // 还款款方式
    private String repayment;
    // 批复文件
    private String file;
    // 拒绝原因
    private String rejectDesc;
    // 评估人
    private String person;
    // 评估时间
    private Date date;

}
