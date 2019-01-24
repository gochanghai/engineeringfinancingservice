package com.shenhua119.leadservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenhua119.leadservice.entity.FinanceProductEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author liuchanghai
 * @create 2018-11-29 22:28
 */
@Mapper
public interface FinanceProductDao extends BaseMapper<FinanceProductEntity> {

    @Select("SELECT tb1.id, tb1.product_no,tb1.product_name,tb1.base_year_rate, " +
            "tb1.`status`,tb2.company_full_name,tb2.cooperation_bank FROM `tb_finance_product` tb1 LEFT JOIN " +
            "tb_finance_company tb2 ON tb1.f_com_id = tb2.id")
    List<Map> selectAll();
}
