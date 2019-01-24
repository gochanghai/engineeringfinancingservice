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
 * 贷款审批信息
 */
@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_credit_approval")
public class CreditApprovalEntity {
    // 主键ID
    private Long id;
    //授信ID
    private Long creditId;
    // 授信类型
    private Integer creditType;

    // 工程公司审批结果
    private Integer ecResult;
    // 授信金额
    private Double ecAmount;
    // 授信期限
    private String ecCreditDate;
    // 还款方式
    private String ecRepayType;
    // 内控要点
    private String ecInControlDesc;
    // 贷后管理
    private String ecLoanAfterManageDesc;
    // 工程公司拒绝原因说明/退回原因
    private String ecDesc;
    // 审批人
    private String ecPerson;
    // 审批时间
    private Date ecDate;


    // 平台审批结果
    private Integer pResult;
    // 拒绝原因
    private String pDesc;
    // 审批人
    private String pPerson;
    // 审批时间
    private Date pDate;


    // 资金方批复结果
    private Integer fResult;
    // 资金方批复金额
    private Double fAmount;
    // 资金方批复批复还款方式
    private String fRepayType;
    // 资金方授信开始日期
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fCreditStartDate;
    // 资金方授信结束日期
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fCreditEndDate;
    // 资金方批复文件
    private String fReplyFile;
    // 资金方特批说明
    private String fSpecialDesc;
    // 资金方拒绝原因
    private String fDesc;
    // 批复人
    private String fPerson;
    // 批复时间
    private Date fDate;

    // 创建时间
    private Date createDate;
    // 更新时间
    private Date updateDate;

}
