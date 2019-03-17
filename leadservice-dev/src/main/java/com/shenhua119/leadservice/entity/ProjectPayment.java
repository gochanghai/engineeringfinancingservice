package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liuchanghai
 * @create 2018-11-30 0:09
 */

@Data
@Getter
@Setter
@TableName("project_payment")
public class ProjectPayment {
    /**
     * Id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 项目Id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long projectId;
    /**
     * 前期累计开票额
     */
    private Double totalBillAmount;
    /**
     * 前期累计回款额
     */
    private Double totalPaybackAmount;

    /**
     * 前期累计付款额
     */
    private Double totalPaymentAmount;

    /**
     * 发票凭证
     */
    private String invoice;

    /**
     * 转账凭证
     */
    private String transfer;

    /**
     * 对应发票凭证
     */
    private String transferInvoice;

    /**
     * 采购合同
     */
    private String purchaseContract;

    /**
     * 采购发票
     */
    private String purchaseInvoice;

    /**
     * 送货单
     */
    private String deliveryNote;

    /**
     * 银行转账凭证
     */
    private String banckTransfer;

}
