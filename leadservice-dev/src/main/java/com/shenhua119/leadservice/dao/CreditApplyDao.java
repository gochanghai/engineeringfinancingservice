package com.shenhua119.leadservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenhua119.leadservice.entity.CreditApplyEntity;
import com.shenhua119.leadservice.entity.CreditListView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-16 16:15
 */
@Mapper
public interface CreditApplyDao extends BaseMapper<CreditApplyEntity> {

    /**返回融资人授信申请数据*/
    @Select("SELECT  tb1.id,tb1.credit_no,tb1.date,tb1.step,tb1.apply_amount,tb1.`status`,tb2.project_name," +
            "tb2.project_progress, tb3.`name` FROM `tb_credit_apply` tb1 LEFT JOIN tb_project tb2" +
            " ON tb1.p_id = tb2.id LEFT JOIN tb_financier tb3 ON tb1.f_id = tb3.id WHERE tb1.f_id =#{id}")
    List<CreditListView> selectByFinancierId(Long id);

    /** 返回工程公司授信申请数据*/
    @Select("SELECT tb1.id, tb1.credit_no, tb1.date,tb1.step,tb1.apply_amount,tb1.`status`,tb2.project_name," +
            " tb2.contract_no,tb2.project_progress,tb5.company_full_name,tb3.`name` FROM `tb_credit_apply`" +
            " tb1 LEFT JOIN tb_project tb2 ON tb1.p_id = tb2.id LEFT JOIN tb_financier tb3 ON " +
            "tb1.f_id = tb3.id LEFT JOIN tb_engineering_company tb4 ON tb3.com_id = tb4.id LEFT JOIN " +
            "tb_finance_company tb5 ON tb4.f_com_id = tb5.id WHERE tb1.com_id = #{id}")
    List<CreditListView> selectListByCompanyId(Long id);

    /** 返回平台授信申请数据*/
    @Select("SELECT tb1.id, tb1.credit_no,tb1.apply_amount,tb1.date,tb1.step,tb2.`name`,tb3.project_name,tb1.`status`," +
            "tb3.project_progress,tb4.company_name,tb5.company_full_name FROM `tb_credit_apply` tb1 LEFT JOIN " +
            "tb_financier tb2 ON tb1.f_id = tb2.id LEFT JOIN tb_project tb3 ON " +
            "tb1.p_id = tb3.id LEFT JOIN tb_engineering_company tb4 ON tb3.com_id = tb4.id LEFT JOIN " +
            "tb_finance_company tb5 ON tb4.f_com_id = tb5.id")
    List<CreditListView> selectListAll();

    /** 返回资金方授信批复数据*/
    @Select("SELECT tb1.id, tb1.apply_amount,tb1.step, tb1.`status`,tb1.credit_type,tb2.project_name, tb3.company_name,tb4.`name`," +
            "tb4.phone FROM `tb_credit_apply` tb1 LEFT JOIN tb_project tb2 ON tb1.p_id " +
            "= tb2.id LEFT JOIN tb_engineering_company tb3 ON tb2.com_id = tb3.id LEFT JOIN" +
            " tb_financier tb4 ON tb1.f_id = tb4.id WHERE tb1.f_com_id = #{id}")
    List<CreditListView> selectListByFundCompanyId(Long id);

}
