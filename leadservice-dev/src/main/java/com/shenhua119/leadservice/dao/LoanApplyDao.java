package com.shenhua119.leadservice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shenhua119.leadservice.entity.ProjectLoanApply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuchanghai
 * @create 2018-11-29 22:28
 */
@Mapper
public interface LoanApplyDao extends BaseMapper<ProjectLoanApply> {

//    @Select("SELECT tb1.id, tb1.loan_no,tb1.loan_date, tb1.loan_amount, tb1.`status`, tb2.project_name," +
//            "tb3.`name` FROM `tb_loan_apply` tb1 LEFT JOIN tb_project tb2 ON tb1.p_id " +
//            "= tb2.id LEFT JOIN tb_financier tb3 ON tb1.f_id = tb3.id WHERE tb1.f_id = #{id}")
    List<ProjectLoanApply> selectByFinancierId(Long id);

//    @Select("SELECT tb1.id, tb1.p_id, tb1.loan_no,tb1.loan_date, tb1.loan_amount, tb1.`status`, " +
//            "tb2.project_name,tb3.`name` FROM `tb_loan_apply` tb1 LEFT JOIN tb_project tb2 ON " +
//            "tb1.p_id = tb2.id LEFT JOIN tb_financier tb3 ON tb1.f_id = tb3.id WHERE tb3.com_id = #{id}")
    List<ProjectLoanApply> selectByEngCompanyId(Long id);

    @Select("SELECT tb1.id, tb1.p_id, tb1.loan_no,tb1.loan_date, tb1.loan_amount, tb1.`status`, " +
            "tb2.project_name,tb3.`name` FROM `tb_loan_apply` tb1 LEFT JOIN tb_project tb2 ON " +
            "tb1.p_id = tb2.id LEFT JOIN tb_financier tb3 ON tb1.f_id = tb3.id")
    List<ProjectLoanApply> selectAll();
    List<ProjectLoanApply> selectAll2();

//    @Select("SELECT tb1.id, tb1.p_id, tb1.loan_no,tb1.loan_date, tb1.loan_amount, tb1.`status`, " +
//            "tb2.project_name,tb3.`name` FROM `tb_loan_apply` tb1 LEFT JOIN tb_project tb2 ON " +
//            "tb1.p_id = tb2.id LEFT JOIN tb_financier tb3 ON tb1.f_id = tb3.id LEFT JOIN " +
//            "tb_engineering_company tb4 ON tb3.com_id = tb4.id WHERE tb4.f_com_id = #{id}")
    List<ProjectLoanApply> selectByFundCompanyId(Long id);

    /** 获取一条放款信息明细 */
    ProjectLoanApply selectLoanApplyInfoDetailsById(Long id);
}
