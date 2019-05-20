package com.shenhua119.leadservice.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FinanceCustomerVO {
    private Long id;
    private String name;
    private String gender;
    private String phone;
    private LocalDate birthdate;
    // 婚姻状况
    private Integer marriageStatus;
    private Double amount1;
    private Double amount2;
    private Double amount3;
    private Integer dnum;
}
