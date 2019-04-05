package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("project_loan_examineapprove")
public class LoanExamineapprove {

    /** 主键ID */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /** 放款申请ID */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long applyId;

    /** 放款申请编号 */
    private String applyNo;

    /** 审批结果 */
    private Integer result;

    /** 审批意见 */
    private String opinion;

    /** 有效采购金额 */
    private Double purchaseAmount;

    /** 现场文件 */
    private String onsiteFile;

    /** 资信评估文件 */
    private String creditrateFile;

    /** 批复人 */
    private String name;

    /** 审批时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
