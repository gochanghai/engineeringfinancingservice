package com.shenhua119.leadservice.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-12-08 23:54
 */
public class LoanEntity {

    // id 主键
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    // 贷款ID
    @JsonSerialize(using= ToStringSerializer.class)
    private Long loanId;

    // 放款日期
    private Date loanDate;

    // 放款金额
    private Double loanMoney;

    // 还款方式
    private Integer repaymentType;

    //账期开始日期
    private Date startDate;

    // 账期结束日期
    private Date endDate;

}
