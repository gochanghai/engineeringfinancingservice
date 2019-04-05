package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <pre> 银行卡信息实体类 </pre>
 * @author liuchanghai
 * @create 2019-01-08 11:20
 *
 */

@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("bank_card")
public class BankCard {

    /**
     * 主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 用户ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;
    /**
     * 银行卡号
     */
    private String accountNo;
    /**
     *银行卡类型  借记卡  信用卡
     */
    private String cardType;
    /**
     *银行卡类型代码  DC: 借记卡 CC: 信用卡
     */
    private String cardTypeCode;
    /**
     * 银行
     */
    private String bank;
    /**
     * 银行代码
     */
    private String bankCode;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 真是姓名
     */
    private String realName;
    /**
     * 证件号码
     */
    private String idCard;
    /**
     * 证件类型
     */
    private String type;

    private String cardName;
    private String birthday;
    private String gender;

    private String province;
    private String city;
    private String prefecture;
    private String area;

    private String addrCode;
    private String lastCode;

    /**
     * 状态
     */
    private String status;
}
