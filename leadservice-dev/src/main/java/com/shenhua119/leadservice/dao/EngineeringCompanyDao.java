package com.shenhua119.leadservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenhua119.leadservice.entity.EngineeringCompany;
import com.shenhua119.leadservice.vo.CompanyInfoVO;
import com.shenhua119.leadservice.vo.CompanyListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-06 18:00
 */
@Mapper
public interface EngineeringCompanyDao extends BaseMapper<EngineeringCompany> {

    @Select("SELECT tb1.id, tb1.company_name,tb1.person,tb1.credit_code," +
            "tb2.amount as credit_amount,tb3.company_name as finance_name FROM `company_engineering` tb1 " +
            "LEFT JOIN credit_company_product tb2 ON tb1.id = tb2.company_id " +
            "LEFT JOIN company_finance tb3 ON tb2.fcompany_id = tb3.id")
    List<CompanyListVO> selectAll();

    @Select("SELECT tb1.*,tb2.company_full_name FROM `engineering_company` tb1 LEFT JOIN " +
            "tb_finance_company tb2 ON tb1.f_com_id = tb2.id WHERE tb1.id = #{id}")
    EngineeringCompany getById(Long id);

    @Select("SELECT tb1.*, tb2.amount as credit_amount,tb3.company_name as finance_name FROM `company_engineering` tb1 " +
            "LEFT JOIN credit_company_product tb2 ON tb1.id = tb2.company_id " +
            "LEFT JOIN company_finance tb3 ON tb2.fcompany_id = tb3.id WHERE tb1.id = #{id}")
    CompanyInfoVO selectInfoById(Long id);
}
