package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2019-01-06 15:45
 *
 * <p>消息通知/系统公告</p>
 */

@Getter
@Setter
@Accessors(chain = true)
@TableName("message_system")
public class MessageEntity {
    /**
     * 主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 消息类型  0:系统公告 1: 消息通知
     */
    private Integer type;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息状态 0: 未读 1：已读
     */
    private Integer status;

    /**
     * 用户ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;


}
