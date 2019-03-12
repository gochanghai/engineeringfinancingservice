package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author liuchanghai
 * @create 2018-12-01 1:23
 * 融资人个人信息
 */

@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("businessmanager_authen")
public class BusinessManagerAuthen {

    // 主键Id
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    // 用户Id
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    // 银行卡号
    private String bankCardNo;

    // 开户行
    private String bank;

    //银行卡手机号
    private String bankCardPhone;

    // 户籍所在地
    private String domicile;

    // 身份证正面
    private String idCardSideFace;

    // 身份证反面
    private String idCardSideBack;

    // 手持身份证
    private String idCardHand;

    // 户口本主页
    private String householdRegHome;

    // 户口本个人页
    private String householdRegPersonal;

    // 婚姻状况
    private Integer marriageStatus;

    // 配偶姓名
    private String spouseName;

    // 配偶身份证号
    private String spouseIdCard;

    // 配偶户籍所在地
    private String spouseDomicile;

    // 配偶身份正面
    private String spouseIdCardSideFace;

    // 配偶身份证反面
    private String spouseIdCardSideBack;

    // 结婚证
    private String marriageCert;

    // 配偶户口本主页
    private String sHouseholdRegHome;

    // 配偶户口本个人页
    private String sHouseholdRegPersonal;

    // 离婚证
    private String divorceCert;

    //公司名称
    private String companyName;

    // 信用代码
    private String creditCode;

    // 公司营业执照
    private String companyBusinessLicense;

    // 公司章程
    private String articlesAssoCompany;

    // 办公场所租赁合同
    private String officeSpaceLeaseContract;

    // 社保卡号
    private String socialInsurCardNo;

    // 现在购买社保公司
    private String buySocialInsurCompany;

    // 最近缴纳社保清单
    private String paySocialInsurDetails;

    // 是否有社保
    private Integer isSocialecurity;

    // 是否有房子
    private Integer isHouse;

    // 是否有车
    private Integer isCar;

}
