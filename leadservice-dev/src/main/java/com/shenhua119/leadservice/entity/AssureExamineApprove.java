package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-12-09 0:48
 * 担保评估信息
 */
@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_assure_examine_approve")
public class AssureExamineApprove {
    // 主键ID
    private Integer id;
    // 贷款ID
    private Integer loanId;
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
    //内控说明
    private String inControlDese;
    // 贷后管理说明
    private String loanAfterDesc;
    // 拒绝原因
    private String rejectDesc;
    // 评估人
    private String person;
    // 评估时间
    private Date date;

}
