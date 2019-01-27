package com.shenhua119.leadservice.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-12-09 0:48
 * 平台审批信息
 */
public class ExamineApprove implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键ID
    private Integer id;
    // 贷款ID
    private Integer loanId;
    // 评估结果（-1：拒绝，0：退回，1：通过）
    private String isCredit;
    // 说明
    private String desc;
    // 评估人
    private String person;
    // 评估时间
    private Date date;

}
