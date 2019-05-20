package com.shenhua119.leadservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenhua119.leadservice.entity.FinanceCompany;
import com.shenhua119.leadservice.vo.FinanceCustomerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-07 1:44
 */

@Mapper
public interface FinanceCompanyDao extends BaseMapper<FinanceCompany> {

    @Select("SELECT t.id, t.company_name, t.coop_bank FROM `company_finance` t")
    List<FinanceCompany> selectListCompanyNameAndComId();

    @Select("SELECT t1.id, t1.name, t1.phone, t1.gender, t1.birthdate,t2.amount1, t2.amount2, t2.amount3, t2.dnum, t3.marriage_status  FROM `company_finance_customer` t2 " +
            "LEFT JOIN businessmanager t1 ON t2.customer_id = t1.user_id " +
            "LEFT JOIN businessmanager_authen t3 ON t2.customer_id = t3.user_id " +
            "WHERE t2.user_id = #{id}")
    List<FinanceCustomerVO> selectCustomerList(Long id);
}
