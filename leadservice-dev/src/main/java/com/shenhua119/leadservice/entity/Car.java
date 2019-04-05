package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.io.Serializable;

/**
 * @author liuchanghai
 * @create 2018-12-01 1:40
 * 融资人车辆信息
 */

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("businessmanager_car")
public class Car implements Serializable {
    /**
     * 主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private  Long id;
    /**
     * 融资人Id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private  Long userId;
    /**
     * 车牌号
     */
    private String number;
    /**
     * 车辆行驶证
     */
    private String driveLicense;
    /**
     * 车辆说明
     */
    private String remarks;

}
