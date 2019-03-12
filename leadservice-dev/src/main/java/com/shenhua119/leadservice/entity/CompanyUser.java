package com.shenhua119.leadservice.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 * 公司用户关系实体
 * @date 2019-03-12
 * @author liuchanghai
 */
@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("company_user")
public class CompanyUser {

    /**
     * 主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 公司ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long companyId;

    /**
     * 用户ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;


}
