package com.shenhua119.leadservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenhua119.leadservice.entity.BusinessManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-11-29 22:28
 */
@Mapper
public interface BusinessManagerDao extends BaseMapper<BusinessManager> {

    @Select("SELECT tb1.id,tb1.`name`, tb1.gender, tb1.phone, tb1.birthdate,tb1.`status`" +
            ",tb2.company_name FROM `businessmanager` tb1 LEFT JOIN company_engineering tb2 ON tb1.company_id = tb2.id")
    List<BusinessManager> selectAll();

    @Select("SELECT tb1.*, tb2.company_name AS child_company_name FROM `businessmanager` tb1 " +
            "LEFT JOIN company_branch tb2 ON tb1.branch_id = tb2.id WHERE tb1.company_id = #{id}")
    List<BusinessManager> selectListByComId(Long companyId);
}
