package com.shenhua119.leadservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author liuchanghai
 * @create 2018-12-20 13:28
 */

@Data
@Getter
@Setter
@Accessors(chain = true)
public class CreditListView {

    // 主键ID
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    // 授信编号
    private String applyNo;

    // 授信时间
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date applyDate;

    // 申请授信额度
    private Double applyAmount;

    // 融资人姓名
    private String name;

    // 手机号
    private String phone;

    // 项目进度
    private Double projectProgress;

    // 项目名称
    private String projectName;

    // 项目编号
    private String contractNo;

    // 工程公司名称
    private String companyName;

    // 资金渠道
    private String fcompanyName;

    // 状态
    private Integer status;

    // 授信进度
    private Integer step;

    // 授信类型
    private Integer creditType;
}
