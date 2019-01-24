package com.shenhua119.leadservice.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author liuchanghai
 * @create 2018-12-20 16:59
 */

@Data
@Getter
@Setter
@Accessors(chain = true)
public class EngineeringCompanyView {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    // 工程公司名称
    private String companyName;

    // 企业法人
    private String person;

    // 法人身份证号
    private String personIdCard;
    // 社会统一信用代码证编号
    private String creditCodeNumber;

    // 对公账户名称
    private String publicAccountName;

    // 开户行
    private String openAccountBank;

    // 银行账号
    private String bankAccount;

    private String companyFullName;

    // 授信额度
    private Double creditAmount;

    // 状态
    private Integer status;
}
