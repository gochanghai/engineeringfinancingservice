package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liuchanghai
 * @create 2018-11-30 0:11
 */
@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_child_company")
public class ChildCompanyEntity implements Serializable {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long pId;

    // 分公司名称
    private String companyName;

    // 分公司地址
    private String companyAddress;

    // 联系人
    private String contactPerson;

    // 联系电话
    private String phone;
}
