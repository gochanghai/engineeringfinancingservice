package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
    private String cardNo;
    /**
     *银行卡类型  DC: 借记卡 CC: 信用卡
     */
    private String cardType;
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
    private String mobile;
    /**
     * 真是姓名
     */
    private String realName;
    /**
     * 证件号码
     */
    private String number;
    /**
     * 证件类型
     */
    private String type;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;
}
