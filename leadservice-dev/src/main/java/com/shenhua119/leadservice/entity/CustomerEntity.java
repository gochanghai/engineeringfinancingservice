package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2019-01-07 0:18
 * <p>资金方客户信息实体</p>
 */

@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_fundcom_customer")
public class CustomerEntity {

    /**
     * 主键ID
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 性别
     */
    private String gender;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 婚姻状况
     */
    private String marriageStatus;
    /**
     * 授信金额
     */
    private Double creditAmount;
    /**
     * 已放款总额
     */
    private Double loanAmount;
    /**
     * 在贷总额
     */
    private Double inLoanAmount;
    /**
     * 违约次数
     */
    private Integer defaultNumber;
    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Integer status;
    /**
     * 资金方ID
     */
    private Long fcId;

}
