package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.io.Serializable;

/**
 * @author liuchanghai
 * @create 2018-12-01 1:35
 * 融资人房产信息
 */

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("businessmanager_house")
public class House implements Serializable {
    /**
     * 房子Id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 用户Id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;
    /**
     * 房产所在地
     */
    private String address;
    /**
     * 房产证
     */
    private String premisesPermit;
    /**
     * 房产说明
     */
    private String remarks;

}
