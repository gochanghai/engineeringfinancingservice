package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:49
 *
 * 金融产品
 */
@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("finance_product")
public class FinanceProduct implements Serializable {

    // 产品主键ID
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    // 产品编号
    private String productNo;
    // 产品名称
    private String productName;
    // 产品大类
    private String productType;

    private String productImg;
    // 基本年利率
    private Double yearRate;
    // 资金渠道ID
    @JsonSerialize(using= ToStringSerializer.class)
    private Long fcompanyId;
    // 期限类型
    private String deadlineType;
    // 期限最小
    private Integer deadlineMin;
    // 期限最大
    private Integer deadlineMax;
    // 逾期罚息利率
    private Double overdueRate;
    // 是否可逾期(1：是，0：否)
    private Integer isDelay;
    // 服务费类型
    private String serviceFeeType;
    // 还款方式
    private Integer repaymentType;
    // 服务费百分比
    private Double serviceFeeRate;
    // 是否担保
    private Integer isAssure;
    // 担保公司ID
    @JsonSerialize(using= ToStringSerializer.class)
    private Long assureCompanyId;
    // 抵押类型
    private String mortgageType;
    // 担保收费类型
    private String assureFeeType;
    // 担保服务费百分比
    private Double assureFeeRate;
    // 产品状态
    private Integer status;
}
