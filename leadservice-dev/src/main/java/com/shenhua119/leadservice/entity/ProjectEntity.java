package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @create 2018-11-29 23:44
 */

@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_project")
public class ProjectEntity implements Serializable {
    /**
     * 项目Id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     *工程公司ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long comId;
    /**
     *项目名称
     */
    private String projectName;
    /**
     *合同编号
     */
    private String  contractNo;
    /**
     *合同金额
     */
    private Double contractAmount;
    /**
     *所属分公司Id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long childComId;
    /**
     *商务经理Id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long fId;
    /**
     *预授信金额
     */
    private Double shouldCreditAmount;
    /**
     *项目进度
     */
    private Double projectProgress;
    /**
     *是否授信
     */
    private Integer isCredit;

    /**
     * 分公司
     */
    @TableField(exist=false)
    private ChildCompanyEntity childCompany;

}
