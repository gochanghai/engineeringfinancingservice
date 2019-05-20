package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 签署协议实体
 * @author liuchanghai
 */
@Data
@Accessors(chain = true)
@TableName("sign_agreement")
public class SignAgreement {
    private Long id;
    private Long userId;
    private Long creditId;
    private String signName;
    private String agreementName;
    private String agreementContent;
    private Date createTime;
    private Date updateTime;
}
