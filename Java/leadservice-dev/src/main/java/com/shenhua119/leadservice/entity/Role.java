package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liuchanghai
 * @create 2018-12-10 19:17
 * 角色实体类
 */
@Data
@Getter
@Setter
@TableName("tb_role")
public class Role {
    private Long roleId;
    private String roleName;
    private String roleDesc;
    private Integer status;
}
