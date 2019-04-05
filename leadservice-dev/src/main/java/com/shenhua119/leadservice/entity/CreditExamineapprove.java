package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-12-09 1:43
 * @update 2019-04-03 23:43
 * 贷款审批信息
 */
@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("project_credit_examineapprove")
public class CreditExamineapprove {

    /** 主键ID */
    private Long id;

    /** 授信申请ID */
    private Long applyId;

    /** 授信申请编号 */
    private String applyNo;

    /** 授信类型 */
    private Integer creditType;

    /** 审批结果 */
    private Integer result;

    /** 授信金额 */
    private Double amount;

    /** 授信期限 */
    private String creditDate;

    /** 还款方式 */
    private String repayType;

    /** 还款意见 */
    private String repayOpinion;

    /** 审批意见 */
    private String opinion;

    /** 内控要点 */
    private String incontrol;

    /** 贷后管理 */
    private String afterLoandesc;

    /** 授信开始日期 */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime;

    /** 授信结束日期 */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime;

    /** 批复文件 */
    private String replyFile;

    /** 审批时间 */
    private Date createTime;

}
