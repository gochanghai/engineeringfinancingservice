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

    @Select("SELECT tb1.id, tb1.project_name, tb1.contract_no, tb1.contract_amount,tb1.should_credit_amount" +
            ", tb1.project_progress, tb2.name,tb3.company_name FROM `tb_project` tb1 " +
            "LEFT JOIN tb_financier tb2 ON tb1.f_id = tb2.id LEFT JOIN tb_child_company tb3 " +
            "ON tb1.child_com_id = tb3.id WHERE tb1.com_id = #{companyId}")
    List<ProjectList> selectByCompanyId(Long companyId);

    List<Project> selectByFId(Long fId);
}
