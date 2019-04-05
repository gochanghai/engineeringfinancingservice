package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-12-09 1:37
 * 短信验证码
 */

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sms_code")
public class SMSCode {

    private Long id;
    private String phoneNumber;
    private String checkCode;
    private Integer validTime;
    private String type;
    private String templateCode;
    private Date createTime;

}
