package com.shenhua119.leadservice.vo;

import lombok.Data;

@Data
public class CompanyInfoVO {
    // 公司名称
    private String companyName;

    // 公司地址
    private String address;

    // 企业法人
    private String person;

    // 法人身份证号
    private String idCard;

    // 法人身份证正面
    private String idCardSideFace;

    // 法人身份证反面
    private String idCardSideBack;

    // 信用代码
    private String creditCode;

    // 信用代码证
    private String creditCodeCert;

    // 对公账户名称
    private String publicAccountName;

    // 开户行
    private String openAccountBank;

    // 银行账号
    private String bankAccount;

    // 授权书文件
    private String authFile;

    private String financeName;

    private Double creditAmount;
}
