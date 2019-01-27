package com.shenhua119.leadservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenhua119.leadservice.entity.EngineeringCompanyEntity;
import com.shenhua119.leadservice.entity.EngineeringCompanyView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:00
 */
@Mapper
public interface EngineeringCompanyDao extends BaseMapper<EngineeringCompanyEntity> {

    @Select("SELECT tb1.id, tb1.company_name,tb1.person,tb1.credit_code_number," +
            "tb1.credit_amount,tb2.company_full_name FROM `tb_engineering_company` tb1 LEFT JOIN " +
            "tb_finance_company tb2 ON tb1.f_com_id = tb2.id")
    List<EngineeringCompanyView> selectAll();

    @Select("SELECT tb1.*,tb2.company_full_name FROM `tb_engineering_company` tb1 LEFT JOIN " +
            "tb_finance_company tb2 ON tb1.f_com_id = tb2.id WHERE tb1.id = #{id}")
    EngineeringCompanyEntity getById(Long id);
}
