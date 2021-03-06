package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

/**
 * @author liuchanghai
 * @create 2018-12-01 2:18
 */

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("project_purchase_order")
public class ProjectPurchaseOrder {

    /**
     * 主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 项目ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long projectId;
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
    private String orderAmount;
    /**
     * 采购订单附件
     */
    private String orderFile;
    /**
     * 采购发票总金额
     */
    private String invoiceAmount;
    /**
     * 采购发票附件
     */
    private String invoiceFile;
    /**
     * 送货单附件
     */
    private String deliveryFile;
    /**
     * 供应商银行账号
     */
    private String bankAccount;
    /**
     * 供应商银行账户名
     */
    private String accountName;
    /**
     * 开户行
     */
    private String bank;

}
