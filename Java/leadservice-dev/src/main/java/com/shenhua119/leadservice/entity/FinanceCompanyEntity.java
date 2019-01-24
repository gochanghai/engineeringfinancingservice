package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:38
 */
@Data
@Getter
@Setter
@TableName("tb_finance_company")
public class FinanceCompanyEntity {

    // 主键ID
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    // 企业全称
    private String companyFullName;

     // 联系人
    private String contactPerson;

    // 联系电话
    private String contactNumber;

    // 合作分行
    private String cooperationBank;

    // 分行地址
    private String cooperationBankAddress;

    // 用户名
    private String userName;

    // 手机号码
    private String phone;

    // 状态
    private Integer status;

}
