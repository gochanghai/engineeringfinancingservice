package com.shenhua119.leadservice.entity;

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-12-09 1:37
 * 短信验证码
 */
public class SMSCode {

    // 主键ID
    private Long id;
    // 手机号码
    private String phone;
    // 验证码
    private String code;
    // 创建时间
    private Date createDate;

}
