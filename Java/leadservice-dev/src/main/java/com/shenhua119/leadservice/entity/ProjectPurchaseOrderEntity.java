package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liuchanghai
 * @create 2018-12-01 2:18
 */

@Data
@Getter
@Setter
@TableName("tb_project_purchase_order")
public class ProjectPurchaseOrderEntity {

    /**
     * 主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 项目ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long pId;
    /**
     * 放款申请编号
     */
    private String loanNo;
    /**
     * 采购合同编号
     */
    private String contractNo;

    /**
     * 采购合同附件
     */
    private String contractFile;
    /**
     * 采购订单总金额
     */
    private String orderSumAmount;
    /**
     * 采购订单附件
     */
    private String orderFile;
    /**
     * 采购发票总金额
     */
    private String invoiceSumAmount;
    /**
     * 采购发票附件
     */
    private String invoiceFile;
    /**
     * 送货单附件
     */
    private String deliveryBillFile;
    /**
     * 供应商银行账号
     */
    private String bankCardNo;
    /**
     * 供应商银行账户名
     */
    private String bankAccountName;
    /**
     * 开户行
     */
    private String openAccountBank;

}
