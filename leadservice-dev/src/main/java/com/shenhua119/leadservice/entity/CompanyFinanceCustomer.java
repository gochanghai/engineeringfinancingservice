package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 资金方客户实体
 * @author liuchanghai
 */
@Data
@Accessors(chain = true)
@TableName("company_finance_customer")
public class CompanyFinanceCustomer {
    private Long id;
    // 资金方ID
    private Long userId;
    // 客户ID
    private Long customerId;
    // 产品ID
    private Long productId;
    // 授信总额
    private Double amount1;
    // 已放款总额
    private Double amount2;
    // 在贷总额
    private Double amount3;
    // 违约次数
    private Integer dnum;
}
