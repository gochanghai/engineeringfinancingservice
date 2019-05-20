package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("service_fee")
public class ServiceFee {
    private Long id;
    private Long loanId;
    private Long loanNo;
    private Long productId;
    private Double amount1;
    private Double amount2;
    private String file1;
    private String file2;
    private Integer status;
}
