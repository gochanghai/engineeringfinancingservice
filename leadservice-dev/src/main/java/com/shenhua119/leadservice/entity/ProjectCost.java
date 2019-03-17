package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liuchanghai
 * @create 2018-11-30 0:04
 */

@Data
@Getter
@Setter
@TableName("project_cost")
public class ProjectCost {
    /**
     * 主键ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 项目ID
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long projectId;
    /**
     * 项目利润率
     */
    private Double profitMargin;
    /**
     * 工程进度回款台帐
     */
    private String progressReturnAccount;
    /**
     * 项目产值表
     */
    private String outputValueTable;
    /**
     * 项目成本分析表
     */
    private String costAnalysisTable;
    /**
     * 项目成本台帐
     */
    private String costAccount;

}
