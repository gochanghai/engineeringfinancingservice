package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 公司金融产品授信实体
 * @author liuchanghai
 * @create 2019-01-15 16:58
 */

@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_eng_company_credit")
public class CompanyProductCredit {
    /**
     * 主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 工程公司ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;
    /**
     * 产品ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long productId;
    /**
     * 资金方ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long companyId;
    /**
     * 授信额度
     */
    private Double amount;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 授信时间
     */
    private Date createDate;
}
