package com.shenhua119.leadservice.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author liuchanghai
 * @create 2018-12-20 8:59
 */
@Data
@Getter
@Setter
@Accessors(chain = true)
public class ProjectList implements Serializable {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String projectName;
    private String contractNo;
    private Double contractAmount;
    private Double shouldCreditAmount;
    private Double projectProgress;
    private String name;
    private String companyName;
}
