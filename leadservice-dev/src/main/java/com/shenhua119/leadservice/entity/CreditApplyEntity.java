package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-12-16 15:06
 */

@Data
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_credit_apply")
public class CreditApplyEntity implements Serializable {

    /**
     * 主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     *申请编号
     */
    private String creditNo;

    /**
     * 申请时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date date;

    /**
     * 融资人ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long fId;
    /**
     * 项目ID
      */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long pId;
    /**
     * 项目合同编号
     */
    private String contractNo;
    /**
     * 申请金额
     */
    private Double applyAmount;
    /**
     * 申请事由
     */
    private String originIncident;
    /**
     * 银行流水文件
     */
    private String bankListFile;
    /**
     * 审批进度
     */
    private Integer step;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 授信类型 1:正常批复 2: 特批
     */
    private Integer creditType;
    /**
     * 工程公司ID
     */
    private Long comId;
    /**
     * 资金方ID
     */
    private Long fComId;

    /**更新时间*/
    private Date updateDate;

}
