package com.shenhua119.leadservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenhua119.leadservice.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {

    @Select("SELECT tb1.* FROM `sys_user` tb1 " +
            "LEFT JOIN company_user tb2 ON tb1.id = tb2.user_id " +
            "WHERE tb2.company_id = #{id}")
    List<User> selectCompanyUserS(Long id);
}
