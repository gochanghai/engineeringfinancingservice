package com.shenhua119.leadservice.vo;

import lombok.Data;

/**
 * 返回前端的工程公司数据
 */

@Data
public class CompanyListVO {
    private Long id;
    private String companyName;
    private String financeName;
    private Double creditAmount;
    private String person;
    private String creditCode;
}
