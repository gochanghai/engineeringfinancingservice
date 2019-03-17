package com.shenhua119.leadservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenhua119.leadservice.entity.Project;
import com.shenhua119.leadservice.entity.ProjectList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-05 19:04
 */
@Mapper
public interface ProjectDao extends BaseMapper<Project> {

    @Select("SELECT tb1.id, tb1.project_name, tb1.contract_no, tb1.contract_amount,tb1.credit_amount" +
            ", tb1.project_progress, tb2.name,tb3.company_name FROM `project` tb1 " +
            "LEFT JOIN businessmanager tb2 ON tb1.user_id = tb2.id LEFT JOIN company_branch tb3 " +
            "ON tb1.branch_id = tb3.id WHERE tb1.company_id = #{companyId}")
    List<ProjectList> selectByCompanyId(Long companyId);

    List<Project> selectByFId(Long fId);
}
