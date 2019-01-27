package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_user")
public class User implements Serializable {

    // 用户主键ID
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    // 用户名
    private String userName;

    // 手机号码
    private String phone;

    // 密码
    private String password;

    // 角色ID
    @JsonSerialize(using=ToStringSerializer.class)
    private Long roleId;

    // 用户信息ID
    @JsonSerialize(using=ToStringSerializer.class)
    private Long userInfoId;

    // 用户类别
    private Integer userType;

    // 用户状态
    private Integer status;
}
