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
 * <p>分公司实体</p>
 */
@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("company_branch")
public class BranchCompany implements Serializable {

    /**
     * 主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 总公司ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long pId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系电话
     */
    private String phone;
}
