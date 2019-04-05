package com.shenhua119.leadservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenhua119.leadservice.entity.FinanceCompany;
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
}
