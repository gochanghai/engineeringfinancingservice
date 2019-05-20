package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 工程公司授信信息
 */
@Data
@Accessors(chain = true)
@TableName("company_credit_product")
public class CreditCompanyProduct {
    /** 主键ID */
    private Long id;
    /** 产品ID */
    private Long productId;
    /** 工程公司ID */
    private Long companyId;
    /** 资金方ID */
    private Long fcompanyId;
    /** 授信额度 */
    private Double amount;
    /** 状态 */
    private Integer status;
}
