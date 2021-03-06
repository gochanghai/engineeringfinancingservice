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
 * 工程公司实体
 * @author liuchanghai
 * @create 2018-11-29 23:27
 */

@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("company_engineering")
public class EngineeringCompany implements Serializable {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

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

    // 手机号码1
    private String phone1;

    // 用户名1
    private String username1;

    // 手机号码2
    private String phone2;

    // 用户名2
    private String username2;

    // 状态
    private Integer status;

}
