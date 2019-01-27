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
@TableName("tb_project_credit")
public class ProjectCreditEntity {

    /**
     * 授信ID
     */
    private Long id;
    /**
     * 项目ID
     */
    private Long projectId;
    /**
     * 授信编号
     */
    private String creditNo;
    /**
     * 授信时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date creditDate;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 授信金额
     */
    private Double creditAmount;
    /**
     * 资金方ID
     */
    private Long fId;
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
    private Date createDate;

}
