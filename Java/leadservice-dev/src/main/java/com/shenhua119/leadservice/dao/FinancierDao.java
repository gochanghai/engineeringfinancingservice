package com.shenhua119.leadservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenhua119.leadservice.entity.FinancierEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-11-29 22:28
 */
@Mapper
public interface FinancierDao extends BaseMapper<FinancierEntity> {

    @Select("SELECT tb1.id,tb1.`name`, tb1.gender, tb1.phone, tb1.birthdate,tb1.marriage_status,tb1.`status`" +
            ",tb2.company_name FROM `tb_financier` tb1 LEFT JOIN tb_engineering_company tb2 ON tb1.com_id = tb2.id")
    List<FinancierEntity> selectAll();

    @Select("SELECT tb1.*, tb2.company_name AS child_company_name FROM `tb_financier` tb1 " +
            "LEFT JOIN tb_child_company tb2 ON tb1.child_com_id = tb2.id WHERE tb1.com_id = #{id}")
    List<FinancierEntity> selectListByComId(Long companyId);
}
