package com.shenhua119.leadservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author liuchanghai
 * @create 2018-12-10 19:14
 * 用户角色关系实体
 */
@Data
@Getter
@Setter
@TableName("tb_user_role")
public class UserRole {
    private Long id;
    private Long userId;
    private Long roleId;
}
