package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 项目授信信息
 * @author liuchanghai
 * @create 2018-12-01 2:09
 */
@Data
@Getter
@Setter
@TableName("project_credit")
public class CreditProjectProduct {

    /**
     * 授信ID
     */
    private Long id;

    /**
     * 商务经理ID
     */
    private Long userId;
    /**
     * 项目ID
     */
    private Long projectId;
    /**
     * 授信申请编号
     */
    private String applyNo;

    /**
     * 授信金额
     */
    private Double amount;
    /**
     * 资金方ID
     */
    private Long fcompanyId;
    /**
     * 金融产品ID
     */
    private Long productId;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime;

}
