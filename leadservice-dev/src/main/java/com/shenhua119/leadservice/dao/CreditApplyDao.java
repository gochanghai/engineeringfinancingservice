package com.shenhua119.leadservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenhua119.leadservice.entity.ProjectCreditApply;
import com.shenhua119.leadservice.entity.CreditListView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-12-16 16:15
 */
@Mapper
public interface CreditApplyDao extends BaseMapper<ProjectCreditApply> {

    /** 返回融资人授信申请数据 */
    @Select("SELECT  tb1.id,tb1.apply_no,tb1.apply_date,tb1.name, tb1.step,tb1.apply_amount,tb1.`status`,tb2.project_name," +
            "tb2.project_progress FROM `project_credit_apply` tb1 " +
            "LEFT JOIN project tb2 ON tb1.project_id = tb2.id " +
            "WHERE tb1.user_id =#{id}")
    List<CreditListView> selectByFinancierId(Long id);

    /** 返回工程公司授信申请数据 */
    @Select("SELECT tb1.id, tb1.apply_no,tb1.project_id, tb1.apply_date,tb1.step,tb1.apply_amount,tb1.`status`,tb2.project_name," +
            " tb1.project_no,tb2.project_progress,tb1.`name`, tb3.company_name FROM `project_credit_apply` tb1 " +
            "LEFT JOIN project tb2 ON tb1.project_id = tb2.id " +
            "LEFT JOIN company_engineering tb3 ON tb1.company_id = tb3.id " +
            "LEFT JOIN company_finance tb4 ON tb1.fcompany_id = tb4.id " +
            "WHERE tb1.company_id = #{companyId} " +
            "order by tb1.update_time asc")
    List<CreditListView> selectListByCompanyId(Long companyId);

    /** 返回平台授信申请数据 */
    @Select("SELECT tb1.id,tb1.name, tb1.apply_no,tb1.project_id, tb1.apply_amount,tb1.apply_date,tb1.step,tb3.project_name,tb1.`status`," +
            "tb3.project_progress,tb4.company_name FROM `project_credit_apply` tb1 " +
            "LEFT JOIN project tb3 ON tb1.project_id = tb3.id " +
            "LEFT JOIN company_engineering tb4 ON tb1.company_id = tb4.id ")
    List<CreditListView> selectListAll();

    /** 返回资金方授信批复数据 */
    @Select("SELECT tb1.id,tb1.name, tb1.apply_no, tb1.project_id, tb1.apply_amount,tb1.apply_date,tb1.step,tb3.project_name,tb1.`status`," +
            "tb3.project_progress,tb5.company_name, tb4.phone FROM `project_credit_apply` tb1 " +
            "LEFT JOIN project tb3 ON tb1.project_id = tb3.id " +
            "LEFT JOIN businessmanager tb4 ON tb1.user_id = tb4.user_id " +
            "LEFT JOIN company_engineering tb5 ON tb1.company_id = tb5.id " +
            "WHERE tb1.fcompany_id = #{fcompanyId}")
    List<CreditListView> selectListByFundCompanyId(Long fcompanyId);

}
