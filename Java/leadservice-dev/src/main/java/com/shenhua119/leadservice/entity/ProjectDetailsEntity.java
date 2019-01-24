package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 项目详情实体
 * @author liuchanghai
 * @create 2019-01-14 13:06
 */
@Data
@Getter
@Setter
@TableName("tb_project_details")
public class ProjectDetailsEntity {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    // 甲方名称
    private String partyAName;
    // 项目地址
    private String projectAdress;
    // 合同金额
    private Double contractAmount;
    // 预结算金额
    private Double shouldSettlemenMoney;
    // 保函要求
    private Integer guaranteeClaim;
    // 施工开始日期
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date constructionStartDate;
    // 施工结束日期
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date constructionEndDate;
    // 结算方式
    private Integer cettlementMethod;
    // 预付款
    private Double shouldPayment;
    // 预付款支付日期
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date shouldPaymentDate;
    // 进度付款方式
    private String progressPaymentType;
    // 月结算日
    private Integer monthSettlementDay;
    // 进度付款比例
    private Double progressPaymentRatio;
    // 验收付款比例
    private Double checkPaymentRatio;
    // 验收款说明
    private String checkPaymentDesc;
    // 结算付款比例
    private Double ettlementPaymentRatio;
    // 结算款备注
    private String ettlementDesc;
    // 质保金付款比例
    private Double warrantyMoneyPaymentRatio;
    // 保质期
    private Integer warranty;
    // 保质期支付方式
    private Double warrantyPayType;
    // 项目合同文件
    private String contractFile;
    // 合同付款方式页附件凭证
    private String  contractPaymentFile;
    // 内部经营承包责任书附件
    private String inContractLiabilityBook;
    // 施工现场照片
    private String constructionSitePhoto;
    // 是否购买保险
    private Integer isBuyInsur;
}
