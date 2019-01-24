package com.shenhua119.leadservice.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-12-09 0:10
 * 还款明细（计划）
 */
public class RepaymentDetail {
    // 主键ID
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    // 贷款ID
    @JsonSerialize(using= ToStringSerializer.class)
    private Long loanId;

    // 资金类型
    private String moneyType;

    //计划收入金额
    private Double revenueMoney;

    // 计划支出金额
    private Double paymentMoney;

    // 到账日期
    private Date date;

    // 状态
    private Integer status;

}
